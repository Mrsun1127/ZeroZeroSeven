package com.ffn.zerozeroseven.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.utlis.UiTipUtil;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.StateLayout;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2018/1/25.
 */

public abstract class BaseReFreshFragment extends BaseFragment implements OnRefreshListener, OnLoadmoreListener {
    @Bind(R.id.refreshlayout)
    SmartRefreshLayout commonRefreshLayout;
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    @Bind(R.id.common_stateLayout)
    StateLayout commonStateLayout;

    BaseRecyclerAdapter adapter;
    private KProgressHUD hud;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    int pageNo = 0;
    public boolean isLoadMore=true;
    private void setRefreshLayoutVis() {
        if (commonRefreshLayout.getVisibility() == View.GONE) {
            commonRefreshLayout.setVisibility(View.VISIBLE);
            commonStateLayout.setVisibility(View.GONE);
        }
    }

    private void showErrorLayout(int errType) {
        commonRefreshLayout.setVisibility(View.GONE);
        adapter.clear();
        commonStateLayout.setVisibility(View.VISIBLE);
        commonStateLayout.showError(errType);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(25));
        adapter = setAdapter();
        recyclerView.setAdapter(adapter);
        commonRefreshLayout.setOnRefreshListener(this);
        commonRefreshLayout.setOnLoadmoreListener(this);
        commonStateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                commonStateLayout.setVisibility(View.GONE);
                commonRefreshLayout.setVisibility(View.VISIBLE);
                rgRefreshStatus = RgRefreshStatus.IDLE;
                requestData();
            }
        });
        setRefreshLayoutVis();
        hud = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setWindowColor(getResources().getColor(R.color.text_secondary_color))
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
        doMain();
    }

    public void doMain() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_refresh;
    }

    @Override
    protected void lazyLoad() {
        requestData();
    }

    protected void showLoadDialog(String txt) {
        hud.setDetailsLabel(txt)
                .show();
    }

    protected void showLoadDialog() {
        hud.setDetailsLabel("正在加载中")
                .show();
    }

    protected void dissMissLoad() {
        if (hud != null) {
            hud.dismiss();
        } else {
        }
    }

    private void disLoadState() {
        switch (rgRefreshStatus) {
            case IDLE:
                dissMissLoad();
                break;
            case REFRESHING:
                commonRefreshLayout.finishRefresh();
                dissMissLoad();
                break;
            case PULL_DOWN:
                commonRefreshLayout.finishLoadmore();
                dissMissLoad();
                break;
        }
    }

    private void setLoadPage() {
        switch (rgRefreshStatus) {
            case PULL_DOWN:
                pageNo = pageNo + 1;
                break;
            case IDLE:
                showLoadDialog();
            case REFRESHING:
                pageNo = 0;
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        rgRefreshStatus = RgRefreshStatus.REFRESHING;
        requestData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        if(isLoadMore){
            rgRefreshStatus = RgRefreshStatus.PULL_DOWN;
            requestData();
        }else{
            refreshlayout.finishLoadmore();
        }

    }

    protected abstract BaseRecyclerAdapter setAdapter();


    protected abstract void readRespones(String response);

    protected abstract int setFlag();

    protected abstract int setSize();

    protected abstract void addAll(BaseRecyclerAdapter adapter);

    protected abstract Object setObj(int pageNo);

    public void requestData() {
        setLoadPage();
        httpPostJSON(setObj(pageNo), true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadState();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                readRespones(response.body().string());
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadState();
                        if (setFlag() == 0) {
                            switch (rgRefreshStatus) {
                                case IDLE:
                                case REFRESHING:
                                    commonRefreshLayout.finishRefresh();
                                    adapter.clear();
                                    if (setSize() == 0) {
                                        showErrorLayout(StateLayout.noData);
                                    } else {
                                        commonRefreshLayout.setVisibility(View.VISIBLE);
                                        commonStateLayout.setVisibility(View.GONE);
                                        addAll(adapter);
                                    }
                                    break;
                                case PULL_DOWN:
                                    commonRefreshLayout.finishLoadmore();
                                    if (setSize() == 0) {
                                        UiTipUtil.showToast(bfCxt, R.string.no_more_data);
                                    } else {
                                        addAll(adapter);
                                    }
                                    break;
                            }
                        } else {
                            showErrorLayout(StateLayout.noData);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
