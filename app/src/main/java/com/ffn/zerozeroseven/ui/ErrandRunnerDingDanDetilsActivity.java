package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.RunnerDingDanDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RrmineRunDetilsInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.ShopStatusView;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrandRunnerDingDanDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.shopView)
    ShopStatusView shopView;
    private String orderNo;
    private RunnerDingDanDetilsInfo runnerDingDanDetilsInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_errand_detils;
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

    @Bind(R.id.tv_quhuo1)
    TextView tv_quhuo1;
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
    @Bind(R.id.tv_picktime)
    TextView tv_picktime;
    @Bind(R.id.tv_weight)
    TextView tv_weight;
    @Bind(R.id.tv_remark)
    TextView tv_remark;
    @Bind(R.id.tv_quhuo2)
    TextView tv_quhuo2;
    @Bind(R.id.tv_tp1)
    TextView tv_tp1;
    @Bind(R.id.tv_tpdesc)
    TextView tv_tpdesc;
    @Bind(R.id.tv_tp2)
    TextView tv_tp2;
    @Bind(R.id.tv_tp2desc)
    TextView tv_tp2desc;
    @Bind(R.id.iv_sibu)
    ImageView iv_sibu;

    private void requestOrder(String orderNo) {
        RrmineRunDetilsInfo rrmineRunDetilsInfo = new RrmineRunDetilsInfo();
        rrmineRunDetilsInfo.setFunctionName("QueryErrandOrder");
        RrmineRunDetilsInfo.ParametersBean parametersBean = new RrmineRunDetilsInfo.ParametersBean();
        parametersBean.setOrderNo(orderNo);
        rrmineRunDetilsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(ErrandRunnerDingDanDetilsActivity.this);
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
                        tv_letadr.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getReceiverAddress()));
                        tv_getadr.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getDeliveryAddress()));
                        tv_letinfo.setText(runnerDingDanDetilsInfo.getData().getReceiverName() + " " + runnerDingDanDetilsInfo.getData().getReceiverPhone());
                        tv_getinfo.setText(runnerDingDanDetilsInfo.getData().getDeliveryName() + " " + runnerDingDanDetilsInfo.getData().getDeliveryPhone());
                        tv_picktime.setText(runnerDingDanDetilsInfo.getData().getPickupTime());
                        if (!TextUtils.isEmpty(runnerDingDanDetilsInfo.getData().getRemark())) {
                            tv_remark.setText(runnerDingDanDetilsInfo.getData().getRemark());
                        } else {
                            tv_remark.setText("无");
                        }
                        tv_weight.setText(String.valueOf(runnerDingDanDetilsInfo.getData().getGoodsWeight()));


                        //-1=无效状态，0=未接单，1=已接单，2=取货中，3=已收货，5=已取消
                        switch (runnerDingDanDetilsInfo.getData().getOrderStatus()) {
                            case 1:
                                shopView.procressTo(1);
                                tv_quhuo1.setText("请前往取货：" + runnerDingDanDetilsInfo.getData().getReceiverAddress());
                                tv_quhuo2.setText("请在15分钟内到达取货地点");
                                tv_tp1.setText("第一步：致电发货人");
                                tv_tpdesc.setText("请先致电发货人核对地址和时间");
                                tv_tp2.setText("第二步：拍照取货");
                                tv_tp2desc.setText("为避免货物纠纷，请在取货时拍照存证");
                                break;
                            case 2:
                                shopView.procressTo(2);
                                tv_quhuo1.setText("请尽快送货：" + runnerDingDanDetilsInfo.getData().getDeliveryAddress());
                                tv_quhuo2.setText("请尽快到达送货地点");
                                tv_tp1.setText("第三步：致电收货人");
                                tv_tpdesc.setText("请先致电收货人核对地址和时间");
                                tv_tp2.setText("第四步：送达收货人验收");
                                tv_tp2desc.setText("需收货人确认收货才能完成收益回报");
                                Glide.with(ErrandRunnerDingDanDetilsActivity.this).load(R.drawable.shouhuorun).into(iv_sibu);
                                break;
                            case 3:
                                shopView.procressTo(3);
                                rl_tp.setVisibility(View.GONE);
                                rl_bt.setVisibility(View.GONE);
                                tv_quhuo1.setText("已完成订单");
                                tv_quhuo2.setText("您此次的跑腿收益："+runnerDingDanDetilsInfo.getData().getErrandIncome()+"元已收入您的收益中");
                                break;
                            case 5:
                                break;
                        }
                    }
                }
            }
        });
    }

    @Bind(R.id.rl_tp)
    RelativeLayout rl_tp;
    @Bind(R.id.rl_bt)
    RelativeLayout rl_bt;

    @OnClick({R.id.rl_tp, R.id.rl_bt})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.rl_tp:
                if (runnerDingDanDetilsInfo.getData().getOrderStatus() == 1 || runnerDingDanDetilsInfo.getData().getOrderStatus() == 2) {
                    ToastUtils.showShort("打发货人电话");
                } else {

                }
                break;
            case R.id.rl_bt:
                break;

        }
    }
}
