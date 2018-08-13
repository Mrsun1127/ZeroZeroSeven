package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.RunnerDingDanDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RrmineRunDetilsInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ErrandRunnerDingDanDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
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
                    if (runnerDingDanDetilsInfo.getData()!= null) {
                        tv_quhuo1.setText("请前往取货：" + runnerDingDanDetilsInfo.getData().getReceiverAddress());
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
                    }
                }
            }
        });
    }
}
