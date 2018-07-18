package com.ffn.zerozeroseven.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.requsetbean.CancelOrderInfo;
import com.ffn.zerozeroseven.ui.PayMoneyActivity;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.view.TitleView;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;
    private TitleView titleView;
    private ImageView iv_success;
    private TextView tv_bottom;
    private Button bt_sub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commiterror);
        api = WXAPIFactory.createWXAPI(this, "wx189141e4085fa0d1");
        api.handleIntent(getIntent(), this);
        iv_success = findViewById(R.id.iv_success);
        tv_bottom = findViewById(R.id.tv_bottom);
        bt_sub = findViewById(R.id.bt_sub);
        titleView = findViewById(R.id.titleview);
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
        findViewById(R.id.bt_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(final BaseResp resp) {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.D("payResult", resp.getType() + "");
                if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
                    int errCode = resp.errCode;
                    if (errCode == -1) {
                        tv_bottom.setText("支付异常，请稍后再试");
                        titleView.setTopText("支付异常");
                        iv_success.setBackgroundResource(R.drawable.empty_box_icon);
                        bt_sub.setText("确定");
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                cancelPay();
                            }
                        });
                    } else if (errCode == 0) {
                        titleView.setTopText("支付成功");
                        tv_bottom.setText("零零七正带着您的宝贝，火速赶往您身边！");
                        bt_sub.setText("完成");
                        iv_success.setBackgroundResource(R.drawable.success);
                        CarShopInfo carShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
                        carShopInfo.getShopInfos().clear();
                        BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
                        SharePrefUtils.saveObject(WXPayEntryActivity.this, "carShopInfo", BaseAppApplication.getInstance().getCarShopInfo());
                        PayMoneyActivity.mInstance.get().finish();
                    } else {
                        tv_bottom.setText("支付异常，请稍后再试");
                        titleView.setTopText("支付异常");
                        iv_success.setBackgroundResource(R.drawable.empty_box_icon);
                        bt_sub.setText("确定");
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                cancelPay();
                            }
                        });
                    }

                }
            }
        });
    }

    private void cancelPay() {
        CancelOrderInfo cancelOrderInfo = new CancelOrderInfo();
        cancelOrderInfo.setFunctionName("CancelOrderPay");
        OkGoUtils okGoUtils = new OkGoUtils(WXPayEntryActivity.this);
        okGoUtils.httpPostJSON(cancelOrderInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
            }
        });
    }
}