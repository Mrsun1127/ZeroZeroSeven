package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.fragment.SnacksFragment;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllDingDanActivity extends BaseActivity {
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.topView)
    TopView topView;
    List<String> titleList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_alldingdan;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("我的订单");
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
        titleList.add("零食日用");
        titleList.add("电子数码");
        titleList.add("跳蚤市场");
        fragmentList.add(new SnacksFragment());
        fragmentList.add(new SnacksFragment());
        fragmentList.add(new SnacksFragment());
        viewPager.setOffscreenPageLimit(titleList.size());
        ShopViewPagerAdapter adapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
