package com.ffn.zerozeroseven.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopTitleAdapter;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.ShangChangShowInfo;
import com.ffn.zerozeroseven.bean.ShopTitleInfo;
import com.ffn.zerozeroseven.bean.requsetbean.GoodTabsInfo;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.ui.CommitDingDanActivity;
import com.ffn.zerozeroseven.ui.FoodCommitDingDanActivity;
import com.ffn.zerozeroseven.ui.LoginActivity;
import com.ffn.zerozeroseven.ui.SearchSchoolActivity;
import com.ffn.zerozeroseven.ui.TakeAwayFoodActivity;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.NXHooldeView;
import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by GT on 2017/11/15.
 */

public class FoodFragment extends BaseFragment implements View.OnClickListener {
    private ViewPager viewPager;                             //定义viewPager
    private FragmentPagerAdapter fAdapter;                               //定义adapter
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表
    private FoodViewPagerFragment mineFragment;
    private ImageButton ib_shopcar;
    public static WeakReference<FoodFragment> mInstance;
    private QBadgeView badgeView;
    private TextView tv_name;
    private TextView tv_yysj;
    private TextView tv_qisongfei;
    private TextView tv_paotuifei;
    private TextView tv_shop_phone;
    private TextView tv_desc;
    private RelativeLayout rl_back;
    private RelativeLayout rl_search;
    private ShopTitleAdapter titleAdapter;

    public static FoodFragment newInstance() {
        return new FoodFragment();
    }

    private RecyclerView recycleview;
    private ImageView iv_icon;
    private ImageView iv_in_bg;

