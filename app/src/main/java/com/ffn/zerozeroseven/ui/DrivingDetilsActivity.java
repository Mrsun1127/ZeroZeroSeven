package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ExamplePagerAdapter;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.fragment.DriverDetilsOneFragment;
import com.ffn.zerozeroseven.view.TopView;
import com.zhouwei.mzbanner.MZBannerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrivingDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

    @Bind(R.id.banner)
    MZBannerView banner;

    @Bind(R.id.tv_name)
    TextView tv_name;

    @Bind(R.id.tv_adr)
    TextView tv_adr;

    @Bind(R.id.rl_call)
    RelativeLayout rl_call;

    @Bind(R.id.tablayout)
    TabLayout tablayout;

    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private static final String[] CHANNELS = new String[]{"热门班型", "练车场地", "驾校简介", "考生须知"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_driver_detils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("驾校详情");
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
        fragmentList.add(new DriverDetilsOneFragment());
        fragmentList.add(new DriverDetilsOneFragment());
        fragmentList.add(new DriverDetilsOneFragment());
        fragmentList.add(new DriverDetilsOneFragment());
        ShopViewPagerAdapter adapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, mDataList);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }
}
