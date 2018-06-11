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

    @Override
    protected int setLayout() {
        return R.layout.activity_productdetils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
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
            }
        });
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                showLoadProgress();
                BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        titleList.clear();
                        titleList.add("8");
                        titleList.add("9");
                        titleList.add("10");
                        titleList.add("11");
                        titleList.add("12");
                        productAdapter.addAll(titleList);
                    }
                },1500);

            }
        });

    }

    @Override
    protected void doMain() {
        prizeId = getIntent().getIntExtra("prizeId", 0);
        requestTitle();


    }

    private void requestTitle() {
        LastInteralInfo lastInteralInfo = new LastInteralInfo();
        lastInteralInfo.setFunctionName("ListPointIssuePrize");
        LastInteralInfo.ParametersBean parametersBean = new LastInteralInfo.ParametersBean();
        parametersBean.setUserPhone(userInfo.getPhone());
        parametersBean.setPrizeId(prizeId);
        lastInteralInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(ProductDetilsActivity.this);
        okGoUtils.httpPostJSON(lastInteralInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ProductTitleInfo productTitleInfo = JSON.parseObject(response, ProductTitleInfo.class);
                if (productTitleInfo.getCode() == 0) {
//                    if (productTitleInfo.getData().getIssuePrizes().size() > 0) {
                    fragmentList = new ArrayList<>();
                    titleList = new ArrayList<>();
                    List = new ArrayList<>();
                    List.add("第一期");
                    titleList.add("第一期");
                    titleList.add("第二期");
                    titleList.add("第三期");
                    titleList.add("第四期");
                    titleList.add("第五期");
                    titleList.add("第六期");
                    titleList.add("第七期");
                    productAdapter.addAll(titleList);
                    productAdapter.setClickPosition(0);
                    fragmentList.add(new ProductDetilsFragment());
                    ShopViewPagerAdapter viewPagerAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, List);
                    viewPager.setAdapter(viewPagerAdapter);
//                    }
                }

            }
        });
    }

}
