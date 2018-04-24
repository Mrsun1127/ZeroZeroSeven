package com.ffn.zerozeroseven.ui;


import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * Created by GT on 2018/2/8.
 */

public class MrsunTestActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @Bind(R.id.refreshing)
    SmartRefreshLayout refreshLayout;
    @Override
    protected int setLayout() {
        return R.layout.activity_mrsuntest;
    }
    @Override
    public void initView() {
        ButterKnife.bind(this);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
    }
    private List<String> imags;
    @Override
    protected void doMain() {


    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore();
    }
}
