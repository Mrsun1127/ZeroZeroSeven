package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RenzhengStatusActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;

    @Override
    protected int setLayout() {
        return R.layout.activity_status_renzheng;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("跑腿认证");
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
