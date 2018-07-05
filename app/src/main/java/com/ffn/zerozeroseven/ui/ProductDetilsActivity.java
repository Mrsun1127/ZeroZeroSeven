package com.ffn.zerozeroseven.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ProductAdapter;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ProductTitleInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.LastInteralInfo;
import com.ffn.zerozeroseven.fragment.ProductDetilsFragment;
import com.ffn.zerozeroseven.listenner.EndlessRecyclerOnScrollListener;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ProductDetilsActivity extends BaseActivity implements OnRefreshListener {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private List<String> titleList;
    private List<String> List;
    private List<Fragment> fragmentList;
    private int prizeId;
    private ProductAdapter productAdapter;
    private ProductTitleInfo productTitleInfo;
    public int clickposition = 0;
    public static WeakReference<ProductDetilsActivity> mInstance;

    @Override
    protected int setLayout() {
        return R.layout.activity_productdetils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mInstance = new WeakReference<>(this);
        topView.setTopText("宝贝详情");
        topView.setTvRightText("分享");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                showShare(ProductDetilsActivity.this);
            }

            @Override
            public void Back() {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetilsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        productAdapter = new ProductAdapter(ProductDetilsActivity.this);
        recyclerView.setAdapter(productAdapter);
        productAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                productAdapter.setClickPosition(position);
                clickposition = position;
                ProductDetilsFragment.mInstance.get().requestId(productTitleInfo.getData().getIssues().get(position).getId());
            }
        });
        refreshlayout.setOnRefreshListener(this);
    }

    private void showShare(Context context) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("积分抽奖有礼啦");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(AppConfig.PRODUCTSHAREURL + prizeId);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("各种法拉利 兰博基尼等着你来领");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(AppConfig.LOGOURL);//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(AppConfig.PRODUCTSHAREURL + prizeId);
        // 启动分享GUI
        oks.show(context);
    }

    @Override
    protected void doMain() {
        prizeId = getIntent().getIntExtra("prizeId", 0);
        requestTitle(true);


    }

    public void requestTitle(final boolean isShow) {
        LastInteralInfo lastInteralInfo = new LastInteralInfo();
        lastInteralInfo.setFunctionName("ListPointPrizeIssue");
        LastInteralInfo.ParametersBean parametersBean = new LastInteralInfo.ParametersBean();
        parametersBean.setPrizeId(prizeId);
        lastInteralInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(ProductDetilsActivity.this);
        okGoUtils.httpPostJSON(lastInteralInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                refreshlayout.finishRefresh();
                productTitleInfo = JSON.parseObject(response, ProductTitleInfo.class);
                if (productTitleInfo.getCode() == 0 && productTitleInfo.getData().getIssues().size() > 0) {
                    fragmentList = new ArrayList<>();
                    titleList = new ArrayList<>();
                    List = new ArrayList<>();
                    for (int i = 0; i < productTitleInfo.getData().getIssues().size(); i++) {
                        titleList.add(String.valueOf(productTitleInfo.getData().getIssues().get(i).getIssue()));
                    }
                    Collections.reverse(titleList);
                    List<ProductTitleInfo.DataBean.IssuesBean> issues = productTitleInfo.getData().getIssues();
                    Collections.reverse(issues);
                    List.add(String.valueOf(issues.get(0).getIssue()));
                    productAdapter.cleanDates();
                    productAdapter.addAll(titleList);
                    productAdapter.setClickPosition(0);
                    fragmentList.add(ProductDetilsFragment.newInstance(prizeId, issues.get(0).getId()));
                    ShopViewPagerAdapter viewPagerAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, List);
                    viewPager.setAdapter(viewPagerAdapter);
                }
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        requestTitle(true);
    }
}
