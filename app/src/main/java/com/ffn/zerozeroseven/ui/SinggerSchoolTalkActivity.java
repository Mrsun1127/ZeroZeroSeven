package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.SinggerDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DafenInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SinggerTieziInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2018/3/3.
 */

public class SinggerSchoolTalkActivity extends BaseActivity {
    @Bind(R.id.titleview)
    TitleView titleView;
    @Bind(R.id.tv_project)
    TextView tv_project;
    @Bind(R.id.tv_detils)
    TextView tv_detils;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_content)
    TextView tv_content;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_likecount)
    TextView tv_likecount;
    @Bind(R.id.ll_share)
    LinearLayout ll_share;
    @Bind(R.id.iv_like)
    ImageView iv_like;
    @Bind(R.id.slide_like)
    ImageView slide_like;
    private int id;
    private SinggerDetilsInfo singgerDetilsInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_singertalk;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        BaseAppApplication.getInstance().addActivity(this);
        id = getIntent().getIntExtra("id", 0);
        titleView.setOnTitleListener(new TitleView.OnTitleClickListener() {
            @Override
            public void ivBack() {
                finish();
            }

            @Override
            public void ivDown() {

            }

            @Override
            public void ivMessAge() {

            }
        });
    }

    @Override
    protected void doMain() {
        requestDate();
    }

    private void requestDate() {
        showLoadProgress();
        SinggerTieziInfo singgerTieziInfo = new SinggerTieziInfo();
        singgerTieziInfo.setFunctionName("QueryPost");
        SinggerTieziInfo.ParametersBean parametersBean = new SinggerTieziInfo.ParametersBean();
        parametersBean.setId(id);
        singgerTieziInfo.setParameters(parametersBean);
        httpPostJSON(singgerTieziInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                singgerDetilsInfo = JSON.parseObject(response.body().string(), SinggerDetilsInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if (singgerDetilsInfo.getCode() == 0) {
                            SinggerDetilsInfo.DataBean data = singgerDetilsInfo.getData();
                            tv_project.setText(data.getUserCollege());
                            if (data.getIsAnonymity() == 1) {
                                tv_detils.setText("匿名");
                            } else {
                                tv_detils.setText(data.getUserClazz() + "  " + data.getUserName());
                            }
                            tv_title.setText(data.getTitle());
                            tv_content.setText(data.getContent());
                            tv_time.setText(data.getCreateTime());
                            tv_likecount.setText(data.getLikeCount() + "");
                            if (data.getIsLike() == 1) {
                                iv_like.setBackgroundResource(R.drawable.heart_solid);
                            } else {
                                iv_like.setBackgroundResource(R.drawable.heart_hollow);
                            }
                            switch (data.getPostType()) {
                                case "01":
                                    titleView.setTopText("表白帖");
                                    break;
                                case "02":
                                    titleView.setTopText("技术帖");
                                    break;
                                case "03":
                                    titleView.setTopText("寻物帖");
                                    break;
                                case "04":
                                    titleView.setTopText("交友帖");
                                    break;
                            }
                        }
                    }
                });

            }
        });
    }

    boolean close = false;
    boolean add = false;

    @OnClick({R.id.ll_share, R.id.iv_dislike,R.id.slide_like})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.ll_share:
                showShare(singgerDetilsInfo.getData().getTitle(), singgerDetilsInfo.getData().getContent());
                break;
            case R.id.slide_like:
                if(singgerDetilsInfo.getData().getIsLike()==1){
                    ZeroZeroSevenUtils.showCustonPop(SinggerSchoolTalkActivity.this,"您已经点过赞",slide_like);
                    return;
                }
                if (add) {
                    return;
                }
                DafenInfo dafenInfo1 = new DafenInfo();
                dafenInfo1.setFunctionName("UpdatePostLike");
                DafenInfo.ParametersBean parametersBean1 = new DafenInfo.ParametersBean();
                parametersBean1.setPostId("" + id);
                parametersBean1.setEvent("ADD");
                dafenInfo1.setParameters(parametersBean1);
                httpPostJSON(dafenInfo1, true);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        LogUtils.E("Singger", response.body().string());
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                add = true;
                                tv_likecount.setText((Integer.parseInt(tv_likecount.getText().toString()) + 1) + "");
                                iv_like.setBackgroundResource(R.drawable.heart_solid);
                                singgerDetilsInfo.getData().setIsLike(1);
                            }
                        });

                    }
                });
                break;
            case R.id.iv_dislike:
                if (close) {
                    return;
                }
                DafenInfo dafenInfo = new DafenInfo();
                dafenInfo.setFunctionName("UpdatePostLike");
                DafenInfo.ParametersBean parametersBean = new DafenInfo.ParametersBean();
                parametersBean.setPostId("" + id);
                parametersBean.setEvent("DELETE");
                dafenInfo.setParameters(parametersBean);
                httpPostJSON(dafenInfo, true);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        close = true;
                        if (singgerDetilsInfo.getData().getLikeCount() != 0) {
                            BaseAppApplication.mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv_likecount.setText((Integer.parseInt(tv_likecount.getText().toString()) - 1) + "");
                                }
                            });
                        }
                    }
                });
                break;
        }
    }

    private void showShare(String title, String content) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(AppConfig.SHAREURL);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(AppConfig.LOGOURL);//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(AppConfig.SHAREURL);
        // 启动分享GUI
        oks.show(SinggerSchoolTalkActivity.this);
    }
}
