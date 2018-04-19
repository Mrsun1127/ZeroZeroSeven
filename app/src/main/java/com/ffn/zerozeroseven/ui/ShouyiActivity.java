package com.ffn.zerozeroseven.ui;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.ShouyiAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.ShouyiOkInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShouyiInfo;

/**
 * Created by GT on 2018/2/6.
 */

public class ShouyiActivity extends BaseRefreshActivity {

    private ShouyiAdapter adapter;
    private ShouyiOkInfo okInfo;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adapter = new ShouyiAdapter(ShouyiActivity.this);
        return adapter;
    }

    @Override
    protected String setTopTitle() {
        return "跑腿收益";
    }

    @Override
    protected void readRespones(String response) {
        okInfo = JSON.parseObject(response,ShouyiOkInfo.class);
    }

    @Override
    protected int setFlag() {
        return okInfo.getCode();
    }

    @Override
    protected int setSize() {
        return okInfo.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(okInfo.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        ShouyiInfo shouyiInfo=new ShouyiInfo();
        shouyiInfo.setFunctionName("ListUserIncomeRecord");
        ShouyiInfo.ParametersBean parametersBean=new ShouyiInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        shouyiInfo.setParameters(parametersBean);
        return shouyiInfo;
    }
}
