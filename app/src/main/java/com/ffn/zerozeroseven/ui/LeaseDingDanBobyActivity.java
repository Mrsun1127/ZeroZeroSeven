package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baoyz.actionsheet.ActionSheet;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.LeaseDingDanDetisAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.LeaseBodyInfo;
import com.ffn.zerozeroseven.bean.LeaseStoreInfo;
import com.ffn.zerozeroseven.bean.RLeaseBodyInfo;
import com.ffn.zerozeroseven.bean.RquxiaoLeaseInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RsnackTuikuanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GT on 2017/11/19.
 */

public class LeaseDingDanBobyActivity extends BaseActivity implements ActionSheet.ActionSheetListener {

    private TextView tv_time;
    private TextView tv_staus;
    private TextView tv_allmoney;
    private TextView tv_endTime;
    private RecyclerView rc_shop;
    private LeaseDingDanDetisAdapter adapter;
    @Bind(R.id.bt_status)
    TextView bt_status;
    @Bind(R.id.tv_show)
    TextView tv_show;
    @Bind(R.id.tv_remark)
    TextView tv_remark;
    @Bind(R.id.bt_show)
    Button bt_show;
    private LeaseStoreInfo shangChangShowInfo;
    private LeaseBodyInfo info;
    private String refuseReson;
    private int orderId;

    @Override
    protected int setLayout() {
        return R.layout.activity_dingdanbody;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        tv_time = findViewById(R.id.tv_time);
        tv_staus = findViewById(R.id.tv_status);
        tv_allmoney = findViewById(R.id.tv_allmoney);
        tv_endTime = findViewById(R.id.tv_endTime);
        rc_shop = findViewById(R.id.rc_shop);
        rc_shop.setLayoutManager(new LinearLayoutManager(this));
        rc_shop.addItemDecoration(new SpaceItemDecoration(5));
        adapter = new LeaseDingDanDetisAdapter(this);
        rc_shop.setAdapter(adapter);
        TitleView titleView = findViewById(R.id.titleview);
        titleView.setTopText("订单详情");
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
        orderId = getIntent().getIntExtra("orderId", 0);
        requestDetils(orderId);
        getShangChangInfo();
    }

