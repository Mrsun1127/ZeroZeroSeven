package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.LeaseDingDanAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.LeaseDingDanListINfo;
import com.ffn.zerozeroseven.bean.requsetbean.DeleteleaseDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DingDanListInfo;
import com.ffn.zerozeroseven.ui.LeaseDingDanBobyActivity;
import com.ffn.zerozeroseven.ui.LeaseZhiJieCommitDingDanActivity;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LeaseDingDanFragment extends BaseReFreshFragment {

    private LeaseDingDanListINfo myDingDanShowInfo;
    private LeaseDingDanAdapter adapter;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adapter = new LeaseDingDanAdapter(bfCxt);
        return adapter;
    }

    @Override
    protected void readRespones(String response) {
        LogUtils.D("response", response);
        myDingDanShowInfo = JSON.parseObject(response, LeaseDingDanListINfo.class);
    }

    @Override
    protected int setFlag() {
        return myDingDanShowInfo.getCode();
    }

    @Override
    protected int setSize() {
        return myDingDanShowInfo.getData().getList().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(myDingDanShowInfo.getData().getList());
    }

    @Override
    protected Object setObj(int pageNo) {
        DingDanListInfo listInfo = new DingDanListInfo();
        listInfo.setFunctionName("ListUserLeaseOrder");
        DingDanListInfo.ParametersBean parametersBean = new DingDanListInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        listInfo.setParameters(parametersBean);
        return listInfo;
    }

    @Override
    public void doMain() {
        adapter.setOnItemImageViewClick(new LeaseDingDanAdapter.OnItemImageClick() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", adapter.getItem(position).getId());
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, LeaseDingDanBobyActivity.class, bundle);
            }
        });

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", adapter.getItem(position).getId());
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, LeaseDingDanBobyActivity.class, bundle);
            }
        });
        adapter.setOnItemDeleteClick(new LeaseDingDanAdapter.OnItemDeleteClick() {
            @Override
            public void onClick(View view, int position) {
                deleteDingDan(position);
            }
        });
        adapter.setOnItemAgainClick(new LeaseDingDanAdapter.OnItemAgainClick() {
            @Override
            public void onClick(View view, int position) {
                moreComeOneAlone(position);
            }
        });
    }

    private void moreComeOneAlone(int position) {
        LeaseDingDanListINfo.DataBean.ListBean item = adapter.getItem(position);
        adapter.getItem(position);
        CarShopInfo carShopInfo = new CarShopInfo();
        List<CarShopInfo.ShopInfo> shopInfos = new ArrayList<>();
        for (int i = 0; i < item.getOrderGoodsList().size(); i++) {
            CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
            shopInfo.setBuyCount(item.getOrderGoodsList().get(i).getGoodsCount());
            shopInfo.setShopName(item.getOrderGoodsList().get(i).getGoodsName());
            shopInfo.setGoodsId(item.getOrderGoodsList().get(i).getGoodId());
            shopInfo.setImagUrl(item.getOrderGoodsList().get(i).getGoodsThumb());
            shopInfo.setShopMoney(item.getOrderGoodsList().get(i).getGoodsPrice());
            shopInfos.add(shopInfo);
        }
        carShopInfo.setShopInfos(shopInfos);
        SharePrefUtils.saveObject(bfCxt, "zhijiecarShopInfo", carShopInfo);
        Bundle bundle = new Bundle();
//        bundle.putString("payCate", item.getOrderCate());
        ZeroZeroSevenUtils.SwitchActivity(bfCxt, LeaseZhiJieCommitDingDanActivity.class, bundle);
    }

    private void deleteDingDan(final int adapterPosition) {
        final ConfirmDialog confirmDialog = new ConfirmDialog(bfCxt);
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
            }
        });
    }

    private void Request(final int position) {
        DeleteleaseDingDanInfo deleteDingDanInfo = new DeleteleaseDingDanInfo();
        deleteDingDanInfo.setFunctionName("DeleteUserLeaseOrder");
        DeleteleaseDingDanInfo.ParametersBean parametersBean = new DeleteleaseDingDanInfo.ParametersBean();
        parametersBean.setOrderId(String.valueOf(adapter.getItem(position).getId()));
        parametersBean.setUserId(userId);
        deleteDingDanInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(deleteDingDanInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final ErrorCodeInfo errorCodeInfo = JSON.parseObject(response, ErrorCodeInfo.class);
                if (errorCodeInfo.getCode() == 0) {
                    adapter.removeItem(adapter.getItem(position));
                } else {
                    ToastUtils.showShort(errorCodeInfo.getMessage());
                }

            }
        });

    }
}
