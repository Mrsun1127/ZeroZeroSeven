package com.ffn.zerozeroseven.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.ShopViewPagerAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.GoodTabsShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.GoodTabsInfo;
import com.ffn.zerozeroseven.ui.CommitDingDanActivity;
import com.ffn.zerozeroseven.ui.LoginActivity;
import com.ffn.zerozeroseven.ui.SearchSchoolActivity;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.NXHooldeView;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by GT on 2017/11/15.
 */

public class ShopFragment extends BaseFragment implements View.OnClickListener {
    private TabLayout tabLayout;                            //定义TabLayout
    private ViewPager viewPager;                             //定义viewPager
    private FragmentPagerAdapter fAdapter;                               //定义adapter
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表
    private ShopViewPagerFragment mineFragment;
    private ImageButton ib_shopcar;
    private TextView tv_school_name;
    public static WeakReference<ShopFragment> mInstance;
    private SearchView etSearch;
    private QBadgeView badgeView;
    private RelativeLayout focus;

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    @Override
    protected void initView(View view) {
        badgeView = new QBadgeView(bfCxt);
        tabLayout = view.findViewById(R.id.tab_level);
        viewPager = view.findViewById(R.id.viewpager);
        ib_shopcar = view.findViewById(R.id.ib_shopcar);
        etSearch = view.findViewById(R.id.et_search);
        focus = view.findViewById(R.id.focus);
        tv_school_name = view.findViewById(R.id.tv_school_name);
        tv_school_name.setOnClickListener(this);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ib_shopcar.setOnClickListener(this);
        badgeView.bindTarget(ib_shopcar);
        badgeView.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                LogUtils.D("state", "" + dragState);
                if (dragState == 5) {
                    CarShopInfo carShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
                    carShopInfo.getShopInfos().clear();
                    BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
                    ShopViewPagerAllFragment.mInstance.get().notifyShop();
                    ShopViewPagerFragment.mInstance.get().notifyShop();
                }
            }
        });
        etSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MobclickAgent.onEvent(getActivity(), "商品搜索关键字");
                if (!TextUtils.isEmpty(query)) {
                    ShopViewPagerAllFragment.mInstance.get().requestShopOnUp(query);
                    BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(0);
                        }
                    }, 500);
                } else {
                    ToastUtils.showShort("请输入关键字");
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    if ("".equals(newText)) {
                        ShopViewPagerAllFragment.mInstance.get().requestShopOnUp("");
                    }
                } catch (Exception e) {
                }
                return false;
            }
        });
        mInstance = new WeakReference<>(this);
        initTabs();
    }

    public void initTabs() {
        GoodTabsInfo goodTabsInfo = new GoodTabsInfo();
        goodTabsInfo.setFunctionName("ListGoodsType");
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(goodTabsInfo, true, true, tabLayout);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                GoodTabsShowInfo showInfo = JSON.parseObject(response, GoodTabsShowInfo.class);
                if (showInfo.getCode() == 0) {
                    LogUtils.D("logcat1", "2");
                    list_title = new ArrayList<>();
                    list_title.add("全部");
                    list_fragment = new ArrayList<>();
                    list_fragment.add(ShopViewPagerAllFragment.newInstance("", ""));
                    for (int i = 0; i < showInfo.getData().getItems().size(); i++) {
                        list_title.add(showInfo.getData().getItems().get(i).getDicValue());
                        mineFragment = ShopViewPagerFragment.newInstance(showInfo.getData().getItems().get(i).getDicValue(), showInfo.getData().getItems().get(i).getDicKey());
                        list_fragment.add(mineFragment);
                        LogUtils.D("logcat1", "3");
                    }
                    tabLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                viewPager.setOffscreenPageLimit(list_title.size());
                                fAdapter = new ShopViewPagerAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
                                viewPager.setAdapter(fAdapter);
                                tabLayout.setupWithViewPager(viewPager);
                                LogUtils.D("logcat1", "4");
                            } catch (Exception e) {
                            }
                        }
                    });
                }
            }
        });


    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_shopcar:
                userInfo = BaseAppApplication.getInstance().getLoginUser();
                if (userInfo != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 0);//0标记从购物车进来的
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, CommitDingDanActivity.class, bundle);
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
        focus.setFocusable(true);
        focus.setFocusableInTouchMode(true);
        focus.requestFocus();
//        String schoolName = MrsunAppCacheUtils.get(getActivity()).getAsString("schoolName");
        userInfo = BaseAppApplication.getInstance().getLoginUser();
        if (userInfo != null) {
            if (!TextUtils.isEmpty(userInfo.getSchoolName())) {
                if (userInfo.getSchoolName().length() > 7) {
                    tv_school_name.setText(userInfo.getSchoolName().substring(0, 6) + "...");
                } else {
                    tv_school_name.setText(userInfo.getSchoolName());

                }
            }
            notifyCar();
        }

    }

    public void notifyCar() {
        final CarShopInfo carShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
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
        }catch (Exception e){}

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
