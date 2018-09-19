package com.ffn.zerozeroseven.ui;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.DriverDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverDetilsInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DriverOrderDetilsActivity extends BaseActivity {

    private DriverDetilsInfo driverDetilsInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_driver_order_detils;
    }

    @Bind(R.id.topView)
    TopView topView;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("订单详情");
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
        requestDate(getIntent().getStringExtra("orderId"));
    }

    private void requestDate(String orderId) {
        RDriverDetilsInfo rDriverDetilsInfo = new RDriverDetilsInfo();
        rDriverDetilsInfo.setFunctionName("QueryDrivingOrder");
        RDriverDetilsInfo.ParametersBean parametersBean = new RDriverDetilsInfo.ParametersBean();
        parametersBean.setDrivingId(orderId);
        rDriverDetilsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(DriverOrderDetilsActivity.this);
        okGoUtils.httpPostJSON(rDriverDetilsInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                driverDetilsInfo = JSON.parseObject(response, DriverDetilsInfo.class);
                if (driverDetilsInfo.getCode() == 0) {

                }
            }
        });
    }
}
