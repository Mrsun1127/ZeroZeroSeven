package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TableLayout;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.fragment.ProductDetilsFragment;
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
        viewPager.setOffscreenPageLimit(4);
    }

    @Override
    protected void doMain() {
        titleList = new ArrayList<>();
        titleList.add("164期");
        titleList.add("163期");
        titleList.add("162期");
        titleList.add("161期");
        fragmentList = new ArrayList<>();
        fragmentList.add(new ProductDetilsFragment());
        fragmentList.add(new ProductDetilsFragment());
        fragmentList.add(new ProductDetilsFragment());
        fragmentList.add(new ProductDetilsFragment());
        ShopViewPagerAdapter viewPagerAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(viewPagerAdapter);
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tableLayout.setupWithViewPager(viewPager);
    }


}
