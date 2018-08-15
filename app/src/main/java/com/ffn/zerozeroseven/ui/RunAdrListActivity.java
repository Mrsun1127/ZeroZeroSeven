package com.ffn.zerozeroseven.ui;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.RunAdrListAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseUpdateRefreshActivity;
import com.ffn.zerozeroseven.bean.FaHuoDiZhiInfo;
import com.ffn.zerozeroseven.bean.RFaHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RAddRunAdrInfo;
import com.ffn.zerozeroseven.utlis.ToastUtils;

public class RunAdrListActivity extends BaseUpdateRefreshActivity {

    private RunAdrListAdapter adrListAdapter;
    private RFaHuoInfo rFaHuoInfo;
    private String type;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adrListAdapter = new RunAdrListAdapter(this);
        adrListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                RAddRunAdrInfo rAddRunAdrInfo = new RAddRunAdrInfo();
                RAddRunAdrInfo.ParametersBean parametersBean = new RAddRunAdrInfo.ParametersBean();
                parametersBean.setAddress(adrListAdapter.getItem(position).getAddress());
                parametersBean.setName(adrListAdapter.getItem(position).getName());
                parametersBean.setPhone(adrListAdapter.getItem(position).getPhone());
                parametersBean.setUserId(userId);
                if ("f".equals(type)) {
                    parametersBean.setType("SEND");
                } else {
                    parametersBean.setType("RECEIVE");
                }
                rAddRunAdrInfo.setParameters(parametersBean);
                Intent intent = new Intent();
                intent.putExtra("adrInfo", rAddRunAdrInfo);
                setResult(2, intent);
                finish();
            }
        });
        return adrListAdapter;
    }

    @Override
    protected void doMain() {
        type = getIntent().getStringExtra("type");
    }

    @Override
    public void doRight() {

        ToastUtils.showShort("新增地址");
    }

    @Override
    protected String setTopTitle() {
        titleView.setTvRightText("新增");
        return "选择地址";
    }

    @Override
    protected void readRespones(String response) {
        rFaHuoInfo = JSON.parseObject(response, RFaHuoInfo.class);
    }

    @Override
    protected int setFlag() {
        return rFaHuoInfo.getCode();
    }

    @Override
    protected int setSize() {
        return rFaHuoInfo.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(rFaHuoInfo.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        FaHuoDiZhiInfo faHuoDiZhiInfo = new FaHuoDiZhiInfo();
        faHuoDiZhiInfo.setFunctionName("ListErrandUserAddress");
        FaHuoDiZhiInfo.ParametersBean parametersBean = new FaHuoDiZhiInfo.ParametersBean();
        parametersBean.setUserId(userId);
        if ("s".equals(type)) {
            parametersBean.setType("RECEIVE");
        } else {
            parametersBean.setType("SEND");
        }
        faHuoDiZhiInfo.setParameters(parametersBean);
        return faHuoDiZhiInfo;
    }
}
