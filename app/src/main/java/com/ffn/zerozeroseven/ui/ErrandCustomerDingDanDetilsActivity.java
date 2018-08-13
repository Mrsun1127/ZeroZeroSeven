package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.RunnerDingDanDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RrmineRunDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SureGetInfo;
import com.ffn.zerozeroseven.bean.requsetbean.TuiKUanoInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ErrandCustomerDingDanDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    private String orderNo;

    @Override
    protected int setLayout() {
        return R.layout.activity_errand_customer_detils;
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);
        topView.setTopText("跑腿详情");
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
        orderNo = getIntent().getStringExtra("orderNo");
        requestOrder(orderNo);
    }

    @Bind(R.id.tv_status)
    TextView tv_status;
    @Bind(R.id.tv_runneerMan)
    TextView tv_runneerMan;
    @Bind(R.id.tv_manyidu)
    TextView tv_manyidu;
    @Bind(R.id.bt_left)
    Button bt_left;
    @Bind(R.id.bt_right)
    Button bt_right;
    @Bind(R.id.tv_createtime)
    TextView tv_createtime;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.tv_shipeeFee)
    TextView tv_shipeeFee;
    @Bind(R.id.tv_letadr)
    TextView tv_letadr;
    @Bind(R.id.tv_letinfo)
    TextView tv_letinfo;
    @Bind(R.id.tv_getadr)
    TextView tv_getadr;
    @Bind(R.id.tv_getinfo)
    TextView tv_getinfo;
    @Bind(R.id.tv_weight)
    TextView tv_weight;
    @Bind(R.id.tv_runMoney)
    TextView tv_runMoney;
    @Bind(R.id.tv_picktime)
    TextView tv_picktime;
    @Bind(R.id.tv_level)
    TextView tv_level;
    @Bind(R.id.tv_remark)
    TextView tv_remark;
    @Bind(R.id.tv_orderNo)
    TextView tv_orderNo;
    @Bind(R.id.tv_payType)
    TextView tv_payType;
    private RunnerDingDanDetilsInfo runnerDingDanDetilsInfo;

    private void requestOrder(String orderNo) {
        RrmineRunDetilsInfo rrmineRunDetilsInfo = new RrmineRunDetilsInfo();
        rrmineRunDetilsInfo.setFunctionName("QueryErrandOrder");
        RrmineRunDetilsInfo.ParametersBean parametersBean = new RrmineRunDetilsInfo.ParametersBean();
        parametersBean.setOrderNo(orderNo);
        rrmineRunDetilsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(ErrandCustomerDingDanDetilsActivity.this);
        okGoUtils.httpPostJSON(rrmineRunDetilsInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                runnerDingDanDetilsInfo = JSON.parseObject(response, RunnerDingDanDetilsInfo.class);
                if (runnerDingDanDetilsInfo.getCode() == 0) {
                    if (runnerDingDanDetilsInfo.getData() != null) {
                        tv_createtime.setText(runnerDingDanDetilsInfo.getData().getCreateTime());
                        tv_type.setText(runnerDingDanDetilsInfo.getData().getGoodsType());
                        tv_shipeeFee.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getShippingFee()));
                        tv_runMoney.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getShippingFee()));
                        tv_letadr.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getReceiverAddress()));
                        tv_getadr.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getDeliveryAddress()));
                        tv_letinfo.setText(runnerDingDanDetilsInfo.getData().getReceiverName() + " " + runnerDingDanDetilsInfo.getData().getReceiverPhone());
                        tv_getinfo.setText(runnerDingDanDetilsInfo.getData().getDeliveryName() + " " + runnerDingDanDetilsInfo.getData().getDeliveryPhone());
                        tv_picktime.setText(runnerDingDanDetilsInfo.getData().getPickupTime());
                        tv_payType.setText(runnerDingDanDetilsInfo.getData().getPayment());
                        tv_orderNo.setText(runnerDingDanDetilsInfo.getData().getOrderNo());
                        tv_level.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getErrandLevel()));
                        if (!TextUtils.isEmpty(runnerDingDanDetilsInfo.getData().getRemark())) {
                            tv_remark.setText(runnerDingDanDetilsInfo.getData().getRemark());
                        } else {
                            tv_remark.setText("无");
                        }
                        tv_weight.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getGoodsWeight()));
                    }
                }
            }
        });
    }

    /**
     * 确认收货
     *
     * @param orderNo
     */
    public void sureGet(final String orderNo) {
        SureGetInfo sureGetInfo = new SureGetInfo();
        sureGetInfo.setFunctionName("UpdateErrandConfirmStatus");
        SureGetInfo.ParametersBean parametersBean = new SureGetInfo.ParametersBean();
        parametersBean.setOrderNo(orderNo);
        parametersBean.setUserId(userId);
        sureGetInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(ErrandCustomerDingDanDetilsActivity.this);
        okGoUtils.httpPostJSON(sureGetInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    requestOrder(orderNo);
                }
            }
        });
    }

    /**
     * 重新发布
     *
     * @param orderNo
     */
    public void releaseAgain(final String orderNo) {
        SureGetInfo sureGetInfo = new SureGetInfo();
        sureGetInfo.setFunctionName("RepublishErrandOrder");
        SureGetInfo.ParametersBean parametersBean = new SureGetInfo.ParametersBean();
        parametersBean.setOrderNo(orderNo);
        parametersBean.setUserId(userId);
        sureGetInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(ErrandCustomerDingDanDetilsActivity.this);
        okGoUtils.httpPostJSON(sureGetInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    requestOrder(orderNo);
                }
            }
        });
    }

    /**
     * 取消订单
     *
     * @param orderNo
     */
    public void Tuikuan(final String orderNo, String remark) {
        TuiKUanoInfo tuiKUanoInfo = new TuiKUanoInfo();
        tuiKUanoInfo.setFunctionName("CancelErrandOrder");
        TuiKUanoInfo.ParametersBean parametersBean = new TuiKUanoInfo.ParametersBean();
        parametersBean.setOrderNo(orderNo);
        parametersBean.setUserId(userId);
        parametersBean.setReason(remark);
        tuiKUanoInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(ErrandCustomerDingDanDetilsActivity.this);
        okGoUtils.httpPostJSON(tuiKUanoInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    requestOrder(orderNo);
                }
            }
        });
    }

    /**
     * 去评价界面
     * @param orderNo
     */
    public void goToRelease(String orderNo) {
        Bundle bundle = new Bundle();
        bundle.putString("ordeNo", orderNo);
        ZeroZeroSevenUtils.SwitchActivity(ErrandCustomerDingDanDetilsActivity.this, ErrandTalkReleaseActivity.class, bundle);
    }
}
