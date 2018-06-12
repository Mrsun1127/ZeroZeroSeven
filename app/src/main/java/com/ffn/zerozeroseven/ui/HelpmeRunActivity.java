package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RleaseRunInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/22.
 */

public class HelpmeRunActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_all;
    @Bind(R.id.et_getAdr)
    EditText et_getAdr;
    @Bind(R.id.et_arvAdr)
    EditText et_arvAdr;
    @Bind(R.id.et_type)
    EditText et_type;
    @Bind(R.id.et_weight)
    EditText et_weight;
    @Bind(R.id.et_money)
    EditText et_money;
    @Bind(R.id.et_remark)
    EditText et_remark;
    @Bind(R.id.et_hotal)
    EditText et_hotal;
    @Bind(R.id.et_phone)
    EditText et_phone;
    @Bind(R.id.et_other)
    EditText et_other;
    @Bind(R.id.et_smallMoney)
    EditText et_smallMoney;

    @Override
    protected int setLayout() {
        return R.layout.activity_helpmerun;
    }

    @Override
    protected void doMain() {
        ButterKnife.bind(this);
        findViewById(R.id.bt_sub).setOnClickListener(this);
        TitleView titleView = findViewById(R.id.titleview);
        ll_all = findViewById(R.id.ll_all);
        titleView.setTopText("帮我跑腿");
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
        String dongxi=getIntent().getStringExtra("type");
        et_type.setText(dongxi);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sub:
                releaseRun();
                break;
            default:

                break;
        }
    }

    private void releaseRun() {
        String runType=et_type.getText().toString().trim();
        String getAdr=et_getAdr.getText().toString().trim();
        String arrAdr=et_arvAdr.getText().toString().trim();
        String goodsWeight=et_weight.getText().toString().trim();
        String goodsMoney=et_money.getText().toString().trim();
        String getGoodsTime=et_remark.getText().toString().trim();
        String hotal=et_hotal.getText().toString().trim();
        String phone=et_phone.getText().toString().trim();
        String other=et_other.getText().toString().trim();
        String smallMoney=et_smallMoney.getText().toString().trim();
        if(!TextUtils.isEmpty(runType)){
            if(!TextUtils.isEmpty(getAdr)){
                if(!TextUtils.isEmpty(arrAdr)){
                    if(!TextUtils.isEmpty(goodsWeight)){
                        if(!TextUtils.isEmpty(goodsMoney)){
                            if(!TextUtils.isEmpty(getGoodsTime)){
                                if(!TextUtils.isEmpty(hotal)){
                                    if(!TextUtils.isEmpty(phone)){
                                        requestData(runType,getAdr,arrAdr,goodsWeight,goodsMoney,getGoodsTime,hotal,phone,other,smallMoney);
                                    }else{
                                        ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,"手机号码不能为空",et_arvAdr);
                                    }
                                }else{
                                    ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,"快递公司不能为空",et_arvAdr);
                                }
                            }else{
                                ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,"取件时间不能为空",et_arvAdr);
                            }
                        }else{
                            ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,"物品价值不能为空",et_arvAdr);
                        }
                    }else{
                        ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,"物品重量不能为空",et_arvAdr);
                    }
                }else{
                    ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,"送达地址不能为空",et_arvAdr);
                }
            }else{
                ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,"取件地址不能为空",et_arvAdr);
            }
        }else{
            ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,"跑腿事项不能为空",et_arvAdr);
        }
    }

    private void requestData(String runType, String getAdr, String arrAdr, String goodsWeight, String goodsMoney, String getGoodsTime, String hotal, String phone, String other, String smallMoney) {
        showLoadProgress();
        RleaseRunInfo rleaseRunInfo=new RleaseRunInfo();
        rleaseRunInfo.setFunctionName("AddErrandOrder");
        RleaseRunInfo.ParametersBean parametersBean=new RleaseRunInfo.ParametersBean();
        parametersBean.setType(runType);
        parametersBean.setSchoolId(schoolIId);
        parametersBean.setPickupAddress(getAdr);
        parametersBean.setDeliverAddress(arrAdr);
        parametersBean.setWeight(goodsWeight);
        parametersBean.setPrice(goodsMoney);
        parametersBean.setPrice(goodsMoney);
        parametersBean.setPickupTime(getGoodsTime);
        parametersBean.setExpressCompany(hotal);
        parametersBean.setCourierPhone(phone);
        if(!TextUtils.isEmpty(smallMoney)){
            parametersBean.setFee(smallMoney);
        }
        rleaseRunInfo.setParameters(parametersBean);
        httpPostJSON(rleaseRunInfo,true);
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
            public void onResponse(Call call, final Response response) throws IOException {
                final ErrorCodeInfo errorCodeInfo= JSON.parseObject(response.body().string(),ErrorCodeInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if(errorCodeInfo.getCode()==0){
                            ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,"发布成功",et_arvAdr);
                        }else{
                            ZeroZeroSevenUtils.showCustonPop(HelpmeRunActivity.this,errorCodeInfo.getMessage(),et_arvAdr);
                        }
                    }
                });
            }
        });
    }
}
