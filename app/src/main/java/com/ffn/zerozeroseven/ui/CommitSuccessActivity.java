package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.view.TitleView;

/**
 * Created by GT on 2017/12/14.
 */

public class CommitSuccessActivity extends BaseActivity {
    @Override
    protected int setLayout() {
        return R.layout.activity_commitsuccess;
    }

    @Override
    public void initView() {
        TitleView titleView = findViewById(R.id.titleview);
        TextView tv_bottom = findViewById(R.id.tv_bottom);
        TextView tv_top = findViewById(R.id.tv_top);
        String info = getIntent().getStringExtra("info");
        if (!TextUtils.isEmpty(info)) {
            tv_bottom.setText(info);
            tv_top.setVisibility(View.GONE);
        }
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
        Button bt_sub = findViewById(R.id.bt_sub);
        bt_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void doMain() {

    }
}
