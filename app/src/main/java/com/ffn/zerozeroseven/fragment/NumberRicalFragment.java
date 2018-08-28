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
import com.ffn.zerozeroseven.ui.NumberTuiKuanDetilsActivity;
import com.ffn.zerozeroseven.ui.PayMoneyActivity;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
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
        mInstance = new WeakReference<>(this);
        adapter.setOnItemDeleteClick(new MyDingDanOfNumberAdapter.OnItemDeleteClick() {
            @Override
            public void onClick(View view, int position) {
                //订单的状态：1=已预约,2=已取消,3=确认收获,4=退货
                if (adapter.getItem(position).getOrderStatus() == 1) {
                    TuiKuan(position);
                } else if (adapter.getItem(position).getOrderStatus() == 3) {
                    deleteDingDan(position);
                }
            }
        });
        adapter.setOnItemAgainClick(new MyDingDanOfNumberAdapter.OnItemAgainClick() {
            @Override
            public void onClick(View view, int position) {
                if (adapter.getItem(position).getOrderStatus() == 1) {
                    gotoPayWeiKuan(position);
                } else if (adapter.getItem(position).getOrderStatus() == 2) {
                    deleteDingDan(position);
                } else if (adapter.getItem(position).getOrderStatus() == 3) {
                    buyAgain(position);
                } else if (adapter.getItem(position).getOrderStatus() == 4) {
                    deleteDingDan(position);
                }
            }
        });
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putDouble("money", adapter.getItem(position).getOrderPrice());
                bundle.putInt("orderId", adapter.getItem(position).getId());
                bundle.putString("orderNo", adapter.getItem(position).getOrderNo());
                if (adapter.getItem(position).isIsApplyRefund()) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, NumberTuiKuanDetilsActivity.class, bundle);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, NumberRicalDetilsActivity.class, bundle);
                }

            }
        });
    }

    private void buyAgain(int position) {
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
        bundle.putString("pay", "numberweikuan");
        bundle.putDouble("allMoney", adapter.getItem(position).getOrderPrice() - adapter.getItem(position).getDepositFee());
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
        LogUtils.D("response",response);
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
