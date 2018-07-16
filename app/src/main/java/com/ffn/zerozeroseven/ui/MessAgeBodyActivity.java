package com.ffn.zerozeroseven.ui;

import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.MessAgeSinggerInfo;
import com.ffn.zerozeroseven.bean.requsetbean.MessAgeDetilsInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/19.
 */

public class MessAgeBodyActivity extends BaseActivity {
    @Bind(R.id.titleview)
    TitleView titleview;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_body)
    TextView tv_body;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_content)
    TextView tv_content;
    private int id;

    @Override
    protected int setLayout() {
        return R.layout.activity_messagebody;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        titleview.setTopText("消息详情");
        titleview.setOnTitleListener(new TitleView.OnTitleClickListener() {
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
        id = getIntent().getIntExtra("id", 0);
        requestData();
    }

    private void requestData() {
        MessAgeDetilsInfo info = new MessAgeDetilsInfo();
        info.setFunctionName("QueryPushNews");
        MessAgeDetilsInfo.ParametersBean parametersBean = new MessAgeDetilsInfo.ParametersBean();
        parametersBean.setId(id + "");
        info.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(MessAgeBodyActivity.this);
        okGoUtils.httpPostJSON(info, true, true,tv_time);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final MessAgeSinggerInfo singgerInfo = JSON.parseObject(response, MessAgeSinggerInfo.class);
                tv_time.post(new Runnable() {
                    @Override
                    public void run() {
                        if (singgerInfo.getCode() == 0) {
                            tv_title.setText(singgerInfo.getData().getTitle());
                            tv_body.setText(singgerInfo.getData().getContent());
                            tv_content.setText(singgerInfo.getData().getSummary());
                            tv_time.setText(singgerInfo.getData().getCreateTime());
                        } else {
                            ToastUtils.showShort("网络异常");
                        }
                    }
                });
            }
        });


    }
}
