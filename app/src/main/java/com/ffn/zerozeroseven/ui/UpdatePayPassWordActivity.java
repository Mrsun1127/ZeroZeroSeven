package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.view.TitleView;

/**
 * Created by GT on 2017/11/22.
 */

public class UpdatePayPassWordActivity extends BaseActivity {
    @Override
    protected int setLayout() {
        return R.layout.activity_updatepay;
    }

    @Override
    protected void doMain() {
        TitleView titleView=findViewById(R.id.titleview);
        titleView.setTopText("修改支付密码");
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
