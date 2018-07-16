package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JumpShopActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

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
        topView.setTopText("跳蚤市场");
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
}
