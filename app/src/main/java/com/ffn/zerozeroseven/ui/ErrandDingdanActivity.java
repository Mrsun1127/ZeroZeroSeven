package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ExamplePagerAdapter;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.fragment.RunYourDingDanFragment;
import com.ffn.zerozeroseven.fragment.RunnerDingDanFragment;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ErrandDingdanActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    private static final String[] CHANNELS = new String[]{"我来跑腿", "帮我跑腿"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_errand_dingdan;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("跑腿订单");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        fragmentList.add(new RunnerDingDanFragment());
        fragmentList.add(new RunYourDingDanFragment());
        ShopViewPagerAdapter adapter = new ShopViewPagerAdapter(getSupportFragmentManager(),fragmentList,mDataList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void doMain() {

    }
}
