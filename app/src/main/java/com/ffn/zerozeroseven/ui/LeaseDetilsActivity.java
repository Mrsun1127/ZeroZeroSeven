package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.GoodsContentShowInfo;
import com.ffn.zerozeroseven.bean.LeaseGoodsInfo;
import com.ffn.zerozeroseven.bean.ShangChangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ScreenUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.NXHooldeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by GT on 2017/11/24.
 */

public class LeaseDetilsActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_allmoney;
    private TextView tv_name;
    private TextView tv_count;
    private TextView tv_money;
    private TextView tv_kucun;
    private TextView tv_smallmoney;
    private TextView tv_addshopcar;
    private int countCompare;
    private Double runMoney;
    private String storeId;
    private LeaseGoodsInfo.DataBean.ListBean goodsInfo;
    private CarShopInfo lastCarShopInfo;
    boolean carBuy = true;//是否是购物车买
    private QBadgeView badgeView;
    private ImageView iv_zzs;
    private ImageButton ib_add;
    private ImageButton ib_lose;
    @Bind(R.id.tv_desc)
    TextView tv_desc;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    private String backType;
    private boolean isAdd = false;
    private String jumpType;

    @Override
    protected int setLayout() {
        return R.layout.activity_shopdetils;
    }

    @Override
    protected void doMain() {
        jumpType = getIntent().getStringExtra("type");
    }

    Double rmb;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        badgeView = new QBadgeView(this);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_zzs = findViewById(R.id.iv_zzs);
        badgeView.bindTarget(iv_zzs);
        ib_lose = findViewById(R.id.ib_lose);
        ib_add = findViewById(R.id.ib_add);
        ImageView iv_icon = findViewById(R.id.iv_icon);
        tv_allmoney = findViewById(R.id.tv_allmoney);
        tv_kucun = findViewById(R.id.tv_kucun);
        tv_count = findViewById(R.id.tv_count);
        tv_name = findViewById(R.id.tv_name);
        tv_money = findViewById(R.id.tv_peoplephone);
        tv_smallmoney = findViewById(R.id.tv_smallmoney);
        tv_smallmoney.setVisibility(View.GONE);
        tv_addshopcar = findViewById(R.id.tv_addshopcar);
        findViewById(R.id.tv_addshopcar).setOnClickListener(this);
        findViewById(R.id.bt_pay).setOnClickListener(this);
        iv_back.setOnClickListener(this);
        ib_lose.setOnClickListener(this);
        ib_add.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        tv_addshopcar.setOnClickListener(this);
        goodsInfo = getIntent().getParcelableExtra("shopInfo");
        Glide.with(LeaseDetilsActivity.this)
                .load(goodsInfo.getGoodsThumb())
                .error(R.drawable.oops)
                .override(ScreenUtils.getScreenWidth() / 3, ScreenUtils.getScreenHeight() / 4)
                .into(iv_icon);
        tv_kucun.setVisibility(View.INVISIBLE);
        tv_name.setText(goodsInfo.getGoodsName());
        if (!TextUtils.isEmpty(goodsInfo.getGoodsDesc())) {
            tv_desc.setText(goodsInfo.getGoodsDesc());
        } else {
            tv_desc.setText(goodsInfo.getGoodsName() + "值得你消费");
        }
        try {
            tv_money.setText("¥" + goodsInfo.getGoodsPrice());
            tv_kucun.setText("库存" + goodsInfo.getStoreCount());
        } catch (Exception e) {
        }
        tv_allmoney.setText("商品总价为 ¥0.0");
        String count = tv_kucun.getText().toString().trim();
        String count1 = "";
        for (int i = 0; i < count.length(); i++) {
            if (count.charAt(i) >= 47 && count.charAt(i) <= 57) {
                count1 += count.charAt(i);
            }
        }
        countCompare = Integer.parseInt(count1);
        lastCarShopInfo = (CarShopInfo) SharePrefUtils.readObject(LeaseDetilsActivity.this, "leasecarShopInfo");
        backType = getIntent().getStringExtra("back");
    }


    int tvCount = 0;

    //    @Override
