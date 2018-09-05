package com.ffn.zerozeroseven.ui;


import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.view.TopView;

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
    }

    @Override
    protected void doMain() {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("baidumap://map/geocoder?src=andr.baidu.openAPIdemo&address=明诚驾校"));
//        startActivity(intent);
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



