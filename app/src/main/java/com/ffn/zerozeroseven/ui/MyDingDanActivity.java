package com.ffn.zerozeroseven.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.MyDingDanAdapter;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.RgConstants;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.MyDingDanShowInfo;
import com.ffn.zerozeroseven.bean.ShangChangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DeleteDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DingDanListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.NetUtil;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.UiTipUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.StateLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.util.BGARefreshLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/19.dsdsds
 */

public class MyDingDanActivity extends BaseActivity implements View.OnClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {
    int pageNo = 0;
    private RecyclerView common_recyclerView;
    private MyDingDanAdapter adapter;
    private MyDingDanShowInfo myDingDanShowInfo;
    private BGARefreshLayout commonRefreshLayout;
    private StateLayout commonStateLayout;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case RgConstants.load_error_net:
                    ToastUtils.showShort(R.string.check_phone_net + "");
                    commonRefreshLayout.endLoadingMore();
                    break;
                case RgConstants.load_no_next:
                    ToastUtils.showShort(R.string.no_more_data + "");
                    commonRefreshLayout.endLoadingMore();
                    break;

            }
        }

    };

    private void setRefreshLayoutVis() {
        if (commonRefreshLayout.getVisibility() == View.GONE) {
            commonRefreshLayout.setVisibility(View.VISIBLE);
            commonStateLayout.setVisibility(View.GONE);
        }

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_dingdan1;
    }

    @Override
    public void initView() {
        BaseAppApplication.getInstance().addActivity(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView textView = findViewById(R.id.tv_top);
        textView.setText("我的订单");
        commonStateLayout = findViewById(R.id.common_stateLayout);
        commonRefreshLayout = findViewById(R.id.common_refreshLayout);
        commonRefreshLayout.setDelegate(this);
        commonRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(this, true));
        common_recyclerView = findViewById(R.id.common_recyclerView);
        common_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        common_recyclerView.addItemDecoration(new SpaceItemDecoration(10));
//        common_recyclerView.setItemViewSwipeEnabled(true);
//        common_recyclerView.setOnItemMoveListener(new OnItemMoveListener() {
//            @Override
//            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
//                return false;
//            }
//
//            @Override
//            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
//                deleteDingDan(srcHolder.getAdapterPosition());
//            }
//        });
        adapter = new MyDingDanAdapter(this);
        adapter.setOnItemImageViewClick(new MyDingDanAdapter.OnItemImageClick() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", adapter.getItem(position).getId());
                ZeroZeroSevenUtils.SwitchActivity(MyDingDanActivity.this, DingDanBobyActivity.class, bundle);
            }
        });
        common_recyclerView.setAdapter(adapter);
        commonStateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                commonStateLayout.setVisibility(View.GONE);
                commonRefreshLayout.setVisibility(View.VISIBLE);
                rgRefreshStatus = RgRefreshStatus.IDLE;
                requestData();
            }
        });
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", adapter.getItem(position).getId());
                ZeroZeroSevenUtils.SwitchActivity(MyDingDanActivity.this, DingDanBobyActivity.class, bundle);
            }
        });
        adapter.setOnItemDeleteClick(new MyDingDanAdapter.OnItemDeleteClick() {
            @Override
            public void onClick(View view, int position) {
                deleteDingDan(position);
            }
        });
        adapter.setOnItemAgainClick(new MyDingDanAdapter.OnItemAgainClick() {
            @Override
            public void onClick(View view, int position) {
                getShangChangInfo(position);
            }
        });
    }

    private void getShangChangInfo(final int position) {
        showLoadProgress();
        final ShangchangInfo shangchangInfo = new ShangchangInfo();
        shangchangInfo.setFunctionName("QuerySchoolStore");
        ShangchangInfo.ParametersBean parametersBean = new ShangchangInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        shangchangInfo.setParameters(parametersBean);
        httpPostJSON(shangchangInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ShangChangShowInfo shangChangShowInfo = JSON.parseObject(response.body().string(), ShangChangShowInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if (shangChangShowInfo.getCode() == 0) {
                            String storeId = shangChangShowInfo.getData().getId() + "";//商家Id
                            moreComeOneAlone(position, storeId);
                        }
                    }
                });
            }
        });
    }


    private void moreComeOneAlone(int position, String id) {
        MyDingDanShowInfo.DataBean.OrdersBean item = adapter.getItem(position);
        CarShopInfo carShopInfo = new CarShopInfo();
        List<CarShopInfo.ShopInfo> shopInfos = new ArrayList<>();
        for (int i = 0; i < item.getDetails().size(); i++) {
            CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
            shopInfo.setBuyCount(item.getDetails().get(i).getGoodsCount());
            shopInfo.setShopName(item.getDetails().get(i).getGoodsName());
            shopInfo.setGoodsId(item.getDetails().get(i).getGoodsId());
            shopInfo.setRunMoney(item.getExtraPrice());
            shopInfo.setImagUrl(item.getDetails().get(i).getGoodsImage());
            shopInfo.setShopMoney(item.getDetails().get(i).getGoodsPrice());
            shopInfo.setShopId(id);
            shopInfos.add(shopInfo);
        }
        carShopInfo.setShopInfos(shopInfos);
        SharePrefUtils.saveObject(MyDingDanActivity.this, "zhijiecarShopInfo", carShopInfo);
        ZeroZeroSevenUtils.SwitchActivity(MyDingDanActivity.this, ZhiJieCommitDingDanActivity.class);
    }

    private void deleteDingDan(final int adapterPosition) {
        final ConfirmDialog confirmDialog = new ConfirmDialog(MyDingDanActivity.this);
        confirmDialog.setTitles("提示");
        confirmDialog.setMessages("确定删除订单？");
        confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                confirmDialog.dismiss();
                Request(adapterPosition);
            }

            @Override
            public void doCancel() {
                confirmDialog.dismiss();
                adapter.replaceItem(adapterPosition, adapter.getItem(adapterPosition));
            }
        });
    }

    private void Request(final int position) {
        showLoadProgress();
        DeleteDingDanInfo deleteDingDanInfo = new DeleteDingDanInfo();
        deleteDingDanInfo.setFunctionName("DeleteUserGoodsOrder");
        DeleteDingDanInfo.ParametersBean parametersBean = new DeleteDingDanInfo.ParametersBean();
        parametersBean.setOrderNo(adapter.getItem(position).getOrderNo());
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        deleteDingDanInfo.setParameters(parametersBean);
        httpPostJSON(deleteDingDanInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        adapter.replaceItem(position, adapter.getItem(position));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ErrorCodeInfo errorCodeInfo = JSON.parseObject(response.body().string(), ErrorCodeInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                        if (errorCodeInfo.getCode() == 0) {
                            adapter.removeItem(adapter.getItem(position));
                            --curSize;
                            if (curSize == 0) {
                                showErrorLayout(StateLayout.noData);
                            }
                        } else {
                            adapter.replaceItem(position, adapter.getItem(position));
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void doMain() {
        setRefreshLayoutVis();
        requestData();
    }

    int curSize = 0;

    private void requestData() {
        setLoadPage();
        DingDanListInfo listInfo = new DingDanListInfo();
        listInfo.setFunctionName("ListUserGoodsOrder");
        DingDanListInfo.ParametersBean parametersBean = new DingDanListInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        if (!TextUtils.isEmpty(schoolIId)) {
            parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        }
        listInfo.setParameters(parametersBean);
        httpPostJSON(listInfo, true);
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
            public void onResponse(Call call, final Response response) throws IOException {
//                LogUtils.E("goodsId","到底有没有Id"+response.body().string());
                myDingDanShowInfo = JSON.parseObject(response.body().string(), MyDingDanShowInfo.class);
                LogUtils.E("myDingDanShowInfo",JSON.toJSONString(myDingDanShowInfo));
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadState();
                        if (myDingDanShowInfo.getCode() == 0) {
                            curSize = myDingDanShowInfo.getData().getOrders().size();
                            switch (rgRefreshStatus) {
                                case IDLE:
                                case REFRESHING:
                                    adapter.clear();
                                    if (myDingDanShowInfo.getData().getOrders().size() == 0) {
                                        showErrorLayout(StateLayout.noData);
                                    } else {
                                        adapter.addAll(myDingDanShowInfo.getData().getOrders());

                                    }
                                    break;
                                case PULL_DOWN:
                                    if (myDingDanShowInfo.getData().getOrders().size() == 0) {
                                        UiTipUtil.showToast(MyDingDanActivity.this, R.string.no_more_data);
                                    } else {
                                        adapter.addAll(myDingDanShowInfo.getData().getOrders());
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:

                break;
        }
    }

    @Override
    public void onRefreshing(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(baContext)) {
            ToastUtils.showShort("请检查网络是否打开");
            refreshLayout.endRefreshing();
        } else {
            rgRefreshStatus = RgRefreshStatus.REFRESHING;
            requestData();
        }
    }

    @Override
    public boolean onLoadingMore(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(baContext)) {
            ToastUtils.showShort("请检查网络是否打开");
            handler.sendEmptyMessageDelayed(RgConstants.load_error_net, 500);
        } else {
            rgRefreshStatus = RgRefreshStatus.PULL_DOWN;
            requestData();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacksAndMessages(null);
    }
}
