package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baoyz.actionsheet.ActionSheet;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.DriverDetilsInfo;
import com.ffn.zerozeroseven.bean.DriverOrderDetilsInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverOrderInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverTuiKuanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RsnackTuikuanInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverOrderDetilsActivity extends BaseActivity implements ActionSheet.ActionSheetListener {

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
    @Bind(R.id.tv_idcard)
    TextView tv_idcard;
    @Bind(R.id.tv_status)
    TextView tv_status;
    @Bind(R.id.bt_show)
    Button bt_show;
    String[] items = new String[]{"还没想好，不想支付了", "服务质量不满意", "其他"};

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
                    tv_idcard.setText(driverDetilsInfo.getData().getDrivingOrder().getIdcard());
                    tv_money.setText(String.valueOf(driverDetilsInfo.getData().getDrivingOrder().getTotalPrice()));
                    if (driverDetilsInfo.getData().getDrivingOrder().getStatus() == 1 && driverDetilsInfo.getData().getDrivingOrder().getIsSignUp() == 1) {
                        tv_status.setText("驾校已受理，请带上身份证，前往驾校登记");
                    } else if (driverDetilsInfo.getData().getDrivingOrder().getStatus() == 1 && driverDetilsInfo.getData().getDrivingOrder().getIsSignUp() == 0) {
                        tv_status.setText("驾校后台已响应，等待工作人员确认");
                    } else {
                        switch (driverDetilsInfo.getData().getDrivingOrder().getStatus()) {
                            case -1:
                                tv_status.setText("退款失败:" + driverDetilsInfo.getData().getDrivingOrder().getRefuseReason());
                                break;
                            case 2:
                                bt_show.setVisibility(View.GONE);
                                tv_status.setText("您的订单已取消，工作人员正在审核（大概工作日1-7天）");
                                break;
                            case 3:
                                tv_status.setText("您的订单已取消，并成功退款");
                                bt_show.setVisibility(View.GONE);
                                break;
                        }
                    }
                    //-1=退款失败，0=未付款，1=已付款，2=取消订单，3=退款成功
                }
            }
        });
    }

    @OnClick({R.id.bt_show})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_show:
                ActionSheet.createBuilder(DriverOrderDetilsActivity.this, getSupportFragmentManager())
                        .setCancelButtonTitle("取消")
                        .setOtherButtonTitles(items[0], items[1], items[2])
                        .setCancelableOnTouchOutside(true)
                        .setListener(DriverOrderDetilsActivity.this).show();
                break;

        }
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        actionSheet.dismiss();
        TuiKuan(items[index]);
    }

    private void TuiKuan(String item) {
        RDriverTuiKuanInfo rDriverTuiKuanInfo = new RDriverTuiKuanInfo();
        rDriverTuiKuanInfo.setFunctionName("DrivingOrderRefund");
        RDriverTuiKuanInfo.ParametersBean parametersBean = new RDriverTuiKuanInfo.ParametersBean();
        parametersBean.setOrderId(getIntent().getStringExtra("orderId"));
        parametersBean.setReason(item);
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        rDriverTuiKuanInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(DriverOrderDetilsActivity.this);
        okGoUtils.httpPostJSON(rDriverTuiKuanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    requestDate(getIntent().getStringExtra("orderId"));
                    ZeroZeroSevenUtils.showCustonPop(DriverOrderDetilsActivity.this, "您的退款已经申请，工作人员正在审核，请留意订单状态通知", tv_name);
                } else {
                    ZeroZeroSevenUtils.showCustonPop(DriverOrderDetilsActivity.this, errorCodeInfo.getMessage(), tv_name);
                }
            }
        });
    }
}
