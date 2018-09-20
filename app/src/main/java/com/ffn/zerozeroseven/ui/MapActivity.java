package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.utlis.MapNaviUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.TopView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.map)
    MapView mapView;
    private BaiduMap mBaiduMap;
    private float latx = 28.199933f;
    private float laty = 113.070635f;
    private String lableName = "明诚驾校";

    // 定位相关声明
    //自定义图标
    private void initMyLocation() {
        mBaiduMap.setMyLocationEnabled(true);
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(100)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(90.0f)
                .latitude(latx)
                .longitude(laty).build();

        float f = mBaiduMap.getMaxZoomLevel();//19.0 最小比例尺
        //float m = mBaiduMap.getMinZoomLevel();//3.0 最大比例尺
        mBaiduMap.setMyLocationData(locData);
        mBaiduMap.setMyLocationEnabled(true);
        LatLng ll = new LatLng(latx, laty);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, f - 2);//设置缩放比例
        mBaiduMap.animateMapStatus(u);
    }

    public void daoHang() {
        final ConfirmDialog confirmDialog = new ConfirmDialog(MapActivity.this);
        confirmDialog.setTitle("提示");
        confirmDialog.setMessages("将打开百度导航为您导航");
        confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                confirmDialog.dismiss();
                startNavBaiDu("明诚驾校", latx, laty);
            }

            @Override
            public void doCancel() {
                confirmDialog.dismiss();
            }
        });
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mBaiduMap = mapView.getMap();
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                daoHang();
                return true;
            }
        });
        initMyLocation();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //开启交通图
        mBaiduMap.setTrafficEnabled(true);
        //定义Maker坐标点
        LatLng point = new LatLng(latx, laty);
        //构建Marker图标
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.map_marker, null);//加载自定义的布局
        TextView tv_temp = view.findViewById(R.id.tv_name);
        tv_temp.setText(lableName);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromView(view);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .title("1")
                .position(point)
                .icon(bitmap);

        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    public void startNavBaiDu(String s, double j, double w) {
        if (!MapNaviUtils.isBaiduMapInstalled()) {
            ToastUtils.showShort("请安装百度地图");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("baidumap://map/marker?location=" + j + "," + "w" + "&title=" + s + "&content=零零7合作驾校+" + s + "&traffic=on&src=andr.baidu.openAPIdemo"));
        startActivity(intent);
    }

    //高德地图,起点就是定位点
    // 终点是LatLng ll = new LatLng("你的纬度latitude","你的经度longitude");
    public void startNaviGao(String jingDu, String weiDu, String dName) {
        if (!MapNaviUtils.isGdMapInstalled()) {
            ToastUtils.showShort("请安装高德地图");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);//113.070635,28.199933
//        intent.setData(Uri.parse("amapuri://route/plan/?sid=BGVIS1&did=BGVIS2&dlat=28.199933&dlon=113.070635&dname=明诚驾校&dev=0&t=0"));
        intent.setData(Uri.parse("amapuri://route/plan/?sid=BGVIS1&did=BGVIS2&dlat=28.199933&dlon=113.070635&dname=" + dName + "&dev=0&t=0"));
        intent.setPackage("com.autonavi.minimap");
        startActivity(intent);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_map;
    }

    @Override
    protected void doMain() {
        topView.setTopText("地图导航");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
        mapView = null;
        super.onDestroy();
    }
}
