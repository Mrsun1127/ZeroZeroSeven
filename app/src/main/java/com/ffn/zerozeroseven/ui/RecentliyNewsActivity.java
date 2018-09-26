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
        RClickBitisInfo rClickBitisInfo = new RClickBitisInfo();
        rClickBitisInfo.setFunctionName("UpdatePostMessagesViewStatus");
        RClickBitisInfo.ParametersBean parametersBean = new RClickBitisInfo.ParametersBean();
        int size = recentliyNewsInfo.getData().getMessages().size();
        Integer[] integers = new Integer[size];
        for (int i = 0; i < size; i++) {
            integers[i] = recentliyNewsInfo.getData().getMessages().get(i).getId();
        }
        parametersBean.setMessageIds(integers);
        rClickBitisInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(RecentliyNewsActivity.this);
        okGoUtils.httpPostJSON(rClickBitisInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
            }
        });

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
        return recentliyNewsInfo.getData().getMessages().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(recentliyNewsInfo.getData().getMessages());
    }

    @Override
    protected Object setObj(int pageNo) {
        RBitisRecentliyNewsInfo rBitisRecentliyNewsInfo = new RBitisRecentliyNewsInfo();
        rBitisRecentliyNewsInfo.setFunctionName("ListLatestPostMessage");
        RBitisRecentliyNewsInfo.ParametersBean parametersBean = new RBitisRecentliyNewsInfo.ParametersBean();
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        rBitisRecentliyNewsInfo.setParameters(parametersBean);
        return rBitisRecentliyNewsInfo;
    }
}
