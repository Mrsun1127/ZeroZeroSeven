package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.BitisInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.OkTalkInfo;
import com.ffn.zerozeroseven.bean.QiangShowInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DafenInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DeleteTieInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDeleteTalkInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RTalksBitisInfo;
import com.ffn.zerozeroseven.ui.BitisNewActivity;
import com.ffn.zerozeroseven.ui.LookPicActivity;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.AllItemDecoration;
import com.ffn.zerozeroseven.view.CommentDialog;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by GT on 2017/11/27.
 */

public class BitisNewAdapter extends BaseRecyclerAdapter<BitisInfo.DataBean.ItemsBean> {
    public int clickPosition = 0;
    public int talkType;
    private TalkAdapter talkAdapter;

    public BitisNewAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new BitisNewAdapter.MViewHolder(mInflater.inflate(R.layout.item_bitis_new, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final BitisInfo.DataBean.ItemsBean item, final int topPosition) {
        final MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext).load(item.getAvatar()).override(60, 60).into(mHolder.user_icon);
        mHolder.tv_phone.setText(TextUtils.isEmpty(item.getUserName()) ? ZeroZeroSevenUtils.phoneClose(item.getUserPhone()) : item.getUserName());
        mHolder.tv_time.setText(item.getCreateTime());
        mHolder.tv_like.setText(String.valueOf(item.getLikeCount()));
        mHolder.tv_talk.setText(String.valueOf(item.getMessages() == null ? 0 : item.getMessages().size()));
        mHolder.tv_content.setText(TextUtils.isEmpty(item.getContent()) ? "加载失败" : item.getContent());
        if (item.getIsLike() == 1) {
            Glide.with(mContext).load(R.drawable.bit_like).override(50, 50).into(mHolder.iv_like);
            mHolder.tv_like.setTextColor(getResource().getColor(R.color.money));
        } else {
            Glide.with(mContext).load(R.drawable.bt_like_nor).override(50, 50).into(mHolder.iv_like);
            mHolder.tv_like.setTextColor(getResource().getColor(R.color.line6));
        }
        if (item.getUserId() == BaseAppApplication.getInstance().getLoginUser().getId()) {
            mHolder.rl_delete.setVisibility(View.VISIBLE);
        } else {
            mHolder.rl_delete.setVisibility(View.GONE);
        }
        mHolder.ll_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();
                // title标题，微信、QQ和QQ空间等平台使用
                oks.setTitle("零零7许愿墙");
                // titleUrl QQ和QQ空间跳转链接
                oks.setTitleUrl(AppConfig.SHAREURL);
                // text是分享文本，所有平台都需要这个字段
                oks.setText(item.getContent());
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                oks.setImageUrl(AppConfig.LOGOURL);//确保SDcard下面存在此张图片
                // url在微信、微博，Facebook等平台中使用
                oks.setUrl(AppConfig.SHAREURL);
                // 启动分享GUI
                oks.show(mContext);
            }
        });
        mHolder.rl_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ConfirmDialog confirmDialog = new ConfirmDialog(mContext);
                confirmDialog.setTitle("提示");
                confirmDialog.setMessages("是否删除该条帖子?");
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        confirmDialog.dismiss();
                        deleteTiezi(item, topPosition);
                    }

                    @Override
                    public void doCancel() {
                        confirmDialog.dismiss();
                    }
                });
            }
        });
        final CommentDialog commentDialog = new CommentDialog(mContext);
        commentDialog.setOnCommitListener(new CommentDialog.OnCommitListener() {
            @Override
            public void onCommit(EditText et, View v) {
                commentDialog.dismiss();
                String content = et.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    return;
                }
                talk(content, item, topPosition, talkType);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        mHolder.rc_photo.setLayoutManager(gridLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = mHolder.rc_photo.getItemDecorationAt(0);
        if (itemDecoration == null) {
            mHolder.rc_photo.addItemDecoration(new GridSpacingItemDecoration(3, 20, false));
        }
        BitisImageAdapter bitisImageAdapter = new BitisImageAdapter(mContext);
        mHolder.rc_photo.setAdapter(bitisImageAdapter);
        bitisImageAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("list", item.getImages());
                ZeroZeroSevenUtils.SwitchActivity(mContext, LookPicActivity.class, bundle);
            }
        });
        if (item.getImages() != null && item.getImages().size() > 0) {
            bitisImageAdapter.cleanDates();
            bitisImageAdapter.addAll(item.getImages());
        }
        mHolder.rc_talk.setLayoutManager(new LinearLayoutManager(mContext));
        RecyclerView.ItemDecoration itemDecoration1 = mHolder.rc_talk.getItemDecorationAt(0);
        if (itemDecoration1 == null) {
            mHolder.rc_talk.addItemDecoration(new AllItemDecoration(0, 2));
        }
        talkAdapter = new TalkAdapter(mContext);
        talkAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                talkAdapter.setClickPosition(position);
                if (item.getMessages().get(position).getFromUid() == BaseAppApplication.getInstance().getLoginUser().getId()) {//点击了自己的留言
                    return;
                }
                talkType = 1;
                commentDialog.show();
                commentDialog.setEt_comment(item.getMessages().get(position).getFromUname());
            }
        });
        talkAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onLongClick(final int position, long itemId) {
                clickPosition = topPosition;
                if (item.getMessages().get(position).getFromUid() != BaseAppApplication.getInstance().getLoginUser().getId()) {
                    return;
                }
                final ConfirmDialog confirmDialog = new ConfirmDialog(mContext);
                confirmDialog.setTitle("提示");
                confirmDialog.setMessages("是否删除这条回复");
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        confirmDialog.dismiss();
                        if (item.getMessages().get(position).isMessage()) {
                            deleteLiuyan(item, position);
                        } else {
                            deleteTalk(item, position);
                        }

                    }

                    @Override
                    public void doCancel() {
                        confirmDialog.dismiss();
                    }
                });
            }
        });
        mHolder.rc_talk.setAdapter(talkAdapter);
        talkAdapter.cleanDates();
        talkAdapter.addAll(item.getMessages());
        mHolder.ll_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnimationone(mHolder.tv_like);
                if (item.getIsLike() == 1) {
                    ToastUtils.showShort("你已经点过赞了");
                    return;
                }
                likebitis(mHolder.tv_like, mHolder.iv_like, item);
            }
        });


        mHolder.ll_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                talkType = 0;
                commentDialog.show();
                commentDialog.setEt_comment("输入你想说的");
            }
        });
    }

    private void deleteTiezi(BitisInfo.DataBean.ItemsBean itemsBean, final int topPoition) {
        DeleteTieInfo deleteTieInfo = new DeleteTieInfo();
        deleteTieInfo.setFunctionName("DeleteUserPost");
        DeleteTieInfo.ParametersBean parametersBean = new DeleteTieInfo.ParametersBean();
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        parametersBean.setPostId(itemsBean.getId());
        deleteTieInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(mContext);
        okGoUtils.httpPostJSON(deleteTieInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo codeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (codeInfo.getCode() == 0) {
                    BitisNewActivity.mInstance.get().removeTopItem(topPoition);
                } else {
                    ToastUtils.showShort(codeInfo.getMessage());
                }
            }
        });
    }

    private void deleteLiuyan(BitisInfo.DataBean.ItemsBean item, final int position) {
        RDeleteTalkInfo rDeleteTalkInfo = new RDeleteTalkInfo();
        rDeleteTalkInfo.setFunctionName("DeletePostMessage");
        RDeleteTalkInfo.ParametersBean parametersBean = new RDeleteTalkInfo.ParametersBean();
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        parametersBean.setId(item.getMessages().get(position).getId());
        rDeleteTalkInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(mContext);
        okGoUtils.httpPostJSON(rDeleteTalkInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    BitisNewActivity.mInstance.get().removeItemBean(position);
                    return;
                }
                ToastUtils.showShort(errorCodeInfo.getMessage());
            }
        });
    }

    private void deleteTalk(BitisInfo.DataBean.ItemsBean itemsBean, final int position) {
        RDeleteTalkInfo rDeleteTalkInfo = new RDeleteTalkInfo();
        rDeleteTalkInfo.setFunctionName("DeletePostReply");
        RDeleteTalkInfo.ParametersBean parametersBean = new RDeleteTalkInfo.ParametersBean();
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        parametersBean.setId(itemsBean.getMessages().get(position).getId());
        rDeleteTalkInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(mContext);
        okGoUtils.httpPostJSON(rDeleteTalkInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    BitisNewActivity.mInstance.get().removeItemBean(position);
                    return;
                }
                ToastUtils.showShort(errorCodeInfo.getMessage());
            }
        });
    }

    private void talk(final String content, final BitisInfo.DataBean.ItemsBean itemsBean, final int position, final int talkType) {
        final UserInfo.DataBean loginUser = BaseAppApplication.getInstance().getLoginUser();

        RTalksBitisInfo rTalksBitisInfo = new RTalksBitisInfo();
        if (talkType == 0) {
            rTalksBitisInfo.setFunctionName("AddPostMessage");
        } else {
            rTalksBitisInfo.setFunctionName("AddPostReply");
        }
        RTalksBitisInfo.ParametersBean parametersBean = new RTalksBitisInfo.ParametersBean();
        parametersBean.setContent(content);
        parametersBean.setPostId(itemsBean.getId());
        if (talkType == 0) {//留言
            parametersBean.setToUid(itemsBean.getUserId());
        } else {//回复
            parametersBean.setToUid(itemsBean.getMessages().get(talkAdapter.clickPosition).getFromUid());
            parametersBean.setMessageId(itemsBean.getMessages().get(talkAdapter.clickPosition).getId());
        }
        parametersBean.setUserId(loginUser.getId());
        rTalksBitisInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(mContext);
        okGoUtils.httpPostJSON(rTalksBitisInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                OkTalkInfo okTalkInfo = JSON.parseObject(response, OkTalkInfo.class);
                if (okTalkInfo.getCode() == 0) {
                    BitisInfo.DataBean.ItemsBean.MessagesBean messagesBean = new BitisInfo.DataBean.ItemsBean.MessagesBean();
                    messagesBean.setContent(content);
                    messagesBean.setFromUid(loginUser.getId());
                    messagesBean.setFromUname(loginUser.getRealName());
                    messagesBean.setId(okTalkInfo.getData().getId());
                    if (talkType == 0) {
                        messagesBean.setToUid(itemsBean.getUserId());
                        messagesBean.setToUname("");
                        messagesBean.setMessage(true);
                    } else {
                        messagesBean.setToUid(itemsBean.getMessages().get(talkAdapter.clickPosition).getFromUid());
                        messagesBean.setToUname(itemsBean.getMessages().get(talkAdapter.clickPosition).getFromUname());
                        messagesBean.setMessage(false);
                    }
                    BitisNewActivity.mInstance.get().addItemBean(messagesBean, position);
                    return;
                }
                ToastUtils.showShort(okTalkInfo.getMessage());
            }
        });
    }

    private void likebitis(final TextView tv, final ImageView iv, final BitisInfo.DataBean.ItemsBean item) {
        DafenInfo dafenInfo1 = new DafenInfo();
        dafenInfo1.setFunctionName("UpdatePostLike");
        DafenInfo.ParametersBean parametersBean1 = new DafenInfo.ParametersBean();
        parametersBean1.setPostId(String.valueOf(item.getId()));
        parametersBean1.setEvent("ADD");
        dafenInfo1.setParameters(parametersBean1);
        OkGoUtils okGoUtils = new OkGoUtils(mContext);
        okGoUtils.httpPostJSON(dafenInfo1, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                item.setIsLike(1);
                tv.setText(String.valueOf(item.getLikeCount() + 1));
                tv.setTextColor(getResource().getColor(R.color.money));
                Glide.with(mContext).load(R.drawable.bit_like).override(50, 50).into(iv);
            }
        });

    }

    public void setAnimationone(final View convertView) {
        ViewHelper.setScaleX(convertView, 2f);
        ViewHelper.setScaleY(convertView, 2f);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewPropertyAnimator.animate(convertView).scaleX(1f).setDuration(500).setInterpolator(new OvershootInterpolator());
                ViewPropertyAnimator.animate(convertView).scaleY(1f).setDuration(500).setInterpolator(new OvershootInterpolator());
            }
        }, 50);

    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        CircleImageView user_icon;
        TextView tv_talk;
        TextView tv_phone;
        TextView tv_time;
        TextView tv_content;
        TextView tv_like;
        ImageView iv_like;
        RecyclerView rc_photo;
        RecyclerView rc_talk;
        LinearLayout ll_talk;
        LinearLayout ll_like;
        LinearLayout ll_share;
        RelativeLayout rl_delete;

        MViewHolder(View itemView) {
            super(itemView);
            rl_delete = itemView.findViewById(R.id.rl_delete);
            rc_talk = itemView.findViewById(R.id.rc_talk);
            tv_talk = itemView.findViewById(R.id.tv_talk);
            iv_like = itemView.findViewById(R.id.iv_like);
            user_icon = itemView.findViewById(R.id.user_icon);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            rc_photo = itemView.findViewById(R.id.rc_photo);
            ll_talk = itemView.findViewById(R.id.ll_talk);
            ll_like = itemView.findViewById(R.id.ll_like);
            ll_share = itemView.findViewById(R.id.ll_share);
        }
    }


}
