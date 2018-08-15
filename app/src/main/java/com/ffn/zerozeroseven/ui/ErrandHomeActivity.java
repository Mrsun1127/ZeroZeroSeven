package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ExamplePagerAdapter;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.fragment.ErrandHelpMineRunFragment;
import com.ffn.zerozeroseven.fragment.ErrandMineRunFragment;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;
import com.ffn.zerozeroseven.view.mainscroll.RefreshingListenerAdapter;
import com.ffn.zerozeroseven.view.mainscroll.SmoothRefreshLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ErrandHomeActivity extends BaseActivity implements OnRefreshListener {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.refreshlayout)
    public SmartRefreshLayout refreshlayout;
    private static final String[] CHANNELS = new String[]{"我来跑腿", "帮我跑腿"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private List<Fragment> fragmentList = new ArrayList<>();
    public static WeakReference<ErrandHomeActivity> mInstance;

    @Override
    protected int setLayout() {
        return R.layout.errand_home;
    }

    @Override
    public void initView() {
        mInstance = new WeakReference<>(this);
        ButterKnife.bind(this);
        topView.setTopText("校园跑腿");
        topView.setTvRightText("订单");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                ZeroZeroSevenUtils.SwitchActivity(ErrandHomeActivity.this, ErrandDingdanActivity.class);
            }

            @Override
            public void Back() {
                finish();
            }
        });
        fragmentList.add(ErrandMineRunFragment.newInstance());
        fragmentList.add(ErrandHelpMineRunFragment.newInstance());
        ShopViewPagerAdapter shopViewPagerAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, mDataList);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(shopViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        refreshlayout.setOnRefreshListener(this);
        refreshlayout.setEnableLoadmore(false);
    }

    @Override
    protected void doMain() {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        ErrandMineRunFragment.mInstance.get().initDate();
    }
}
