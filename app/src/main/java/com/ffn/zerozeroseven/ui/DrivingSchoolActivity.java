package com.ffn.zerozeroseven.ui;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.DriverHomeAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.DriverSchoolMainInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MapNaviUtils;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected int setLayout() {
        return R.layout.activity_driver;
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
                        requestDate(postUrl, 112.982211, 28.20261, false, 0, 6);
                        break;
                    case R.id.rb_price:
                        postUrl = "http://api.map.baidu.com/geosearch/v3/local?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=price:1&filter=check_status:1";
                        requestDate(postUrl, 112.982211, 28.20261, false, 0, 6);
                        break;
                    case R.id.rb_other:
                        postUrl = "http://api.map.baidu.com/geosearch/v3/local?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&filter=check_status:1";
                        requestDate(postUrl, 112.982211, 28.20261, false, 0, 6);
                        break;
                }
            }
        });
    }

    @Override
    protected void doMain() {
        requestDate(postUrl, 112.982211, 28.20261, false, 0, 6);
    }

    private String postUrl = "http://api.map.baidu.com/geosearch/v3/nearby?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=distance:1&radius=100000";
    //http://api.map.baidu.com/geosearch/v3/local?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=" + sortby + "&radius=100000&tags=驾校" + ":1&location=" + jingDu + "," + weiDu
    private String distanceUrl = "http://api.map.baidu.com/geosearch/v3/nearby?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=distance:1&radius=100000";
    private String priceUrl = "http://api.map.baidu.com/geosearch/v3/local?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&sortby=price:1&filter=check_status:1";
    private String otherUrl = "http://api.map.baidu.com/geosearch/v3/local?status=1&ak=i8WdSeZmGLma4ecnvs7YXIzXo8ok9Mvq&geotable_id=194571&filter=check_status:1";
    private int index = 0;

    private void requestDate(String url, Double jingDu, Double weiDu, final boolean b, final int index, int size) {
        LogUtils.D("response",url + "&tags=驾校" + "&location=" + jingDu + "," + weiDu + "&pageIndex=" + index + "&pageSize=" + 6);
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
        requestDate(postUrl, 112.982211, 28.20261, true, index, 6);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        index++;
        requestDate(postUrl, 112.982211, 28.20261, true, index, 6);
    }
}



