package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.view.TitleView;

/**
 * Created by GT on 2017/11/27.
 */

public class PaySuccessActivity extends BaseActivity {
    @Override
    protected int setLayout() {
        return R.layout.activity_paysuccess;
    }

    @Override
    protected void doMain() {

    }

    @Override
    public void initView() {
        TitleView titleView=findViewById(R.id.titleview);
        titleView.setTopText("支付成功");
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
}
