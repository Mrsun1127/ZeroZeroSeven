package com.ffn.zerozeroseven.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.fragment.ReleaseTaskFragment;
import com.ffn.zerozeroseven.fragment.RunDingDanFragment;
import com.ffn.zerozeroseven.view.TitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GT on 2017/11/22.
 */

public class MyRunActivity extends BaseActivity {
    @Bind(R.id.bt_release)
    Button release;
    @Bind(R.id.bt_mine)
    Button mine;
    @Bind(R.id.viewpage)
    ViewPager viewPager;
    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    @Override
    protected int setLayout() {
        return R.layout.activity_myrun;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        fragmentList.add(new RunDingDanFragment());
        fragmentList.add(new ReleaseTaskFragment());
        titleList.add("");
        titleList.add("");
        viewPager.setOffscreenPageLimit(fragmentList.size());
        ShopViewPagerAdapter viewPagerAdapter=new ShopViewPagerAdapter(getSupportFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i==0){
                    mine.setBackgroundResource(R.drawable.bg);
                    release.setBackgroundResource(R.color.line_color);
                }else{
                    mine.setBackgroundResource(R.color.line_color);
                    release.setBackgroundResource(R.drawable.bg);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void doMain() {
        TitleView titleView = findViewById(R.id.titleview);
        titleView.setTopText("我的跑腿");
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

    @OnClick({R.id.bt_mine, R.id.bt_release})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_mine:
                viewPager.setCurrentItem(0);
                mine.setBackgroundResource(R.drawable.circlebutton);
                release.setBackgroundResource(R.drawable.circlenorbutton);
                break;
            case R.id.bt_release:
                viewPager.setCurrentItem(1);
                mine.setBackgroundResource(R.drawable.circlenorbutton);
                release.setBackgroundResource(R.drawable.circlebutton);
                break;
        }
    }
}