//    public void onBackPressed() {
//            HomeActivity.getmInstance().get().go2Fragment(1);
//        LogUtils.D("backType","backType="+backType+"::是否添加购物车="+isAdd);
//        finish();
//    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && PermissionChecker.checkSelfPermission(LeaseDetilsActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            ToastUtils.showShort("授权成功,请重新拨打电话");
        }
    }

    private void callPhone(String phoneNum) {
        //直接拨号
        Uri uri = Uri.parse("tel:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        //此处不判断就会报错
        if (ActivityCompat.checkSelfPermission(LeaseDetilsActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }

    private final int REQUEST_CODE = 0x1001;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_phone:
                if (Build.VERSION.SDK_INT >= 23) {

                    //判断有没有拨打电话权限
                    if (PermissionChecker.checkSelfPermission(LeaseDetilsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        //请求拨打电话权限
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

                    } else {
                        callPhone(userInfo.getServicePhone());
                    }

                } else {
                    callPhone(userInfo.getServicePhone());
                }
                break;
            case R.id.iv_back:
//                    HomeActivity.getmInstance().get().go2Fragment(1);
                LogUtils.D("backType", "backType=" + backType + "::是否添加购物车=" + isAdd);
                finish();
                break;
            case R.id.ib_add:
                if (tvCount >= countCompare) {
                    ZeroZeroSevenUtils.showCustonPop(LeaseDetilsActivity.this, "商品已售完，请等待商家补充货源", ib_lose);
                    return;
                }
                tv_count.setText(++tvCount + "");
                tv_allmoney.setText("商品总价为 ¥" + ZeroZeroSevenUtils.reactMoney((goodsInfo.getGoodsPrice() * tvCount)));
                tvCount = Integer.parseInt(tv_count.getText().toString());
                addAction(ib_add, iv_zzs);
                BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        badgeView.setBadgeNumber(tvCount);
                    }
                }, 500);
                break;
            case R.id.ib_lose:
                if (tvCount <= 0) {
                    ZeroZeroSevenUtils.showCustonPop(LeaseDetilsActivity.this, "亲 再减就成负数了", ib_lose);
                    return;
                }
                tv_count.setText(--tvCount + "");
                tv_allmoney.setText("商品总价为 ¥" + ZeroZeroSevenUtils.reactMoney((goodsInfo.getGoodsPrice() * tvCount)));
                tvCount = Integer.parseInt(tv_count.getText().toString());
                addAction(iv_zzs, ib_lose);
                badgeView.setBadgeNumber(tvCount);
                break;
            case R.id.bt_pay:
                carBuy = false;
                buySinggerShop();
                break;
            case R.id.tv_addshopcar:
                AddCarInfo();
                break;

        }
    }

    private void AddCarInfo() {
        if (Integer.parseInt(tv_count.getText().toString()) > 0) {
            try {
                if (lastCarShopInfo.getShopInfos().size() > 0) {//说明购物车里面有东西
                    List<CarShopInfo.ShopInfo> list = lastCarShopInfo.getShopInfos();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getGoodsId() == goodsInfo.getId()) {
                            lastCarShopInfo.getShopInfos().get(i).setBuyCount(list.get(i).getBuyCount() + Integer.parseInt(tv_count.getText().toString()));
                            BaseAppApplication.getInstance().setCarShopInfo(lastCarShopInfo);
                            ToastUtils.showShort("添加成功");
                            return;
                        }
                    }
                    CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
                    shopInfo.setImagUrl(goodsInfo.getGoodsThumb());
                    shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
                    shopInfo.setRunMoney(runMoney);
                    shopInfo.setShopId(storeId);
                    shopInfo.setGoodsId(goodsInfo.getId());
                    shopInfo.setShopName(goodsInfo.getGoodsName());
                    shopInfo.setShopMoney(goodsInfo.getGoodsPrice());
                    list.add(shopInfo);
                    lastCarShopInfo.setShopInfos(list);
                    BaseAppApplication.getInstance().setCarShopInfo(lastCarShopInfo);
                    ToastUtils.showShort("添加成功");
                } else {//购物车里面的东西是空的
                    List<CarShopInfo.ShopInfo> list = new ArrayList<>();
                    CarShopInfo carShopInfo = new CarShopInfo();
                    CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
                    shopInfo.setImagUrl(goodsInfo.getGoodsThumb());
                    shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
                    shopInfo.setRunMoney(runMoney);
                    shopInfo.setShopId(storeId);
                    shopInfo.setGoodsId(goodsInfo.getId());
                    shopInfo.setShopName(goodsInfo.getGoodsName());
                    shopInfo.setShopMoney(goodsInfo.getGoodsPrice());
                    list.add(shopInfo);
                    carShopInfo.setShopInfos(list);
                    BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
                    ToastUtils.showShort("添加成功");
                }
            } catch (Exception e) {
                List<CarShopInfo.ShopInfo> list = new ArrayList<>();
                CarShopInfo carShopInfo = new CarShopInfo();
                CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
                shopInfo.setImagUrl(goodsInfo.getGoodsThumb());
                shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
                shopInfo.setRunMoney(runMoney);
                shopInfo.setShopId(storeId);
                shopInfo.setGoodsId(goodsInfo.getId());
                shopInfo.setShopName(goodsInfo.getGoodsName());
                shopInfo.setShopMoney(goodsInfo.getGoodsPrice());
                list.add(shopInfo);
                carShopInfo.setShopInfos(list);
                BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
                ToastUtils.showShort("添加成功");
            }
            isAdd = true;
        } else {
            ToastUtils.showShort("请选择添加购物车商品的数量");
        }
    }

    private void buySinggerShop() {
        if (Integer.parseInt(tv_count.getText().toString()) < 1) {
            ToastUtils.showShort("请选择购买商品的数量");
            return;
        }
        List<CarShopInfo.ShopInfo> list = new ArrayList<>();
        CarShopInfo carShopInfo = new CarShopInfo();
        CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
        shopInfo.setImagUrl(goodsInfo.getGoodsThumb());
        shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
        shopInfo.setRunMoney(runMoney);
        shopInfo.setShopId(storeId);
        shopInfo.setGoodsId(goodsInfo.getId());
        shopInfo.setShopName(goodsInfo.getGoodsName());
        shopInfo.setShopMoney(goodsInfo.getGoodsPrice());
        list.add(shopInfo);
        carShopInfo.setShopInfos(list);
        SharePrefUtils.saveObject(LeaseDetilsActivity.this, "zhijiecarShopInfo", carShopInfo);
        ZeroZeroSevenUtils.SwitchActivity(LeaseDetilsActivity.this, LeaseZhiJieCommitDingDanActivity.class);
    }

    public void addAction(View startView, View endView) {
        NXHooldeView nxHooldeView = new NXHooldeView(this);
        int position[] = new int[2];
        startView.getLocationInWindow(position);
        nxHooldeView.setStartPosition(new Point(position[0], position[1]));
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        rootView.addView(nxHooldeView);
        int endPosition[] = new int[2];
        endView.getLocationInWindow(endPosition);
        nxHooldeView.setEndPosition(new Point(endPosition[0], endPosition[1]));
        nxHooldeView.startBeizerAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharePrefUtils.saveObject(LeaseDetilsActivity.this, "leasecarShopInfo", BaseAppApplication.getInstance().getLeasecarShopInfo());
    }
}
