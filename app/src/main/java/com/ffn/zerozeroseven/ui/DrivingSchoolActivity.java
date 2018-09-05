package com.ffn.zerozeroseven.ui;


import android.content.Intent;
import android.net.Uri;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.utlis.MapNaviUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.TopView;

import java.net.URISyntaxException;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DrivingSchoolActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.mmap)
    MapView mMapView;
    private BaiduMap mBaiduMap;

    @Override
    protected int setLayout() {
        return R.layout.activity_map;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("百度地图");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        mBaiduMap = mMapView.getMap();
        //设定中心点坐标
        LatLng cenpt = new LatLng(30.663791, 104.07281);
//定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(12)
                .build();
//定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
//改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    @Override
    protected void doMain() {
        startNavBaiDu();
    }

    public void startNavBaiDu() {
        if (!MapNaviUtils.isBaiduMapInstalled()) {
            ToastUtils.showShort("请安装百度地图");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("baidumap://map/geocoder?src=andr.baidu.openAPIdemo&address=明诚驾校"));
        intent.setData(Uri.parse("baidumap://map/marker?location=28.199933,113.070635&title=明诚驾校&content=零零7合作驾校明诚驾校&traffic=on&src=andr.baidu.openAPIdemo"));
        startActivity(intent);
    }

    //高德地图,起点就是定位点
    // 终点是LatLng ll = new LatLng("你的纬度latitude","你的经度longitude");
    public void startNaviGao(String jingDu,String weiDu,String dName) {
        if (!MapNaviUtils.isGdMapInstalled()) {
            ToastUtils.showShort("请安装高德地图");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);//113.070635,28.199933
//        intent.setData(Uri.parse("amapuri://route/plan/?sid=BGVIS1&did=BGVIS2&dlat=28.199933&dlon=113.070635&dname=明诚驾校&dev=0&t=0"));
        intent.setData(Uri.parse("amapuri://route/plan/?sid=BGVIS1&did=BGVIS2&dlat=28.199933&dlon=113.070635&dname=明诚驾校&dev=0&t=0"));
        intent.setPackage("com.autonavi.minimap");
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }


}



