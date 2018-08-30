package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.ErrandmineDingdanadapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RrunDingDanInfo;
import com.ffn.zerozeroseven.bean.RunDingdanInfo;
import com.ffn.zerozeroseven.ui.ErrandRunnerDingDanDetilsActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

public class RunnerDingDanFragment extends BaseReFreshFragment {

    private RunDingdanInfo runDingdanInfo;
    private ErrandmineDingdanadapter errandmineDingdanadapter;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        isLoadMore = false;
        errandmineDingdanadapter = new ErrandmineDingdanadapter(getActivity());
        errandmineDingdanadapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putString("orderNo",errandmineDingdanadapter.getItem(position).getOrderNo());
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, ErrandRunnerDingDanDetilsActivity.class,bundle);
            }
        });
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
        parametersBean.setSchoolId(schoolIId);
        rrunDingDanInfo.setParameters(parametersBean);
        return rrunDingDanInfo;
    }
}
