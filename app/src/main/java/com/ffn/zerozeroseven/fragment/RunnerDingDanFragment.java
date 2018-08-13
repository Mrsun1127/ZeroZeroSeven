package com.ffn.zerozeroseven.fragment;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.ErrandmineDingdanadapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RrunDingDanInfo;
import com.ffn.zerozeroseven.bean.RunDingdanInfo;

public class RunnerDingDanFragment extends BaseReFreshFragment {

    private RunDingdanInfo runDingdanInfo;
    private ErrandmineDingdanadapter errandmineDingdanadapter;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        isLoadMore = false;
        errandmineDingdanadapter = new ErrandmineDingdanadapter(bfCxt);
        return errandmineDingdanadapter;
    }

    @Override
    protected void readRespones(String response) {
        runDingdanInfo = JSON.parseObject(response, RunDingdanInfo.class);
    }

    @Override
    protected int setFlag() {
        return runDingdanInfo.getCode();
    }

    @Override
    protected int setSize() {
        return runDingdanInfo.getData().getErrandOrders().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(runDingdanInfo.getData().getErrandOrders());
    }

    @Override
    protected Object setObj(int pageNo) {
        RrunDingDanInfo rrunDingDanInfo = new RrunDingDanInfo();
        rrunDingDanInfo.setFunctionName("ListReceiverErrandOrder");
        RrunDingDanInfo.ParametersBean parametersBean = new RrunDingDanInfo.ParametersBean();
        parametersBean.setUserId(userId);
        rrunDingDanInfo.setParameters(parametersBean);
        return rrunDingDanInfo;
    }
}
