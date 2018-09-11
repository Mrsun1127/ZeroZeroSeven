package com.ffn.zerozeroseven.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.DriverFourAdapter;
import com.ffn.zerozeroseven.adapter.DriverTwoAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DriverDetilsFourFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    private DriverFourAdapter driverOneAdapter;

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void initDate() {
        driverOneAdapter = new DriverFourAdapter(bfCxt);
        recycleview.setLayoutManager(new LinearLayoutManager(bfCxt));
        recycleview.addItemDecoration(new SpaceItemDecoration(2));
        recycleview.setAdapter(driverOneAdapter);


    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_driver_four;
    }

    @Override
    protected void lazyLoad() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        driverOneAdapter.addAll(list);
    }
}