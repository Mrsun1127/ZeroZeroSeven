package com.ffn.zerozeroseven.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.HistorySwipeAdapter;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.HistoryInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DeleteTieInfo;
import com.ffn.zerozeroseven.bean.requsetbean.HistoryTieInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.NetUtil;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.UiTipUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.StateLayout;
import com.ffn.zerozeroseven.view.TitleView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.io.IOException;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.util.BGARefreshLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/21.
 */

public class HistoryTalkActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    int pageNo = 0;
    SwipeMenuRecyclerView common_recyclerView;
    private BGARefreshLayout commonRefreshLayout;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    private StateLayout commonStateLayout;
    private HistorySwipeAdapter adapter;
    private HistoryInfo info;

    private void setRefreshLayoutVis() {
        if (commonRefreshLayout.getVisibility() == View.GONE) {
            commonRefreshLayout.setVisibility(View.VISIBLE);
            commonStateLayout.setVisibility(View.GONE);
        }

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_historytalk;
    }

    @Override
    public void initView() {
        BaseAppApplication.getInstance().addActivity(this);
        TitleView titleview = findViewById(R.id.titleview);
        titleview.setTopText("历史发表");
        titleview.setOnTitleListener(new TitleView.OnTitleClickListener() {
            @Override
            public void ivBack() {
                finish();
            }

            @Override
            public void ivDown() {

            }

            @Override
            public void ivMessAge() {

            }
        });
        common_recyclerView = findViewById(R.id.common_recyclerView);
        commonStateLayout = findViewById(R.id.common_stateLayout);
        commonRefreshLayout = findViewById(R.id.common_refreshLayout);
        commonRefreshLayout.setDelegate(this);
        commonRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
        common_recyclerView.setLayoutManager(new LinearLayoutManager(HistoryTalkActivity.this));
        common_recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        adapter=new HistorySwipeAdapter(HistoryTalkActivity.this);
        common_recyclerView.setAdapter(adapter);
        commonStateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                commonStateLayout.setVisibility(View.GONE);
                commonRefreshLayout.setVisibility(View.VISIBLE);
                rgRefreshStatus = RgRefreshStatus.IDLE;
                reQuestData(false);
            }
        });
        common_recyclerView.setItemViewSwipeEnabled(true);
        common_recyclerView.setOnItemMoveListener(new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                return false;
            }

            @Override
            public void onItemDismiss(final RecyclerView.ViewHolder srcHolder) {
                final ConfirmDialog confirmDialog = new ConfirmDialog(HistoryTalkActivity.this);
                confirmDialog.setTitles("提示");
                confirmDialog.setMessages("确定删除帖子？");
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        confirmDialog.dismiss();
                        delete(srcHolder.getAdapterPosition());
                    }

                    @Override
                    public void doCancel() {
                        confirmDialog.dismiss();
                        adapter.replaceItem(srcHolder.getAdapterPosition(),adapter.getItem(srcHolder.getAdapterPosition()));
                    }
                });

            }
        });
    }

    @Override
    protected void doMain() {
        setRefreshLayoutVis();
        reQuestData(false);
    }

    private void delete(final int position) {
        showLoadProgress();
        DeleteTieInfo deleteTieInfo = new DeleteTieInfo();
        deleteTieInfo.setFunctionName("DeleteUserPost");
        DeleteTieInfo.ParametersBean parametersBean = new DeleteTieInfo.ParametersBean();
        parametersBean.setUserId(Integer.parseInt(userId));
        parametersBean.setPostId(adapter.getItem(position).getId());
        deleteTieInfo.setParameters(parametersBean);
        httpPostJSON(deleteTieInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        adapter.replaceItem(position,adapter.getItem(position));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                disLoadProgress();
                final ErrorCodeInfo codeInfo = JSON.parseObject(response.body().string(), ErrorCodeInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (codeInfo.getCode() == 0) {
                            adapter.removeItem(adapter.getItem(position));
                            ZeroZeroSevenUtils.showCustonPop(HistoryTalkActivity.this, "删除成功", common_recyclerView);
                        } else {
                            adapter.replaceItem(position,adapter.getItem(position));
                            ZeroZeroSevenUtils.showCustonPop(HistoryTalkActivity.this, "删除失败", common_recyclerView);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void reQuestData(final boolean isRefsh) {
        setLoadPage();
        HistoryTieInfo tieInfo = new HistoryTieInfo();
        tieInfo.setFunctionName("ListUserPost");
        HistoryTieInfo.ParametersBean parametersBean = new HistoryTieInfo.ParametersBean();
        parametersBean.setUserId(Integer.parseInt(userId));
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(20);
        tieInfo.setParameters(parametersBean);
        httpPostJSON(tieInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showErrorLayout(StateLayout.netError);
                        disLoadState();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                info = JSON.parseObject(response.body().string(), HistoryInfo.class);
                LogUtils.E("HistoryInfo","return JsonString::  "+JSON.toJSONString(info));
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadState();
                        if (info.getCode() == 0) {
                            List<HistoryInfo.DataBean.PostsBean> posts = info.getData().getPosts();
                            switch (rgRefreshStatus) {
                                case IDLE:
                                case REFRESHING:
                                    adapter.clear();
                                    if (posts.size() == 0) {
                                        showErrorLayout(StateLayout.noData);
                                    } else {
                                        adapter.addAll(posts);
                                    }
                                    break;
                                case PULL_DOWN:
                                    if (posts.size() == 0) {
                                        UiTipUtil.showToast(HistoryTalkActivity.this, R.string.no_more_data);
                                    } else {
                                        adapter.addAll(posts);
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

    private void disLoadState() {
        switch (rgRefreshStatus) {
            case IDLE:
                disLoadProgress();
                break;
            case REFRESHING:
                commonRefreshLayout.endRefreshing();
                disLoadProgress();
                break;
            case PULL_DOWN:
                commonRefreshLayout.endLoadingMore();
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

    private void showErrorLayout(int errType) {

        commonRefreshLayout.setVisibility(View.GONE);
        adapter.clear();
        commonStateLayout.setVisibility(View.VISIBLE);
        commonStateLayout.showError(errType);
    }

    @Override
    public void onRefreshing(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(baContext)) {
            UiTipUtil.showToast(baContext, R.string.check_phone_net);
            refreshLayout.endRefreshing();
        } else {
            rgRefreshStatus = RgRefreshStatus.REFRESHING;
            reQuestData(false);
        }
    }

    @Override
    public boolean onLoadingMore(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(HistoryTalkActivity.this)) {
            UiTipUtil.showToast(HistoryTalkActivity.this, R.string.check_phone_net);
            BaseAppApplication.mainHandler.post(new Runnable() {
                @Override
                public void run() {
//                    handler.sendEmptyMessageDelayed(RgConstants.load_error_net, 500);
                    ToastUtils.showShort(R.string.check_phone_net + "");
                    commonRefreshLayout.endLoadingMore();
                }
            });
        } else {
            rgRefreshStatus = RgRefreshStatus.PULL_DOWN;
            reQuestData(false);
        }
        return true;
    }
}
