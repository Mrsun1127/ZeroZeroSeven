package com.ffn.zerozeroseven.ui;

import android.media.MediaPlayer;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.requsetbean.AppUpdateInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GT on 2018/2/8.
 */

public class MrsunTestActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @Bind(R.id.refreshing)
    SmartRefreshLayout refreshLayout;
    public MediaPlayer mediaPlayer;
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

    @Override
    protected void doMain() {
        mediaPlayer = MediaPlayer.create(MrsunTestActivity.this, R.raw.herewego);
        mediaPlayer.start();

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
