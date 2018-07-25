package com.ffn.zerozeroseven.fragment;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.MyDingDanOfNumberAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NumberDingDAnInfo;

public class NumberRicalFragment extends BaseReFreshFragment {

    private MyDingDanOfNumberAdapter adapter;
    private NumberDingDanInfo numberDingDanInfo;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adapter = new MyDingDanOfNumberAdapter(bfCxt);
        return adapter;
    }

    @Override
    protected void readRespones(String response) {
        numberDingDanInfo = JSON.parseObject(response, NumberDingDanInfo.class);
    }

    @Override
    protected int setFlag() {
        return numberDingDanInfo.getCode();
    }

    @Override
    protected int setSize() {
        return numberDingDanInfo.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(numberDingDanInfo.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        NumberDingDAnInfo numberDingDAnInfo = new NumberDingDAnInfo();
        numberDingDAnInfo.setFunctionName("ListDigitalGoodsOrder");
        NumberDingDAnInfo.ParametersBean parametersBean = new NumberDingDAnInfo.ParametersBean();
        if (!TextUtils.isEmpty(userId)) {
            parametersBean.setUserId(Integer.parseInt(userId));
        }
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        numberDingDAnInfo.setParameters(parametersBean);
        return numberDingDAnInfo;
    }
}
