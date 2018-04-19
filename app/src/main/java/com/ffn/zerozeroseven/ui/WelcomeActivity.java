package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;


/*
* WelcomeActivity 引导页
* @author archerlee
* @time 16/7/6 15:33
*/
public class WelcomeActivity extends Activity implements ViewPager.OnPageChangeListener {




    private int[] images;
    private ArrayList<View> views;
    private ImageView[] indicators = null;
    private PagerAdapter pagerAdapter;
    private ViewPager mViewpage;
    private Button mStartButton;
    private LinearLayout mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.welcome);
        mViewpage = findViewById(R.id.viewpage);
        mStartButton = findViewById(R.id.start_button);
        mIndicator = findViewById(R.id.indicator);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shared = getSharedPreferences("lead", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putBoolean("First", false);
                editor.commit();
                ZeroZeroSevenUtils.SwitchActivity(WelcomeActivity.this,LoginActivity.class);
                finish();
            }
        });
        // 设置引导图片
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  仅需在这设置图片 指示器和page自动添加
        images = new int[]{R.drawable.leadone, R.drawable.leadtwo,
                R.drawable.leadthree};
        initData();
        requestSomePermission();
    }
    private void requestSomePermission() {

        // 先判断是否有权限。
        if (!AndPermission.hasPermission(WelcomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                !AndPermission.hasPermission(WelcomeActivity.this, Manifest.permission.READ_PHONE_STATE) ||
                !AndPermission.hasPermission(WelcomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                !AndPermission.hasPermission(WelcomeActivity.this, Manifest.permission.CAMERA)
                ) {
            // 申请权限。
            AndPermission.with(WelcomeActivity.this)
                    .requestCode(100)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CAMERA)
                    .send();
        }
    }
    private void initData() {
        views = new ArrayList<>();
        indicators = new ImageView[images.length]; // 定义指示器数组大小
        for (int i = 0; i < images.length; i++) {
            // 循环加入图片
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(images[i]);
            views.add(imageView);
            // 循环加入指示器
            indicators[i] = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            indicators[i].setLayoutParams(params);
            indicators[i].setBackgroundResource(R.drawable.indicators_now);
            if (i == 0) {
                indicators[i].setBackgroundResource(R.drawable.indicators_default);
            }
            mIndicator.addView(indicators[i]);
        }
        pagerAdapter = new BasePagerAdapter(views);
        mViewpage.setAdapter(pagerAdapter); // 设置适配器
        mViewpage.setOnPageChangeListener(this);
    }





    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {
        // 显示最后一个图片时显示按钮
        if (position == indicators.length - 1) {
            mStartButton.setVisibility(View.VISIBLE);
            mIndicator.setVisibility(View.GONE);
        } else {
            mStartButton.setVisibility(View.GONE);
            mIndicator.setVisibility(View.VISIBLE);

        }
        // 更改指示器图片
        for (int i = 0; i < indicators.length; i++) {
            indicators[position].setBackgroundResource(R.drawable.indicators_default);
            if (position != i) {
                indicators[i]
                        .setBackgroundResource(R.drawable.indicators_now);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class BasePagerAdapter extends PagerAdapter {
        private List<View> views = new ArrayList<>();

        public BasePagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(views.get(position));
            return views.get(position);
        }
    }
}
