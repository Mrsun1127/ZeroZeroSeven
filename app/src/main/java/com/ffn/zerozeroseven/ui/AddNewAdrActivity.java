package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.requsetbean.AddAdrInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/27.
 */

public class AddNewAdrActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_adr;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_men;
    private TextView et_lou;
    private LinearLayout ll_addLou;

    @Override
    protected int setLayout() {
        return R.layout.activity_addnewadr;
    }

    @Override
    public void initView() {
        MobclickAgent.onEvent(this, "地址管理");
        BaseAppApplication.getInstance().addActivity(this);
        et_adr = findViewById(R.id.et_adr);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_lou=findViewById(R.id.et_lou);
        et_men=findViewById(R.id.et_men);
        findViewById(R.id.ll_addLou).setOnClickListener(this);
        Button bt_sub = findViewById(R.id.bt_sub);
        bt_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adr = et_adr.getText().toString().trim();
                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String loudong=et_lou.getText().toString();
                String sushe=et_men.getText().toString().trim();
                if (!TextUtils.isEmpty(adr)) {
                    if (!TextUtils.isEmpty(name)) {
                        if (!TextUtils.isEmpty(phone)) {
                            if(!"楼栋号".equals(loudong)){
                                if(!TextUtils.isEmpty(sushe)){
                                    if(ZeroZeroSevenUtils.isMobileNO(phone)){
                                        if(adr.contains("大学")||adr.contains("学院")||adr.contains("学校")){
                                            AddAdr(adr, name, phone,loudong,sushe);
                                        }else{
                                            ToastUtils.showShort("请将学校名称填写正确");
                                        }
                                    }else{
                                        ToastUtils.showShort("请输入正确的手机号码");
                                    }
                                }else{
                                    ToastUtils.showShort("请选择宿舍");
                                }
                            }else{
                                ToastUtils.showShort("请选择楼栋号");
                            }
                        } else {
                            ToastUtils.showShort("请输入手机号码");
                        }
                    } else {
                        ToastUtils.showShort("请输入姓名");
                    }
                } else {
                    ToastUtils.showShort("请输入地址");
                }
            }
        });
    }

    private void AddAdr(String adr, String name, String phone,String loudong,String sushe) {
        showLoadProgress();
        AddAdrInfo addAdrInfo = new AddAdrInfo();
        addAdrInfo.setFunctionName("AddUserAddress");
        AddAdrInfo.ParametersBean parametersBean = new AddAdrInfo.ParametersBean();
        parametersBean.setContactSchool(adr);
        parametersBean.setContactName(name);
        parametersBean.setContactPhone(phone);
        parametersBean.setContactBuilding(loudong);
        parametersBean.setContactDorm(sushe);
//        parametersBean.setBuildingId(id);
        addAdrInfo.setParameters(parametersBean);
        httpPostJSON(addAdrInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                String code = JsonUtil.getFieldValue(response.body().string(), "code");
                if ("0".equals(code)) {
                    finish();
                } else {
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showShort("服务器正忙，请稍后再试");
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void doMain() {
        if(!TextUtils.isEmpty(userInfo.getSchoolName())){
            et_adr.setText(userInfo.getSchoolName());
        }else{
            ToastUtils.showShort("请重新登录");
            finish();
        }
        TitleView titleView = findViewById(R.id.titleview);
        titleView.setTopText("新增地址");
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_addLou:
                ZeroZeroSevenUtils.SwitchActivity(AddNewAdrActivity.this, LouDongActivity.class, null, 0);
                break;
            default:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
               String lou= data.getStringExtra("loudong");
                et_lou.setText(lou);
                break;
            default:

                break;
        }
    }
}
