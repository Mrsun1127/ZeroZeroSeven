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
import com.ffn.zerozeroseven.bean.WeChatInfo;
import com.ffn.zerozeroseven.bean.requsetbean.CallNewDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.OrderJsonInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
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
    private CarShopInfo carShopInfo;
    private TextView tv_money;
    private LinearLayout ll_all;
    private String dormId;
    ZFBPayUtil mZFbutils;
    public static WeakReference<PayMoneyNewActivity> mInstance;
    private String payType;
    private boolean isPaySupported;//判断是否支持微信支付
    private static IWXAPI api;

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
        double allMoney = getIntent().getDoubleExtra("allMoney", 0);
        payType = getIntent().getStringExtra("pay");
        dormId = getIntent().getStringExtra("dormId");
        tv_money.setText("支付金额：" + 2000 + "RMB");
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
                        ZeroZeroSevenUtils.showCustonPop(PayMoneyNewActivity.this, "未安装微信客户端", tv_money);
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

    }
}
