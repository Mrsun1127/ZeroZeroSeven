package com.ffn.zerozeroseven.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.adapter.MyDingDanAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.ErrorCodeInfo;
import com.ffn.zerozeroseven.bean.MyDingDanShowInfo;
import com.ffn.zerozeroseven.bean.ShangChangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DeleteDingDanInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DingDanListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.ui.DingDanBobyActivity;
import com.ffn.zerozeroseven.ui.ZhiJieCommitDingDanActivity;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ConfirmDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SnacksFragment extends BaseReFreshFragment {

    private MyDingDanShowInfo myDingDanShowInfo;
    private MyDingDanAdapter adapter;

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        adapter = new MyDingDanAdapter(bfCxt);
        return adapter;
    }

    @Override
    protected void readRespones(String response) {
        myDingDanShowInfo = JSON.parseObject(response, MyDingDanShowInfo.class);
    }

    @Override
    protected int setFlag() {
        return myDingDanShowInfo.getCode();
    }

    @Override
    protected int setSize() {
        return myDingDanShowInfo.getData().getOrders().size();
    }

    @Override
    protected void addAll(BaseRecyclerAdapter adapter) {
        adapter.addAll(myDingDanShowInfo.getData().getOrders());
    }

    @Override
    protected Object setObj(int pageNo) {
        DingDanListInfo listInfo = new DingDanListInfo();
        listInfo.setFunctionName("ListUserGoodsOrder");
        DingDanListInfo.ParametersBean parametersBean = new DingDanListInfo.ParametersBean();
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(6);
        if (!TextUtils.isEmpty(schoolIId)) {
            parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        }
        listInfo.setParameters(parametersBean);
        return listInfo;
    }

    @Override
    public void doMain() {
        adapter.setOnItemImageViewClick(new MyDingDanAdapter.OnItemImageClick() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", adapter.getItem(position).getId());
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, DingDanBobyActivity.class, bundle);
            }
        });

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", adapter.getItem(position).getId());
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, DingDanBobyActivity.class, bundle);
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
        SharePrefUtils.saveObject(bfCxt, "zhijiecarShopInfo", carShopInfo);
        ZeroZeroSevenUtils.SwitchActivity(bfCxt, ZhiJieCommitDingDanActivity.class);
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
                        } else {
                            adapter.replaceItem(position, adapter.getItem(position));
                        }
                    }
                });
            }
        });
    }
}
