package com.ffn.zerozeroseven.ui;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.DriverDingDanListAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.bean.DriverDingDanListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RDingDanListInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

public class DriverDingDanListActivity extends BaseRefreshActivity {

    private DriverDingDanListInfo driverDingDanListInfo;
    private DriverDingDanListAdapter driverDingDanListAdapter;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        driverDingDanListAdapter = new DriverDingDanListAdapter(DriverDingDanListActivity.this);
        driverDingDanListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putString("orderId", String.valueOf(driverDingDanListAdapter.getItem(position).getId()));
                ZeroZeroSevenUtils.SwitchActivity(DriverDingDanListActivity.this, DriverOrderDetilsActivity.class,bundle);
            }
        });
        return driverDingDanListAdapter;
    }

    @Override
    protected String setTopTitle() {
        return "驾校订单";
    }

    @Override
    protected void readRespones(String response) {
        driverDingDanListInfo = JSON.parseObject(response, DriverDingDanListInfo.class);
    }

    @Override
    protected int setFlag() {
        return driverDingDanListInfo.getCode();
    }

    @Override
    protected int setSize() {
        return driverDingDanListInfo.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(driverDingDanListInfo.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        RDingDanListInfo rDingDanListInfo = new RDingDanListInfo();
        rDingDanListInfo.setFunctionName("ListDrivingOrder");
        RDingDanListInfo.ParametersBean parametersBean = new RDingDanListInfo.ParametersBean();
        parametersBean.setUserId(BaseAppApplication.getInstance().getLoginUser().getId());
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        rDingDanListInfo.setParameters(parametersBean);
        return rDingDanListInfo;
    }
}
