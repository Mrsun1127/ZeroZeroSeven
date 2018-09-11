package com.ffn.zerozeroseven.ui;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;

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
import com.ffn.zerozeroseven.utlis.MapNaviUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.StateLayout;
import com.ffn.zerozeroseven.view.TopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DrivingSchoolActivity extends BaseActivity {
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
        refreshlayout.setEnableLoadmore(false);
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
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        driverHomeAdapter.addAll(list);
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_distance:
                        break;
                    case R.id.rb_price:
                        break;
                    case R.id.rb_other:
                        break;
                }
            }
        });
    }

    @Override
    protected void doMain() {
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}



