package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TableLayout;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.ProductTitleInfo;
import com.ffn.zerozeroseven.bean.requsetbean.LastInteralInfo;
import com.ffn.zerozeroseven.fragment.ProductDetilsFragment;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetilsActivity extends BaseActivity {
    @Bind(R.id.tab_level)
    TabLayout tableLayout;
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private int prizeId;

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
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tableLayout.setupWithViewPager(viewPager);
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
                    if (productTitleInfo.getData().getIssuePrizes().size() > 0) {
                        fragmentList = new ArrayList<>();
                        titleList = new ArrayList<>();
                        if (productTitleInfo.getData().getIssuePrizes().size() < 4) {
                            viewPager.setOffscreenPageLimit(productTitleInfo.getData().getIssuePrizes().size());
                            for (int i = 0; i < productTitleInfo.getData().getIssuePrizes().size(); i++) {
                                titleList.add(String.valueOf(productTitleInfo.getData().getIssuePrizes().get(i).getIssue()));
                                fragmentList.add(new ProductDetilsFragment());
                            }
                        } else {
                            viewPager.setOffscreenPageLimit(4);
                            for (int i = 0; i < 4; i++) {
                                titleList.add(String.valueOf(productTitleInfo.getData().getIssuePrizes().get(i).getIssue()));
                                fragmentList.add(new ProductDetilsFragment());
                            }
                        }
                        ShopViewPagerAdapter viewPagerAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
                        viewPager.setAdapter(viewPagerAdapter);
                    }
                }

            }
        });
    }

}
