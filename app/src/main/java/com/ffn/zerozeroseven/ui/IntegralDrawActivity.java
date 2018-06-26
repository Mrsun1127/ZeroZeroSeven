package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.InteralAdapter;
import com.ffn.zerozeroseven.base.BaseFullActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.JiangChiInfo;
import com.ffn.zerozeroseven.bean.requsetbean.InteraglSignInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.ffn.zerozeroseven.view.TopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntegralDrawActivity extends BaseFullActivity implements OnRefreshListener {
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    @Bind(R.id.recyclerView_with_recyclerView_in_coordinatorLayout)
    RecyclerView recycleview;
    private InteralAdapter adapter;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    @Bind(R.id.topView)
    TopView topView;

    @Override
    protected int setLayout() {
        return R.layout.activity_integraldraw;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("积分抽奖");
        topView.setTvRightText("我的中奖");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                ZeroZeroSevenUtils.SwitchActivity(IntegralDrawActivity.this,MySignGoodActivity.class);
            }

            @Override
            public void Back() {
                finish();
            }
        });
        refreshlayout.setOnRefreshListener(this);
        recycleview.setLayoutManager(new GridLayoutManager(this, 2));
        recycleview.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));
        adapter = new InteralAdapter(this);
        recycleview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                try {
                    bundle.putInt("prizeId",adapter.getItem(position).getId());
                }catch (Exception e){}
                ZeroZeroSevenUtils.SwitchActivity(IntegralDrawActivity.this, ProductDetilsActivity.class,bundle);
            }
        });
    }

    @OnClick({R.id.bt_sign, R.id.bt_bestnew})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_sign:
                sign();
                break;
            case R.id.bt_bestnew:
                ZeroZeroSevenUtils.SwitchActivity(IntegralDrawActivity.this,BestNewActivity.class);
                break;

        }
    }

    private void sign() {
        InteraglSignInfo signInfo = new InteraglSignInfo();
        signInfo.setFunctionName("AddUserSignInPoint");
        InteraglSignInfo.ParametersBean parametersBean = new InteraglSignInfo.ParametersBean();
        parametersBean.setUserId(userId);
        signInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(IntegralDrawActivity.this);
        okGoUtils.httpPostJSON(signInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ErrorCodeInfo codeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (codeInfo.getCode() == 0) {
                    ToastUtils.showShort(codeInfo.getMessage());
                } else {
                    ToastUtils.showShort(codeInfo.getMessage());
                }
            }
        });
    }

    @Override
    protected void doMain() {
        requestData();
    }


    private void requestData() {
        InteraglSignInfo interaglSignInfo = new InteraglSignInfo();
        interaglSignInfo.setFunctionName("ListPointJackpotPrize");
        OkGoUtils okGoUtils = new OkGoUtils(IntegralDrawActivity.this);
        okGoUtils.httpPostJSON(interaglSignInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                JiangChiInfo jiangChiInfo = JSON.parseObject(response, JiangChiInfo.class);
                refreshlayout.finishRefresh();
                if (jiangChiInfo.getCode() == 0) {
                    adapter.cleanDates();
                    adapter.addAll(jiangChiInfo.getData().getJackpotPrizes());
                } else {
                    ToastUtils.showShort("奖池暂无信息");
                }
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        requestData();
    }
}
