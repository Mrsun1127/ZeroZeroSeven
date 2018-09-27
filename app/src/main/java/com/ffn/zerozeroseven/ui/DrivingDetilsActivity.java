package com.ffn.zerozeroseven.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.DriverDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverDetilsInfo;
import com.ffn.zerozeroseven.fragment.DriverDetilsFourFragment;
import com.ffn.zerozeroseven.fragment.DriverDetilsOneFragment;
import com.ffn.zerozeroseven.fragment.DriverDetilsThreeFragment;
import com.ffn.zerozeroseven.fragment.DriverDetilsTwoFragment;
import com.ffn.zerozeroseven.utlis.GlideImageLoader;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrivingDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

    @Bind(R.id.banner)
    Banner banner;

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
    private String phoneNumber;
    public String driverId;
    public static WeakReference<DrivingDetilsActivity> mInstance;
    private DriverDetilsInfo driverDetilsInfo;
    public String driverName;

    @Override
    protected int setLayout() {
        return R.layout.activity_driver_detils;
    }

    public String getName() {
        return driverName;
    }

    public String getId() {
        return driverId;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mInstance = new WeakReference<>(this);
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
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        banner.isAutoPlay(false);
    }

    @Override
    protected void doMain() {
        driverId = getIntent().getStringExtra("driverId");
        requesetDate(driverId);

    }

    private List<String> images;

    private void requesetDate(String driverId) {
        RDriverDetilsInfo rDriverDetilsInfo = new RDriverDetilsInfo();
        rDriverDetilsInfo.setFunctionName("QueryDrivingSchool");
        RDriverDetilsInfo.ParametersBean parametersBean = new RDriverDetilsInfo.ParametersBean();
        parametersBean.setDrivingId(driverId);
        rDriverDetilsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(this);
        okGoUtils.httpPostJSON(rDriverDetilsInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                driverDetilsInfo = JSON.parseObject(response, DriverDetilsInfo.class);
                if (driverDetilsInfo.getCode() == 0) {
                    driverName = driverDetilsInfo.getData().getDrivingSchool().getName();
                    tv_name.setText(driverName);
                    tv_adr.setText(driverDetilsInfo.getData().getDrivingSchool().getAddress());
                    phoneNumber = driverDetilsInfo.getData().getDrivingSchool().getContact();
                    fragmentList.add(DriverDetilsOneFragment.newInstance(driverDetilsInfo));
                    fragmentList.add(DriverDetilsTwoFragment.newInstance(driverDetilsInfo));
                    fragmentList.add(DriverDetilsThreeFragment.newInstance(driverDetilsInfo.getData().getDrivingSchool().getContent()));
                    fragmentList.add(DriverDetilsFourFragment.newInstance(driverDetilsInfo.getData().getDrivingSchool().getNotice()));
                    ShopViewPagerAdapter adapter = new ShopViewPagerAdapter(getSupportFragmentManager(), fragmentList, mDataList);
                    viewpager.setAdapter(adapter);
                    viewpager.setOffscreenPageLimit(fragmentList.size());
                    tablayout.setupWithViewPager(viewpager);
                    if (driverDetilsInfo.getData().getDrivingSchool().getDrivingGalleryList() == null || driverDetilsInfo.getData().getDrivingSchool().getDrivingGalleryList().size() < 1) {
                        return;
                    }
                    if (images == null) {
                        images = new ArrayList<>();
                    }
                    int size = driverDetilsInfo.getData().getDrivingSchool().getDrivingGalleryList().size();
                    for (int i = 0; i < size; i++) {
                        images.add(driverDetilsInfo.getData().getDrivingSchool().getDrivingGalleryList().get(i).getPicUrl());
                    }
                    banner.setImages(images);
                    banner.start();
                }
            }
        });
    }

    @OnClick({R.id.rl_call, R.id.rl_ask, R.id.bt_talk})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.rl_call:
                ZeroZeroSevenUtils.requestCallMainifest(DrivingDetilsActivity.this);
                ZeroZeroSevenUtils.MakingCalls(DrivingDetilsActivity.this, phoneNumber);
                break;
            case R.id.bt_talk:
                ZeroZeroSevenUtils.requestCallMainifest(DrivingDetilsActivity.this);
                ZeroZeroSevenUtils.MakingCalls(DrivingDetilsActivity.this, phoneNumber);
                break;
            case R.id.rl_ask:
                ZeroZeroSevenUtils.SwitchActivity(DrivingDetilsActivity.this, JoinDriverSchoolActivity.class);
                break;
        }
    }
}
