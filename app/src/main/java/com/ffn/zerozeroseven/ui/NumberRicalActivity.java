package com.ffn.zerozeroseven.ui;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.NumberRicalScrollAdapter;
import com.ffn.zerozeroseven.adapter.NumberRicalVerticalAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberRicalActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.rc_scroll)
    RecyclerView scroll;
    @Bind(R.id.rc_vetical)
    RecyclerView vetical;
    @Bind(R.id.iv_up)
    ImageView iv_up;
    @Bind(R.id.iv_shopcar)
    ImageView iv_shopcar;
    @Bind(R.id.scrollview)
    NestedScrollView scrollview;

    @Override
    protected int setLayout() {
        return R.layout.activity_numberrical;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("数码市场");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NumberRicalActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        scroll.setLayoutManager(linearLayoutManager);
        vetical.setLayoutManager(new LinearLayoutManager(NumberRicalActivity.this));
        vetical.addItemDecoration(new SpaceItemDecoration(2));
    }

    @Override
    protected void doMain() {
        NumberRicalScrollAdapter scrollAdapter = new NumberRicalScrollAdapter(NumberRicalActivity.this);
        scroll.setAdapter(scrollAdapter);
        NumberRicalVerticalAdapter verticalAdapter = new NumberRicalVerticalAdapter(NumberRicalActivity.this);
        vetical.setAdapter(verticalAdapter);
        List<String> s = new ArrayList<>();
        s.add("");
        s.add("");
        s.add("");
        s.add("");
        s.add("");
        s.add("");
        s.add("");
        s.add("");
        s.add("");
        scrollAdapter.addAll(s);
        verticalAdapter.addAll(s);
    }

    @OnClick({R.id.iv_up, R.id.iv_shopcar})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.iv_up:
                scrollview.scrollTo(0, 0);
                break;
            case R.id.iv_shopcar:
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalActivity.this, NumberRicalShopCarActivity.class);
                break;
        }
    }
}
