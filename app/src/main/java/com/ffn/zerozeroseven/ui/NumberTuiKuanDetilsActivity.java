package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.TuiKuanDetialsInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NumberTuiKuanDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.rc_product)
    RecyclerView rc_product;

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

    }

    @Override
    protected void doMain() {
        requestDate(getIntent().getIntExtra("orderId",0));
    }

    private void requestDate(int orderId) {
        TuiKuanDetialsInfo tuiKuanDetialsInfo = new TuiKuanDetialsInfo();
        tuiKuanDetialsInfo.setFunctionName("QueryDigitalOrderRefundApply");
        TuiKuanDetialsInfo.ParametersBean parametersBean = new TuiKuanDetialsInfo.ParametersBean();
        parametersBean.setUserId(userId);
        parametersBean.setOrderNo(orderId);
        tuiKuanDetialsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(NumberTuiKuanDetilsActivity.this);
        okGoUtils.httpPostJSON(tuiKuanDetialsInfo,true,true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {

            }
        });
    }
}