    private void getshangchangInfo() {
        final ShangchangInfo shangchangInfo = new ShangchangInfo();
        shangchangInfo.setFunctionName("QuerySchoolStore");
        ShangchangInfo.ParametersBean parametersBean = new ShangchangInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        parametersBean.setCate("WM");
        shangchangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(shangchangInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ShangChangShowInfo shangChangShowInfo = JSON.parseObject(response, ShangChangShowInfo.class);
                if (shangChangShowInfo.getCode() == 0) {
                    tv_name.setText(shangChangShowInfo.getData().getStoreName());
                    tv_qisongfei.setText("起送：￥" + shangChangShowInfo.getData().getDeliveryPrice());
                    tv_paotuifei.setText("跑腿费：￥" + shangChangShowInfo.getData().getExtraFee());
                    tv_shop_phone.setText("客服电话：" + shangChangShowInfo.getData().getServicePhone());
                    tv_desc.setText(TextUtils.isEmpty(shangChangShowInfo.getData().getPromotion()) ? "下单有惊喜" : shangChangShowInfo.getData().getPromotion());
                    Glide.with(bfCxt).load(shangChangShowInfo.getData().getLogo()).into(iv_icon);
                    Glide.with(bfCxt).load(shangChangShowInfo.getData().getBackground()).override(10, 10).into(iv_in_bg);
                    if (shangChangShowInfo.getData().getStoreBusiTimes() != null && shangChangShowInfo.getData().getStoreBusiTimes().size() > 1) {
                        tv_yysj.setText("营业时间：" + shangChangShowInfo.getData().getStoreBusiTimes().get(0).getOpeningTime() + "--" + shangChangShowInfo.getData().getStoreBusiTimes().get(0).getClosingTime() + " " + shangChangShowInfo.getData().getStoreBusiTimes().get(1).getOpeningTime() + "--" + shangChangShowInfo.getData().getStoreBusiTimes().get(1).getClosingTime());
                    } else {
                        tv_yysj.setText("营业时间：" + shangChangShowInfo.getData().getStoreBusiTimes().get(0).getOpeningTime() + "--" + shangChangShowInfo.getData().getStoreBusiTimes().get(0).getClosingTime());
                    }

                }
            }
        });
    }

    @Override
    protected void initView(View view) {
        badgeView = new QBadgeView(bfCxt);
        rl_search = view.findViewById(R.id.rl_search);
        iv_icon = view.findViewById(R.id.iv_icon);
        iv_in_bg = view.findViewById(R.id.iv_in_bg);
        iv_in_bg.setScaleX(1.8f);
        iv_in_bg.setScaleY(1.2f);
        rl_back = view.findViewById(R.id.rl_back);
        rl_back.setVisibility(View.VISIBLE);
        tv_shop_phone = view.findViewById(R.id.tv_shop_phone);
        tv_desc = view.findViewById(R.id.tv_desc);
        tv_paotuifei = view.findViewById(R.id.tv_paotuifei);
        tv_qisongfei = view.findViewById(R.id.tv_qisongfei);
        tv_yysj = view.findViewById(R.id.tv_yysj);
        viewPager = view.findViewById(R.id.viewpager);
        recycleview = view.findViewById(R.id.recycleview);
        ib_shopcar = view.findViewById(R.id.ib_shopcar);
        tv_name = view.findViewById(R.id.tv_name);
        ib_shopcar.setOnClickListener(this);
        rl_back.setOnClickListener(this);
        badgeView.bindTarget(ib_shopcar);
        rl_search.setVisibility(View.GONE);
        badgeView.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                LogUtils.D("state", "" + dragState);
                if (dragState == 5) {
                    CarShopInfo carShopInfo = BaseAppApplication.getInstance().getFoodcarShopInfo();
                    carShopInfo.getShopInfos().clear();
                    BaseAppApplication.getInstance().setFoodcarShopInfo(carShopInfo);
                    try {
                        FoodViewPagerAllFragment.mInstance.get().notifyShop();
                        FoodViewPagerFragment.mInstance.get().notifyShop();
                    } catch (Exception e) {
                    }
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(bfCxt);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview.setLayoutManager(layoutManager);
        titleAdapter = new ShopTitleAdapter(bfCxt);
        recycleview.setAdapter(titleAdapter);
        titleAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                titleAdapter.setClickPosition(position);
                viewPager.setCurrentItem(position);
                recycleview.scrollToPosition(position);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                titleAdapter.setClickPosition(position);
                recycleview.scrollToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mInstance = new WeakReference<>(this);

    }


    public void initTabs() {
        GoodTabsInfo goodTabsInfo = new GoodTabsInfo();
        goodTabsInfo.setFunctionName("ListSchoolGoodsType");
        GoodTabsInfo.ParametersBean parametersBean = new GoodTabsInfo.ParametersBean();
        parametersBean.setSchoolId(schoolIId);
        parametersBean.setCate("WM");
        goodTabsInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(goodTabsInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                ShopTitleInfo showInfo = JSON.parseObject(response, ShopTitleInfo.class);
                if (showInfo.getCode() == 0) {
                    list_title = new ArrayList<>();
                    list_fragment = new ArrayList<>();
                    titleAdapter.cleanDates();
                    ShopTitleInfo.DataBean.GoodsTypesBean goodsTypesBean = new ShopTitleInfo.DataBean.GoodsTypesBean();
                    goodsTypesBean.setName("全部");
                    goodsTypesBean.setIcon(AppConfig.SHOP_ALL);
                    showInfo.getData().getGoodsTypes().add(0, goodsTypesBean);
                    titleAdapter.addAll(showInfo.getData().getGoodsTypes());
                    int length = showInfo.getData().getGoodsTypes().size();
                    for (int i = 0; i < length; i++) {
                        list_title.add(showInfo.getData().getGoodsTypes().get(i).getName());
                        if (i == 0) {
                            list_fragment.add(FoodViewPagerAllFragment.newInstance("", ""));
                        } else {
                            mineFragment = FoodViewPagerFragment.newInstance(showInfo.getData().getGoodsTypes().get(i).getName(), String.valueOf(showInfo.getData().getGoodsTypes().get(i).getId()));
                            list_fragment.add(mineFragment);
                        }
                    }
                    try {
                        viewPager.setOffscreenPageLimit(list_title.size());
                        fAdapter = new ShopViewPagerAdapter(getChildFragmentManager(), list_fragment, list_title);
                        viewPager.setAdapter(fAdapter);
                    } catch (Exception e) {
                    }

                }
            }
        });


    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_shopupdate;
    }

    @Override
    protected void lazyLoad() {
        LogUtils.D("lazyLoad", "lazyLoad");
        getshangchangInfo();
        initTabs();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                BaseAppApplication.getInstance().finishActivity(getActivity());
                break;
            case R.id.ib_shopcar:
                userInfo = BaseAppApplication.getInstance().getLoginUser();
                if (userInfo != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 0);//0标记从购物车进来的
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, FoodCommitDingDanActivity.class, bundle);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.tv_school_name:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, SearchSchoolActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }

                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
        userInfo = BaseAppApplication.getInstance().getLoginUser();
        if (userInfo != null) {
            if (!TextUtils.isEmpty(userInfo.getSchoolName())) {
                if (userInfo.getSchoolName().length() > 7) {
                    tv_name.setText(userInfo.getSchoolName().substring(0, 6) + "...");
                } else {
                    tv_name.setText(userInfo.getSchoolName());

                }
            }
            notifyCar();
        }

    }

    public void notifyCar() {
        final CarShopInfo carShopInfo = BaseAppApplication.getInstance().getFoodcarShopInfo();
        try {
            if (carShopInfo == null || carShopInfo.getShopInfos().size() == 0) {
                BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        badgeView.setBadgeNumber(0);
                    }
                }, 500);
            } else {
                BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        badgeView.setBadgeNumber(carShopInfo.getShopInfos().size());
                    }
                }, 500);
            }
        } catch (Exception e) {
        }

    }

    public void addAction(View startView) {
        NXHooldeView nxHooldeView = new NXHooldeView(bfCxt);
        int position[] = new int[2];
        startView.getLocationInWindow(position);
        nxHooldeView.setStartPosition(new Point(position[0], position[1]));
        ViewGroup rootView = (ViewGroup) getActivity().getWindow().getDecorView();
        rootView.addView(nxHooldeView);
        int endPosition[] = new int[2];
        ib_shopcar.getLocationInWindow(endPosition);
        nxHooldeView.setEndPosition(new Point(endPosition[0], endPosition[1]));
        nxHooldeView.startBeizerAnimation();
    }

}
