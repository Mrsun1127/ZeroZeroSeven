package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.RecentliyNewsAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.RecentliyNewsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RBitisRecentliyNewsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RClickBitisInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

public class RecentliyNewsActivity extends BaseRefreshActivity {

    private RecentliyNewsInfo recentliyNewsInfo;
    private RecentliyNewsAdapter recentliyNewsAdapter;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        recentliyNewsAdapter = new RecentliyNewsAdapter(this);
        recentliyNewsAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", recentliyNewsAdapter.getItem(position));
                ZeroZeroSevenUtils.SwitchActivity(RecentliyNewsActivity.this, BitisNewDetils.class, bundle);
            }
        });
        return recentliyNewsAdapter;
    }

    @Override
    public void WantoDo() {
    }

    @Override
    protected String setTopTitle() {
        return "消息";
    }

    @Override
    protected void readRespones(String response) {
        recentliyNewsInfo = JSON.parseObject(response, RecentliyNewsInfo.class);
    }

    @Override
    protected int setFlag() {
        return recentliyNewsInfo.getCode();
    }

    @Override
    protected int setSize() {
        return recentliyNewsInfo.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(recentliyNewsInfo.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        RBitisRecentliyNewsInfo rBitisRecentliyNewsInfo = new RBitisRecentliyNewsInfo();
        rBitisRecentliyNewsInfo.setFunctionName("ListLatestPostMessage");
        RBitisRecentliyNewsInfo.ParametersBean parametersBean = new RBitisRecentliyNewsInfo.ParametersBean();
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        parametersBean.setPageSize(20);
        parametersBean.setPageIndex(pageNo);
        rBitisRecentliyNewsInfo.setParameters(parametersBean);
        return rBitisRecentliyNewsInfo;
    }
}
