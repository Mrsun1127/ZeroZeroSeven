package com.ffn.zerozeroseven.ui;

import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.DriverDetilsInfo;
import com.ffn.zerozeroseven.bean.DriverOrderDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverOrderInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DriverOrderDetilsActivity extends BaseActivity {

    private DriverOrderDetilsInfo driverDetilsInfo;

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

    @Bind(R.id.tv_driverName)
    TextView tv_driverName;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_money)
    TextView tv_money;
    @Bind(R.id.tv_pay)
    TextView tv_pay;

    private void requestDate(String orderId) {
        RDriverOrderInfo rDriverDetilsInfo = new RDriverOrderInfo();
        rDriverDetilsInfo.setFunctionName("QueryDrivingOrder");
        RDriverOrderInfo.ParametersBean parametersBean = new RDriverOrderInfo.ParametersBean();
        parametersBean.setOrderId(orderId);
        rDriverDetilsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(DriverOrderDetilsActivity.this);
        okGoUtils.httpPostJSON(rDriverDetilsInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                driverDetilsInfo = JSON.parseObject(response, DriverOrderDetilsInfo.class);
                if (driverDetilsInfo.getCode() == 0) {
                    tv_driverName.setText(driverDetilsInfo.getData().getDrivingOrder().getDrivingName());
                    tv_name.setText(driverDetilsInfo.getData().getDrivingOrder().getApplicantName());
                    tv_phone.setText(driverDetilsInfo.getData().getDrivingOrder().getApplicantPhone());
                    tv_time.setText(driverDetilsInfo.getData().getDrivingOrder().getCreateTime());
                    tv_pay.setText(driverDetilsInfo.getData().getDrivingOrder().getPayment());
                    tv_money.setText(String.valueOf(driverDetilsInfo.getData().getDrivingOrder().getTotalPrice()));

                }
            }
        });
    }
}
