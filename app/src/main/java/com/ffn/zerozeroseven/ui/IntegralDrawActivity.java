package com.ffn.zerozeroseven.ui;

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


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        topView.setTvRightText("我的中奖");
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                finish();
            }
        });
        recycleview.setLayoutManager(new GridLayoutManager(this, 2));
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

    @OnClick({R.id.bt_sign, R.id.bt_bestnew})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_sign:
                sign();
                break;
            case R.id.bt_bestnew:
                ToastUtils.showShort("最新揭晓");
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

    ArrayList<String> list = new ArrayList<>();

    private void requestData() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        InteraglSignInfo interaglSignInfo = new InteraglSignInfo();
        interaglSignInfo.setFunctionName("ListPointJackpotPrize");
        OkGoUtils okGoUtils = new OkGoUtils(IntegralDrawActivity.this);
        okGoUtils.httpPostJSON(interaglSignInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                JiangChiInfo jiangChiInfo = JSON.parseObject(response, JiangChiInfo.class);
                if (jiangChiInfo.getCode() == 0) {
                    adapter.addAll(list);
                } else {
                    ToastUtils.showShort("奖池暂无信息");
                }
            }
        });
    }
}
