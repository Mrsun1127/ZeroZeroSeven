package com.ffn.zerozeroseven.ui;

import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.BitisImageAdapter;
import com.ffn.zerozeroseven.adapter.DetilsTalkAdapter;
import com.ffn.zerozeroseven.adapter.TalkAdapter;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.BitisDetilsInfo;
import com.ffn.zerozeroseven.bean.BitisInfo;
import com.ffn.zerozeroseven.bean.OkTalkInfo;
import com.ffn.zerozeroseven.bean.RecentliyNewsInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DafenInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RBitisDeitlsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RTalksBitisInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.AllItemDecoration;
import com.ffn.zerozeroseven.view.CommentDialog;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.ffn.zerozeroseven.view.TopView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import de.hdodenhof.circleimageview.CircleImageView;

public class BitisNewDetils extends BaseActivity {
    @Bind(R.id.user_icon)
    CircleImageView user_icon;
    @Bind(R.id.tv_talk)
    TextView tv_talk;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_content)
    TextView tv_content;
    @Bind(R.id.tv_like)
    TextView tv_like;
    @Bind(R.id.iv_like)
    ImageView iv_like;
    @Bind(R.id.rc_photo)
    RecyclerView rc_photo;
    @Bind(R.id.rc_talk)
    RecyclerView rc_talk;
    @Bind(R.id.ll_talk)
    LinearLayout ll_talk;
    @Bind(R.id.ll_like)
    LinearLayout ll_like;
    @Bind(R.id.ll_share)
    LinearLayout ll_share;
    @Bind(R.id.topView)
    TopView topView;
    private CommentDialog commentDialog;
    private DetilsTalkAdapter talkAdapter;
    private BitisDetilsInfo bitisDetilsInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_bitis_detile;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("详情");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        commentDialog = new CommentDialog(this);
        commentDialog.setOnCommitListener(new CommentDialog.OnCommitListener() {
            @Override
            public void onCommit(EditText et, View v) {
                commentDialog.dismiss();
                String content = et.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    return;
                }
                talk(content, talkType);
            }
        });
        commentDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                    commentDialog.cancel();
                return false;
            }
        });
    }

    int talkType;

    @Override
    protected void doMain() {
        RecentliyNewsInfo.DataBean.ListBean messagesBean
                = (RecentliyNewsInfo.DataBean.ListBean) getIntent().getSerializableExtra("info");
        RBitisDeitlsInfo rBitisDeitlsInfo = new RBitisDeitlsInfo();
        rBitisDeitlsInfo.setFunctionName("QueryPost");
        RBitisDeitlsInfo.ParametersBean parametersBean = new RBitisDeitlsInfo.ParametersBean();
        parametersBean.setId(messagesBean.getPostId());
        parametersBean.setMessageId(messagesBean.getId());
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        rBitisDeitlsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(this);
        okGoUtils.httpPostJSON(rBitisDeitlsInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                bitisDetilsInfo = JSON.parseObject(response, BitisDetilsInfo.class);
                if (bitisDetilsInfo.getCode() == 0) {
                    Glide.with(BitisNewDetils.this).load(bitisDetilsInfo.getData().getAvatar()).override(60, 60).into(user_icon);
                    tv_phone.setText(TextUtils.isEmpty(bitisDetilsInfo.getData().getUserName()) ? ZeroZeroSevenUtils.phoneClose(bitisDetilsInfo.getData().getUserPhone()) : bitisDetilsInfo.getData().getUserName());
                    tv_time.setText(bitisDetilsInfo.getData().getCreateTime());
                    tv_like.setText(String.valueOf(bitisDetilsInfo.getData().getLikeCount()));
                    tv_talk.setText(String.valueOf(bitisDetilsInfo.getData().getMessages() == null ? 0 : bitisDetilsInfo.getData().getMessages().size()));
                    tv_content.setText(TextUtils.isEmpty(bitisDetilsInfo.getData().getContent()) ? "加载失败" : bitisDetilsInfo.getData().getContent());
                    if (bitisDetilsInfo.getData().getIsLike() == 1) {
                        Glide.with(BitisNewDetils.this).load(R.drawable.bit_like).override(50, 50).into(iv_like);
                        tv_like.setTextColor(getResources().getColor(R.color.money));
                    } else {
                        Glide.with(BitisNewDetils.this).load(R.drawable.bt_like_nor).override(50, 50).into(iv_like);
                        tv_like.setTextColor(getResources().getColor(R.color.line6));
                    }

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(BitisNewDetils.this, 3);
                    rc_photo.setLayoutManager(gridLayoutManager);
                    RecyclerView.ItemDecoration itemDecoration = rc_photo.getItemDecorationAt(0);
                    if (itemDecoration == null) {
                        rc_photo.addItemDecoration(new GridSpacingItemDecoration(3, 20, false));
                    }
                    BitisImageAdapter bitisImageAdapter = new BitisImageAdapter(BitisNewDetils.this);
                    rc_photo.setAdapter(bitisImageAdapter);
                    if (bitisDetilsInfo.getData().getImages() != null && bitisDetilsInfo.getData().getImages().size() > 0) {
                        bitisImageAdapter.cleanDates();
                        bitisImageAdapter.addAll(bitisDetilsInfo.getData().getImages());
                    }
                    rc_talk.setLayoutManager(new LinearLayoutManager(BitisNewDetils.this));
                    RecyclerView.ItemDecoration itemDecoration1 = rc_talk.getItemDecorationAt(0);
                    if (itemDecoration1 == null) {
                        rc_talk.addItemDecoration(new AllItemDecoration(0, 2));
                    }
                    talkAdapter = new DetilsTalkAdapter(BitisNewDetils.this);
                    talkAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, long itemId) {
                            talkAdapter.setClickPosition(position);
                            if (bitisDetilsInfo.getData().getMessages().get(position).getFromUid() == BaseAppApplication.getInstance().getLoginUser().getId()) {//点击了自己的留言
                                return;
                            }
                            talkType = 1;
                            commentDialog.show();
                            commentDialog.setEt_comment(talkAdapter.getItem(position).getFromUname());
                        }
                    });
                    rc_talk.setAdapter(talkAdapter);
                    talkAdapter.cleanDates();
                    talkAdapter.addAll(bitisDetilsInfo.getData().getMessages());
                    return;
                }
                ZeroZeroSevenUtils.showCustonPop(BitisNewDetils.this, bitisDetilsInfo.getMessage(), topView);
            }
        });
    }

    @OnClick({R.id.ll_talk, R.id.ll_like, R.id.ll_share})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.ll_talk:
                talkType = 0;
                commentDialog.show();
                commentDialog.setEt_comment("输入你想说的");
                break;
            case R.id.ll_share:
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();
                // title标题，微信、QQ和QQ空间等平台使用
                oks.setTitle("零零7许愿墙");
                // titleUrl QQ和QQ空间跳转链接
                oks.setTitleUrl(AppConfig.SHAREURL);
                // text是分享文本，所有平台都需要这个字段
                oks.setText(bitisDetilsInfo.getData().getContent());
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                oks.setImageUrl(AppConfig.LOGOURL);//确保SDcard下面存在此张图片
                // url在微信、微博，Facebook等平台中使用
                oks.setUrl(AppConfig.SHAREURL);
                // 启动分享GUI
                oks.show(BitisNewDetils.this);
                break;
            case R.id.ll_like:
                if (bitisDetilsInfo.getData().getIsLike() == 1) {
                    ToastUtils.showShort("你已经点过赞了");
                    return;
                }
                likebitis(tv_like, iv_like, bitisDetilsInfo.getData());
                break;

        }
    }

    private void talk(final String content, final int talkType) {
        final UserInfo.DataBean loginUser = BaseAppApplication.getInstance().getLoginUser();

        RTalksBitisInfo rTalksBitisInfo = new RTalksBitisInfo();
        if (talkType == 0) {
            rTalksBitisInfo.setFunctionName("AddPostMessage");
        } else {
            rTalksBitisInfo.setFunctionName("AddPostReply");
        }
        RTalksBitisInfo.ParametersBean parametersBean = new RTalksBitisInfo.ParametersBean();
        parametersBean.setContent(content);
        parametersBean.setPostId(bitisDetilsInfo.getData().getId());
        if (talkType == 0) {//留言
            parametersBean.setToUid(bitisDetilsInfo.getData().getUserId());
        } else {//回复
            parametersBean.setMessageId(bitisDetilsInfo.getData().getMessages().get(talkAdapter.clickPosition).getId());
            parametersBean.setToUid(bitisDetilsInfo.getData().getMessages().get(talkAdapter.clickPosition).getFromUid());
        }
        parametersBean.setUserId(loginUser.getId());
        rTalksBitisInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(BitisNewDetils.this);
        okGoUtils.httpPostJSON(rTalksBitisInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                OkTalkInfo okTalkInfo = JSON.parseObject(response, OkTalkInfo.class);
                if (okTalkInfo.getCode() == 0) {
                    BitisDetilsInfo.DataBean.MessagesBean messagesBean = new BitisDetilsInfo.DataBean.MessagesBean();
                    messagesBean.setContent(content);
                    messagesBean.setFromUid(loginUser.getId());
                    messagesBean.setFromUname(loginUser.getRealName());
                    messagesBean.setId(bitisDetilsInfo.getData().getId());
                    if (talkType == 0) {
                        messagesBean.setToUid(bitisDetilsInfo.getData().getUserId());
                        messagesBean.setToUname("");
                    } else {
                        messagesBean.setToUid(bitisDetilsInfo.getData().getMessages().get(talkAdapter.clickPosition).getFromUid());
                        messagesBean.setToUname(bitisDetilsInfo.getData().getMessages().get(talkAdapter.clickPosition).getFromUname());
                    }
                    talkAdapter.getItems().add(messagesBean);
                    talkAdapter.notifyDataSetChanged();
                    return;
                }
                ToastUtils.showShort(okTalkInfo.getMessage());
            }
        });
    }

    private void likebitis(final TextView tv, final ImageView iv, final BitisDetilsInfo.DataBean item) {
        DafenInfo dafenInfo1 = new DafenInfo();
        dafenInfo1.setFunctionName("UpdatePostLike");
        DafenInfo.ParametersBean parametersBean1 = new DafenInfo.ParametersBean();
        parametersBean1.setPostId(String.valueOf(item.getId()));
        parametersBean1.setEvent("ADD");
        dafenInfo1.setParameters(parametersBean1);
        OkGoUtils okGoUtils = new OkGoUtils(BitisNewDetils.this);
        okGoUtils.httpPostJSON(dafenInfo1, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                bitisDetilsInfo.getData().setIsLike(1);
                tv.setText(String.valueOf(item.getLikeCount() + 1));
                tv.setTextColor(getResources().getColor(R.color.money));
                Glide.with(BitisNewDetils.this).load(R.drawable.bit_like).override(50, 50).into(iv);
            }
        });

    }
}
