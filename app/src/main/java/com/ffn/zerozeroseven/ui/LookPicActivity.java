package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.utlis.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LookPicActivity extends BaseActivity {
    @Bind(R.id.banner)
    Banner banner;

    @Override
    protected int setLayout() {
        return R.layout.activity_pic;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        banner.isAutoPlay(false);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                finish();
            }
        });
        ArrayList list = getIntent().getStringArrayListExtra("list");
        banner.setImages(list);
        banner.start();
    }

    @Override
    protected void doMain() {

    }
}
