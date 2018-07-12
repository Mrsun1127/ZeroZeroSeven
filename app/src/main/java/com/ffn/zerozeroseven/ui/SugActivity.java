package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SugAppInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SugInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/19.
 */

public class SugActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_body;
    private String sugType;

    @Override
    protected int setLayout() {
        return R.layout.activity_sug;
    }

    @Override
    protected void doMain() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView textView = findViewById(R.id.tv_top);
        textView.setText("意见反馈");
        findViewById(R.id.bt_sub).setOnClickListener(this);
        et_body = findViewById(R.id.et_body);
        sugType = getIntent().getStringExtra("sugType");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_sub:
                if (TextUtils.isEmpty(et_body.getText().toString().trim())) {
                    ZeroZeroSevenUtils.showCustonPop(SugActivity.this, "请输入投诉建议的内容", et_body);
                } else {
                    if ("people".equals(sugType)) {
                        String phone = getIntent().getStringExtra("phone");
                        if (!TextUtils.isEmpty(phone)) {
                            sugPeople(phone);
                        } else {
                            ToastUtils.showShort("服务器异常，请稍后再试！");
                        }
                    } else {
                        sugApp();
                    }
                }
                break;
        }
    }

    private void sugApp() {
        SugAppInfo sugAppInfo = new SugAppInfo();
        sugAppInfo.setFunctionName("AddUserFeedback");
        SugAppInfo.ParametersBean parametersBean = new SugAppInfo.ParametersBean();
        parametersBean.setContent(et_body.getText().toString());
        sugAppInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(SugActivity.this);
        okGoUtils.httpPostJSON(sugAppInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final ErrorCodeInfo info = JSON.parseObject(response, ErrorCodeInfo.class);
                et_body.post(new Runnable() {
                    @Override
                    public void run() {
                        if (info.getCode() == 0) {
                            ToastUtils.showShort("提交成功");
                            finish();
                        } else {
                            ToastUtils.showShort(info.getMessage());
                        }
                    }
                });

            }
        });


    }

    private void sugPeople(String phone) {
        SugInfo sugInfo = new SugInfo();
        sugInfo.setFunctionName("AddCourierComplaint");
        SugInfo.ParametersBean parametersBean = new SugInfo.ParametersBean();
        parametersBean.setContent(et_body.getText().toString().trim());
        parametersBean.setCourierPhone(phone);
        sugInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils1 = new OkGoUtils(SugActivity.this);
        okGoUtils1.httpPostJSON(sugInfo, true, true);
        okGoUtils1.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final ErrorCodeInfo info = JSON.parseObject(response, ErrorCodeInfo.class);
                et_body.post(new Runnable() {
                    @Override
                    public void run() {
                        if (info.getCode() == 0) {
                            ToastUtils.showShort("提交成功");
                            finish();
                        } else {
                            ToastUtils.showShort(info.getMessage());
                        }
                    }
                });
            }
        });

    }
}
