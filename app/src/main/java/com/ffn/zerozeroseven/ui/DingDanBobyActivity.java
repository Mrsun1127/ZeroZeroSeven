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
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.DingDanDetisAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.DingDanDetlsInfo;
import com.ffn.zerozeroseven.bean.ShangChangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.GoodsDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.TitleView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/19.
 */

public class DingDanBobyActivity extends BaseActivity {

    private TextView tv_time;
    private TextView tv_staus;
    private TextView tv_allmoney;
    private TextView tv_endTime;
    private RecyclerView rc_shop;
    private DingDanDetisAdapter adapter;
    @Bind(R.id.bt_status)
    Button bt_status;
    @Bind(R.id.tv_show)
    TextView tv_show;
    @Bind(R.id.tv_remark)
    TextView tv_remark;
    private ShangChangShowInfo shangChangShowInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_dingdanbody;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        BaseAppApplication.getInstance().addActivity(this);
        tv_time = findViewById(R.id.tv_time);
        tv_staus = findViewById(R.id.tv_status);
        tv_allmoney = findViewById(R.id.tv_allmoney);
        tv_endTime = findViewById(R.id.tv_endTime);
        rc_shop = findViewById(R.id.rc_shop);
        rc_shop.setLayoutManager(new LinearLayoutManager(this));
        rc_shop.addItemDecoration(new SpaceItemDecoration(5));
        adapter = new DingDanDetisAdapter(this);
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
        int orderId = getIntent().getIntExtra("orderId", 0);
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
        shangchangInfo.setFunctionName("QuerySchoolStore");
        ShangchangInfo.ParametersBean parametersBean = new ShangchangInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        shangchangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(DingDanBobyActivity.this);
        okGoUtils.httpPostJSON(shangchangInfo,true,false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                shangChangShowInfo = JSON.parseObject(response, ShangChangShowInfo.class);
                if(shangChangShowInfo.getCode()==0){
                    tv_endTime.setText("联系客服:"+ shangChangShowInfo.getData().getServicePhone());
                }
            }
        });
    }
    private void requestDetils(int orderId) {
        showLoadProgress();
        GoodsDetilsInfo detilsInfo = new GoodsDetilsInfo();
        detilsInfo.setFunctionName("QueryGoodsOrderDetail");
        GoodsDetilsInfo.ParametersBean parametersBean = new GoodsDetilsInfo.ParametersBean();
        parametersBean.setOrderId(orderId);
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        detilsInfo.setParameters(parametersBean);
        httpPostJSON(detilsInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                disLoadProgress();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                final DingDanDetlsInfo info = JSON.parseObject(response.body().string(), DingDanDetlsInfo.class);
                LogUtils.E("dingdan", JSON.toJSONString(info));
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (info.getCode() == 0) {
                            tv_time.setText("下单时间：" + info.getData().getOrder().getCreateTime());

                            tv_staus.setText(info.getData().getOrder().getStatus() + "");
                            tv_allmoney.setText("共" + info.getData().getOrder().getTotalCount() + "个商品，合计¥：" + (info.getData().getOrder().getTotalPrice() + info.getData().getOrder().getExtraPrice()) + "（含跑腿费）¥" + info.getData().getOrder().getExtraPrice());
                            adapter.addAll(info.getData().getOrder().getDetails());
                            if(!TextUtils.isEmpty(info.getData().getOrder().getRemark())){
                                tv_remark.setVisibility(View.VISIBLE);
                                tv_remark.setText("备注:"+info.getData().getOrder().getRemark());
                            }
                            try {
                                if (!TextUtils.isEmpty(info.getData().getCourier().getCourierName())) {
                                    rl_peple.setVisibility(View.VISIBLE);
                                    tv_peopleName.setText(info.getData().getCourier().getCourierName() + "为您服务");
                                    tv_phone.setText(info.getData().getCourier().getPhone());
                                } else {
                                    rl_peple.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                BaseAppApplication.mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        rl_peple.setVisibility(View.GONE);
                                    }
                                });
                            }
                            String s;
                            int i = 0;
                            try {
                                i = info.getData().getCourier().getStatus();
                            } catch (Exception e) {
                                i = info.getData().getOrder().getStatus();
                            }
                            switch (i) {
                                case -1:
                                    s = "支付失败";
                                    tv_show.setText("请重新支付");
                                    tv_staus.setText("支付失败");
                                    tv_finish.setVisibility(View.GONE);
                                    rl_peple.setVisibility(View.GONE);
                                    break;
                                case 0:
                                    tv_show.setText("快把商品带回家");
                                    s = "未支付";
                                    tv_staus.setText("未支付");
                                    tv_finish.setVisibility(View.GONE);
                                    rl_peple.setVisibility(View.GONE);
                                    break;
                                case 1:
                                    tv_show.setText("正在等待007接单");
                                    tv_staus.setText("支付成功");
                                    s = "支付成功";
                                    tv_finish.setVisibility(View.GONE);
                                    rl_peple.setVisibility(View.GONE);
                                    break;
                                case 2:
                                    s = "已接单";
                                    tv_show.setText("007已接单 ,稍等片刻，美食即将到来");
                                    tv_staus.setText("已接单");
                                    tv_finish.setVisibility(View.GONE);
                                    rl_peple.setVisibility(View.VISIBLE);
                                    break;
                                case 3:
                                    tv_show.setText("007已取货 ,稍等片刻，美食即将到来");
                                    tv_staus.setText("已取货");
                                    s = "已取货";
                                    tv_finish.setVisibility(View.GONE);
                                    rl_peple.setVisibility(View.VISIBLE);
                                    break;
                                case 4:
                                    tv_show.setText("您的商品已到达您的手中");
                                    tv_staus.setText("已完成");
                                    tv_finish.setVisibility(View.VISIBLE);
                                    rl_peple.setVisibility(View.VISIBLE);
                                    if (!TextUtils.isEmpty(info.getData().getOrder().getUpdateTime())) {
                                        tv_finish.setText("完成时间: " + info.getData().getOrder().getUpdateTime());
                                    }
                                    s = "已完成";
                                    break;
                                default:
                                    tv_show.setText("异常单，请联系零零7");
                                    tv_staus.setText("异常单");
                                    s = "异常单";
                                    break;
                            }
                            bt_status.setText(s);

                        }
                    }
                });
            }
        });
    }

    @OnClick({R.id.tv_phone, R.id.tv_sug, R.id.tv_endTime})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_phone:
                if (Build.VERSION.SDK_INT >= 23) {

                    //判断有没有拨打电话权限
                    if (PermissionChecker.checkSelfPermission(DingDanBobyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

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
                ZeroZeroSevenUtils.SwitchActivity(DingDanBobyActivity.this, SugActivity.class, bundle);
                break;
            case R.id.tv_endTime:
                if (Build.VERSION.SDK_INT >= 23) {

                    //判断有没有拨打电话权限
                    if (PermissionChecker.checkSelfPermission(DingDanBobyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        //请求拨打电话权限
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

                    } else {
                        callPhone(shangChangShowInfo.getData().getServicePhone());
                    }

                } else {
                    callPhone(shangChangShowInfo.getData().getServicePhone());
                }
                break;
        }
    }

    private final int REQUEST_CODE = 0x1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && PermissionChecker.checkSelfPermission(DingDanBobyActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            ToastUtils.showShort("授权成功,请重新拨打电话");
        }
    }

    private void callPhone(String phoneNum) {
        //直接拨号
        Uri uri = Uri.parse("tel:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        //此处不判断就会报错
        if (ActivityCompat.checkSelfPermission(DingDanBobyActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }
}
