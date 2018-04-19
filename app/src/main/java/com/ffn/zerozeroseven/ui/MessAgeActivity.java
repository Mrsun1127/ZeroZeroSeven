package com.ffn.zerozeroseven.ui;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.MessAgeAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.PushMessAgeOkInfo;
import com.ffn.zerozeroseven.bean.requsetbean.PushMessAgeInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by GT on 2017/11/19.
 */

public class MessAgeActivity extends BaseRefreshActivity {

    private PushMessAgeOkInfo info;
    private MessAgeAdapter adapter;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adapter = new MessAgeAdapter(MessAgeActivity.this);
        return adapter;
    }

    @Override
    protected String setTopTitle() {
        return "消息中心";
    }

    @Override
    protected void readRespones(String response) {
        info = JSON.parseObject(response, PushMessAgeOkInfo.class);
    }

    @Override
    protected int setFlag() {
        return info.getCode();
    }

    @Override
    protected int setSize() {
        return info.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(info.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        PushMessAgeInfo info = new PushMessAgeInfo();
        info.setFunctionName("ListPushNews");
        PushMessAgeInfo.ParametersBean parametersBean = new PushMessAgeInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        info.setParameters(parametersBean);
        return info;
    }

    @Override
    protected void doMain() {
        MobclickAgent.onEvent(this, "消息管理");
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", adapter.getItem(position).getId());
                ZeroZeroSevenUtils.SwitchActivity(MessAgeActivity.this, MessAgeBodyActivity.class, bundle);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
