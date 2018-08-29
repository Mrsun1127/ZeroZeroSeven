package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.bean.WocaoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RRunnerMoneyInfo;
import com.ffn.zerozeroseven.ui.ErrandAuitActivity;
import com.ffn.zerozeroseven.ui.PayMoneyNewActivity;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrandRenzhen2Fragment extends BaseFragment {

    private WocaoInfo wocaoInfo;

    public static ErrandRenzhen2Fragment newInstance() {
        return new ErrandRenzhen2Fragment();
    }


    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected int setLayout() {
        return R.layout.errand_renzheng2;
    }

    @Bind(R.id.cb)
    CheckBox cb;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.tv_money)
    TextView tv_money;

    @OnClick({R.id.bt_next})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                if (cb.isChecked()) {
                    Bundle bundle = new Bundle();
                    try {
                        bundle.putString("money", String.valueOf(wocaoInfo.getData().getErrandCheckFee()));
                    } catch (Exception e) {
                        bundle.putString("money", "99");
                    }
                    bundle.putString("pay", "renzheng");
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, PayMoneyNewActivity.class, bundle);
                }
                break;

        }
    }

    @Override
    protected void lazyLoad() {
        webview.loadUrl(AppConfig.WEBRENZHENGCONTENT);
        doQuest();
    }

    private void doQuest() {
        RRunnerMoneyInfo rRunnerMoneyInfo = new RRunnerMoneyInfo();
        rRunnerMoneyInfo.setFunctionName("QueryErrandCheckFee");
        RRunnerMoneyInfo.ParametersBean parametersBean = new RRunnerMoneyInfo.ParametersBean();
        parametersBean.setSchoolId(schoolIId);
        rRunnerMoneyInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(rRunnerMoneyInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                wocaoInfo = JSON.parseObject(response, WocaoInfo.class);
                if (wocaoInfo.getCode() == 0) {
                    tv_money.setText("平台认证押金" + wocaoInfo.getData().getErrandCheckFee() + "元");
                } else {
                    tv_money.setText("平台认证押金99元");
                }
            }
        });
    }
}
