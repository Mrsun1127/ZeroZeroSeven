package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.CarShopGoodsAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.ShouHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.AllAdrInfo;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/27.
 */

public class LeaseZhiJieCommitDingDanActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_selectadr;
    private RelativeLayout rl_addadr;
    private RecyclerView rc_shop;
    private TextView tv_location;
    private TextView tv_username;
    private TextView tv_phone;
    private TextView tv_allmoney;
    private TextView tv_runMoney;
    private ShouHuoInfo shouHuoInfo;
    private CarShopInfo carShopInfo;
    private Double allMoney;
    private Double a;
    private String dormId;
    private CarShopGoodsAdapter adapter;
    private TextView money;
    private EditText et_beizhu;

    @Override
    protected int setLayout() {
        return R.layout.activity_commitdingdan;
    }

    @Override
    protected void doMain() {
        initAdr();
    }

    @Override
    public void initView() {
        carShopInfo = (CarShopInfo) SharePrefUtils.readObject(LeaseZhiJieCommitDingDanActivity.this, "zhijiecarShopInfo");
        rl_selectadr = findViewById(R.id.rl_selectadr);
        et_beizhu = findViewById(R.id.et_beizhu);
        rl_addadr = findViewById(R.id.rl_addadr);
        rl_selectadr.setOnClickListener(this);
        rl_addadr.setOnClickListener(this);
        findViewById(R.id.bt_pay).setOnClickListener(this);
        tv_location = findViewById(R.id.tv_location);
        tv_username = findViewById(R.id.tv_username);
        tv_phone = findViewById(R.id.tv_phone);
        money = findViewById(R.id.money);
        rc_shop = findViewById(R.id.rc_shop);
        tv_allmoney = findViewById(R.id.tv_allmoney);
        tv_runMoney = findViewById(R.id.tv_runMoney);
        tv_runMoney.setVisibility(View.GONE);
        rc_shop.setLayoutManager(new LinearLayoutManager(LeaseZhiJieCommitDingDanActivity.this));
        adapter = new CarShopGoodsAdapter(LeaseZhiJieCommitDingDanActivity.this);
        rc_shop.setAdapter(adapter);
        adapter.setOnItemAddViewClick(new CarShopGoodsAdapter.OnItemAddClick() {
            @Override
            public void onClick(View view, int position) {
                carShopInfo.getShopInfos().get(position).setBuyCount(carShopInfo.getShopInfos().get(position).getBuyCount() + 1);
                adapter.cleanDates();
                adapter.addAll(carShopInfo.getShopInfos());
                SharePrefUtils.saveObject(LeaseZhiJieCommitDingDanActivity.this, "zhijiecarShopInfo", carShopInfo);
                reactMoney();
            }
        });
        adapter.setOnItemCloseViewClick(new CarShopGoodsAdapter.OnItemCloseClick() {
            @Override
            public void onClick(View view, int position) {
                if (carShopInfo.getShopInfos().get(position).getBuyCount() < 2) {
                    carShopInfo.getShopInfos().remove(position);
                    adapter.cleanDates();
                    adapter.addAll(carShopInfo.getShopInfos());
                    SharePrefUtils.saveObject(LeaseZhiJieCommitDingDanActivity.this, "zhijiecarShopInfo", carShopInfo);
                    reactMoney();
                    return;
                }
                carShopInfo.getShopInfos().get(position).setBuyCount(carShopInfo.getShopInfos().get(position).getBuyCount() - 1);
                adapter.cleanDates();
                adapter.addAll(carShopInfo.getShopInfos());
                SharePrefUtils.saveObject(LeaseZhiJieCommitDingDanActivity.this, "zhijiecarShopInfo", carShopInfo);
                reactMoney();
            }
        });
        adapter.setOnItemImgViewClick(new CarShopGoodsAdapter.OnItemImgClick() {
            @Override
            public void onClick(View view, final int position) {
                final ConfirmDialog confirmDialog = new ConfirmDialog(LeaseZhiJieCommitDingDanActivity.this);
                confirmDialog.setTitles("提示");
                confirmDialog.setMessages("确定删除此商品？");
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        confirmDialog.dismiss();
                        carShopInfo.getShopInfos().remove(position);
                        adapter.cleanDates();
                        SharePrefUtils.saveObject(LeaseZhiJieCommitDingDanActivity.this, "zhijiecarShopInfo", carShopInfo);
                        reactMoney();
                    }

                    @Override
                    public void doCancel() {
                        confirmDialog.dismiss();
                    }
                });
            }
        });
        if (carShopInfo != null) {
            if (carShopInfo.getShopInfos().size() > 0) {
                adapter.addAll(carShopInfo.getShopInfos());
                allMoney = 0.0;
                for (int i = 0; i < carShopInfo.getShopInfos().size(); i++) {
                    allMoney = allMoney + carShopInfo.getShopInfos().get(i).getShopMoney() * carShopInfo.getShopInfos().get(i).getBuyCount();
                }
                tv_allmoney.setText("共计：¥" + ZeroZeroSevenUtils.reactMoney(allMoney));
                money.setText("" + ZeroZeroSevenUtils.reactMoney(allMoney));
            } else {
                ToastUtils.showShort("暂无可支付的商品");
            }
        } else {
            ToastUtils.showShort("暂无可支付的商品");
        }

        TitleView titleView = findViewById(R.id.titleview);
        titleView.setTopText("提交订单");
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

    public void reactMoney() {
        carShopInfo = (CarShopInfo) SharePrefUtils.readObject(LeaseZhiJieCommitDingDanActivity.this, "zhijiecarShopInfo");
        double a = 0.0;
        if (carShopInfo.getShopInfos().size() != 0) {
            for (int i = 0; i < carShopInfo.getShopInfos().size(); i++) {
                a = a + carShopInfo.getShopInfos().get(i).getShopMoney() * carShopInfo.getShopInfos().get(i).getBuyCount();
            }

            tv_allmoney.setText("共计：¥" + (ZeroZeroSevenUtils.reactMoney((a))));
            money.setText("" + (ZeroZeroSevenUtils.reactMoney((a))));
            return;
        }
        tv_allmoney.setText("共计：¥" + 0.0);
        money.setText("" + 0.0);
    }

    private void initAdr() {
        showLoadProgress();
        AllAdrInfo allAdrInfo = new AllAdrInfo();
        allAdrInfo.setFunctionName("ListUserAddress");
        httpPostJSON(allAdrInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                disLoadProgress();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                shouHuoInfo = JSON.parseObject(response.body().string(), ShouHuoInfo.class);
                rc_shop.post(new Runnable() {
                    @Override
                    public void run() {
                        if (shouHuoInfo.getCode() == 0) {
                            if (shouHuoInfo.getData().getAddresses().size() > 0) {
                                rl_addadr.setVisibility(View.GONE);
                                rl_selectadr.setVisibility(View.VISIBLE);
                                tv_location.setText(shouHuoInfo.getData().getAddresses().get(0).getContactSchool() + shouHuoInfo.getData().getAddresses().get(0).getContactBuilding() + shouHuoInfo.getData().getAddresses().get(0).getContactDorm());
                                tv_username.setText(shouHuoInfo.getData().getAddresses().get(0).getContactName());
                                tv_phone.setText(shouHuoInfo.getData().getAddresses().get(0).getContactPhone());
                                dormId = shouHuoInfo.getData().getAddresses().get(0).getContactBuilding();

                            } else {
                                rl_addadr.setVisibility(View.VISIBLE);
                                rl_selectadr.setVisibility(View.GONE);
                            }
                        }
                    }
                });

            }
        });
    }

    private void initAdrone(final int position) {
        showLoadProgress();
        AllAdrInfo allAdrInfo = new AllAdrInfo();
        allAdrInfo.setFunctionName("ListUserAddress");
        httpPostJSON(allAdrInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                disLoadProgress();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                shouHuoInfo = JSON.parseObject(response.body().string(), ShouHuoInfo.class);
                rc_shop.post(new Runnable() {
                    @Override
                    public void run() {
                        if (shouHuoInfo.getCode() == 0) {
                            if (shouHuoInfo.getData().getAddresses().size() > 0) {
                                rl_addadr.setVisibility(View.GONE);
                                rl_selectadr.setVisibility(View.VISIBLE);
                                tv_location.setText(shouHuoInfo.getData().getAddresses().get(position).getContactSchool() + shouHuoInfo.getData().getAddresses().get(position).getContactBuilding() + shouHuoInfo.getData().getAddresses().get(position).getContactDorm());
                                tv_username.setText(shouHuoInfo.getData().getAddresses().get(position).getContactName());
                                tv_phone.setText(shouHuoInfo.getData().getAddresses().get(position).getContactPhone());
                                dormId = shouHuoInfo.getData().getAddresses().get(position).getContactBuilding();
                            } else {
                                rl_addadr.setVisibility(View.VISIBLE);
                                rl_selectadr.setVisibility(View.GONE);


                            }
                        }
                    }
                });
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_selectadr:
                ZeroZeroSevenUtils.SwitchActivity(LeaseZhiJieCommitDingDanActivity.this, SelectAdrMannGerActivity.class, null, 2);
                break;
            case R.id.rl_addadr:
                ZeroZeroSevenUtils.SwitchActivity(LeaseZhiJieCommitDingDanActivity.this, SelectAdrMannGerActivity.class, null, 2);
                break;
            case R.id.bt_pay:
                carShopInfo = (CarShopInfo) SharePrefUtils.readObject(LeaseZhiJieCommitDingDanActivity.this, "zhijiecarShopInfo");
                if (shouHuoInfo.getData().getAddresses() != null && shouHuoInfo.getData().getAddresses().size() > 0) {
                    if (carShopInfo.getShopInfos().size() > 0) {
                        if (tv_location.getText().toString().trim().contains(userInfo.getSchoolName())) {
                            String reMark = et_beizhu.getText().toString().trim();
                            Bundle bundle = new Bundle();
                            bundle.putString("pay", "zhijie");
                            bundle.putString("name", tv_username.getText().toString());
                            bundle.putString("adr", tv_location.getText().toString());
                            bundle.putString("phone", tv_phone.getText().toString());
                            double d = 0.0;
                            try {
                                d = Double.parseDouble(money.getText().toString());
                            } catch (Exception e) {
                                BaseAppApplication.mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtils.showShort("网络加载数据异常，建议连接wifi再下单");
                                    }
                                });
                                return;
                            }
                            bundle.putDouble("allMoney", d);
                            bundle.putString("dormId", dormId);
                            bundle.putString("carType","leasezhijie");
                            bundle.putString("who","lease");
                            bundle.putString("payCate",getIntent().getStringExtra("payCate"));

                            if (!TextUtils.isEmpty(reMark)) {
                                bundle.putString("beizhu", reMark);
                            }
                            ZeroZeroSevenUtils.SwitchActivity(LeaseZhiJieCommitDingDanActivity.this, PayMoneyActivity.class, bundle);
                            finish();
                        } else {
                            ZeroZeroSevenUtils.showCustonPop(LeaseZhiJieCommitDingDanActivity.this, "请选择当前学校有关的地址", rl_selectadr);
                        }

                    } else {
                        ZeroZeroSevenUtils.showCustonPop(LeaseZhiJieCommitDingDanActivity.this, "购物车空空如也", rl_selectadr);
                    }

                } else {
                    ZeroZeroSevenUtils.showCustonPop(LeaseZhiJieCommitDingDanActivity.this, "请先选择收货地址", rl_selectadr);
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2:
                if (data.getStringExtra("position") != null) {
                    int position = Integer.parseInt(data.getStringExtra("position"));
                    initAdrone(position);
                }

                break;
            default:
                break;

        }
    }

}
