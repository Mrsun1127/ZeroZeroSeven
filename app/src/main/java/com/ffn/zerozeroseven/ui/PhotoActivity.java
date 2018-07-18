package com.ffn.zerozeroseven.ui;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoActivity extends BaseActivity {
    @Bind(R.id.photo)
    ImageView photo;

    @Override
    protected int setLayout() {
        return R.layout.activity_photo;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Glide.with(this).load(getIntent().getStringExtra("imgurl")).centerCrop().into(photo);
    }

    @OnClick({R.id.photo})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.photo:
                finish();
                break;

        }
    }

    @Override
    protected void doMain() {

    }
}
