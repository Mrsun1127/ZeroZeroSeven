package com.ffn.zerozeroseven.ui;


import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.DriverHomeAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.DriverCountInfo;
import com.ffn.zerozeroseven.bean.DriverLocalInfo;
import com.ffn.zerozeroseven.bean.DriverSchoolMainInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDiverDistanceInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverCountInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDriverLocal;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.StateLayout;
import com.ffn.zerozeroseven.view.TopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DrivingSchoolActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    public static WeakReference<DrivingSchoolActivity> mInstance;
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.rg_group)
    RadioGroup rg_group;
    @Bind(R.id.stateLayout)
    StateLayout stateLayout;
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.tv_count)
    TextView tv_count;
    private DriverHomeAdapter driverHomeAdapter;
    private DriverSchoolMainInfo driverSchoolMainInfo;
    public String[] split = new String[2];
    private LocationManager locationManager;
    private int index = 0;
    private int type = 0;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    @Override
    protected int setLayout() {
        return R.layout.activity_driver;
    }


    @Override
    protected void doMain() {
        mInstance = new WeakReference<>(this);
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setOpenGps(true);
        option.setIgnoreKillProcess(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        requestCount();
    }

    private void requestLocal(final int index, int type) {
        RDriverLocal rDriverLocal = new RDriverLocal();
        rDriverLocal.setFunctionName("ListDrivingByLocal");
        RDriverLocal.ParametersBean parametersBean = new RDriverLocal.ParametersBean();
        parametersBean.setPageSize(20);
        parametersBean.setPageIndex(index);
        if (type != 0) {
            parametersBean.setSortBy("price");
        }
        rDriverLocal.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(DrivingSchoolActivity.this);
        okGoUtils.httpPostJSON(rDriverLocal, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                DriverLocalInfo driverLocalInfo = JSON.parseObject(response, DriverLocalInfo.class);
                refreshlayout.finishRefresh();
                if (driverLocalInfo.getCode() == 0 && driverLocalInfo.getData().getList() != null && driverLocalInfo.getData().getList().size() > 0) {
                    if (index == 0) {
                        stateLayout.setVisibility(View.GONE);
                        refreshlayout.setVisibility(View.VISIBLE);
                        driverHomeAdapter.cleanDates();
                    } else {
                        refreshlayout.finishLoadmore();
                    }
                    driverHomeAdapter.addAll(driverLocalInfo.getData().getList());
                } else {
                    if (index == 0) {
                        driverHomeAdapter.cleanDates();
                        stateLayout.setVisibility(View.VISIBLE);
                        stateLayout.showError(StateLayout.noData);
                        refreshlayout.setVisibility(View.GONE);
                    } else {
                        refreshlayout.finishLoadmore();
                        ToastUtils.showShort("没有更多数据了");
                    }
                }
            }
        });
    }

    public void requestDistance(final int index) {

        RDiverDistanceInfo rDiverDistanceInfo = new RDiverDistanceInfo();
        rDiverDistanceInfo.setFunctionName("ListDrivingByNearby");
        RDiverDistanceInfo.ParametersBean parametersBean = new RDiverDistanceInfo.ParametersBean();
        parametersBean.setLatitude(split[1]);
//        parametersBean.setLatitude("28.000000000000000");
        parametersBean.setLongitude(split[0]);
//        parametersBean.setLongitude("113.000000000000000");
        rDiverDistanceInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(DrivingSchoolActivity.this);
        okGoUtils.httpPostJSON(rDiverDistanceInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                DriverLocalInfo driverLocalInfo = JSON.parseObject(response, DriverLocalInfo.class);
                refreshlayout.finishRefresh();
                if (driverLocalInfo.getCode() == 0 && driverLocalInfo.getData().getList() != null && driverLocalInfo.getData().getList().size() > 0) {
                    if (index == 0) {
                        stateLayout.setVisibility(View.GONE);
                        refreshlayout.setVisibility(View.VISIBLE);
                        driverHomeAdapter.cleanDates();
                    } else {
                        refreshlayout.finishLoadmore();
                    }
                    driverHomeAdapter.addAll(driverLocalInfo.getData().getList());
                } else {
                    if (index == 0) {
                        stateLayout.setVisibility(View.VISIBLE);
                        stateLayout.showError(StateLayout.noData);
                        refreshlayout.setVisibility(View.GONE);
                        driverHomeAdapter.cleanDates();
                    } else {
                        refreshlayout.finishLoadmore();
                        ToastUtils.showShort("没有更多数据了");
                    }
                }
            }
        });
    }

    private void requestCount() {
        RDriverCountInfo rDriverCountInfo = new RDriverCountInfo();
        rDriverCountInfo.setFunctionName("QueryStatistics");
        OkGoUtils okGoUtils = new OkGoUtils(this);
        okGoUtils.httpPostJSON(rDriverCountInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                DriverCountInfo driverCountInfo = JSON.parseObject(response, DriverCountInfo.class);
                if (driverCountInfo.getCode() == 0) {
                    tv_count.setText(String.valueOf(driverCountInfo.getData().getCount()));
                }
            }
        });
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("校园驾校");
        topView.setTvRightText("订单");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                ZeroZeroSevenUtils.SwitchActivity(DrivingSchoolActivity.this, DriverDingDanListActivity.class);
            }

            @Override
            public void Back() {
                finish();
            }
        });
        stateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                stateLayout.setVisibility(View.GONE);
                if (type == 3) {
                    requestDistance(index);
                } else {
                    requestLocal(index, type);
                }
            }
        });
        refreshlayout.setOnLoadmoreListener(this);
        refreshlayout.setOnRefreshListener(this);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.addItemDecoration(new SpaceItemDecoration(1));
        driverHomeAdapter = new DriverHomeAdapter(this);
        recycleview.setAdapter(driverHomeAdapter);
        driverHomeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putString("driverId", String.valueOf(driverHomeAdapter.getItem(position).getId()));
                ZeroZeroSevenUtils.SwitchActivity(DrivingSchoolActivity.this, DrivingDetilsActivity.class, bundle);
            }
        });
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_distance:
                        if (TextUtils.isEmpty(split[0])) {
                            ToastUtils.showShort("正在获取您的位置");
                            return;
                        }
                        index = 0;
                        type = 3;
                        requestDistance(index);
                        break;
                    case R.id.rb_price:
                        type = 1;
                        requestLocal(index, type);
                        break;
                    case R.id.rb_other:
                        type = 0;
                        requestLocal(index, type);
                        break;
                }
            }
        });

    }

    @OnClick({R.id.bt_liucheng, R.id.bt_xuzhi})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_liucheng:
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://www.lingling7.com/lingling7-res/app/driving/driving-process.html");
                bundle.putString("title", "学车流程");
                ZeroZeroSevenUtils.SwitchActivity(DrivingSchoolActivity.this, MrsunWebActivity.class, bundle);
                break;
            case R.id.bt_xuzhi:
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", "https://www.lingling7.com/lingling7-res/app/driving/admission-Information.html");
                bundle1.putString("title", "报名须知");
                ZeroZeroSevenUtils.SwitchActivity(DrivingSchoolActivity.this, MrsunWebActivity.class, bundle1);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null && myListener != null) {
            mLocationClient.unRegisterLocationListener(myListener);
        }
        myListener = null;
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        index = 0;
        if (type == 1 || type == 0) {
            requestLocal(index, type);
        } else {
            requestDistance(index);
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        index++;
        if (type == 1 || type == 0) {
            requestLocal(index, type);
        } else {
            requestDistance(index);
        }
    }

    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            split[0] = String.valueOf(longitude);
            split[1] = String.valueOf(latitude);
            requestLocal(index, type);

        }
    }

}



