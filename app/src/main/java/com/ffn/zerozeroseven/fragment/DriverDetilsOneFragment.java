package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.DriverOneAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.DriverDetilsInfo;
import com.ffn.zerozeroseven.ui.DriverClassTypeDetilsActivity;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DriverDetilsOneFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    private DriverOneAdapter driverOneAdapter;
    private DriverDetilsInfo info;

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    public static DriverDetilsOneFragment newInstance(DriverDetilsInfo driverDetilsInfo) {
        Bundle args = new Bundle();
        args.putSerializable("info", driverDetilsInfo);
        DriverDetilsOneFragment fragment = new DriverDetilsOneFragment();
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
        driverOneAdapter = new DriverOneAdapter(bfCxt);
        driverOneAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, DriverClassTypeDetilsActivity.class);
            }
        });
        recycleview.setLayoutManager(new LinearLayoutManager(bfCxt));
        recycleview.addItemDecoration(new SpaceItemDecoration(2));
        recycleview.setAdapter(driverOneAdapter);


    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_driver_one;
    }

    @Override
    protected void lazyLoad() {
        driverOneAdapter.addAll(info.getData().getDrivingSchool().getDrivingClassList());
    }
}
