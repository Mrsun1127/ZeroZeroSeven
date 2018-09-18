package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RJoinDriverInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ClearEditText;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinDriverSchoolActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.name)
    ClearEditText name;
    @Bind(R.id.phone)
    ClearEditText phone;

    @Override
    protected int setLayout() {
        return R.layout.activity_join_driver;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("我是驾校，入驻零零7帮助");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
    }

    @Override
    protected void doMain() {

    }

    @OnClick({R.id.bt_sub})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_sub:
                sub();
                break;

        }
    }

    private void sub() {
        String driverName = name.getText().toString().trim();
        String driverPhone = phone.getText().toString().trim();
        if(TextUtils.isEmpty(driverName)){
            ToastUtils.showShort("请输入驾校名称");
            return;
        }
        if(TextUtils.isEmpty(driverPhone)){
            ToastUtils.showShort("请输入联系方式");
            return;
        }
        RJoinDriverInfo rJoinDriverInfo = new RJoinDriverInfo();
        rJoinDriverInfo.setFunctionName("AddDrivingApply");
        RJoinDriverInfo.ParametersBean parametersBean = new RJoinDriverInfo.ParametersBean();
        parametersBean.setDrivingName(driverName);
        parametersBean.setPhone(driverPhone);
        rJoinDriverInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(JoinDriverSchoolActivity.this);
        okGoUtils.httpPostJSON(rJoinDriverInfo,true,true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response,ErrorCodeInfo.class);
                if(errorCodeInfo.getCode()==0){
                    ZeroZeroSevenUtils.showCustonPop(JoinDriverSchoolActivity.this,"申请成功，请留意工作人员通知",name,JoinDriverSchoolActivity.this);
                }else {
                    ZeroZeroSevenUtils.showCustonPop(JoinDriverSchoolActivity.this,errorCodeInfo.getMessage(),name);
                }
            }
        });
    }
}
