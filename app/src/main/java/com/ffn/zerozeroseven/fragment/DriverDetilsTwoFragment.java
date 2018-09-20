package com.ffn.zerozeroseven.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.DriverOneAdapter;
import com.ffn.zerozeroseven.adapter.DriverTwoAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.DriverDetilsInfo;
import com.ffn.zerozeroseven.utlis.MapNaviUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DriverDetilsTwoFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    private DriverTwoAdapter driverOneAdapter;
    private DriverDetilsInfo info;

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    public static DriverDetilsTwoFragment newInstance(DriverDetilsInfo driverDetilsInfo) {
        Bundle args = new Bundle();
        args.putSerializable("info", driverDetilsInfo);
        DriverDetilsTwoFragment fragment = new DriverDetilsTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        info = (DriverDetilsInfo) getArguments().getSerializable("info");
    }

    @Override
    public void initDate() {
        driverOneAdapter = new DriverTwoAdapter(bfCxt);
        driverOneAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                final ConfirmDialog confirmDialog = new ConfirmDialog(bfCxt);
                confirmDialog.setTitle("提示");
                confirmDialog.setMessages("将打开百度导航为您导航");
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        confirmDialog.dismiss();
                        startNavBaiDu("明诚驾校");
                    }

                    @Override
                    public void doCancel() {
                        confirmDialog.dismiss();
                    }
                });
            }
        });
        recycleview.setLayoutManager(new LinearLayoutManager(bfCxt));
        recycleview.addItemDecoration(new SpaceItemDecoration(2));
        recycleview.setAdapter(driverOneAdapter);


    }

    public void startNavBaiDu(String s) {
        if (!MapNaviUtils.isBaiduMapInstalled()) {
            ToastUtils.showShort("请安装百度地图");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("baidumap://map/geocoder?src=andr.baidu.openAPIdemo&address=明诚驾校"));
        intent.setData(Uri.parse("baidumap://map/marker?location=28.199933,113.070635&title=" + s + "&content=零零7合作驾校+" + s + "&traffic=on&src=andr.baidu.openAPIdemo"));
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
        return R.layout.fragment_driver_two;
    }

    @Override
    protected void lazyLoad() {
        driverOneAdapter.addAll(info.getData().getDrivingSchool().getDrivingPlaceList());
    }
}
