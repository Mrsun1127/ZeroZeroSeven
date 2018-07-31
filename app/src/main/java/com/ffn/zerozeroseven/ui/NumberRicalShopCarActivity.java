package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.NumberRicalCarAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TopView;

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
    private List<NumberRicalInfo.RicalInfo> numberRicalListInfo;
    private NumberRicalInfo numberRicalInfo;
    @Bind(R.id.tv_money)
    TextView tv_money;

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

    private void notifyBottom() {
        tv_money.setText(String.valueOf(ZeroZeroSevenUtils.reactMoney(reactMoney())));
    }

    private double reactMoney() {
        boolean b = false;
        double a = 0.0;
        if (numberRicalListInfo == null) {
            a = 0.0;
        } else {
            if (numberRicalListInfo.size() > 0) {
                for (int i = 0; i < numberRicalListInfo.size(); i++) {
                    if (numberRicalListInfo.get(i).isChecked()) {
                        a = a + numberRicalListInfo.get(i).getCount() * numberRicalListInfo.get(i).getNeedsMoney();
                    } else {
                        b = true;
                    }
                }
                if (b) {
                    cb_all_click.setChecked(false);
                } else {
                    cb_all_click.setChecked(true);
                }
                if (a > 0.0) {
                    bt_buy.setBackgroundColor(getResources().getColor(R.color.tab_under_line));
                    bt_buy.setTextColor(getResources().getColor(R.color.white));
                } else {
                    bt_buy.setBackgroundColor(getResources().getColor(R.color.numberical_default_bg));
                    bt_buy.setTextColor(getResources().getColor(R.color.numberical_default_text));
                }
            }
        }
        return a;
    }

    @Override
    protected void doMain() {
        carAdapter = new NumberRicalCarAdapter(NumberRicalShopCarActivity.this);
        rc_car.setAdapter(carAdapter);
        numberRicalListInfo = BaseAppApplication.getInstance().getNumberRicalInfo().getNumberRicalListInfo();
        if (numberRicalListInfo != null) {
            if (numberRicalListInfo.size() > 0) {
                carAdapter.addAll(numberRicalListInfo);
                notifyBottom();
            } else {
                ToastUtils.showShort("购物车暂无商品");
            }
        } else {
            ToastUtils.showShort("购物车暂无商品");
        }
        numberRicalInfo = new NumberRicalInfo();
        carAdapter.setOnItemAddViewClick(new NumberRicalCarAdapter.OnItemAddClick() {
            @Override
            public void onClick(View view, int position) {
                numberRicalListInfo.get(position).setCount(numberRicalListInfo.get(position).getCount() + 1);
                carAdapter.cleanDates();
                carAdapter.addAll(numberRicalListInfo);
                numberRicalInfo.setNumberRicalListInfo(numberRicalListInfo);
                BaseAppApplication.getInstance().setNumberRicalInfo(numberRicalInfo);
                notifyBottom();
            }
        });
        carAdapter.setOnItemCloseViewClick(new NumberRicalCarAdapter.OnItemCloseClick() {
            @Override
            public void onClick(View view, int position) {
                numberRicalListInfo.get(position).setCount(numberRicalListInfo.get(position).getCount() + 1);
                if (numberRicalListInfo.get(position).getCount() < 2) {
                    numberRicalListInfo.remove(position);
                    carAdapter.cleanDates();
                    carAdapter.addAll(numberRicalListInfo);
                    numberRicalInfo.setNumberRicalListInfo(numberRicalListInfo);
                    BaseAppApplication.getInstance().setNumberRicalInfo(numberRicalInfo);
                    return;
                }
                numberRicalListInfo.get(position).setCount(numberRicalListInfo.get(position).getCount() - 1);
                carAdapter.cleanDates();
                carAdapter.addAll(numberRicalListInfo);
                numberRicalInfo.setNumberRicalListInfo(numberRicalListInfo);
                BaseAppApplication.getInstance().setNumberRicalInfo(numberRicalInfo);
                notifyBottom();
            }
        });
        carAdapter.setOnItemImgViewClick(new NumberRicalCarAdapter.OnItemImgClick() {
            @Override
            public void onClick(View view, int position) {
                if (numberRicalListInfo.get(position).isChecked()) {//选中状态
                    numberRicalListInfo.get(position).setChecked(false);
                } else {
                    numberRicalListInfo.get(position).setChecked(true);
                }
                carAdapter.cleanDates();
                carAdapter.addAll(numberRicalListInfo);
                numberRicalInfo.setNumberRicalListInfo(numberRicalListInfo);
                BaseAppApplication.getInstance().setNumberRicalInfo(numberRicalInfo);
                notifyBottom();
            }
        });
        cb_all_click.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (numberRicalListInfo != null) {
                    if (numberRicalListInfo.size() > 0) {
                        if (b) {
                            for (int i = 0; i < numberRicalListInfo.size(); i++) {
                                if (!numberRicalListInfo.get(i).isChecked()) {
                                    numberRicalListInfo.get(i).setChecked(true);
                                }
                                carAdapter.cleanDates();
                                carAdapter.addAll(numberRicalListInfo);
                                notifyBottom();
                            }
                        } else {
                            for (int i = 0; i < numberRicalListInfo.size(); i++) {
                                if (numberRicalListInfo.get(i).isChecked()) {
                                    numberRicalListInfo.get(i).setChecked(false);
                                }
                                carAdapter.cleanDates();
                                carAdapter.addAll(numberRicalListInfo);
                                notifyBottom();
                            }
                        }
                    }
                }
            }
        });
    }

    @Bind(R.id.bt_buy)
    Button bt_buy;

    @OnClick({R.id.bt_buy})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_buy:
                if ("0.0".equals(tv_money.getText().toString())) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("money", tv_money.getText().toString());
                ZeroZeroSevenUtils.SwitchActivity(NumberRicalShopCarActivity.this, NumberRicalCommitDingDanActivity.class, bundle);
                break;

        }
    }
}
