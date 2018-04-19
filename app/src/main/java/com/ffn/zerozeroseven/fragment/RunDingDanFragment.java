package com.ffn.zerozeroseven.fragment;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.MyRunDingdanAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.MyRunDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.MyrunnerInfo;

/**
 * Created by GT on 2018/1/25.
 */

public class RunDingDanFragment extends BaseReFreshFragment {

    private MyRunDingdanAdapter adapter;
    private MyRunDingDanInfo dingDanInfo;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adapter = new MyRunDingdanAdapter(getContext());
        return adapter;
    }

    @Override
    protected void readRespones(String response) {
        dingDanInfo = JSON.parseObject(response,MyRunDingDanInfo.class);
    }

    @Override
    protected int setFlag() {
        return dingDanInfo.getCode();
    }

    @Override
    protected int setSize() {
        return dingDanInfo.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(dingDanInfo.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        MyrunnerInfo myrunnerInfo=new MyrunnerInfo();
        myrunnerInfo.setFunctionName("ListSubscriberErrandOrder");
        MyrunnerInfo.ParametersBean parametersBean=new MyrunnerInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        myrunnerInfo.setParameters(parametersBean);
        return myrunnerInfo;
    }
}
