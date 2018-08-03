package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ExamplePagerAdapter;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.fragment.ErrandMineRunFragment;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ErrandHomeActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    private static final String[] CHANNELS = new String[]{"我来跑腿", "帮我跑腿"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.errand_home;
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);
        topView.setTopText("校园跑腿");
        topView.setTvRightText("订单");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                ToastUtils.showShort("进入订单页面");
            }

            @Override
            public void Back() {
                finish();
            }
        });
        fragmentList.add(ErrandMineRunFragment.newInstance());
        fragmentList.add(ErrandMineRunFragment.newInstance());
        ShopViewPagerAdapter shopViewPagerAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, mDataList);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(shopViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void doMain() {

    }
}
