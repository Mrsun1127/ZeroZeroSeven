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
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.DriverHomeAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.DriverSchoolMainInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
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
    private DriverHomeAdapter driverHomeAdapter;
    private DriverSchoolMainInfo driverSchoolMainInfo;
    private String[] split;
    private LocationManager locationManager;

    @Override
    protected int setLayout() {
        return R.layout.activity_driver;
    }

    @Override
    protected void doMain() {
        String jingWeidu = getLngAndLat(this);
        split = jingWeidu.split(",");
        if (split.length < 1) {
            ToastUtils.showShort("请打开Gps方便定位");
        }

    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("校园驾校");
        topView.setTvRightText(userInfo.getSchoolName());
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
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
                ZeroZeroSevenUtils.SwitchActivity(DrivingSchoolActivity.this, DrivingDetilsActivity.class);
            }
        });
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_distance:
                        postUrl = "http://api.map.baidu.com/geosearch/v3/nearby?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=distance:1&radius=100000";
                        requestDate(postUrl, split[0], split[1], false, 0, 6);
                        break;
                    case R.id.rb_price:
                        postUrl = "http://api.map.baidu.com/geosearch/v3/local?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=price:1&filter=check_status:1";
                        requestDate(postUrl, split[0], split[1], false, 0, 6);
                        break;
                    case R.id.rb_other:
                        postUrl = "http://api.map.baidu.com/geosearch/v3/local?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&filter=check_status:1";
                        requestDate(postUrl, split[0], split[1], false, 0, 6);
                        break;
                }
            }
        });

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
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
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
        requestDate(postUrl, String.valueOf(longitude), String.valueOf(latitude), false, 0, 6);
        return longitude + "," + latitude;
    }

    //从网络获取经纬度
    public String getLngAndLatWithNetwork() {
        double latitude = 0.0;
        double longitude = 0.0;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermisson();
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        requestDate(postUrl, String.valueOf(longitude), String.valueOf(latitude), false, 0, 6);
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


    private String postUrl = "http://api.map.baidu.com/geosearch/v3/nearby?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=distance:1&radius=100000";
    private String distanceUrl = "http://api.map.baidu.com/geosearch/v3/nearby?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=distance:1&radius=100000";
    private String priceUrl = "http://api.map.baidu.com/geosearch/v3/local?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=price:1&filter=check_status:1";
    private String otherUrl = "http://api.map.baidu.com/geosearch/v3/local?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&filter=check_status:1";
    private int index = 0;

    private void requestDate(String url, String jingDu, String weiDu, final boolean b, final int index, int size) {
        LogUtils.D("response", url + "&tags=驾校" + "&location=" + jingDu + "," + weiDu + "&pageIndex=" + index + "&pageSize=" + 6);
        OkHttpUtils.get()
                .url(url + "&tags=驾校" + "&location=" + jingDu + "," + weiDu + "&pageIndex=" + index + "&pageSize=" + 6)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        disLoadProgress();
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        showLoadProgress();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        disLoadProgress();
                        if (b) {
                            refreshlayout.finishRefresh();
                        }
                        LogUtils.D("response", response);
                        driverSchoolMainInfo = JSON.parseObject(response, DriverSchoolMainInfo.class);
                        if (driverSchoolMainInfo.getStatus() == 0 && driverSchoolMainInfo.getContents() != null && driverSchoolMainInfo.getContents().size() > 0) {
                            if (index == 0) {
                                driverHomeAdapter.cleanDates();
                            } else {
                                refreshlayout.finishLoadmore();
                            }
                            driverHomeAdapter.addAll(driverSchoolMainInfo.getContents());
                        } else {
                            if (index == 0) {
                                driverHomeAdapter.cleanDates();
                            } else {
                                refreshlayout.finishLoadmore();
                                ToastUtils.showShort("没有更多数据了");
                            }
                        }

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        index = 0;
        requestDate(postUrl, split[0], split[1], true, index, 6);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        index++;
        requestDate(postUrl, split[0], split[1], true, index, 6);
    }
}



