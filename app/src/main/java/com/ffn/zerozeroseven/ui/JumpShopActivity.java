package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;


import butterknife.ButterKnife;

public class JumpShopActivity extends BaseActivity {


    @Override
    protected int setLayout() {
        return R.layout.activity_jump_shop;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void doMain() {

    }
}
