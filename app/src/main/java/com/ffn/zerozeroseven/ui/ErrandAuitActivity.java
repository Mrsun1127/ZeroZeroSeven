package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ExamplePagerAdapter;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.fragment.ErrandRenzhen1Fragment;
import com.ffn.zerozeroseven.fragment.ErrandRenzhen2Fragment;
import com.ffn.zerozeroseven.fragment.ErrandRenzhen3Fragment;
import com.ffn.zerozeroseven.view.NoScrollViewPager;
import com.ffn.zerozeroseven.view.TopView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ErrandAuitActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    NoScrollViewPager viewpager;
    private static final String[] CHANNELS = new String[]{"1、完善个人信息", "2、缴纳平台押金", "3、认证审核"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private List<Fragment> fragmentList = new ArrayList<>();
    public static WeakReference<ErrandAuitActivity> mInstance;

    @Override
    protected int setLayout() {
        return R.layout.errand_renzheng;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mInstance = new WeakReference<>(this);
        topView.setTopText("跑腿认证");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        fragmentList.add(ErrandRenzhen1Fragment.newInstance());
        fragmentList.add(ErrandRenzhen2Fragment.newInstance());
        fragmentList.add(ErrandRenzhen3Fragment.newInstance());
        ShopViewPagerAdapter adapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, mDataList);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }

    public void goVp(int position) {
        viewpager.setCurrentItem(position);
    }

    @Override
    protected void doMain() {

    }
}
