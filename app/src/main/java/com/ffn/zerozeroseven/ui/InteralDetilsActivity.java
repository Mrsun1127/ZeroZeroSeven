package com.ffn.zerozeroseven.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.JiInfo;
import com.ffn.zerozeroseven.bean.ProductDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.GobuyInfo;
import com.ffn.zerozeroseven.bean.requsetbean.JifenInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
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
    @Bind(R.id.tv_allinteral)
    TextView tv_allinteral;
    @Bind(R.id.et_count)
    EditText et_count;
    @Bind(R.id.rl_add)
    RelativeLayout rl_add;
    @Bind(R.id.rl_close)
    RelativeLayout rl_close;
    @Bind(R.id.rl_pop)
    RelativeLayout rl_pop;
    @Bind(R.id.tv_count)
    TextView tv_count;
    @Bind(R.id.rl_out)
    RelativeLayout rl_out;
    private ProductDetilsInfo productDetilsInfo;
    private int prizeId;
    private int replaceId;

    @Override
    protected int setLayout() {
        return R.layout.activity_interaldetils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        prizeId = getIntent().getIntExtra("prizeId", 0);
        replaceId = getIntent().getIntExtra("replaceId", 0);
        productDetilsInfo = (ProductDetilsInfo) getIntent().getSerializableExtra("product");
        Glide.with(InteralDetilsActivity.this)
                .load(productDetilsInfo.getData().getPointPrize().getPrizePic());
        tv_needinteral.setText("总需" + productDetilsInfo.getData().getPointPrize().getPrizePoint()+"积分");
        tv_closeinteral.setText("还差" + (productDetilsInfo.getData().getPointPrize().getPrizePoint()-productDetilsInfo.getData().getPointPrize().getContributionPoint())+"积分");
        et_count.setText("");
        et_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_allinteral.setText("共1个宝贝 总计： " + et_count.getText().toString().trim() + "积分");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
    int isBaowei=0;
    String i ="0";
    @OnClick({R.id.bt_gobuy, R.id.rl_close, R.id.rl_add, R.id.bt_sure, R.id.tv_back,R.id.bt_baowei})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_baowei:
                isBaowei=1;
                tv_count.setText("您将消耗" +(productDetilsInfo.getData().getPointPrize().getPrizePoint()-productDetilsInfo.getData().getPointPrize().getContributionPoint()) + "积分");
                tv_allinteral.setText("共1个宝贝 总计： " + (productDetilsInfo.getData().getPointPrize().getPrizePoint()-productDetilsInfo.getData().getPointPrize().getContributionPoint())  + "积分");
                rl_out.setVisibility(View.VISIBLE);
                i=(productDetilsInfo.getData().getPointPrize().getPrizePoint()-productDetilsInfo.getData().getPointPrize().getContributionPoint())+"";
                break;
            case R.id.tv_back:
                rl_out.setVisibility(View.GONE);
                break;
            case R.id.bt_sure:
                rl_out.setVisibility(View.GONE);
                goBuy(i,isBaowei);
                break;
            case R.id.bt_gobuy:
                isBaowei=0;
                i=et_count.getText().toString().trim();
                if (!TextUtils.isEmpty(i)) {
                    if (Integer.parseInt(i) < 1) {
                        ToastUtils.showShort("请贡献(>=1)积分");
                    } else {
                        tv_count.setText("您将消耗" + et_count.getText().toString() + "积分");
                        rl_out.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtils.showShort("请贡献(>=1)积分");
                }
                break;
            case R.id.rl_close:
                i=et_count.getText().toString().trim();
                if (!TextUtils.isEmpty(i)) {
                    int j = Integer.parseInt(i);
                    if (j > 0) {
                        et_count.setText(String.valueOf(j - 1));
//                        tv_allinteral.setText("共1个宝贝 总计： " + (j - 1) + "积分");
                    }
                }

                break;
            case R.id.rl_add:
                i=et_count.getText().toString().trim();
                if (!TextUtils.isEmpty(i)) {
                    int j = Integer.parseInt(i);
                    et_count.setText(String.valueOf(j + 1));
//                    tv_allinteral.setText("共1个宝贝 总计： " + (j + 1) + "积分");
                } else {
                    et_count.setText("1");
//                    tv_allinteral.setText("共1个宝贝 总计： 1积分");
                }
                break;

        }
    }

    private void goBuy(String i,int baowei) {
        OkGoUtils okGoUtils = new OkGoUtils(InteralDetilsActivity.this);
        GobuyInfo gobuyInfo = new GobuyInfo();
        gobuyInfo.setFunctionName("AddPointPrizeUserContribution");
        GobuyInfo.ParametersBean parametersBean = new GobuyInfo.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        parametersBean.setPoint(Integer.parseInt(i));
        parametersBean.setIssuePrizeId(replaceId);
        parametersBean.setIsRest(baowei);
        gobuyInfo.setParameters(parametersBean);
        okGoUtils.httpPostJSON(gobuyInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    ToastUtils.showShort("贡献成功");
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }
            }
        });
    }
}
