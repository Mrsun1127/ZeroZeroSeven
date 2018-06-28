package com.ffn.zerozeroseven.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ProductAdapter;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ProductTitleInfo;
import com.ffn.zerozeroseven.bean.requsetbean.LastInteralInfo;
import com.ffn.zerozeroseven.fragment.ProductDetilsFragment;
import com.ffn.zerozeroseven.listenner.EndlessRecyclerOnScrollListener;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TopView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    private List<String> titleList;
    private List<String> List;
    private List<Fragment> fragmentList;
    private int prizeId;
    private ProductAdapter productAdapter;
    private ProductTitleInfo productTitleInfo;
    public int issuePrizeId;
    public int clickposition=0;
    public static WeakReference<ProductDetilsActivity> mInstance;
    @Override
    protected int setLayout() {
        return R.layout.activity_productdetils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mInstance=new WeakReference<>(this);
        topView.setTopText("宝贝详情");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

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
                clickposition=position;
                ProductDetilsFragment.mInstance.get().requestId(prizeId,productTitleInfo.getData().getIssues().get(position).getId());
            }
        });
    }

    @Override
    protected void doMain() {
        prizeId = getIntent().getIntExtra("prizeId", 0);
        issuePrizeId = getIntent().getIntExtra("issuePrizeId", 0);
        requestTitle();


    }

    private void requestTitle() {
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
                productTitleInfo = JSON.parseObject(response, ProductTitleInfo.class);
                if (productTitleInfo.getCode() == 0 && productTitleInfo.getData().getIssues().size() > 0) {
                    fragmentList = new ArrayList<>();
                    titleList = new ArrayList<>();
                    List = new ArrayList<>();
                    for (int i = 0; i < productTitleInfo.getData().getIssues().size(); i++) {
                        titleList.add(String.valueOf(productTitleInfo.getData().getIssues().get(i).getIssue()));
                    }
                    List.add(String.valueOf(productTitleInfo.getData().getIssues().get(0).getIssue()));
                    productAdapter.addAll(titleList);
                    productAdapter.setClickPosition(0);
                    fragmentList.add(ProductDetilsFragment.newInstance(prizeId,productTitleInfo.getData().getIssues().get(0).getId()));
                    ShopViewPagerAdapter viewPagerAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, List);
                    viewPager.setAdapter(viewPagerAdapter);
                }
            }
        });
    }

}
