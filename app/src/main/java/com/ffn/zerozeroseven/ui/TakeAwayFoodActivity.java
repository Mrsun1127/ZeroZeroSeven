package com.ffn.zerozeroseven.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.fragment.FoodFragment;
import com.ffn.zerozeroseven.fragment.ShopFragment;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.wxapi.WXPayEntryActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TakeAwayFoodActivity extends BaseActivity {
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> fragmentList;
    private List<String> stringList;

    @Override
    protected int setLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void doMain() {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        if (stringList == null) {
            stringList = new ArrayList<>();
        }
        stringList.add("");
        fragmentList.add(FoodFragment.newInstance());
        ShopViewPagerAdapter viewPagerAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, stringList);
        viewpager.setAdapter(viewPagerAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharePrefUtils.saveObject(TakeAwayFoodActivity.this, "carShopInfo", BaseAppApplication.getInstance().getCarShopInfo());
    }
}
