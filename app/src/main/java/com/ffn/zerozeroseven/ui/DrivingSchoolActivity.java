package com.ffn.zerozeroseven.ui;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
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
import com.ffn.zerozeroseven.utlis.LogUtils;
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
import com.yanzhenjie.permission.AndPermission;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;


public class DrivingSchoolActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
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
    private String[] split;
    private LocationManager locationManager;
    private int index = 0;
    private int type = 0;

    @Override
    protected int setLayout() {
        return R.layout.activity_driver;
    }

    @Override
    protected void doMain() {
        String jingWeidu = getLngAndLat(getApplicationContext());
        split = jingWeidu.split(",");
        if (split.length < 1) {
            ToastUtils.showShort("请打开Gps方便定位");
        }
        requestCount();
        requestLocal(index, type);
    }

    private void requestLocal(final int index, int type) {
        RDriverLocal rDriverLocal = new RDriverLocal();
        rDriverLocal.setFunctionName("ListDrivingByLocal");
        RDriverLocal.ParametersBean parametersBean = new RDriverLocal.ParametersBean();
        parametersBean.setPageSize(20);
        parametersBean.setPageIndex(index);
        if (type != 0) {
            parametersBean.setSortBy("1");
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
        parametersBean.setSortBy("1");
//        parametersBean.setLatitude(Double.valueOf(split[0]));
        parametersBean.setLatitude("28.000000000000000");
//        parametersBean.setLongitude(Double.valueOf(split[1]));
        parametersBean.setLongitude("113.000000000000000");
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
                bundle.putString("driverId", String.valueOf(driverHomeAdapter.getItem(position).getDrivingId()));
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

    //获取权限
    public void requestPermisson() {
        AndPermission.with(DrivingSchoolActivity.this)
                .requestCode(100)
                .permission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .send();
    }

    //获取经纬度
    private String getLngAndLat(Context context) {
        double latitude = 0.0;
        double longitude = 0.0;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  //从gps获取经纬度
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermisson();
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            } else {//当GPS信号弱没获取到位置的时候又从网络获取
                return getLngAndLatWithNetwork();
            }
        } else {    //从网络获取经纬度
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }
//        requestDate(postUrl, String.valueOf(longitude), String.valueOf(latitude), false, 0, 6);
        return longitude + "," + latitude;
    }

    //从网络获取经纬度
    public String getLngAndLatWithNetwork() {
        double latitude = 0.0;
        double longitude = 0.0;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermisson();
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
//        requestDate(postUrl, String.valueOf(longitude), String.valueOf(latitude), false, 0, 6);
        return longitude + "," + latitude;
    }

    static LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

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
}



