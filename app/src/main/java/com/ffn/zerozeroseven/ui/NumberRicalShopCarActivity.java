package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.NumberRicalCarAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;
import com.ffn.zerozeroseven.utlis.ToastUtils;
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
    @Bind(R.id.cb_all_click)
    CheckBox cb_all_click;
    private NumberRicalCarAdapter carAdapter;

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
        carAdapter = new NumberRicalCarAdapter(NumberRicalShopCarActivity.this);
        rc_car.setAdapter(carAdapter);
        final List<NumberRicalInfo.RicalInfo> numberRicalListInfo = BaseAppApplication.getInstance().getNumberRicalInfo().getNumberRicalListInfo();
        if(numberRicalListInfo!=null){
            if (numberRicalListInfo.size() > 0) {
                carAdapter.addAll(numberRicalListInfo);
            } else {
                ToastUtils.showShort("购物车暂无商品");
            }
        }else{
            ToastUtils.showShort("购物车暂无商品");
        }
        cb_all_click.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(numberRicalListInfo!=null){
                    if (numberRicalListInfo.size() > 0) {
                        if (b) {
                            for (int i = 0; i < numberRicalListInfo.size(); i++) {
                                if (!numberRicalListInfo.get(i).isChecked()) {
                                    numberRicalListInfo.get(i).setChecked(true);
                                }
                                carAdapter.cleanDates();
                                carAdapter.addAll(numberRicalListInfo);
                            }
                        } else {
                            for (int i = 0; i < numberRicalListInfo.size(); i++) {
                                if (numberRicalListInfo.get(i).isChecked()) {
                                    numberRicalListInfo.get(i).setChecked(false);
                                }
                                carAdapter.cleanDates();
                                carAdapter.addAll(numberRicalListInfo);
                            }
                        }
                    }
                }
            }
        });
    }

    @OnClick({R.id.bt_buy})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_buy:
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalShopCarActivity.this, NumberRicalCommitDingDanActivity.class);
                break;

        }
    }
}
