package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.NumberRicalCarAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberRicalShopCarActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.rc_car)
    RecyclerView rc_car;

    @Override
    protected int setLayout() {
        return R.layout.activity_numberical_shopcar;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("购物车");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        rc_car.setLayoutManager(new LinearLayoutManager(NumberRicalShopCarActivity.this));
        rc_car.addItemDecoration(new SpaceItemDecoration(2));
    }

    @Override
    protected void doMain() {
        NumberRicalCarAdapter carAdapter = new NumberRicalCarAdapter(NumberRicalShopCarActivity.this);
        rc_car.setAdapter(carAdapter);
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
        carAdapter.addAll(s);

    }
    @OnClick({R.id.bt_buy})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_buy:
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalShopCarActivity.this,NumberRicalCommitDingDanActivity.class);
            break;

        }
    }
}
