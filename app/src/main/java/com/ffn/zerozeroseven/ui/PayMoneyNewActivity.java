package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.DriverUserInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.NumberAliPayInfo;
import com.ffn.zerozeroseven.bean.NumberCommiDingDanInfo;
import com.ffn.zerozeroseven.bean.NumberOrderJsonInfo;
import com.ffn.zerozeroseven.bean.NumberPayInfo;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;
import com.ffn.zerozeroseven.bean.RrnzPayInfo;
import com.ffn.zerozeroseven.bean.RrunnerPayInfo;
import com.ffn.zerozeroseven.bean.ShouHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverPayInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZFBPayUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by GT on 2017/11/27.
 */

public class PayMoneyNewActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_money;
    private LinearLayout ll_all;
    ZFBPayUtil mZFbutils;
    public static WeakReference<PayMoneyNewActivity> mInstance;
    private boolean isPaySupported;//判断是否支持微信支付
    private IWXAPI api;
    private ShouHuoInfo.DataBean.AddressesBean addressesBean;
    private NumberRicalInfo numberRicalInfo;
    private NumberRicalInfo.RicalInfo ricalInfo;
    private RrunnerPayInfo rrunnerPayInfo;
    private String jumpType;
    private DriverUserInfo driverUserInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_paymoney;
    }

    @Override
    protected void doMain() {
        mInstance = new WeakReference<>(this);
        api = WXAPIFactory.createWXAPI(getApplicationContext(), "wx189141e4085fa0d1", false);
        api.registerApp("wx189141e4085fa0d1");
        isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        mZFbutils = new ZFBPayUtil(this);
        numberRicalInfo = BaseAppApplication.getInstance().getNumberRicalInfo();
        addressesBean = (ShouHuoInfo.DataBean.AddressesBean) getIntent().getSerializableExtra("adrInfo");
        ricalInfo = (NumberRicalInfo.RicalInfo) getIntent().getSerializableExtra("ricalInfo");
        rrunnerPayInfo = (RrunnerPayInfo) getIntent().getSerializableExtra("runInfo");
        driverUserInfo = (DriverUserInfo) getIntent().getSerializableExtra("driverInfo");
        tv_money.setText("支付金额：" + getIntent().getStringExtra("money") + "RMB");
        jumpType = getIntent().getStringExtra("pay");
    }

    @Override
    public void initView() {
        ll_all = findViewById(R.id.ll_all);
        findViewById(R.id.bt_pay).setOnClickListener(this);
        findViewById(R.id.rb_zfb).setOnClickListener(this);
        findViewById(R.id.rb_wx).setOnClickListener(this);
        tv_money = findViewById(R.id.tv_peoplephone);
        TitleView titleview = findViewById(R.id.titleview);
        titleview.setTopText("支付订单");
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

    String payment = "AliPay";

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_pay:
                if (payment.equals("WechatPay")) {
                    if (!isPaySupported) {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, "未安装微信客户端", tv_money);
                        return;
                    }
                }
                if (jumpType.equals("numbercar") || jumpType.equals("numberzhijie")) {
                    PayMoney(payment);
                } else if (jumpType.equals("run")) {
                    RunPay(payment);
                } else if (jumpType.equals("renzheng")) {
                    VertifyInfo(payment);
                } else if (jumpType.equals("driver")) {
                    DriverPay(payment);
                }
                break;
            case R.id.rb_zfb:
                payment = "AliPay";
                break;
            case R.id.rb_wx:
                payment = "WechatPay";
                break;

        }
    }

    private void DriverPay(final String payment) {
        RDriverPayInfo rDriverPayInfo = new RDriverPayInfo();
        rDriverPayInfo.setFunctionName("AddDrivingOrder");
        RDriverPayInfo.ParametersBean parametersBean = new RDriverPayInfo.ParametersBean();
        parametersBean.setUserId(String.valueOf(BaseAppApplication.getInstance().getLoginUser().getId()));
        parametersBean.setPayment(payment);
        parametersBean.setDrivingId(DrivingDetilsActivity.mInstance.get().getId());
        parametersBean.setDrivingName(DrivingDetilsActivity.mInstance.get().getName());
        parametersBean.setClassId(getIntent().getStringExtra("classId"));
        parametersBean.setIdcard(driverUserInfo.idCard);
        parametersBean.setApplicantName(driverUserInfo.name);
        parametersBean.setApplicantAddress("adr");
        parametersBean.setApplicantPhone(driverUserInfo.phoneNumber);
        parametersBean.setTotalPrice(driverUserInfo.money);
        rDriverPayInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(PayMoneyNewActivity.this);
        okGoUtils.httpPostJSON(rDriverPayInfo,true,true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                BaseAppApplication.clearType = "driver";
                if (payment.equals("AliPay")) {
                    NumberAliPayInfo aliPayInfo = JSON.parseObject(response, NumberAliPayInfo.class);
                    if (aliPayInfo.getCode() == 0) {
                        mZFbutils.pay(aliPayInfo.getData().getBody(), "driver");
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, aliPayInfo.getMessage(), ll_all);
                    }
                } else {
                    NumberPayInfo numberPayInfo = JSON.parseObject(response, NumberPayInfo.class);
                    if (numberPayInfo.getCode() == 0) {
                        PayReq req = new PayReq();
                        req.appId = numberPayInfo.getData().getAppid();
                        req.partnerId = numberPayInfo.getData().getPartnerid();
                        req.prepayId = numberPayInfo.getData().getPrepayid();
                        req.nonceStr = numberPayInfo.getData().getNoncestr();
                        req.timeStamp = numberPayInfo.getData().getTimestamp();
                        req.packageValue = "Sign=WXPay";
                        req.sign = numberPayInfo.getData().getSign();
                        api.sendReq(req);
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, numberPayInfo.getMessage(), ll_all);
                    }
                }
            }
        });
    }

    private void VertifyInfo(final String payment) {
        OkGoUtils okGoUtils = new OkGoUtils(PayMoneyNewActivity.this);
        okGoUtils.httpPostJSON(ErrandAuitActivity.mInstance.get().getRrenzhengInfo(), true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    RenzhengPay(payment);
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }
            }
        });
    }

    private void RenzhengPay(final String payment) {
        RrnzPayInfo rrnzPayInfo = new RrnzPayInfo();
        rrnzPayInfo.setFunctionName("AddErrandUserCheckOrder");
        RrnzPayInfo.ParametersBean parametersBean = new RrnzPayInfo.ParametersBean();
        parametersBean.setPayment(payment);
        parametersBean.setUserId(userId);
        rrnzPayInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(PayMoneyNewActivity.this);
        okGoUtils.httpPostJSON(rrnzPayInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                BaseAppApplication.clearType = "renzheng";
                if (payment.equals("AliPay")) {
                    NumberAliPayInfo aliPayInfo = JSON.parseObject(response, NumberAliPayInfo.class);
                    if (aliPayInfo.getCode() == 0) {
                        mZFbutils.pay(aliPayInfo.getData().getBody(), "runpay");
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, aliPayInfo.getMessage(), ll_all);
                    }
                } else {
                    NumberPayInfo numberPayInfo = JSON.parseObject(response, NumberPayInfo.class);
                    if (numberPayInfo.getCode() == 0) {
                        PayReq req = new PayReq();
                        req.appId = numberPayInfo.getData().getAppid();
                        req.partnerId = numberPayInfo.getData().getPartnerid();
                        req.prepayId = numberPayInfo.getData().getPrepayid();
                        req.nonceStr = numberPayInfo.getData().getNoncestr();
                        req.timeStamp = numberPayInfo.getData().getTimestamp();
                        req.packageValue = "Sign=WXPay";
                        req.sign = numberPayInfo.getData().getSign();
                        api.sendReq(req);
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, numberPayInfo.getMessage(), ll_all);
                    }
                }
            }
        });
    }

    private void RunPay(final String payment) {
        OkGoUtils okGoUtils = new OkGoUtils(PayMoneyNewActivity.this);
        rrunnerPayInfo.getParameters().setPayment(payment);
        okGoUtils.httpPostJSON(rrunnerPayInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                BaseAppApplication.clearType = "run";
                if (payment.equals("AliPay")) {
                    NumberAliPayInfo aliPayInfo = JSON.parseObject(response, NumberAliPayInfo.class);
                    if (aliPayInfo.getCode() == 0) {
                        mZFbutils.pay(aliPayInfo.getData().getBody(), "runpay");
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, aliPayInfo.getMessage(), ll_all);
                    }
                } else {
                    NumberPayInfo numberPayInfo = JSON.parseObject(response, NumberPayInfo.class);
                    if (numberPayInfo.getCode() == 0) {
                        PayReq req = new PayReq();
                        req.appId = numberPayInfo.getData().getAppid();
                        req.partnerId = numberPayInfo.getData().getPartnerid();
                        req.prepayId = numberPayInfo.getData().getPrepayid();
                        req.nonceStr = numberPayInfo.getData().getNoncestr();
                        req.timeStamp = numberPayInfo.getData().getTimestamp();
                        req.packageValue = "Sign=WXPay";
                        req.sign = numberPayInfo.getData().getSign();
                        api.sendReq(req);
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, numberPayInfo.getMessage(), ll_all);
                    }
                }
            }
        });
    }


    private void PayMoney(final String str) {
        NumberCommiDingDanInfo numberCommiDingDanInfo = new NumberCommiDingDanInfo();
        numberCommiDingDanInfo.setFunctionName("AddDigitalGoodsOrder");
        NumberCommiDingDanInfo.ParametersBean parametersBean = new NumberCommiDingDanInfo.ParametersBean();
        parametersBean.setPayment(str);
        if (ricalInfo != null) {
            if (ricalInfo.getType() == 0) {
                parametersBean.setPayType("DEPOSIT");
            } else {
                parametersBean.setPayType("DEPOSIT");
            }
        } else {
            parametersBean.setPayType("DEPOSIT");
        }
        parametersBean.setUserId(Integer.parseInt(userId));
        NumberOrderJsonInfo orderJsonInfo = new NumberOrderJsonInfo();
        orderJsonInfo.setReceiver_address(addressesBean.getContactSchool() + addressesBean.getContactBuilding() + addressesBean.getContactDorm());
        orderJsonInfo.setReceiver_name(addressesBean.getContactName());
        orderJsonInfo.setReceiver_phone(addressesBean.getContactPhone());
        orderJsonInfo.setSchoolId(schoolIId);
        parametersBean.setOrderInfoJson(JSON.toJSONString(orderJsonInfo));
        JSONArray jsonArray = new JSONArray();
        if (jumpType.equals("numberzhijie")) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("goodsId", ricalInfo.getId());
                jsonObject.put("goods_name", ricalInfo.getName());
                jsonObject.put("goods_count", ricalInfo.getCount());
                jsonObject.put("specKey", ricalInfo.getSpecId());
                jsonObject.put("specKeyName", ricalInfo.getConfiguration());
                jsonArray.put(jsonObject);
            } catch (Exception e) {
            }
        } else {
            for (int i = 0; i < numberRicalInfo.getNumberRicalListInfo().size(); i++) {
                try {
                    if (numberRicalInfo.getNumberRicalListInfo().get(i).isChecked()) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("goodsId", numberRicalInfo.getNumberRicalListInfo().get(i).getId());
                        jsonObject.put("goods_name", numberRicalInfo.getNumberRicalListInfo().get(i).getName());
                        jsonObject.put("goods_count", numberRicalInfo.getNumberRicalListInfo().get(i).getCount());
                        jsonObject.put("specKey", numberRicalInfo.getNumberRicalListInfo().get(i).getSpecId());
                        jsonObject.put("specKeyName", numberRicalInfo.getNumberRicalListInfo().get(i).getConfiguration());
                        jsonArray.put(jsonObject);
                    }
                } catch (Exception e) {
                }
            }
        }
        parametersBean.setOrderGoodsJson(jsonArray.toString());
        numberCommiDingDanInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(PayMoneyNewActivity.this);
        okGoUtils.httpPostJSON(numberCommiDingDanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                BaseAppApplication.clearType = getIntent().getStringExtra("pay");
                if (str.equals("AliPay")) {
                    NumberAliPayInfo aliPayInfo = JSON.parseObject(response, NumberAliPayInfo.class);
                    if (aliPayInfo.getCode() == 0) {
                        mZFbutils.pay(aliPayInfo.getData().getBody(), "支付尾款");
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, aliPayInfo.getMessage(), ll_all);
                    }
                } else {
                    NumberPayInfo numberPayInfo = JSON.parseObject(response, NumberPayInfo.class);
                    if (numberPayInfo.getCode() == 0) {
                        PayReq req = new PayReq();
                        req.appId = numberPayInfo.getData().getAppid();
                        req.partnerId = numberPayInfo.getData().getPartnerid();
                        req.prepayId = numberPayInfo.getData().getPrepayid();
                        req.nonceStr = numberPayInfo.getData().getNoncestr();
                        req.timeStamp = numberPayInfo.getData().getTimestamp();
                        req.packageValue = "Sign=WXPay";
                        req.sign = numberPayInfo.getData().getSign();
                        api.sendReq(req);
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, numberPayInfo.getMessage(), ll_all);
                    }
                }
            }
        });

    }
}
