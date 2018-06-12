package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CodeInfo;
import com.ffn.zerozeroseven.bean.OkInfo;
import com.ffn.zerozeroseven.bean.requsetbean.JiaoYanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SendCodeInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MyCountTimer;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/15.
 */

public class ForgetPassWordActivity extends BaseActivity implements View.OnClickListener {
    Button btn_next;
    RelativeLayout iv_back;
    private EditText et_phoneNumber;
    private EditText et_code;
    private Button bt_send;
    private String ftCode;
    private MyCountTimer timer;
    @Override
    public void initView() {
        ftCode="";
        btn_next = this.findViewById(R.id.bt_next);
        btn_next.setOnClickListener(this);
        iv_back = this.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        et_code = findViewById(R.id.et_code);
        bt_send = findViewById(R.id.bt_send);
        bt_send.setOnClickListener(this);
        timer = new MyCountTimer(bt_send);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_forgetpwd;
    }

    @Override
    protected void doMain() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_send:
                sendCode();
                break;
            case R.id.bt_next:
                if(TextUtils.isEmpty(et_phoneNumber.getText().toString().trim())|| TextUtils.isEmpty(et_code.getText().toString().trim())){
                    ToastUtils.showShort("请将信息输入完善!");
                    return;
                }
                Yanzheng();
                break;
        }
    }
    private void Yanzheng() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                showLoadProgress();
            }
        });
        JiaoYanInfo jiaoYanInfo=new JiaoYanInfo();
        jiaoYanInfo.setFunctionName("ValidateAuthcode");
        JiaoYanInfo.ParametersBean parametersBean=new JiaoYanInfo.ParametersBean();
        parametersBean.setAuthcode(et_code.getText().toString().trim());
        parametersBean.setEvent("FORGET_PASSWORD");
        parametersBean.setPhone(et_phoneNumber.getText().toString().trim());
        jiaoYanInfo.setParameters(parametersBean);
        httpPostJSON(jiaoYanInfo);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        ZeroZeroSevenUtils.showCustonPop(ForgetPassWordActivity.this,"服务器异常",et_code);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                    }
                });
                final OkInfo okInfo= JSON.parseObject(response.body().string(),OkInfo.class);
                if(okInfo.getCode()==0){
                    if(okInfo.getData().isCorrect()){
                        Bundle bundle=new Bundle();
                        bundle.putString("phone",et_phoneNumber.getText().toString().trim());
                        bundle.putString("code",et_code.getText().toString().trim());
                        ZeroZeroSevenUtils.SwitchActivity(ForgetPassWordActivity.this,ForgetPassWordTwoActivity.class,bundle);
                        finish();
                    }else{
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                ZeroZeroSevenUtils.showCustonPop(ForgetPassWordActivity.this,"验证码错误",et_code);
                            }
                        });
                    }

                }else{
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ZeroZeroSevenUtils.showCustonPop(ForgetPassWordActivity.this,okInfo.getMessage(),et_code);
                        }
                    });
                }
            }
        });
    }


    private void sendCode() {
        if (TextUtils.isEmpty(et_phoneNumber.getText().toString().trim())) {
            ToastUtils.showShort("手机号码不能为空");
            return;
        }
        if (!ZeroZeroSevenUtils.isMobileNO(et_phoneNumber.getText().toString().trim())) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }
        showLoadProgress();
        SendCodeInfo codeInfo = new SendCodeInfo();
        codeInfo.setFunctionName("SendAuthcode");
        codeInfo.setId("1");
        SendCodeInfo.ParametersBean parametersBean = new SendCodeInfo.ParametersBean();
        parametersBean.setEvent("FORGET_PASSWORD");
        parametersBean.setPhone(et_phoneNumber.getText().toString().trim());
        codeInfo.setParameters(parametersBean);
        httpPostJSON(codeInfo);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtils.showShort("获取失败，请稍后再试");
                disLoadProgress();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                disLoadProgress();
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final CodeInfo codeInfo = JsonUtil.parseJsonToBean(response.body().string(), CodeInfo.class);
                            if (codeInfo.getCode() == 0) {
                                ftCode = codeInfo.getData().getAuthcode();
                                timer.start();
                                LogUtils.D("ForgetPassWordActivity", ftCode);
                                ToastUtils.showShort("发送成功");
                            }else{
                                ToastUtils.showShort(codeInfo.getMessage());
                            }
                        } catch (Exception e) {
                            ToastUtils.showShort("服务器异常请稍后再试");
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
