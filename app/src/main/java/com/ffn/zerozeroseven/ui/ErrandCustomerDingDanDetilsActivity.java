package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.RunnerDingDanDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RrmineRunDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SureGetInfo;
import com.ffn.zerozeroseven.bean.requsetbean.TuiKUanoInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        LogUtils.D("orderNo", orderNo);
        requestOrder(orderNo);
    }

    @Bind(R.id.tv_status)
    TextView tv_status;
    @Bind(R.id.tv_runneerMan)
    TextView tv_runneerMan;
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
    @Bind(R.id.tv_frist)
    TextView tv_frist;
    @Bind(R.id.tv_jixing)
    TextView tv_jixing;
    @Bind(R.id.ll_runner)
    LinearLayout ll_runner;
    private RunnerDingDanDetilsInfo runnerDingDanDetilsInfo;

    @OnClick({R.id.bt_left, R.id.bt_right})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_left:
                switch (runnerDingDanDetilsInfo.getData().getOrderStatus()) {
                    case 1:
                    case 0:
                        final ConfirmDialog confirmDialog = new ConfirmDialog(ErrandCustomerDingDanDetilsActivity.this);
                        confirmDialog.setTitles("提示");
                        confirmDialog.setMessages("您确认取消订单？");
                        confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                            @Override
                            public void doConfirm() {
                                confirmDialog.dismiss();
                                Quxiao(orderNo, "正常取消");
                            }

                            @Override
                            public void doCancel() {
                                confirmDialog.dismiss();
                            }
                        });
                        break;//取消

                    case 2:
                        sureGet(orderNo);
                        break;//确认收货
                    case 3:
                        goToRelease(orderNo, runnerDingDanDetilsInfo.getData().getRealName());
                        break;//去评价
                    case 5:
//                        TuiKuanla();
                        break;

                }
                break;
            case R.id.bt_right:
                switch (runnerDingDanDetilsInfo.getData().getOrderStatus()) {
                    case 0:
                    case 3:
                    case 5:
                        releaseAgain(orderNo);
                        break;
                    case 1:
                    case 2:
                        ZeroZeroSevenUtils.requestCallMainifest(ErrandCustomerDingDanDetilsActivity.this);
                        ZeroZeroSevenUtils.MakingCalls(ErrandCustomerDingDanDetilsActivity.this, runnerDingDanDetilsInfo.getData().getPhone());
                        break;

                }
                break;

        }
    }

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
                        tv_letadr.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getDeliveryAddress()));
                        tv_getadr.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getReceiverAddress()));
                        tv_letinfo.setText(runnerDingDanDetilsInfo.getData().getDeliveryName() + " " + runnerDingDanDetilsInfo.getData().getDeliveryPhone());
                        tv_getinfo.setText(runnerDingDanDetilsInfo.getData().getReceiverName() + " " + runnerDingDanDetilsInfo.getData().getReceiverPhone());
                        tv_picktime.setText(runnerDingDanDetilsInfo.getData().getPickupTime());
                        tv_payType.setText(runnerDingDanDetilsInfo.getData().getPayment());
                        tv_orderNo.setText(runnerDingDanDetilsInfo.getData().getOrderNo());
                        tv_level.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getErrandLevel()));
                        if (!TextUtils.isEmpty(runnerDingDanDetilsInfo.getData().getRemark())) {
                            tv_remark.setText(runnerDingDanDetilsInfo.getData().getRemark());
                        } else {
                            tv_remark.setText("无");
                        }
                    }
                    //-1=无效状态，0=未接单，1=已接单，2=取货中，3=已收货，5=已取消
                    switch (runnerDingDanDetilsInfo.getData().getOrderStatus()) {
                        case 0:
                            tv_status.setText("等待跑腿接单");
                            tv_frist.setVisibility(View.VISIBLE);
                            tv_frist.setText("5分钟未接单，系统将自动为您取消订单");
                            bt_left.setText("取消订单");
                            bt_right.setText("再来一单");
                            break;
                        case 1:
                            tv_status.setText("正在取货中");
                            bt_left.setText("取消订单");
                            bt_right.setText("联系骑手");
                            ll_runner.setVisibility(View.VISIBLE);
                            tv_runneerMan.setText(runnerDingDanDetilsInfo.getData().getRealName());
                            tv_jixing.setText(runnerDingDanDetilsInfo.getData().getErrandLevel() + "星级");
                            break;
                        case 2:
                            tv_status.setText("正在配送中");
                            bt_left.setText("确认收货");
                            bt_right.setText("联系骑手");
                            ll_runner.setVisibility(View.VISIBLE);
                            tv_runneerMan.setText(runnerDingDanDetilsInfo.getData().getRealName());
                            tv_jixing.setText(runnerDingDanDetilsInfo.getData().getErrandLevel() + "星级");
                            break;
                        case 3:
                            tv_status.setText("订单已完成");
                            if(runnerDingDanDetilsInfo.getData().getIsComment()==0){
                                bt_left.setText("去评价");
                            }else{
                                bt_left.setVisibility(View.GONE);
                            }
                            bt_right.setText("再来一单");
                            ll_runner.setVisibility(View.VISIBLE);
                            tv_runneerMan.setText(runnerDingDanDetilsInfo.getData().getRealName());
                            tv_jixing.setText(runnerDingDanDetilsInfo.getData().getErrandLevel() + "星级");
                            break;
                        case 5:
                            tv_status.setText("订单已取消");
                            tv_frist.setVisibility(View.VISIBLE);
                            tv_frist.setText("订单已取消，感谢您对校园跑腿的信任");
                            bt_left.setVisibility(View.GONE);
                            bt_right.setText("再来一单");
                            break;
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
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
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
//        SureGetInfo sureGetInfo = new SureGetInfo();
//        sureGetInfo.setFunctionName("RepublishErrandOrder");
//        SureGetInfo.ParametersBean parametersBean = new SureGetInfo.ParametersBean();
//        parametersBean.setOrderNo(orderNo);
//        parametersBean.setUserId(userId);
//        sureGetInfo.setParameters(parametersBean);
//        OkGoUtils okGoUtils = new OkGoUtils(ErrandCustomerDingDanDetilsActivity.this);
//        okGoUtils.httpPostJSON(sureGetInfo, true, true);
//        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
//            @Override
//            public void onSuccLoad(String response) {
//                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
//                if (errorCodeInfo.getCode() == 0) {
//                    requestOrder(orderNo);
//                } else {
//                    ToastUtils.showShort(errorCodeInfo.getMessage());
//                }
//            }
//        });
        finish();
    }

    /**
     * 取消订单
     *
     * @param orderNo
     */
    public void Quxiao(final String orderNo, String remark) {
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
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }
            }
        });
    }

    /**
     * 去评价界面
     *
     * @param orderNo
     */
    public void goToRelease(String orderNo, String name) {
        Bundle bundle = new Bundle();
        bundle.putString("orderNo", orderNo);
        bundle.putString("name", name);
        ZeroZeroSevenUtils.SwitchActivity(ErrandCustomerDingDanDetilsActivity.this, ErrandTalkReleaseActivity.class, bundle);
    }
}
