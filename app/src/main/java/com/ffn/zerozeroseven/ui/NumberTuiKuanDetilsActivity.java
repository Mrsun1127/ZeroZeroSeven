package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.TuiKuanItemAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.TuiKuanDetialsInfo;
import com.ffn.zerozeroseven.bean.TuiKuanInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NumberTuiKuanDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.tv_top)
    TextView tv_top;
    @Bind(R.id.rc_product)
    RecyclerView rc_product;
    private TuiKuanItemAdapter tuiKuanItemAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_numbertuikuandetils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("退款详情");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        rc_product.setLayoutManager(new LinearLayoutManager(this));
        tuiKuanItemAdapter = new TuiKuanItemAdapter(NumberTuiKuanDetilsActivity.this);
        rc_product.setAdapter(tuiKuanItemAdapter);

    }

    @Override
    protected void doMain() {
        requestDate(getIntent().getStringExtra("orderNo"));
    }

    @Bind(R.id.tv_reson)
    TextView tv_reson;
    @Bind(R.id.tv_money)
    TextView tv_money;
    @Bind(R.id.tv_bot)
    TextView tv_bot;
    @Bind(R.id.tv_bot1)
    TextView tv_bot1;
    @Bind(R.id.tv_remark)
    TextView tv_remark;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_gettime)
    TextView tv_gettime;
    @Bind(R.id.bt_three)
    Button bt_three;
    @Bind(R.id.line2)
    View line2;

    private void requestDate(String orderId) {
        TuiKuanDetialsInfo tuiKuanDetialsInfo = new TuiKuanDetialsInfo();
        tuiKuanDetialsInfo.setFunctionName("QueryDigitalOrderRefundApply");
        TuiKuanDetialsInfo.ParametersBean parametersBean = new TuiKuanDetialsInfo.ParametersBean();
        parametersBean.setUserId(userId);
        parametersBean.setOrderNo(orderId);
        tuiKuanDetialsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(NumberTuiKuanDetilsActivity.this);
        okGoUtils.httpPostJSON(tuiKuanDetialsInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                TuiKuanInfo tuiKuanInfo = JSON.parseObject(response, TuiKuanInfo.class);
                if (tuiKuanInfo.getCode() == 0) {
                    if (tuiKuanInfo.getData().getOrderGoodsList() != null && tuiKuanInfo.getData().getOrderGoodsList().size() > 0) {
                        tuiKuanItemAdapter.cleanDates();
                        tuiKuanItemAdapter.addAll(tuiKuanInfo.getData().getOrderGoodsList());
                    }
                    if (tuiKuanInfo.getData().getRefundApply() != null) {
                        tv_reson.setText("退款原因:" + tuiKuanInfo.getData().getRefundApply().getRefundReason());
                        tv_money.setText("退款金额:" +tuiKuanInfo.getData().getRefundApply().getRefundAmount()+"元" );
//                        tv_remark.setText("退款说明:" + tuiKuanInfo.getData().getRefundApply().getRefundRemark());
                        tv_time.setText("申请时间:" + tuiKuanInfo.getData().getRefundApply().getCreateTime());
                        tv_gettime.setText(tuiKuanInfo.getData().getRefundApply().getCreateTime());
                        if (tuiKuanInfo.getData().getRefundApply().getStatus() == 1) {
                            bt_three.setBackgroundResource(R.drawable.money_circle);
                            line2.setBackgroundResource(R.color.money);
                        } else if (tuiKuanInfo.getData().getRefundApply().getStatus() == -2) {
                            tv_top.setText("退款失败");
                            tv_bot.setText("退款失败");
                            tv_bot1.setText(tuiKuanInfo.getData().getRefundApply().getRefuseReason());
                            bt_three.setBackgroundResource(R.drawable.money_circle);
                            line2.setBackgroundResource(R.color.money);
                        }
                    }
                }
            }
        });
    }
}
