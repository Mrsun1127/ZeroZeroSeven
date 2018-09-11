package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverClassTypeDetilsActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

    @Override
    protected int setLayout() {
        return R.layout.activity_class_detils;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("班型详情");
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

    @OnClick({R.id.bt_left, R.id.bt_right})
    void setOnClicks(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.bt_left:
                bundle.putString("title","预约");
                ZeroZeroSevenUtils.SwitchActivity(DriverClassTypeDetilsActivity.this,DriverCommitActivity.class,bundle);
                break;
            case R.id.bt_right:
                bundle.putString("title","报名");
                ZeroZeroSevenUtils.SwitchActivity(DriverClassTypeDetilsActivity.this,DriverCommitActivity.class,bundle);
                break;

        }
    }
}
