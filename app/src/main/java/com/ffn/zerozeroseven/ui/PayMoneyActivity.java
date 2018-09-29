package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
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
import com.ffn.zerozeroseven.bean.NumberPayInfo;
import com.ffn.zerozeroseven.bean.OrderLeaseJsonInfo;
import com.ffn.zerozeroseven.bean.WeChatInfo;
import com.ffn.zerozeroseven.bean.requsetbean.CallNewDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NumberWeiKuanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.OrderJsonInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RleaseXiaDanInfo;
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
import com.umeng.analytics.MobclickAgent;

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

public class PayMoneyActivity extends BaseActivity implements View.OnClickListener {
    private CarShopInfo carShopInfo;
    private TextView tv_money;
    private LinearLayout ll_all;
    private String dormId;
    ZFBPayUtil mZFbutils;
    public static WeakReference<PayMoneyActivity> mInstance;
    private String payType;
    private boolean isPaySupported;//判断是否支持微信支付
    private IWXAPI api;
    private String reMark;
    private String orderNO;
    private String carType;

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
        orderNO = getIntent().getStringExtra("parentOrderNo");
        double allMoney = getIntent().getDoubleExtra("allMoney", 0);
        if (!TextUtils.isEmpty(orderNO)) {
            tv_money.setText("支付金额：" +allMoney + "RMB");
        } else {
            payType = getIntent().getStringExtra("pay");
            carType = getIntent().getStringExtra("carType");
            dormId = getIntent().getStringExtra("dormId");
            reMark = getIntent().getStringExtra("beizhu");
            if ("carpay".equals(payType)) {
                MobclickAgent.onEvent(this, "购物车结算");
                if ("shop".equals(carType)) {
                    carShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
                } else if ("food".equals(carType)) {
                    carShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
                } else if ("lease".equals(carType)) {
                    carShopInfo = BaseAppApplication.getInstance().getLeasecarShopInfo();
                }
                if (carShopInfo == null) {
                    ToastUtils.showShort("支付异常 请稍后再试！");
                    finish();
                }
                tv_money.setText("支付金额：" + allMoney + "RMB");
            } else if ("zhijie".equals(payType)) {
                MobclickAgent.onEvent(this, "立即购买");
                carShopInfo = (CarShopInfo) SharePrefUtils.readObject(PayMoneyActivity.this, "zhijiecarShopInfo");
                if (carShopInfo == null) {
                    ToastUtils.showShort("支付异常 请稍后再试！");
                    finish();
                }
                tv_money.setText("支付金额：" + allMoney + "RMB");
            }
        }
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
                        PayMoney(payment);
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyActivity.this, "未安装微信客户端", tv_money);
                    }
                } else {
                    PayMoney(payment);
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


    private void PayMoney(final String str) {
        if (!TextUtils.isEmpty(getIntent().getStringExtra("who")) && "lease".equals(getIntent().getStringExtra("who"))) {
            leasePay(str);
        } else {
            if (!TextUtils.isEmpty(orderNO)) {
                NumberWeiKuanPay(str);
            } else {
                lingshiBuy(str, carType);
            }
        }


    }

    private void leasePay(final String str) {
        RleaseXiaDanInfo rleaseXiaDanInfo = new RleaseXiaDanInfo();
        rleaseXiaDanInfo.setFunctionName("AddLeaseGoodsOrder");
        RleaseXiaDanInfo.ParametersBean parametersBean = new RleaseXiaDanInfo.ParametersBean();
        parametersBean.setPayment(str);
        parametersBean.setUserId(userId);
        OrderLeaseJsonInfo orderJsonInfo = new OrderLeaseJsonInfo();
        orderJsonInfo.setReceivingAddress(getIntent().getStringExtra("adr"));
        orderJsonInfo.setDeliveryName(getIntent().getStringExtra("name"));
        orderJsonInfo.setReceivingPhone(getIntent().getStringExtra("phone"));
        orderJsonInfo.setSchoolId(schoolIId);
        if (!TextUtils.isEmpty(reMark)) {
            orderJsonInfo.setPostscript(reMark);
        }
        if (TextUtils.isEmpty(userId)) {
            ToastUtils.showShort("请重新登陆");
            return;
        }
        String orderJson = JSON.toJSONString(orderJsonInfo);
        parametersBean.setOrderInfoJson(orderJson);
        try {
            JSONArray json = new JSONArray();
            for (int i = 0; i < carShopInfo.getShopInfos().size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("goodsCount", carShopInfo.getShopInfos().get(i).getBuyCount());
                jsonObject.put("goodsId", carShopInfo.getShopInfos().get(i).getGoodsId());
                json.put(jsonObject);
            }
            String s = json.toString();
            LogUtils.D("PayMoneyActivity", s);
            parametersBean.setOrderGoodsJson(s);

        } catch (Exception e) {

        }
        rleaseXiaDanInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(PayMoneyActivity.this);
        okGoUtils.httpPostJSON(rleaseXiaDanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                BaseAppApplication.clearType = getIntent().getStringExtra("carType");
                if (str.equals("WechatPay")) {
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
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyActivity.this, numberPayInfo.getMessage(), ll_all);
                    }
                } else {
                    NumberAliPayInfo aliPayInfo = JSON.parseObject(response, NumberAliPayInfo.class);
                    if (aliPayInfo.getCode() == 0) {
                        mZFbutils.pay(aliPayInfo.getData().getBody(), "电脑租赁");
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyActivity.this, aliPayInfo.getMessage(), ll_all);
                    }
                }
            }
        });
    }

    private void NumberWeiKuanPay(final String str) {
        NumberWeiKuanInfo numberWeiKuanInfo = new NumberWeiKuanInfo();
        numberWeiKuanInfo.setFunctionName("AddDigitalGoodsBalanceOrder");
        NumberWeiKuanInfo.ParametersBean parametersBean = new NumberWeiKuanInfo.ParametersBean();
        parametersBean.setParentOrderNo(orderNO);
        parametersBean.setUserId(userId);
        parametersBean.setPayment(str);
        numberWeiKuanInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(PayMoneyActivity.this);
        okGoUtils.httpPostJSON(numberWeiKuanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                BaseAppApplication.clearType = getIntent().getStringExtra("pay");
                if (str.equals("WechatPay")) {
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
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyActivity.this, numberPayInfo.getMessage(), ll_all);
                    }
                } else {
                    NumberAliPayInfo aliPayInfo = JSON.parseObject(response, NumberAliPayInfo.class);
                    if (aliPayInfo.getCode() == 0) {
                        mZFbutils.pay(aliPayInfo.getData().getBody(), "支付尾款");
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyActivity.this, aliPayInfo.getMessage(), ll_all);
                    }
                }

            }
        });
    }

    private void lingshiBuy(final String str, final String carType) {
        CallNewDingDanInfo callNewDingDanInfo = new CallNewDingDanInfo();
        callNewDingDanInfo.setFunctionName("PayGoodsOrder");
        CallNewDingDanInfo.ParametersBean parametersBean1 = new CallNewDingDanInfo.ParametersBean();
        parametersBean1.setPayment(str);
        if ("shop".equals(carType)) {
            parametersBean1.setCate("ZH");
            LogUtils.D("carType", "shop");
        } else if ("food".equals(carType)) {
            parametersBean1.setCate("WM");
            LogUtils.D("carType", "food");
        } else if ("zhijie".equals(carType)) {
            if ("WM".equals(getIntent().getStringExtra("payCate"))) {
                parametersBean1.setCate("WM");
                LogUtils.D("carType", "zhijieWM");
            } else {
                parametersBean1.setCate("ZH");
                LogUtils.D("carType", "zhijieZH");
            }
        }
        parametersBean1.setTradeType("APP");
        OrderJsonInfo orderJsonInfo = new OrderJsonInfo();
        if (!TextUtils.isEmpty(reMark)) {
            orderJsonInfo.setRemark(reMark);
        }
        orderJsonInfo.setBuildingName(dormId);
        orderJsonInfo.setReceiverAddress(getIntent().getStringExtra("adr"));
        orderJsonInfo.setReceiverName(getIntent().getStringExtra("name"));
        orderJsonInfo.setReceiverPhone(getIntent().getStringExtra("phone"));
        if (!TextUtils.isEmpty(carShopInfo.getShopInfos().get(0).getShopId())) {
            orderJsonInfo.setStoreId(Integer.parseInt(carShopInfo.getShopInfos().get(0).getShopId()));
        }
        orderJsonInfo.setSchoolId(schoolIId);
        if (TextUtils.isEmpty(userId)) {
            ToastUtils.showShort("请重新登陆");
            return;
        }
        orderJsonInfo.setUserId(Integer.parseInt(userId));
        String orderJson = JSON.toJSONString(orderJsonInfo);
        parametersBean1.setOrderJsonStr(orderJson);
        try {
            JSONArray json = new JSONArray();
            for (int i = 0; i < carShopInfo.getShopInfos().size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("goodsCount", carShopInfo.getShopInfos().get(i).getBuyCount());
                jsonObject.put("goodsId", carShopInfo.getShopInfos().get(i).getGoodsId());
                jsonObject.put("userId", userId);
                jsonObject.put("goodsName", carShopInfo.getShopInfos().get(i).getShopName());
//                jsonObject.put("goodsType", "03");
                jsonObject.put("goodsPrice", carShopInfo.getShopInfos().get(i).getShopMoney());
                json.put(jsonObject);
            }
            String s = json.toString();
            LogUtils.D("PayMoneyActivity", s);
            parametersBean1.setOrderDetailJsonStr(s);

        } catch (Exception e) {

        }
        callNewDingDanInfo.setParameters(parametersBean1);
        OkGoUtils okGoUtils = new OkGoUtils(PayMoneyActivity.this);
        okGoUtils.httpPostJSON(callNewDingDanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(final String response) {
                if ("shop".equals(carType)) {
                    BaseAppApplication.clearType = getIntent().getStringExtra("pay");
                } else if ("food".equals(carType)) {
                    BaseAppApplication.clearType = getIntent().getStringExtra("carType");
                }
                if (str.equals("AliPay")) {//支付宝支付
                    final CommitDingDanInfo commitDingDanInfo = JSON.parseObject(response, CommitDingDanInfo.class);
                    if (commitDingDanInfo.getCode() == 0) {
                        mZFbutils.pay(commitDingDanInfo.getData().getBody(), "支付零食");
                    } else if (commitDingDanInfo.getCode() == -101) {
                        ZeroZeroSevenUtils.showSleepPop(PayMoneyActivity.this, ll_all);
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyActivity.this, commitDingDanInfo.getMessage(), ll_all);
                    }
                } else {
                    final WeChatInfo wxPayInfo = JSON.parseObject(response, WeChatInfo.class);
                    if (wxPayInfo.getCode() == 0) {
                        PayReq req = new PayReq();
                        req.appId = wxPayInfo.getData().getAppid();
                        req.partnerId = wxPayInfo.getData().getPartnerid();
                        req.prepayId = wxPayInfo.getData().getPrepayid();
                        req.nonceStr = wxPayInfo.getData().getNoncestr();
                        req.timeStamp = wxPayInfo.getData().getTimestamp();
                        req.packageValue = "Sign=WXPay";
                        req.sign = wxPayInfo.getData().getSign();
                        api.sendReq(req);
                    } else if (wxPayInfo.getCode() == -101) {
                        ZeroZeroSevenUtils.showSleepPop(PayMoneyActivity.this, ll_all);
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyActivity.this, wxPayInfo.getMessage(), ll_all);

                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInstance = null;
    }
}
