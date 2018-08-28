package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.ErrandmineDingdanadapter;
import com.ffn.zerozeroseven.adapter.ErrandyouDingdanadapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.RrunDingDanInfo;
import com.ffn.zerozeroseven.bean.RunDingdanInfo;
import com.ffn.zerozeroseven.bean.RunYouDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.SureGetInfo;
import com.ffn.zerozeroseven.ui.ErrandCustomerDingDanDetilsActivity;
import com.ffn.zerozeroseven.ui.ErrandDingdanActivity;
import com.ffn.zerozeroseven.ui.ErrandRunnerDingDanDetilsActivity;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

public class RunYourDingDanFragment extends BaseReFreshFragment {

    private RunYouDingDanInfo runDingdanInfo;
    private ErrandyouDingdanadapter errandmineDingdanadapter;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        isLoadMore = false;
        errandmineDingdanadapter = new ErrandyouDingdanadapter(bfCxt);
        errandmineDingdanadapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putString("orderNo", errandmineDingdanadapter.getItem(position).getOrderNo());
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, ErrandCustomerDingDanDetilsActivity.class, bundle);
            }
        });
        errandmineDingdanadapter.setOnItemImageViewClick(new ErrandyouDingdanadapter.OnItemImageClick() {
            @Override
            public void onClick(View view, int position) {
                if (errandmineDingdanadapter.getItem(position).getPayStatus() != -2) {
                    BaseAppApplication.getInstance().finishActivity(getActivity());
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("orderNo", errandmineDingdanadapter.getItem(position).getOrderNo());
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, ErrandCustomerDingDanDetilsActivity.class, bundle);
                }
            }
        });
        return errandmineDingdanadapter;
    }

    @Override
    protected void readRespones(String response) {
        runDingdanInfo = JSON.parseObject(response, RunYouDingDanInfo.class);
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
        rrunDingDanInfo.setFunctionName("ListPublisherErrandOrder");
        RrunDingDanInfo.ParametersBean parametersBean = new RrunDingDanInfo.ParametersBean();
        parametersBean.setUserId(userId);
        parametersBean.setSchoolId(schoolIId);
        rrunDingDanInfo.setParameters(parametersBean);
        return rrunDingDanInfo;
    }
}
