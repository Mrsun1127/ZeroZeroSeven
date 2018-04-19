package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CodeInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.BindZFbInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SendCodeInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MyCountTimer;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/21.
 */

public class BindZFbPayActivity extends BaseActivity {
    @Bind(R.id.tv_send)
    Button tv_send;
    @Bind(R.id.et_phoneNumber)
    EditText et_phoneNumber;//验证码
    @Bind(R.id.et_phoneYan)
    EditText et_phoneYan;//支付宝账号
    private MyCountTimer timer;

    @Override
    protected int setLayout() {
        return R.layout.activity_bindpay;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        timer = new MyCountTimer(tv_send);
        BaseAppApplication.getInstance().addActivity(this);
    }

    @Override
    protected void doMain() {
        TitleView titleView = findViewById(R.id.titleview);
        titleView.setTopText("绑定支付宝账号");
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

    @OnClick({R.id.tv_send, R.id.bt_sub})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_send:
                requesetNumber();
                break;
            case R.id.bt_sub:
                String yzm = et_phoneNumber.getText().toString().trim();
                String zfb = et_phoneYan.getText().toString().trim();
                bindZFB(yzm, zfb);
                break;
        }
    }

    private void bindZFB(String yzm, final String zfb) {
        showLoadProgress();
        BindZFbInfo bindZFbInfo = new BindZFbInfo();
        bindZFbInfo.setFunctionName("UpdateAlipayAccount");
        BindZFbInfo.ParametersBean parametersBean = new BindZFbInfo.ParametersBean();
        parametersBean.setPhone(userInfo.getPhone());
        parametersBean.setAlipayAccount(zfb);
        parametersBean.setAuthcode(yzm);
        bindZFbInfo.setParameters(parametersBean);
        httpPostJSON(bindZFbInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        ToastUtils.showShort("绑定失败,请检查网络");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ErrorCodeInfo info = JSON.parseObject(response.body().string(), ErrorCodeInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if (info.getCode() == 0) {
                            ToastUtils.showShort("绑定成功");
                            SharePrefUtils.setString(BindZFbPayActivity.this,"zfb",zfb);
                            finish();
                        } else {
                            ToastUtils.showShort(info.getMessage());
                        }
                    }
                });
            }
        });
    }


    private void requesetNumber() {
        showLoadProgress();
        SendCodeInfo sendCodeInfo = new SendCodeInfo();
        sendCodeInfo.setFunctionName("SendAuthcode");
        SendCodeInfo.ParametersBean parametersBean = new SendCodeInfo.ParametersBean();
        parametersBean.setPhone(userInfo.getPhone());
        parametersBean.setEvent("LOGIN");
        sendCodeInfo.setParameters(parametersBean);
        httpPostJSON(sendCodeInfo);
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
                final CodeInfo info = JsonUtil.parseJsonToBean(response.body().string(), CodeInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if (info.getCode() == 0) {
                            LogUtils.E("codeinfo",info.getData().getAuthcode());
                            ToastUtils.showShort("发送成功!");
                            timer.start();
                        } else {
                            ToastUtils.showShort(info.getMessage());
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.onFinish();
    }
}
