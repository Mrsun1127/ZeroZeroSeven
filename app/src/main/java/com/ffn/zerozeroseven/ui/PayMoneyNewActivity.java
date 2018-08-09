package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.CommitDingDanInfo;
import com.ffn.zerozeroseven.bean.NumberAliPayInfo;
import com.ffn.zerozeroseven.bean.NumberCommiDingDanInfo;
import com.ffn.zerozeroseven.bean.NumberOrderJsonInfo;
import com.ffn.zerozeroseven.bean.NumberPayInfo;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;
import com.ffn.zerozeroseven.bean.RrunnerPayInfo;
import com.ffn.zerozeroseven.bean.ShouHuoInfo;
import com.ffn.zerozeroseven.bean.WeChatInfo;
import com.ffn.zerozeroseven.bean.requsetbean.CallNewDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.OrderJsonInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
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

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/27.
 */

public class PayMoneyNewActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_money;
    private LinearLayout ll_all;
    ZFBPayUtil mZFbutils;
    public static WeakReference<PayMoneyNewActivity> mInstance;
    private boolean isPaySupported;//判断是否支持微信支付
    private static IWXAPI api;
    private ShouHuoInfo.DataBean.AddressesBean addressesBean;
    private NumberRicalInfo numberRicalInfo;
    private NumberRicalInfo.RicalInfo ricalInfo;
    private RrunnerPayInfo rrunnerPayInfo;
    private String jumpType;

    @Override
    protected int setLayout() {
        return R.layout.activity_paymoney;
    }

    @Override
    protected void doMain() {
        mInstance = new WeakReference<>(this);
        api = WXAPIFactory.createWXAPI(this, "wx189141e4085fa0d1", false);
        api.registerApp("wx189141e4085fa0d1");
        isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        mZFbutils = new ZFBPayUtil(this);
        numberRicalInfo = BaseAppApplication.getInstance().getNumberRicalInfo();
        addressesBean = (ShouHuoInfo.DataBean.AddressesBean) getIntent().getSerializableExtra("adrInfo");
        ricalInfo = (NumberRicalInfo.RicalInfo) getIntent().getSerializableExtra("ricalInfo");
        rrunnerPayInfo = (RrunnerPayInfo) getIntent().getSerializableExtra("runInfo");
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
                    if (isPaySupported) {
                        if (jumpType.equals("numbercar") || jumpType.equals("numberzhijie")) {
                            PayMoney(payment);
                        } else if (jumpType.equals("run")) {
                            RunPay(payment);
                        }
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, "未安装微信客户端", tv_money);
                    }
                } else {
                    if (jumpType.equals("numbercar") || jumpType.equals("numberzhijie")) {
                        PayMoney(payment);
                    } else if (jumpType.equals("run")) {
                        RunPay(payment);
                    }
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

    private void RunPay(final String payment) {
        OkGoUtils okGoUtils = new OkGoUtils(PayMoneyNewActivity.this);
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
                parametersBean.setPayType("FULL");
            }
        } else {
            parametersBean.setPayType("FULL");
        }
        parametersBean.setUserId(Integer.parseInt(userId));
        NumberOrderJsonInfo orderJsonInfo = new NumberOrderJsonInfo();
        orderJsonInfo.setReceiver_address(addressesBean.getContactSchool() + addressesBean.getContactBuilding() + addressesBean.getContactDorm());
        orderJsonInfo.setReceiver_name(addressesBean.getContactName());
        orderJsonInfo.setReceiver_phone(addressesBean.getContactPhone());
        parametersBean.setOrderInfoJson(JSON.toJSONString(orderJsonInfo));
        JSONArray jsonArray = new JSONArray();
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
