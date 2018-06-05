package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.RecyclerView;

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
import com.ffn.zerozeroseven.view.FullyGridLayoutManager;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.ffn.zerozeroseven.view.TopView;
import com.ffn.zerozeroseven.view.mainscroll.SmoothRefreshLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


import butterknife.Bind;
import butterknife.ButterKnife;

public class IntegralDrawActivity extends BaseFullActivity {
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
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        recycleview.setLayoutManager(new FullyGridLayoutManager(this, 2));
        recycleview.addItemDecoration(new GridSpacingItemDecoration(2, 10, false));
        adapter = new InteralAdapter(this);
        recycleview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                ZeroZeroSevenUtils.SwitchActivity(IntegralDrawActivity.this, ProductDetilsActivity.class);
            }
        });
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
        okGoUtils.httpPostJSON(interaglSignInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                JiangChiInfo jiangChiInfo = JSON.parseObject(response, JiangChiInfo.class);
                if (jiangChiInfo.getCode() == 0) {
                    adapter.addAll(jiangChiInfo.getData().getJackpotPrizes());
                } else {
                    ToastUtils.showShort("奖池暂无信息");
                }
            }
        });
    }
}
