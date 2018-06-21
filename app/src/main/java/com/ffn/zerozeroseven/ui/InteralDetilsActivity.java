package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.JiInfo;
import com.ffn.zerozeroseven.bean.ProductDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.GobuyInfo;
import com.ffn.zerozeroseven.bean.requsetbean.JifenInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InteralDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.iv_product)
    ImageView iv_product;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_needinteral)
    TextView tv_needinteral;
    @Bind(R.id.tv_closeinteral)
    TextView tv_closeinteral;
    private ProductDetilsInfo productDetilsInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_interaldetils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        productDetilsInfo = (ProductDetilsInfo) getIntent().getSerializableExtra("product");
        Glide.with(InteralDetilsActivity.this)
                .load(productDetilsInfo.getData().getPointPrize().getPrizePic());
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
        requestJifen();
    }

    private void requestJifen() {
        JifenInfo jifenInfo = new JifenInfo();
        jifenInfo.setFunctionName("QueryUserHonerPoint");
        JifenInfo.ParametersBean parametersBean = new JifenInfo.ParametersBean();
        parametersBean.setUserId(Integer.parseInt(userId));
        jifenInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(InteralDetilsActivity.this);
        okGoUtils.httpPostJSON(jifenInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                JiInfo jiInfo = JSON.parseObject(response, JiInfo.class);
                if (jiInfo.getCode() == 0) {
                    topView.setTvRightText("当前积分：" + jiInfo.getData().getHonerPoint());
                }
            }
        });
    }

    @OnClick({R.id.bt_gobuy})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_gobuy:
                goBuy("20");
                break;

        }
    }

    private void goBuy(String i) {
        OkGoUtils okGoUtils = new OkGoUtils(InteralDetilsActivity.this);
        GobuyInfo gobuyInfo = new GobuyInfo();
        gobuyInfo.setFunctionName("AddUserContribution");
        GobuyInfo.ParametersBean parametersBean = new GobuyInfo.ParametersBean();
        parametersBean.setUserId(Integer.parseInt(userId));
        parametersBean.setHonerPoint("1");
        parametersBean.setIssuePrizeId(productDetilsInfo.getData().getIssue());
        gobuyInfo.setParameters(parametersBean);
        okGoUtils.httpPostJSON(gobuyInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {

            }
        });
    }
}
