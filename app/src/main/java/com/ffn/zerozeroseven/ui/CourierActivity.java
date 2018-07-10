package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CuriousInfo;
import com.ffn.zerozeroseven.fragment.FinishFragment;
import com.ffn.zerozeroseven.fragment.PeiSongFragment;
import com.ffn.zerozeroseven.fragment.QiangDanFragment;
import com.ffn.zerozeroseven.fragment.YiChangFragment;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GT on 2017/12/5.
 */

public class CourierActivity extends BaseActivity {

    private TextView tv_place;
    private TextView tv_name;
    private TextView tv_bianhao;
    private TabLayout tab_level;
    private ViewPager vp_courier;
    private List<String> list_title;
    private List<Fragment> list_fragments;
    private FragmentPagerAdapter fAdapter;
    private String json;
    private CuriousInfo curiousInfo;
    private YiChangFragment yiChangFragment;

    @Override
    protected int setLayout() {
        return R.layout.activity_courier;
    }

    @Override
    public void initView() {
        json = userInfo.getCurisInfoJson();
        if(TextUtils.isEmpty(json)){
            ToastUtils.showShort("请重新登陆");
            ZeroZeroSevenUtils.SwitchActivity(this,LoginActivity.class);
            return;
        }
        curiousInfo = JSON.parseObject(json, CuriousInfo.class);
        tv_place = findViewById(R.id.tv_place);
        tv_name = findViewById(R.id.tv_name);
        tv_bianhao = findViewById(R.id.tv_bianhao);
        tab_level = findViewById(R.id.tab_level);
        vp_courier = findViewById(R.id.vp_courier);
        tab_level.setTabMode(TabLayout.MODE_FIXED);
        initTabs();
        initFragments();
        fAdapter = new ShopViewPagerAdapter(getSupportFragmentManager(), list_fragments, list_title);
        vp_courier.setOffscreenPageLimit(0);
        vp_courier.setAdapter(fAdapter);
        tab_level.setupWithViewPager(vp_courier);
        TitleView titleView=findViewById(R.id.titleview);
        titleView.setTopText("我是快递员");
        titleView.setOnTitleListener(new TitleView.OnTitleClickListener() {
            @Override
            public void ivBack() {
                finish();
            }

            @Override
            public void ivDown() {

            }

            @Override
            public void ivMessAge() {

            }
        });
    }

    private void initFragments() {
        list_fragments = new ArrayList<>();
        list_fragments.add(QiangDanFragment.newInstance(curiousInfo.getData().getId()));
        list_fragments.add(PeiSongFragment.newInstance(curiousInfo.getData().getId()));
        list_fragments.add(FinishFragment.newInstance(curiousInfo.getData().getId()));
        list_fragments.add(YiChangFragment.newInstance(curiousInfo.getData().getId()));
    }

    private void initTabs() {
        list_title = new ArrayList<>();
        list_title.add("抢单");
        list_title.add("配送中");
        list_title.add("已完成");
        list_title.add("异常单");
    }

    @Override
    protected void doMain() {

        tv_name.setText(curiousInfo.getData().getCourierName());
        if(!TextUtils.isEmpty(curiousInfo.getData().getOrderScope())){
            tv_place.setText(curiousInfo.getData().getOrderScope());
        }
        tv_bianhao.setText("快递员编号: " + curiousInfo.getData().getCourierNo());
        LogUtils.D("CourierActivity",curiousInfo.getData().getId()+"");
    }
}
