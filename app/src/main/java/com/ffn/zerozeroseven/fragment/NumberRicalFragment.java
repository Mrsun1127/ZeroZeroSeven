package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.MyDingDanOfNumberAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberDelete;
import com.ffn.zerozeroseven.bean.NumberDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.NumberDingDAnInfo;
import com.ffn.zerozeroseven.ui.NumberDrawBackActivity;
import com.ffn.zerozeroseven.ui.NumberRicalDetilsActivity;
import com.ffn.zerozeroseven.ui.PayMoneyActivity;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.lang.ref.WeakReference;

public class NumberRicalFragment extends BaseReFreshFragment {

    private MyDingDanOfNumberAdapter adapter;
    private NumberDingDanInfo numberDingDanInfo;
    public static WeakReference<NumberRicalFragment> mInstance;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adapter = new MyDingDanOfNumberAdapter(bfCxt);
        return adapter;
    }

    @Override
    public void doMain() {
//        deleteDingDan(0);
        mInstance = new WeakReference<>(this);
        adapter.setOnItemDeleteClick(new MyDingDanOfNumberAdapter.OnItemDeleteClick() {
            @Override
            public void onClick(View view, int position) {
                TuiKuan(position);
            }
        });
        adapter.setOnItemAgainClick(new MyDingDanOfNumberAdapter.OnItemAgainClick() {
            @Override
            public void onClick(View view, int position) {
                gotoPayWeiKuan(0);
            }
        });
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", adapter.getItem(position).getId());
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, NumberRicalDetilsActivity.class, bundle);
            }
        });
    }

    public void request() {
        requestData();
    }

    private void TuiKuan(int i) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("info", adapter.getItem(i));
        ZeroZeroSevenUtils.SwitchActivity(bfCxt, NumberDrawBackActivity.class, bundle);
    }

    private void gotoPayWeiKuan(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("parentOrderNo", adapter.getItem(position).getOrderNo());
        ZeroZeroSevenUtils.SwitchActivity(bfCxt, PayMoneyActivity.class, bundle);
    }

    private void deleteDingDan(int position) {
        NumberDelete numberDelete = new NumberDelete();
        numberDelete.setFunctionName("DeleteDigitalGoodsOrder");
        NumberDelete.ParametersBean parametersBean = new NumberDelete.ParametersBean();
        parametersBean.setUserId(userId);
        parametersBean.setOrderId(adapter.getItem(position).getId());
        numberDelete.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(numberDelete, true, true);
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
