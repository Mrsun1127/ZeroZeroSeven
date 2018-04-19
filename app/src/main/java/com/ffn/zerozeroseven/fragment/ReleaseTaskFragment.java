package com.ffn.zerozeroseven.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GT on 2018/1/25.
 */

public class ReleaseTaskFragment extends BaseFragment {
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpage)
    ViewPager viewpage;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        titleList.add("全部");
        titleList.add("待接单");
        titleList.add("派送中");
        titleList.add("已完成");
        fragmentList.add(new TaskRunTypeFragment(0));
        fragmentList.add(new TaskRunTypeFragment(1));
        fragmentList.add(new TaskRunTypeFragment(2));
        fragmentList.add(new TaskRunTypeFragment(3));
        viewpage.setOffscreenPageLimit(0);
        ShopViewPagerAdapter viewPagerAdapter = new ShopViewPagerAdapter(getChildFragmentManager(), fragmentList, titleList);
        viewpage.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewpage);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_releasetask;
    }

    @Override
    protected void lazyLoad() {

    }
}
