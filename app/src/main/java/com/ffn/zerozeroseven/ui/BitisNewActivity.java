package com.ffn.zerozeroseven.ui;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.BitisNewAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.BitisInfo;
import com.ffn.zerozeroseven.bean.requsetbean.XiaoYuanQiangInfo;

public class BitisNewActivity extends BaseRefreshActivity {

    private BitisInfo bitisInfo;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        BitisNewAdapter bitisNewAdapter = new BitisNewAdapter(this);
        return bitisNewAdapter;
    }

    @Override
    protected String setTopTitle() {
        return "许愿墙";
    }

    @Override
    protected void readRespones(String response) {
        bitisInfo = JSON.parseObject(response, BitisInfo.class);
    }

    @Override
    protected int setFlag() {
        return bitisInfo.getCode();
    }

    @Override
    protected int setSize() {
        return bitisInfo.getData().getItems().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(bitisInfo.getData().getItems());
    }

    @Override
    protected Object setObj(int pageNo) {
        XiaoYuanQiangInfo qiangInfo = new XiaoYuanQiangInfo();
        qiangInfo.setFunctionName("ListPost");
        XiaoYuanQiangInfo.ParametersBean parametersBean = new XiaoYuanQiangInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(20);
        qiangInfo.setParameters(parametersBean);
        return qiangInfo;
    }
}
