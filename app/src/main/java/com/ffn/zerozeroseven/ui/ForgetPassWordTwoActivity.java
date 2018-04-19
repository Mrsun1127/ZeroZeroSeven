package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

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
 * Created by GT on 2017/11/15.
 */

public class ForgetPassWordTwoActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout iv_back;
    private String phone;
    private String code;
    private EditText et_pwd;
    private EditText et_passWord;
    private String onePwd;
    private String twoPwd;

    @Override
    public void initView() {
        BaseAppApplication.getInstance().addActivity(this);
        iv_back = this.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.bt_next).setOnClickListener(this);
        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
        et_pwd = findViewById(R.id.et_pwd);
        et_passWord = findViewById(R.id.et_passWord);

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_forgetpwdtwo;
    }

    @Override
    protected void doMain() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                onePwd = et_pwd.getText().toString().trim();
                twoPwd = et_passWord.getText().toString().trim();
                reg();
                break;
            default:

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
        regInfo.setFunctionName("UserUpdatePassword");
        RegInfo.ParametersBean parametersBean=new RegInfo.ParametersBean();
        parametersBean.setAuthcode(code);
        parametersBean.setPhone(phone);
        parametersBean.setPassword(twoPwd);
        regInfo.setParameters(parametersBean);
        httpPostJSON(regInfo);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            CodeInfo codeInfo= JsonUtil.parseJsonToBean(response.body().string(),CodeInfo.class);
                            if(codeInfo.getCode()==0){
                                ToastUtils.showShort("修改成功");
                                ZeroZeroSevenUtils.SwitchActivity(ForgetPassWordTwoActivity.this,LoginActivity.class);
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