    @Bind(R.id.rl_peple)
    RelativeLayout rl_peple;
    @Bind(R.id.tv_peopleName)
    TextView tv_peopleName;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_finish)
    TextView tv_finish;

    private void getShangChangInfo() {
        final ShangchangInfo shangchangInfo = new ShangchangInfo();
        shangchangInfo.setFunctionName("QueryLeaseConfig");
        ShangchangInfo.ParametersBean parametersBean = new ShangchangInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        shangchangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(LeaseDingDanBobyActivity.this);
        okGoUtils.httpPostJSON(shangchangInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                shangChangShowInfo = JSON.parseObject(response, LeaseStoreInfo.class);
                if (shangChangShowInfo.getCode() == 0) {
                    tv_endTime.setText("联系客服:" + shangChangShowInfo.getData().getLeaseConfig().getServicePhone());
                }
            }
        });


    }

    private void moreComemore() {
        List<LeaseBodyInfo.DataBean.OrderGoodsListBean> item = info.getData().getOrderGoodsList();
        CarShopInfo carShopInfo = new CarShopInfo();
        List<CarShopInfo.ShopInfo> shopInfos = new ArrayList<>();
        for (int i = 0; i < item.size(); i++) {
            CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
            shopInfo.setBuyCount(item.get(i).getGoodsCount());
            shopInfo.setShopName(item.get(i).getGoodsName());
            shopInfo.setGoodsId(item.get(i).getGoodsId());
            shopInfo.setImagUrl(item.get(i).getGoodsThumb());
            shopInfo.setShopMoney(item.get(i).getGoodsPrice());
            shopInfos.add(shopInfo);
        }
        carShopInfo.setShopInfos(shopInfos);
        SharePrefUtils.saveObject(LeaseDingDanBobyActivity.this, "zhijiecarShopInfo", carShopInfo);
        ZeroZeroSevenUtils.SwitchActivity(LeaseDingDanBobyActivity.this, LeaseZhiJieCommitDingDanActivity.class);
    }

    private void requestDetils(int orderId) {
        RLeaseBodyInfo rLeaseBodyInfo = new RLeaseBodyInfo();
        rLeaseBodyInfo.setFunctionName("QueryLeaseOrder");
        RLeaseBodyInfo.ParametersBean parametersBean = new RLeaseBodyInfo.ParametersBean();
        parametersBean.setOrderId(String.valueOf(orderId));
        parametersBean.setUserId(userId);
        rLeaseBodyInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(LeaseDingDanBobyActivity.this);
        okGoUtils.httpPostJSON(rLeaseBodyInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                info = JSON.parseObject(response, LeaseBodyInfo.class);
                if (info.getCode() == 0) {
                    tv_time.setText("下单时间：" + info.getData().getCreateTime());

                    tv_staus.setText(info.getData().getOrderStatus() + "");
                    tv_allmoney.setText("共" + info.getData().getGoodsCount() + "个商品，合计¥：" + (info.getData().getGoodsPrice()));
                    adapter.cleanDates();
                    adapter.addAll(info.getData().getOrderGoodsList());
                    if (!TextUtils.isEmpty(info.getData().getPostscript())) {
                        tv_remark.setVisibility(View.VISIBLE);
                        tv_remark.setText("备注:" + info.getData().getPostscript());
                    }
                    try {
                        if (!TextUtils.isEmpty(info.getData().getDeliveryName())) {
                            rl_peple.setVisibility(View.VISIBLE);
                            tv_peopleName.setText(info.getData().getDeliveryName() + "为您服务");
                            tv_phone.setText(info.getData().getDeliveryPhone());
                        } else {
                            rl_peple.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {

                        rl_peple.setVisibility(View.GONE);

                    }
                    String s;
                    int i = 0;
                    i = info.getData().getOrderStatus();
                    switch (i) {
                        case -1:
                            s = "支付失败";
                            tv_show.setText("请重新支付");
                            tv_staus.setText("支付失败");
                            bt_show.setText("再来一单");
                            tv_finish.setVisibility(View.GONE);
                            rl_peple.setVisibility(View.GONE);
                            break;
                        case 0:
                            tv_show.setText("快把商品带回家");
                            s = "未支付";
                            tv_staus.setText("未支付");
                            bt_show.setText("去支付");
                            tv_finish.setVisibility(View.GONE);
                            rl_peple.setVisibility(View.GONE);
                            break;
                        case 1:
                            tv_show.setText("正在等待007接单");
                            tv_staus.setText("支付成功");
                            bt_show.setText("取消订单");
                            s = "支付成功";
                            tv_finish.setVisibility(View.GONE);
                            rl_peple.setVisibility(View.GONE);
                            break;
                        case 2:
                            s = "已接单";
                            tv_show.setText("007已接单 ,稍等片刻，美食即将到来");
                            tv_staus.setText("已接单");
                            bt_show.setText("取消订单");
                            tv_finish.setVisibility(View.GONE);
                            rl_peple.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            tv_show.setText("007已取货 ,稍等片刻，美食即将到来");
                            tv_staus.setText("已取货");
                            s = "已取货";
                            bt_show.setText("再来一单");
                            tv_finish.setVisibility(View.GONE);
                            rl_peple.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            tv_show.setText("您的商品已到达您的手中");
                            bt_show.setText("再来一单");
                            tv_staus.setText("已完成");
                            tv_finish.setVisibility(View.VISIBLE);
                            rl_peple.setVisibility(View.VISIBLE);
                            if (!TextUtils.isEmpty(info.getData().getShippingTime())) {
                                tv_finish.setText("完成时间: " + info.getData().getShippingTime());
                            }
                            s = "已完成";
                            break;
                        // 6=退款中，7=退款成功，8=拒绝退款
                        case 6:
                            tv_show.setText("商家正在处理退款");
                            tv_staus.setText("退款中");
                            s = "退款中";
                            bt_show.setText("再来一单");
                            tv_finish.setVisibility(View.GONE);
                            break;
                        case 7:
                            tv_show.setText("商家已经退款到您的支付账户中");
                            tv_staus.setText("退款成功");
                            s = "退款成功";
                            bt_show.setText("退款成功");
                            tv_finish.setVisibility(View.GONE);
                            break;
                        case 8:
                            tv_show.setText("商家拒绝退款");
                            tv_staus.setText("退款失败");
                            s = "退款失败";
                            bt_show.setText("退款失败");
                            tv_finish.setVisibility(View.VISIBLE);
                            tv_finish.setText("退款失败原因:");
                            break;
                        default:
                            tv_show.setText("异常单，请联系零零7");
                            tv_staus.setText("异常单");
                            bt_show.setText("再来一单");
                            s = "异常单";
                            break;
                    }
                    bt_status.setText(s);

                }
            }
        });

    }

    String[] items = new String[]{"还没想好，不想支付了", "价格太贵了", "服务质量不满意", "其他"};

    @OnClick({R.id.bt_show, R.id.tv_phone, R.id.tv_sug, R.id.tv_endTime})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_show:
                int status = info.getData().getOrderStatus();
                if (status == 1 || status == 2) {
                    ActionSheet.createBuilder(LeaseDingDanBobyActivity.this, getSupportFragmentManager())
                            .setCancelButtonTitle("取消")
                            .setOtherButtonTitles(items[0], items[1], items[2], items[3])
                            .setCancelableOnTouchOutside(true)
                            .setListener(this).show();
                } else {
                    moreComemore();
                }
                break;
            case R.id.tv_phone:
                if (Build.VERSION.SDK_INT >= 23) {

                    //判断有没有拨打电话权限
                    if (PermissionChecker.checkSelfPermission(LeaseDingDanBobyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        //请求拨打电话权限
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

                    } else {
                        callPhone(tv_phone.getText().toString());
                    }

                } else {
                    callPhone(tv_phone.getText().toString());
                }
                break;
            case R.id.tv_sug:
                Bundle bundle = new Bundle();
                bundle.putString("sugType", "people");
                bundle.putString("phone", tv_phone.getText().toString().trim());
                ZeroZeroSevenUtils.SwitchActivity(LeaseDingDanBobyActivity.this, SugActivity.class, bundle);
                break;
            case R.id.tv_endTime:
                if (Build.VERSION.SDK_INT >= 23) {

                    //判断有没有拨打电话权限
                    if (PermissionChecker.checkSelfPermission(LeaseDingDanBobyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        //请求拨打电话权限
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

                    } else {
                        callPhone(shangChangShowInfo.getData().getLeaseConfig().getServicePhone());
                    }

                } else {
                    callPhone(shangChangShowInfo.getData().getLeaseConfig().getServicePhone());
                }
                break;
        }
    }

    private final int REQUEST_CODE = 0x1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && PermissionChecker.checkSelfPermission(LeaseDingDanBobyActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            ToastUtils.showShort("授权成功,请重新拨打电话");
        }
    }

    private void callPhone(String phoneNum) {
        //直接拨号
        Uri uri = Uri.parse("tel:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        //此处不判断就会报错
        if (ActivityCompat.checkSelfPermission(LeaseDingDanBobyActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        actionSheet.dismiss();
        refuseReson = items[index];
        TuiKuan(refuseReson);
    }

    private void TuiKuan(String refuseReson) {
        RquxiaoLeaseInfo rsnackTuikuanInfo = new RquxiaoLeaseInfo();
        rsnackTuikuanInfo.setFunctionName("CancelLeaseGoodsOrder");
        RquxiaoLeaseInfo.ParametersBean parametersBean = new RquxiaoLeaseInfo.ParametersBean();
        parametersBean.setOrderId(String.valueOf(info.getData().getId()));
        parametersBean.setReason(refuseReson);
        parametersBean.setUserId(userId);
        rsnackTuikuanInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(LeaseDingDanBobyActivity.this);
        okGoUtils.httpPostJSON(rsnackTuikuanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    requestDetils(orderId);
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }
            }
        });
    }
    private void sureGet(){
        RquxiaoLeaseInfo rsnackTuikuanInfo = new RquxiaoLeaseInfo();
        rsnackTuikuanInfo.setFunctionName("ConfirmLeaseGoodsOrder");
        RquxiaoLeaseInfo.ParametersBean parametersBean = new RquxiaoLeaseInfo.ParametersBean();
        parametersBean.setOrderId(String.valueOf(info.getData().getId()));
        parametersBean.setUserId(userId);
        rsnackTuikuanInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(LeaseDingDanBobyActivity.this);
        okGoUtils.httpPostJSON(rsnackTuikuanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    requestDetils(orderId);
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }
            }
        });
    }
}
