package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CodeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RegInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/29.
 */

public class SetPassWordActivity extends BaseActivity implements View.OnClickListener {

    private String onePwd;
    private String twoPwd;
    private String regCode;
    private String phone;
    private EditText et_pwd;
    private EditText et_password;

    @Override
    protected int setLayout() {
        return R.layout.activity_forgetpwdtwo;
    }

    @Override
    protected void doMain() {

    }

    @Override
    public void initView() {
        et_pwd = findViewById(R.id.et_pwd);
        et_password = findViewById(R.id.et_passWord);
        regCode = getIntent().getStringExtra("rgCode");
        phone = getIntent().getStringExtra("phone");
        findViewById(R.id.bt_next).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                onePwd = et_pwd.getText().toString().trim();
                twoPwd = et_password.getText().toString().trim();
                reg();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void reg() {
        if(TextUtils.isEmpty(onePwd) || TextUtils.isEmpty(twoPwd)){
            ToastUtils.showShort("请将信息输入完整");
            return;
        }
        if(!onePwd.equals(twoPwd)){
            ToastUtils.showShort("两次密码输入不一致");
            return;
        }
        RegInfo regInfo=new RegInfo();
        regInfo.setFunctionName("UserRegister");
        regInfo.setId("");
        RegInfo.ParametersBean parametersBean=new RegInfo.ParametersBean();
        parametersBean.setAuthcode(regCode);
        parametersBean.setPhone(phone);
        parametersBean.setPassword(twoPwd);
        if(!TextUtils.isEmpty(getIntent().getStringExtra("yaoqing"))){
           parametersBean.setInviteCode(getIntent().getStringExtra("yaoqing"));
        }
        regInfo.setParameters(parametersBean);
        httpPostJSON(regInfo);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            CodeInfo codeInfo= JsonUtil.parseJsonToBean(response.body().string(),CodeInfo.class);
                            if(codeInfo.getCode()==0){
                                ToastUtils.showShort("注册成功");
                                ZeroZeroSevenUtils.SwitchActivity(SetPassWordActivity.this,LoginActivity.class);
                                finish();
                            }else{
                                ToastUtils.showShort(codeInfo.getMessage());
                            }
                        }catch (Exception e){
                            ToastUtils.showShort("服务器异常，请稍后再试");
                        }
                    }
                });
            }
        });
    }
}
