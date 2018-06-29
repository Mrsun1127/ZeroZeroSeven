package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ProductGoInAdapter;
import com.ffn.zerozeroseven.adapter.ProductSinggerGoInAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.bean.ProductDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ProductDtilsInfo;
import com.ffn.zerozeroseven.ui.InteralDetilsActivity;
import com.ffn.zerozeroseven.ui.ProductDetilsActivity;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

public class ProductDetilsFragment extends BaseFragment {
    @Bind(R.id.rc_minegoin)
    RecyclerView rc_minegoin;
    @Bind(R.id.rc_allgoin)
    RecyclerView rc_allgoin;
    private ProductGoInAdapter productGoInAdapter;
    private ProductSinggerGoInAdapter singgerGoInAdapter;
    private int id;
    private int issuePId;
    private ProductDetilsInfo productDetilsInfo;
    private int replaceId;

    public static ProductDetilsFragment newInstance(int id, int issuePId) {
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putInt("pid", issuePId);
        ProductDetilsFragment fragment = new ProductDetilsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static WeakReference<ProductDetilsFragment> mInstance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
        issuePId = getArguments().getInt("pid");
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        mInstance = new WeakReference<>(this);
        rc_minegoin.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        rc_allgoin.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        productGoInAdapter = new ProductGoInAdapter(bfCxt);
        singgerGoInAdapter = new ProductSinggerGoInAdapter(bfCxt);
        rc_minegoin.setAdapter(singgerGoInAdapter);
        rc_allgoin.setAdapter(productGoInAdapter);
        tv_time.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                ProductDetilsActivity.mInstance.get().requestTitle(false);
            }
        });
    }

    @Override
    public void initDate() {
        requestId(id, issuePId);
    }

    @Bind(R.id.iv_product)
    ImageView iv_product;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_name1)
    TextView tv_name1;
    @Bind(R.id.rl_close)
    RelativeLayout rl_close;
    @Bind(R.id.rl_open)
    RelativeLayout rl_open;
    @Bind(R.id.tv_money)
    TextView tv_money;
    @Bind(R.id.tv_detils)
    TextView tv_detils;
    @Bind(R.id.tv_need)
    TextView tv_need;
    @Bind(R.id.tv_close)
    TextView tv_close;
    @Bind(R.id.pb_watch)
    ProgressBar pb_watch;
    @Bind(R.id.tv_username)
    TextView tv_username;
    @Bind(R.id.tv_usercount)
    TextView tv_usercount;
    @Bind(R.id.tv_usernumber)
    TextView tv_usernumber;
    @Bind(R.id.tv_usertime)
    TextView tv_usertime;
    @Bind(R.id.iv_mine_no)
    ImageView iv_mineno;
    @Bind(R.id.iv_all_no)
    ImageView iv_allno;
    @Bind(R.id.rl_top)
    RelativeLayout rl_top;
    @Bind(R.id.rl_bottom)
    RelativeLayout rl_bot;
    @Bind(R.id.tv_time)
    CountdownView tv_time;
    @Bind(R.id.rl_ok)
    RelativeLayout rl_ok;
    @Bind(R.id.bt_go)
    Button bt_go;

    public void requestId(int id, int issueId) {
        ProductDtilsInfo lastInteralInfo = new ProductDtilsInfo();
        lastInteralInfo.setFunctionName("QueryPointIssuePrize");
        ProductDtilsInfo.ParametersBean parametersBean = new ProductDtilsInfo.ParametersBean();
        parametersBean.setPrizeId(id);
        parametersBean.setIssuePId(issueId);
        parametersBean.setUserPhone(userInfo.getPhone());
        lastInteralInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(lastInteralInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                productDetilsInfo = JSON.parseObject(response, ProductDetilsInfo.class);
                if (productDetilsInfo.getCode() == 0) {
                    //商品详情
                    Glide.with(bfCxt).load(productDetilsInfo.getData().getPointPrize().getPrizePic()).into(iv_product);
                    tv_name.setText(productDetilsInfo.getData().getPointPrize().getPrizeName());
                    tv_name1.setText(productDetilsInfo.getData().getPointPrize().getPrizeName());
                    tv_money.setText("￥" + productDetilsInfo.getData().getPointPrize().getPrizePrice());
                    tv_detils.setText(productDetilsInfo.getData().getPointPrize().getPrizeIntro());
                    productGoInAdapter.cleanDates();
                    singgerGoInAdapter.cleanDates();
                    if (productDetilsInfo.getData().getAllUserContributionList().size() > 0) {
                        rc_allgoin.setVisibility(View.VISIBLE);
                        rl_bot.setVisibility(View.GONE);
                        productGoInAdapter.addAll(productDetilsInfo.getData().getAllUserContributionList());
                    } else {
                        rc_allgoin.setVisibility(View.GONE);
                        rl_bot.setVisibility(View.VISIBLE);
                    }
                    if (productDetilsInfo.getData().getUserContributionList().size() > 0) {
                        singgerGoInAdapter.addAll(productDetilsInfo.getData().getUserContributionList());
                        rc_minegoin.setVisibility(View.VISIBLE);
                        rl_top.setVisibility(View.GONE);
                    } else {
                        rc_minegoin.setVisibility(View.GONE);
                        rl_top.setVisibility(View.VISIBLE);
                    }
                    switch (productDetilsInfo.getData().getStatus()) {
                        //-1=失效奖品，0=未开奖，1=可开奖，2=已开奖，3=已填配送配送
                        case -1:
                            rl_ok.setVisibility(View.GONE);
                            rl_close.setVisibility(View.GONE);
                            rl_open.setVisibility(View.GONE);
                            ZeroZeroSevenUtils.showCustonPop(bfCxt, "该奖品已失效", tv_name);
                            break;
                        case 0:
                            bt_go.setVisibility(View.VISIBLE);
                            rl_ok.setVisibility(View.GONE);
                            rl_close.setVisibility(View.VISIBLE);
                            rl_open.setVisibility(View.GONE);
                            pb_watch.setMax(productDetilsInfo.getData().getPointPrize().getPrizePoint());
                            tv_need.setText("总需" + productDetilsInfo.getData().getPointPrize().getPrizePoint() + "积分");
                            tv_close.setText("还差" + (productDetilsInfo.getData().getPointPrize().getPrizePoint() - productDetilsInfo.getData().getPointPrize().getContributionPoint()) + "积分");
                            pb_watch.setProgress(productDetilsInfo.getData().getPointPrize().getContributionPoint());
                            break;
                        case 1:
                            rl_ok.setVisibility(View.VISIBLE);
                            rl_close.setVisibility(View.GONE);
                            rl_open.setVisibility(View.GONE);
                            tv_time.start(30000);
                            break;
                        case 2:
                            rl_ok.setVisibility(View.GONE);
                            rl_open.setVisibility(View.VISIBLE);
                            rl_close.setVisibility(View.GONE);
                            tv_username.setText(productDetilsInfo.getData().getPointPrizeWinner().getUserPhone());
                            tv_usercount.setText(productDetilsInfo.getData().getUserContributionList().size() + "次");
                            tv_usernumber.setText(productDetilsInfo.getData().getPointPrizeWinner().getLuckNum());
                            tv_usertime.setText(productDetilsInfo.getData().getPointPrizeWinner().getCreateTime());
                            break;
                        case 3:
                            rl_ok.setVisibility(View.GONE);
                            rl_open.setVisibility(View.VISIBLE);
                            rl_close.setVisibility(View.GONE);
                            break;

                    }

                }
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_product_detils;
    }

    @Override
    protected void lazyLoad() {
    }

    @OnClick({R.id.bt_go})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_go:
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", productDetilsInfo);
                bundle.putInt("prizeId", id);
                bundle.putInt("replaceId", productDetilsInfo.getData().getId());
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, InteralDetilsActivity.class, bundle);
                break;
        }
    }
}
