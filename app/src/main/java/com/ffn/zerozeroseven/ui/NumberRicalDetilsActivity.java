package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ItemNumberDingDanDetilsAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.DingDanNumberDetilsInfo;
import com.ffn.zerozeroseven.bean.NumberDetlsInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NumberRicalDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.rc_product)
    RecyclerView rc_product;
    private ItemNumberDingDanDetilsAdapter itemNumberDingDanDetilsAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_numberical_detils;
    }

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
        rc_product.setLayoutManager(new LinearLayoutManager(this));
        itemNumberDingDanDetilsAdapter = new ItemNumberDingDanDetilsAdapter(this);
        rc_product.setAdapter(itemNumberDingDanDetilsAdapter);
    }

    @Override
    protected void doMain() {
        int orderId = getIntent().getIntExtra("orderId", 0);
        requestDetils(orderId);
    }

    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.iv_sure)
    ImageView iv_sure;
    @Bind(R.id.tv_sure)
    TextView tv_sure;
    @Bind(R.id.iv_walk)
    ImageView iv_walk;
    @Bind(R.id.tv_walk)
    TextView tv_walk;
    @Bind(R.id.iv_let)
    ImageView iv_let;
    @Bind(R.id.tv_let)
    TextView tv_let;
    @Bind(R.id.view_line1)
    View line1;
    @Bind(R.id.view_line2)
    View line2;
    @Bind(R.id.ll_run_people)
    LinearLayout ll_run_people;
    @Bind(R.id.ll_number)
    LinearLayout ll_number;
    @Bind(R.id.ll_gettime)
    LinearLayout ll_gettime;
    @Bind(R.id.tv_lettime)
    TextView tv_lettime;
    @Bind(R.id.tv_tel)
    TextView tv_tel;
    @Bind(R.id.tv_desc)
    TextView tv_desc;
    @Bind(R.id.tv_people)
    TextView tv_people;
    @Bind(R.id.tv_phone)
    TextView tv_phone;

    private void requestDetils(int orderId) {
        DingDanNumberDetilsInfo dingDanNumberDetilsInfo = new DingDanNumberDetilsInfo();
        dingDanNumberDetilsInfo.setFunctionName("QueryDigitalGoodsOrder");
        DingDanNumberDetilsInfo.ParametersBean parametersBean = new DingDanNumberDetilsInfo.ParametersBean();
        parametersBean.setOrderId(orderId);
        dingDanNumberDetilsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(NumberRicalDetilsActivity.this);
        okGoUtils.httpPostJSON(dingDanNumberDetilsInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                NumberDetlsInfo numberDetlsInfo = JSON.parseObject(response, NumberDetlsInfo.class);
                if (numberDetlsInfo.getCode() == 0) {
                    //商品配送状态：0=未发货,1=已发货,2=已收货,4=退货
                    switch (numberDetlsInfo.getData().getShippingStatus()) {
                        case 0:
                            ll_run_people.setVisibility(View.GONE);
                            ll_number.setVisibility(View.GONE);
                            ll_gettime.setVisibility(View.GONE);
                            break;
                        case 1:
                            ll_gettime.setVisibility(View.GONE);
                            tv_people.setText("零零7跑腿小哥（" + numberDetlsInfo.getData().getDeliveryName() + ")为您服务");
                            tv_phone.setText(numberDetlsInfo.getData().getDeliveryPhone());
                            line1.setBackgroundResource(R.color.money);
                            Glide.with(NumberRicalDetilsActivity.this).load(R.drawable.progress_number_walk).into(iv_walk);
                            break;
                        case 2:
                            ll_run_people.setVisibility(View.GONE);
                            ll_number.setVisibility(View.GONE);
                            line1.setBackgroundResource(R.color.money);
                            line2.setBackgroundResource(R.color.money);
                            tv_lettime.setText(numberDetlsInfo.getData().getConfirmTime());
                            Glide.with(NumberRicalDetilsActivity.this).load(R.drawable.progress_number_walk).into(iv_walk);
                            Glide.with(NumberRicalDetilsActivity.this).load(R.drawable.progress_number_let).into(iv_let);
                            break;
                        case 4:
                            break;
                    }
                    if (numberDetlsInfo.getData().getOrderGoodsList().size() > 0) {
                        itemNumberDingDanDetilsAdapter.addAll(numberDetlsInfo.getData().getOrderGoodsList());
                    }
                    tv_time.setText(numberDetlsInfo.getData().getCreateTime());
                    tv_desc.setText("共" + numberDetlsInfo.getData().getGoodsCount() + "商品 合计 ￥" + numberDetlsInfo.getData().getOrderPrice());

                } else {
                    ToastUtils.showShort(numberDetlsInfo.getMessage());
                }
            }
        });
    }
}
