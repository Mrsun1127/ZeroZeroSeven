package com.ffn.zerozeroseven.ui;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.view.TopView;
import com.yanzhenjie.permission.AndPermission;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DrivingSchoolActivity extends BaseActivity {
    @Bind(R.id.topView)
    TopView topView;
    //    @Bind(R.id.mmap)
//    MapView mMapView;
//    private BaiduMap mBaiduMap;
//    private MapView mMapView;
//    private BaiduMap mBaiduMap;

    @Override
    protected int setLayout() {
        return R.layout.activity_map;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        // 先判断是否有权限。
        if (!AndPermission.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                !AndPermission.hasPermission(this, Manifest.permission.READ_PHONE_STATE) ||
                !AndPermission.hasPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                !AndPermission.hasPermission(this, Manifest.permission.CAMERA)
                ) {
            // 申请权限。
            AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA)
                    .send();
        }
        topView.setTopText("百度地图");
//        mMapView =  findViewById(R.id.mmap);
//        mBaiduMap = mMapView.getMap();
    }

    @Override
    protected void doMain() {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("baidumap://map/geocoder?src=andr.baidu.openAPIdemo&address=明诚驾校"));
//        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}



