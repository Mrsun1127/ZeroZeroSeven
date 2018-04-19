package com.ffn.zerozeroseven.ui;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.view.TitleView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LevelActivity extends BaseActivity {
    @Bind(R.id.titleView)
    TitleView titleView;
    @Override
    protected int setLayout() {
        return R.layout.activity_level;
    }
    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        titleView.setTopText("等级说明");
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

    @Override
    protected void doMain() {
    }
}
