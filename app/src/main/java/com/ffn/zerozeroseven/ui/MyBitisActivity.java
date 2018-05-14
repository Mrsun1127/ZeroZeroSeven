package com.ffn.zerozeroseven.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.BitisAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRefreshActivity;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.QiangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.XiaoYuanQiangInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.UiTipUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.StateLayout;
import com.ffn.zerozeroseven.view.TopView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyBitisActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @Bind(R.id.smartrefreshlayout)
    SmartRefreshLayout commonRefreshLayout;
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.topView)
    TopView topView;
    @Bind(R.id.common_stateLayout)
    StateLayout commonStateLayout;
    private BitisAdapter bitisAdapter;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    int pageNo = 0;
    private void setRefreshLayoutVis() {
        if (commonRefreshLayout.getVisibility() == View.GONE) {
            commonRefreshLayout.setVisibility(View.VISIBLE);
            commonStateLayout.setVisibility(View.GONE);
        }
    }

    private void showErrorLayout(int errType) {
        commonRefreshLayout.setVisibility(View.GONE);
        bitisAdapter.clear();
        commonStateLayout.setVisibility(View.VISIBLE);
        commonStateLayout.showError(errType);
    }
    private void disLoadState() {
        switch (rgRefreshStatus) {
            case IDLE:
                disLoadProgress();
                break;
            case REFRESHING:
                commonRefreshLayout.finishRefresh();
                disLoadProgress();
                break;
            case PULL_DOWN:
                commonRefreshLayout.finishLoadmore();
                disLoadProgress();
                break;
        }
    }
    private void setLoadPage() {
        switch (rgRefreshStatus) {
            case PULL_DOWN:
                pageNo = pageNo + 1;
                break;
            case IDLE:
                showLoadProgress();
            case REFRESHING:
                pageNo = 0;
                break;
        }
    }
    @Override
    protected int setLayout() {
        return R.layout.activtity_mybitis;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        topView.setTopText("许愿墙");
        topView.setTvRightDrawable(R.drawable.bitits_post);
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {
                Bundle bundle=new Bundle();
                bundle.putString("showType","01");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
            }

            @Override
            public void Back() {
                finish();
            }
        });
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        bitisAdapter = new BitisAdapter(this);
        recycleview.setAdapter(bitisAdapter);
        commonRefreshLayout.setOnRefreshListener(this);
        commonRefreshLayout.setOnLoadmoreListener(this);
        bitisAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle=new Bundle();
                bundle.putString("clickType","detils");
                bundle.putSerializable("info",bitisAdapter.getItem(position));
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this,BitisDetils.class,bundle);
            }
        });
        commonStateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                commonStateLayout.setVisibility(View.GONE);
                commonRefreshLayout.setVisibility(View.VISIBLE);
                rgRefreshStatus = RgRefreshStatus.IDLE;
                requestDate("");
            }
        });
        setRefreshLayoutVis();
    }

    @Override
    protected void doMain() {
        requestDate("");
    }
    @OnClick({R.id.rl_love,R.id.rl_good,R.id.rl_find,R.id.rl_friend,R.id.tv_top})
    void setOnClicks(View v) {
        Bundle bundle=new Bundle();
        switch (v.getId()) {
            case R.id.tv_top:
                bundle.putString("showType","01");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
                break;
            case R.id.rl_love:
                bundle.putString("showType","01");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
            break;
            case R.id.rl_good:
                bundle.putString("showType","02");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
                break;
            case R.id.rl_find:
                bundle.putString("showType","03");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
                break;
            case R.id.rl_friend:
                bundle.putString("showType","04");
                ZeroZeroSevenUtils.SwitchActivity(MyBitisActivity.this, MineWantGoQiangActivity.class,bundle);
                break;

        }
    }
    private void requestDate(String type) {
        setLoadPage();
        XiaoYuanQiangInfo qiangInfo = new XiaoYuanQiangInfo();
        qiangInfo.setFunctionName("ListPost");
        XiaoYuanQiangInfo.ParametersBean parametersBean = new XiaoYuanQiangInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(20);
        parametersBean.setPostType(type);
        qiangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(MyBitisActivity.this);
        okGoUtils.httpPostJSON(qiangInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                disLoadState();
                QiangShowInfo showInfo = JSON.parseObject(response, QiangShowInfo.class);
                if (showInfo.getCode() == 0) {
                    bitisAdapter.addAll(showInfo.getData().getItems());
                    switch (rgRefreshStatus) {
                        case IDLE:
                        case REFRESHING:
                            commonRefreshLayout.finishRefresh();
                            bitisAdapter.clear();
                            if (showInfo.getData().getItems().size() == 0) {
                                showErrorLayout(StateLayout.noData);
                            } else {
                                commonRefreshLayout.setVisibility(View.VISIBLE);
                                commonStateLayout.setVisibility(View.GONE);
                                bitisAdapter.addAll(showInfo.getData().getItems());
                            }
                            break;
                        case PULL_DOWN:
                            commonRefreshLayout.finishLoadmore();
                            if (showInfo.getData().getItems().size() == 0) {
                                UiTipUtil.showToast(MyBitisActivity.this, R.string.no_more_data);
                            } else {
                                bitisAdapter.addAll(showInfo.getData().getItems());
                            }
                            break;
                    }
                }else {
                    showErrorLayout(StateLayout.noData);
                }
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        rgRefreshStatus = RgRefreshStatus.REFRESHING;
        requestDate("");
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        rgRefreshStatus = RgRefreshStatus.PULL_DOWN;
        requestDate("");
    }
}
