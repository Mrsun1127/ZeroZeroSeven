package com.ffn.zerozeroseven.ui;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseLoginActivity;
import com.ffn.zerozeroseven.bean.CodeInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.BindJiGuangInfo;
import com.ffn.zerozeroseven.bean.requsetbean.CodeLoginInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SendCodeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.UserLoginInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MyCountTimer;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.UserInfoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by GT on 2017/11/15.
 */

public class LoginActivity extends BaseLoginActivity implements View.OnClickListener {
    private int LoginStatus = 0; //判断登录是以验证码还是用户名密码登录 0 验证码 1用户名密码
    Button bt_send;
    TextView tv_yanzhen;
    TextView tv_pwd;
    TextView tv_forgetpwd;
    TextView tv_desigh;
    TextView tv_bottom;
    RelativeLayout rl_yzm;
    RelativeLayout rl_pwd;
    View v_yzm;
    View v_pwd;
    ImageView iv_close;
    Button bt_login;
    EditText et_userpassWord;
    EditText et_username;
    EditText et_phoneNumber;
    EditText et_code;
    private String loginCode;
    private String message;
    private String codeLoginMessage;
    private String userLoginMessage;
    private String message1;
    private MyCountTimer timer;
    @Bind(R.id.et_yaoqing)
    EditText et_yaoqing;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        loginCode = "";
        bt_send = findViewById(R.id.bt_send);
        tv_forgetpwd = findViewById(R.id.tv_forgetpwd);
        tv_yanzhen = findViewById(R.id.tv_yanzhen);
        tv_pwd = findViewById(R.id.tv_pwd);
        rl_yzm = findViewById(R.id.rl_yzm);
        rl_pwd = findViewById(R.id.rl_pwd);
        v_pwd = findViewById(R.id.v_pwd);
        v_yzm = findViewById(R.id.v_yzm);
        et_username = findViewById(R.id.et_username);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        tv_bottom = findViewById(R.id.tv_bottom);
        tv_desigh = findViewById(R.id.tv_desigh);
        iv_close = findViewById(R.id.iv_close);
        bt_login = findViewById(R.id.bt_login);
        et_userpassWord = findViewById(R.id.et_userpassWord);
        et_code = findViewById(R.id.et_code);
        initClickListener();
        timer = new MyCountTimer(bt_send);
        verifyStoragePermissions(this);
        Map<String, String> txtFileInfo = UserInfoUtils.getTxtFileInfo(LoginActivity.this);
        try {
            String username = txtFileInfo.get("username");
            String password = txtFileInfo.get("password");
            if (!TextUtils.isEmpty(username)) {
                et_username.setText(username);
                et_phoneNumber.setText(username);
            }
            if (!TextUtils.isEmpty(password) && !"nopwd".equals(password)) {
                et_userpassWord.setText(password);
                if (!"set".equals(getIntent().getStringExtra("exit"))) {
                    LoginByPwd();
                }
            }

        } catch (Exception e) {
        }

    }


    private void initClickListener() {
        bt_send.setOnClickListener(this);
        tv_yanzhen.setOnClickListener(this);
        tv_pwd.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        tv_bottom.setOnClickListener(this);
        tv_forgetpwd.setOnClickListener(this);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_yanzhen:
                LoginStatus = 0;
                ZeroZeroSevenUtils.setUnderline(this, tv_bottom, "去随便逛逛>>");
                rl_yzm.setVisibility(View.VISIBLE);
                tv_desigh.setVisibility(View.VISIBLE);
                rl_pwd.setVisibility(View.INVISIBLE);
                v_yzm.setVisibility(View.VISIBLE);
                v_pwd.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_pwd:
                LoginStatus = 1;
                ZeroZeroSevenUtils.setUnderline(this, tv_bottom, "还未注册？点击这里注册");
                rl_pwd.setVisibility(View.VISIBLE);
                rl_yzm.setVisibility(View.INVISIBLE);
                tv_desigh.setVisibility(View.INVISIBLE);
                v_pwd.setVisibility(View.VISIBLE);
                v_yzm.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_bottom:
                if (LoginStatus == 0) {
                    ZeroZeroSevenUtils.SwitchActivity(LoginActivity.this, HomeActivity.class);
                    finish();
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(LoginActivity.this, RegistActivity.class);
                }
                break;
            case R.id.bt_login:
                if (LoginStatus == 0) {
                    LoginByCode();
                } else {
                    LoginByPwd();
                }
                break;
            case R.id.tv_forgetpwd:
                ZeroZeroSevenUtils.SwitchActivity(LoginActivity.this, ForgetPassWordActivity.class, null);
                break;
            case R.id.bt_send:
                SendCode();
                break;
        }
    }

    //发送验证码
    private void SendCode() {
        if (TextUtils.isEmpty(et_phoneNumber.getText().toString().trim())) {
            ToastUtils.showShort("手机号码不能为空");
            return;
        }
        if (!ZeroZeroSevenUtils.isMobileNO(et_phoneNumber.getText().toString().trim())) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }
        SendCodeInfo codeInfo = new SendCodeInfo();
        codeInfo.setFunctionName("SendAuthcode");
        codeInfo.setId("2");
        SendCodeInfo.ParametersBean parametersBean = new SendCodeInfo.ParametersBean();
        parametersBean.setEvent("LOGIN");
        parametersBean.setPhone(et_phoneNumber.getText().toString().trim());
        codeInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(LoginActivity.this);
        okGoUtils.httpPostJSON(codeInfo, false, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                    CodeInfo info = JSON.parseObject(response, CodeInfo.class);
                    if (info.getCode() == 0) {
                        timer.start();
                        loginCode = info.getData().getAuthcode();
                        ToastUtils.showShort("发送成功!");
                        LogUtils.D("LoginActivity", loginCode);
                    } else {
                        ToastUtils.showShort(info.getMessage());
                    }
            }
        });
    }

    //账号密码登录
    private void LoginByPwd() {
        if (TextUtils.isEmpty(et_username.getText().toString().trim())) {
            ToastUtils.showShort("手机号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(et_userpassWord.getText().toString().trim())) {
            ToastUtils.showShort("密码不能为空");
            return;
        }
        final UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setFunctionName("UserLogin");
        userLoginInfo.setId("5");
        UserLoginInfo.ParametersBean parametersBean = new UserLoginInfo.ParametersBean();
        parametersBean.setPassword(et_userpassWord.getText().toString().trim());
        parametersBean.setPhone(et_username.getText().toString().trim());
        if (!TextUtils.isEmpty(et_yaoqing.getText().toString().trim())) {
            parametersBean.setInviteCode(et_yaoqing.getText().toString().trim());
        }
        userLoginInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils2 = new OkGoUtils(LoginActivity.this);
        okGoUtils2.httpPostJSON(userLoginInfo, false, true);
        okGoUtils2.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final UserInfo userInfo = JsonUtil.parseJsonToBean(response, UserInfo.class);
                if (userInfo.getCode() == 0) {
                    UserInfoUtils.saveUserInfo(LoginActivity.this, et_username.getText().toString().trim(), et_userpassWord.getText().toString().trim());
                    BaseAppApplication.getInstance().setLoginUser(userInfo.getData());
                    SharePrefUtils.saveObject(LoginActivity.this, "userInfo", userInfo.getData());
                    bindJiGuang(userInfo.getData().getId());
                } else {
                    ToastUtils.showShort(userInfo.getMessage());
                }
            }
        });
    }

    //验证码登录
    private void LoginByCode() {
        if (TextUtils.isEmpty(et_phoneNumber.getText().toString().trim())) {
            ToastUtils.showShort("手机号码不能为空");
            return;
        }
        if (!ZeroZeroSevenUtils.isMobileNO(et_phoneNumber.getText().toString().trim())) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(et_code.getText().toString().trim())) {
            ToastUtils.showShort("请输入验证码");
            return;
        }
        CodeLoginInfo codeLoginInfo = new CodeLoginInfo();
        codeLoginInfo.setFunctionName("UserAuthcodeLogin");
        CodeLoginInfo.ParametersBean parametersBean = new CodeLoginInfo.ParametersBean();
        parametersBean.setAuthcode(et_code.getText().toString().trim());
        parametersBean.setPhone(et_phoneNumber.getText().toString().trim());
        if (!TextUtils.isEmpty(et_yaoqing.getText().toString().trim())) {
            parametersBean.setInviteCode(et_yaoqing.getText().toString().trim());
        }
        codeLoginInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils3=new OkGoUtils(LoginActivity.this);
        okGoUtils3.httpPostJSON(codeLoginInfo,false,true);
        okGoUtils3.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                try {
                    final UserInfo userInfo1 = JSON.parseObject(response, UserInfo.class);
                    if (userInfo1.getCode() == 0) {
                        UserInfoUtils.saveUserInfo(LoginActivity.this, et_phoneNumber.getText().toString().trim(), "nopwd");
                        BaseAppApplication.getInstance().setLoginUser(userInfo1.getData());
                        SharePrefUtils.saveObject(LoginActivity.this, "userInfo", userInfo1.getData());
                        bindJiGuang(userInfo1.getData().getId());
                    } else {
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showShort(userInfo1.getMessage());
                            }
                        });
                    }

                } catch (Exception e) {
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showShort("服务器异常，请稍后再试");
                        }
                    });
                }
            }
        });
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void doMain() {
        ZeroZeroSevenUtils.setUnderline(this, tv_forgetpwd, "忘记密码？");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.onFinish();
        timer = null;
    }

    public void bindJiGuang(int userId) {
        String registId = JPushInterface.getRegistrationID(this);
        LogUtils.D("JPushInterface", registId);
        BindJiGuangInfo bindJiGuangInfo = new BindJiGuangInfo();
        bindJiGuangInfo.setFunctionName("AddUserDevice");
        BindJiGuangInfo.ParametersBean parametersBean = new BindJiGuangInfo.ParametersBean();
        parametersBean.setPlatform("android");
        parametersBean.setUserId(String.valueOf(userId));
        parametersBean.setClientId(registId);
        bindJiGuangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils4=new OkGoUtils(LoginActivity.this);
        okGoUtils4.httpPostJSON(bindJiGuangInfo,false,true);
        okGoUtils4.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ZeroZeroSevenUtils.SwitchActivity(LoginActivity.this, HomeActivity.class);
                finish();
            }
        });
    }
}
