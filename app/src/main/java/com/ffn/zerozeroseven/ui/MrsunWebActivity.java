package com.ffn.zerozeroseven.ui;

import android.text.TextUtils;
import android.widget.LinearLayout;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.fragment.MainFragment;
import com.ffn.zerozeroseven.view.TitleView;
import com.just.library.AgentWeb;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MrsunWebActivity extends BaseActivity {
    @Bind(R.id.titleView)
    TitleView titleView;
    @Bind(R.id.ll_web)
    LinearLayout ll_web;
    AgentWeb mAgentWeb;

    @Override
    protected int setLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
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
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)) {
            if (title.length() > 8) {
                titleView.setTopText(title.substring(0, 6) + "...");
            } else {
                titleView.setTopText(title);
            }
        }
    }

    @Override
    protected void doMain() {
        String url = getIntent().getStringExtra("url");
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(ll_web, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.destroyAndKill();
    }
}
