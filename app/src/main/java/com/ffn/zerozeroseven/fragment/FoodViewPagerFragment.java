package com.ffn.zerozeroseven.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.FoodsAdapter;
import com.ffn.zerozeroseven.adapter.GoodsAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.RgRefreshStatus;
import com.ffn.zerozeroseven.bean.GoodsContentShowInfo;
import com.ffn.zerozeroseven.bean.ShangChangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.GoodsListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.ui.ShopDetilsActivity;
import com.ffn.zerozeroseven.utlis.NetUtil;
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
public class FoodViewPagerFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private RecyclerView commonRecyclerView;
    private BGARefreshLayout commonRefreshLayout;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    private StateLayout commonStateLayout;
    private FoodsAdapter adapter;
    String title;
    String shopType;
    private GoodsContentShowInfo contentShowInfo;
    private Double runMoney;
    private String storeId;
    private RelativeLayout rl_no_select;
    public static WeakReference<FoodViewPagerFragment> mInstance;

    private void setRefreshLayoutVis() {
        if (commonRefreshLayout.getVisibility() == View.GONE) {
            commonRefreshLayout.setVisibility(View.VISIBLE);
            commonStateLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        shopType = getArguments().getString("shopType");
    }

    public static FoodViewPagerFragment newInstance(String title, String shopType) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("shopType", shopType);
        FoodViewPagerFragment fragment = new FoodViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
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
        adapter = new FoodsAdapter(bfCxt);
        commonRecyclerView.setAdapter(adapter);
        adapter.setOnItemImageViewClick(new FoodsAdapter.OnItemImageClick() {
            @Override
            public void onClick(View view, int position) {
                GoodsContentShowInfo.DataBean.ProductsBean item = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("shopInfo", item);
                bundle.putString("type", "food");
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

    public void notifyShop() {
        try {
            adapter.clearCount();

        } catch (Exception e) {

        }
    }

    @Override
    public void initDate() {
        setRefreshLayoutVis();

    }

    private void requestShop() {
        setLoadPage();
        GoodsListInfo listInfo = new GoodsListInfo();
        listInfo.setFunctionName("ListSchoolGoods");
        GoodsListInfo.ParametersBean parametersBean = new GoodsListInfo.ParametersBean();
        parametersBean.setGoodsType(shopType);
        parametersBean.setCate("WM");
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
                                        ToastUtils.showShort("没有更多数据了");
                                    } else {
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
        ShangchangInfo shangchangInfo = new ShangchangInfo();
        shangchangInfo.setFunctionName("QuerySchoolStore");
        ShangchangInfo.ParametersBean parametersBean = new ShangchangInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        parametersBean.setCate("WM");
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

                            runMoney = shangChangShowInfo.getData().getExtraFee();
                            storeId = shangChangShowInfo.getData().getId() + "";
                            requestShop();

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


    @Override
    public void onRefreshing(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(bfCxt)) {
            commonRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showShort("请检查网络");
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
