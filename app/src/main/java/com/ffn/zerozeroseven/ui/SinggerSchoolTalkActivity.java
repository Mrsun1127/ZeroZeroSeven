package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
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

    @Bind(R.id.iv_type)
    ImageView iv_type;
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

                            switch (data.getPostType()) {
                                case "01":
                                    titleView.setTopText("表白帖");
//                                    Glide.with(SinggerSchoolTalkActivity.this).load(R.drawable.topic_romance_bg).into(iv_type);
                                    break;
                                case "02":
                                    titleView.setTopText("技术帖");
//                                    Glide.with(SinggerSchoolTalkActivity.this).load(R.drawable.topic_tech_bg).into(iv_type);
                                    break;
                                case "03":
                                    titleView.setTopText("寻物帖");
//                                    Glide.with(SinggerSchoolTalkActivity.this).load(R.drawable.topic_lostnfound_bg).into(iv_type);
                                    break;
                                case "04":
                                    titleView.setTopText("交友帖");
//                                    Glide.with(SinggerSchoolTalkActivity.this).load(R.drawable.topic_friends_bg).into(iv_type);
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
