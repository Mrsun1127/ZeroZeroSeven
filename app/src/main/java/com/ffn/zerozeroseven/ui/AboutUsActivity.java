package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.view.TitleView;

/**
 * Created by GT on 2017/11/21.
 */

public class AboutUsActivity extends BaseActivity {
    @Override
    protected int setLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void doMain() {
        TitleView titleView=findViewById(R.id.titleview);
        titleView.setTopText("关于我们");
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
