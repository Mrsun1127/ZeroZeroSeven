package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ProductGoInAdapter;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.bean.ProductDetilsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.LastInteralInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ProductDtilsInfo;
import com.ffn.zerozeroseven.ui.InteralDetilsActivity;
import com.ffn.zerozeroseven.ui.ProductDetilsActivity;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetilsFragment extends BaseFragment {
    @Bind(R.id.rc_minegoin)
    RecyclerView rc_minegoin;
    @Bind(R.id.rc_allgoin)
    RecyclerView rc_allgoin;
    private ProductGoInAdapter goInAdapter;
    private ProductGoInAdapter goInAdapter1;
    private int id;
    public static ProductDetilsFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("id", id);
        ProductDetilsFragment fragment = new ProductDetilsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public static WeakReference<ProductDetilsFragment> mInstance;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id=savedInstanceState.getInt("id");
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        mInstance=new WeakReference<>(this);
        rc_minegoin.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        rc_allgoin.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        goInAdapter = new ProductGoInAdapter(bfCxt);
        goInAdapter1 = new ProductGoInAdapter(bfCxt);
        rc_minegoin.setAdapter(goInAdapter);
        rc_allgoin.setAdapter(goInAdapter);
    }

    @Override
    public void initDate() {
       requestId(id);
    }

    public void requestId(int id) {
        ProductDtilsInfo lastInteralInfo = new ProductDtilsInfo();
        lastInteralInfo.setFunctionName("QueryPointIssuePrize");
        ProductDtilsInfo.ParametersBean parametersBean = new ProductDtilsInfo.ParametersBean();
        parametersBean.setIssuePrizeId(id);
        lastInteralInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(lastInteralInfo,true,true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ProductDetilsInfo productDetilsInfo = JSON.parseObject(response,ProductDetilsInfo.class);
                if(productDetilsInfo.getCode()==0){

                }
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_product_detils;
    }

    @Override
    protected void lazyLoad() {
    }

    @OnClick({R.id.bt_go})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_go:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, InteralDetilsActivity.class);
                break;

        }
    }
}
