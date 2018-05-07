package com.ffn.zerozeroseven.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
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
import com.ffn.zerozeroseven.ui.ShopDetilsActivity;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.NetUtil;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.UiTipUtil;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.ffn.zerozeroseven.view.StateLayout;
import com.ffn.zerozeroseven.view.WaitingDialog;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.util.BGARefreshLayout;
import me.wangyuwei.loadingview.LoadingView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by GT on 2017/11/20.
 */

@SuppressLint("ValidFragment")
public class ShopViewPagerFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private RecyclerView commonRecyclerView;
    private BGARefreshLayout commonRefreshLayout;
    private RgRefreshStatus rgRefreshStatus = RgRefreshStatus.IDLE;
    private StateLayout commonStateLayout;
    private GoodsAdapter adapter;
    String title;
    String shopType;
    private GoodsContentShowInfo contentShowInfo;
    private LoadingView loadingView;
    private Double runMoney;
    private String storeId;
    private RelativeLayout rl_no_select;
    public static WeakReference<ShopViewPagerFragment> mInstance;
    private void setRefreshLayoutVis() {
        if (commonRefreshLayout.getVisibility() == View.GONE) {
            commonRefreshLayout.setVisibility(View.VISIBLE);
            commonStateLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title=getArguments().getString("title");
        shopType=getArguments().getString("shopType");
    }

    public static ShopViewPagerFragment newInstance(String title, String shopType){
        Bundle args=new Bundle();
        args.putString("title",title);
        args.putString("shopType",shopType);
        ShopViewPagerFragment fragment=new ShopViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        mInstance=new WeakReference<>(this);
        rl_no_select = view.findViewById(R.id.rl_no_select);
        loadingView = view.findViewById(R.id.loadingView);
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
                ZeroZeroSevenUtils.SwitchActivity(getContext(), ShopDetilsActivity.class, bundle);
            }
        });

        commonStateLayout.setOnStateCallListener(new StateLayout.OnStateLayoutCallListener() {
            @Override
            public void reCall() {
                commonStateLayout.setVisibility(View.GONE);
                commonRefreshLayout.setVisibility(View.VISIBLE);
                rgRefreshStatus = RgRefreshStatus.IDLE;
                requestShop();
            }
        });

    }
    public void notifyShop(){
        try {
            adapter.clearCount();

        }catch (Exception e){

        }
    }
    @Override
    public void initDate() {
        setRefreshLayoutVis();
        if(userInfo!=null){
            schoolIId=BaseAppApplication.getInstance().getLoginUser().getSchoolId();
        }
        if ("943478288".equals(schoolIId)) {
            rl_no_select.setVisibility(View.VISIBLE);
        } else {
            rl_no_select.setVisibility(View.GONE);
            try {
                getShangChangInfo();
                requestShop();
            }catch (Exception e){

            }
        }
    }

    private void requestShop() {
        setLoadPage();
        GoodsListInfo listInfo = new GoodsListInfo();
        listInfo.setFunctionName("ListSchoolGoods");
        GoodsListInfo.ParametersBean parametersBean = new GoodsListInfo.ParametersBean();
        parametersBean.setGoodsType(shopType);
        parametersBean.setPageIndex(pageNo);
        parametersBean.setPageSize(16);
        try {
            parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        }catch (Exception e){
        }
        listInfo.setParameters(parametersBean);
        httpPostJSON(listInfo,true);
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
                contentShowInfo = JSON.parseObject(response.body().string(), GoodsContentShowInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
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
                                        adapter.setLastCarShopInfo(SharePrefUtils.readObject(getContext(), "carShopInfo"));
                                    }
                                    break;
                                case PULL_DOWN:
                                    if (products.size() == 0) {
                                        UiTipUtil.showToast(bfCxt, R.string.no_more_data);
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



    public void requestShopOnUp(String name) {
        setLoadPage();
        GoodsListInfo listInfo = new GoodsListInfo();
        listInfo.setFunctionName("ListSchoolGoods");
        GoodsListInfo.ParametersBean parametersBean = new GoodsListInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        parametersBean.setGoodsName(name);
        listInfo.setParameters(parametersBean);
        httpPostJSON(listInfo,true);
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
                contentShowInfo = JSON.parseObject(response.body().string(), GoodsContentShowInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
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
                                        adapter.setLastCarShopInfo(SharePrefUtils.readObject(getContext(), "carShopInfo"));
                                    }
                                    break;
                                case PULL_DOWN:
                                    if (products.size() == 0) {
                                        UiTipUtil.showToast(bfCxt, R.string.no_more_data);
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
        showLoadProgress();
        final ShangchangInfo shangchangInfo = new ShangchangInfo();
        shangchangInfo.setFunctionName("QuerySchoolStore");
        ShangchangInfo.ParametersBean parametersBean = new ShangchangInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        shangchangInfo.setParameters(parametersBean);
        httpPostJSON(shangchangInfo,true);
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
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        disLoadProgress();
                    }
                });
                final ShangChangShowInfo shangChangShowInfo = JSON.parseObject(response.body().string(), ShangChangShowInfo.class);
                if (shangChangShowInfo.getCode() == 0) {
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            runMoney = shangChangShowInfo.getData().getExtraFee();
                            storeId = shangChangShowInfo.getData().getId() + "";
                        }
                    });
                }
            }
        });
    }

    private void disLoadState() {
        switch (rgRefreshStatus) {
            case IDLE:
                loadingView.setVisibility(View.GONE);
                break;
            case REFRESHING:
                commonRefreshLayout.endRefreshing();
                loadingView.setVisibility(View.GONE);
                break;
            case PULL_DOWN:
                commonRefreshLayout.endLoadingMore();
                loadingView.setVisibility(View.GONE);
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
                loadingView.setVisibility(View.VISIBLE);

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
            UiTipUtil.showToast(bfCxt, R.string.check_phone_net);
            refreshLayout.endRefreshing();
        } else {
            rgRefreshStatus = RgRefreshStatus.REFRESHING;
            requestShop();
        }
    }

    @Override
    public boolean onLoadingMore(BGARefreshLayout refreshLayout) {
        if (!NetUtil.hasNetConnect(bfCxt)) {
            UiTipUtil.showToast(bfCxt, R.string.check_phone_net);
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
            requestShop();
        }
        return true;
    }
}
