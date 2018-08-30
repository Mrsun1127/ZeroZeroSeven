package com.ffn.zerozeroseven.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.GoodsAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.GoodsContentShowInfo;
import com.ffn.zerozeroseven.bean.ShangChangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.GoodsListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.ui.SeachSchoolListActivity;
import com.ffn.zerozeroseven.ui.ShopDetilsActivity;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.NetUtil;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.UiTipUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.ffn.zerozeroseven.view.StateLayout;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.util.BGARefreshLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/20.
 */

@SuppressLint("ValidFragment")
public class ShopViewPagerAllFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private RecyclerView commonRecyclerView;
    private BGARefreshLayout commonRefreshLayout;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    private StateLayout commonStateLayout;
    private GoodsAdapter adapter;
    String title;
    String shopType;
    Context context;
    private GoodsContentShowInfo contentShowInfo;
    private Double runMoney;
    private String storeId;
    private RelativeLayout rl_no_select;
    public static WeakReference<ShopViewPagerAllFragment> mInstance;

    private void setRefreshLayoutVis() {
        if (commonRefreshLayout.getVisibility() == View.GONE) {
            commonRefreshLayout.setVisibility(View.VISIBLE);
            commonStateLayout.setVisibility(View.GONE);
        }

    }


    public static ShopViewPagerAllFragment newInstance(String title, String shopType) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("shopType", shopType);
        ShopViewPagerAllFragment fragment = new ShopViewPagerAllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void notifyShop() {
        adapter.clearCount();
    }

    @Override
    protected void initView(View view) {
        mInstance = new WeakReference<>(this);
        rl_no_select = view.findViewById(R.id.rl_no_select);
        commonStateLayout = view.findViewById(R.id.common_stateLayout);
        commonRefreshLayout = view.findViewById(R.id.common_refreshLayout);
        commonRecyclerView = view.findViewById(R.id.common_recyclerView);
        commonRefreshLayout.setDelegate(this);
        commonRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(bfCxt, true));
        commonRecyclerView.setLayoutManager(new GridLayoutManager(bfCxt, 2));
        commonRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 30, false));
        adapter = new GoodsAdapter(bfCxt);
        commonRecyclerView.setAdapter(adapter);
        adapter.setOnItemImageViewClick(new GoodsAdapter.OnItemImageClick() {
            @Override
            public void onClick(View view, int position) {
                GoodsContentShowInfo.DataBean.ProductsBean item = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("shopInfo", item);
                bundle.putString("type", "shop");
                ZeroZeroSevenUtils.SwitchActivity(getContext(), ShopDetilsActivity.class, bundle);
            }
        });

        commonStateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                commonStateLayout.setVisibility(View.GONE);
                commonRefreshLayout.setVisibility(View.VISIBLE);
                rgRefreshStatus = RgRefreshStatus.IDLE;
                getShangChangInfo();
            }
        });

    }

    @Override
    public void initDate() {
        setRefreshLayoutVis();
        userInfo = BaseAppApplication.getInstance().getLoginUser();
        if (userInfo != null) {
            schoolIId = userInfo.getSchoolId();
        }
        if ("943478288".equals(schoolIId)) {
            rl_no_select.setVisibility(View.VISIBLE);
        } else {
            rl_no_select.setVisibility(View.GONE);
            try {
                getShangChangInfo();
            } catch (Exception e) {

            }
        }
    }

    private void requestShop() {
        setLoadPage();
        GoodsListInfo listInfo = new GoodsListInfo();
        listInfo.setFunctionName("ListSchoolGoods");
        GoodsListInfo.ParametersBean parametersBean = new GoodsListInfo.ParametersBean();
        parametersBean.setGoodsType(shopType);
        parametersBean.setCate("ZH");
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(8);

        try {
            parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        } catch (Exception e) {
        }
        listInfo.setParameters(parametersBean);
        httpPostJSON(listInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                commonRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        showErrorLayout(StateLayout.netError);
                        disLoadState();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                contentShowInfo = JSON.parseObject(response.body().string(), GoodsContentShowInfo.class);
                commonRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadState();
                        if (contentShowInfo.getCode() == 0) {
                            List<GoodsContentShowInfo.DataBean.ProductsBean> products = contentShowInfo.getData().getProducts();
                            switch (rgRefreshStatus) {
                                case IDLE:
                                case REFRESHING:
                                    adapter.clear();
                                    if (products.size() == 0) {
                                        showErrorLayout(StateLayout.noData);
                                    } else {
                                        adapter.addAll(products);
                                        adapter.setRunMoneyAndStoreId(runMoney, storeId);
                                    }
                                    break;
                                case PULL_DOWN:
                                    if (products.size() == 0) {
                                        UiTipUtil.showToast(bfCxt, R.string.no_more_data);
                                    } else {
                                        adapter.addAll(products);
                                        adapter.setRunMoneyAndStoreId(runMoney, storeId);
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

    public void requestShopOnUp(String name) {
        commonStateLayout.setVisibility(View.GONE);
        commonRefreshLayout.setVisibility(View.VISIBLE);
        rgRefreshStatus = RgRefreshStatus.IDLE;
        setLoadPage();
        GoodsListInfo listInfo = new GoodsListInfo();
        listInfo.setFunctionName("ListSchoolGoods");
        GoodsListInfo.ParametersBean parametersBean = new GoodsListInfo.ParametersBean();
        parametersBean.setCate("ZH");
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        if (!TextUtils.isEmpty(name)) {
            parametersBean.setGoodsName(name);
        }
        parametersBean.setGoodsType(shopType);
        parametersBean.setPageIndex(0);
        parametersBean.setPageSize(16);
        listInfo.setParameters(parametersBean);
        httpPostJSON(listInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                commonRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        showErrorLayout(StateLayout.netError);
                        disLoadState();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                contentShowInfo = JSON.parseObject(response.body().string(), GoodsContentShowInfo.class);
                commonRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadState();
                        if (contentShowInfo.getCode() == 0) {
                            List<GoodsContentShowInfo.DataBean.ProductsBean> products = contentShowInfo.getData().getProducts();
                            switch (rgRefreshStatus) {
                                case IDLE:
                                case REFRESHING:
                                    adapter.clear();
                                    if (products.size() == 0) {
                                        showErrorLayout(StateLayout.noData);
                                    } else {
                                        adapter.addAll(products);
                                        adapter.setRunMoneyAndStoreId(runMoney, storeId);
                                        adapter.setLastCarShopInfo(BaseAppApplication.getInstance().getCarShopInfo());
                                    }
                                    break;
                                case PULL_DOWN:
                                    if (products.size() == 0) {
                                        UiTipUtil.showToast(bfCxt, R.string.no_more_data);
                                    } else {
                                        adapter.clear();
                                        adapter.addAll(products);
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

    private void getShangChangInfo() {
        final ShangchangInfo shangchangInfo = new ShangchangInfo();
        shangchangInfo.setFunctionName("QuerySchoolStore");
        ShangchangInfo.ParametersBean parametersBean = new ShangchangInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        parametersBean.setCate("ZH");
        shangchangInfo.setParameters(parametersBean);
        httpPostJSON(shangchangInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                commonRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        showErrorLayout(StateLayout.netError);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ShangChangShowInfo shangChangShowInfo = JSON.parseObject(response.body().string(), ShangChangShowInfo.class);
                commonRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (shangChangShowInfo.getCode() == 0) {
                            userInfo.setServicePhone(shangChangShowInfo.getData().getServicePhone());
                            BaseAppApplication.getInstance().setLoginUser(userInfo);
                            SharePrefUtils.saveObject(bfCxt, "userInfo", userInfo);
                            runMoney = shangChangShowInfo.getData().getExtraFee();
                            storeId = shangChangShowInfo.getData().getId() + "";
                            userInfo.setSmallRmb(shangChangShowInfo.getData().getDeliveryPrice());
                            SharePrefUtils.saveObject(bfCxt, "userInfo", userInfo);
                            requestShop();
                            if (shangChangShowInfo.getData().isIsClosing() && shangChangShowInfo.getData().getStoreBusiTimes() != null && shangChangShowInfo.getData().getStoreBusiTimes().size() > 1) {
                                ZeroZeroSevenUtils.showSleepPop(bfCxt, "\t\t营业时间" + "\n" + shangChangShowInfo.getData().getStoreBusiTimes().get(0).getOpeningTime() + "--" + shangChangShowInfo.getData().getStoreBusiTimes().get(0).getClosingTime() + "\n" + shangChangShowInfo.getData().getStoreBusiTimes().get(1).getOpeningTime() + "--" + shangChangShowInfo.getData().getStoreBusiTimes().get(1).getClosingTime(), rl_no_select);
                            } else {
                                ZeroZeroSevenUtils.showSleepPop(bfCxt, "小店正在打烊中", rl_no_select);
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

    int pageNo = 0;

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
    protected int setLayout() {
        return R.layout.fragment_shop_viewpager;
    }

    @Override
    protected void lazyLoad() {
    }


    @Override
    public void onRefreshing(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(bfCxt)) {
            commonRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    UiTipUtil.showToast(bfCxt, R.string.check_phone_net);
                    commonRefreshLayout.endRefreshing();
                }
            });

        } else {
            rgRefreshStatus = RgRefreshStatus.REFRESHING;
            requestShop();
        }
    }

    @Override
    public boolean onLoadingMore(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(bfCxt)) {
            commonRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showShort(R.string.check_phone_net + "");
                    commonRefreshLayout.endLoadingMore();
                }
            });
        } else {
            rgRefreshStatus = RgRefreshStatus.PULL_DOWN;
            requestShop();
        }
        return true;
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindDrawables(commonRecyclerView);
    }
}
