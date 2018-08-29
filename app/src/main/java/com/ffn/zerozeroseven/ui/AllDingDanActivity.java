package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.fragment.LeaseDingDanFragment;
import com.ffn.zerozeroseven.fragment.NumberRicalFragment;
import com.ffn.zerozeroseven.fragment.SnacksFragment;
import com.ffn.zerozeroseven.view.TopView;

import java.lang.ref.WeakReference;
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
    public static WeakReference<AllDingDanActivity> mIntance;

    @Override
    protected int setLayout() {
        return R.layout.activity_alldingdan;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mIntance = new WeakReference<>(this);
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

    public void goVp(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    protected void doMain() {
        titleList.add("零食外卖");
        titleList.add("电子数码");
        titleList.add("电脑租赁");
        fragmentList.add(new SnacksFragment());
        fragmentList.add(new NumberRicalFragment());
        fragmentList.add(new LeaseDingDanFragment());
        viewPager.setOffscreenPageLimit(titleList.size());
        ShopViewPagerAdapter adapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        goVp(getIntent().getIntExtra("position",0));
    }
}
