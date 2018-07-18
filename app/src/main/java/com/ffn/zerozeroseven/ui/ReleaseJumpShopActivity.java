package com.ffn.zerozeroseven.ui;

import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReleaseJumpShopActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

    @OnClick({R.id.tv_take_photo})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_take_photo:

                break;

        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_rlease_jump_shop;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("发布宝贝");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
    }

    @Override
    protected void doMain() {

    }
}
