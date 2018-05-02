package com.ffn.zerozeroseven.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.MainGoodsAdapter;
import com.ffn.zerozeroseven.adapter.RunListAdapter;
import com.ffn.zerozeroseven.adapter.UserLikeAdapter;
import com.ffn.zerozeroseven.adapter.WebBannerAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.base.BaseRecyclerTallAniAdapter;
import com.ffn.zerozeroseven.bean.AppVersionInfo;
import com.ffn.zerozeroseven.bean.BannerInfo;
import com.ffn.zerozeroseven.bean.FindSchoolInfo;
import com.ffn.zerozeroseven.bean.GoodsContentShowInfo;
import com.ffn.zerozeroseven.bean.GoodsDetilsInfo;
import com.ffn.zerozeroseven.bean.LunBoOkInfo;
import com.ffn.zerozeroseven.bean.QiangDanOkInfo;
import com.ffn.zerozeroseven.bean.RunListRquestInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.UserLikeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.AppUpdateInfo;
import com.ffn.zerozeroseven.bean.requsetbean.GoodsOftenInfo;
import com.ffn.zerozeroseven.bean.requsetbean.LunBoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.LunXunInfo;
import com.ffn.zerozeroseven.bean.requsetbean.OftenShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.PoppurlarListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.QiangRunRequsetInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RequeseGoods;
import com.ffn.zerozeroseven.bean.requsetbean.SearchSchoolInfo;
import com.ffn.zerozeroseven.ui.HelpmeRunActivity;
import com.ffn.zerozeroseven.ui.HomeActivity;
import com.ffn.zerozeroseven.ui.LoginActivity;
import com.ffn.zerozeroseven.ui.LookMoreRunActivity;
import com.ffn.zerozeroseven.ui.MessAgeActivity;
import com.ffn.zerozeroseven.ui.MineRunActivity;
import com.ffn.zerozeroseven.ui.MineWantGoQiangActivity;
import com.ffn.zerozeroseven.ui.MrsunWebActivity;
import com.ffn.zerozeroseven.ui.RunDetilsActivity;
import com.ffn.zerozeroseven.ui.SchoolnewCardActivity;
import com.ffn.zerozeroseven.ui.SearchSchoolActivity;
import com.ffn.zerozeroseven.ui.ShopDetilsActivity;
import com.ffn.zerozeroseven.ui.SinggerSchoolTalkActivity;
import com.ffn.zerozeroseven.ui.ToBeAGoodPeople;
import com.ffn.zerozeroseven.utlis.DownLoadManager;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.AutoVerticalScrollTextView;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.ffn.zerozeroseven.view.ScroolRecyleView;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.ZQImageViewRoundOval;
import com.ffn.zerozeroseven.view.mainscroll.CustomTwoLevelHeader;
import com.ffn.zerozeroseven.view.mainscroll.TwoLevelRefreshingListenerAdapter;
import com.ffn.zerozeroseven.view.mainscroll.TwoLevelSmoothRefreshLayout;
import com.yanzhenjie.permission.AndPermission;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.net.wifi.WifiConfiguration.Status.strings;

/**
 * Created by GT on 2017/11/15.
 */

public class MainFragment extends BaseFragment implements View.OnClickListener, OnGetPoiSearchResultListener {

    //    private RelativeLayout et_select;
    private UserLikeAdapter userLikeAdapter;
    private ImageView iv_centertext;
    private MainGoodsAdapter mainGoodsAdapter;
    private MainGoodsAdapter bothGoodsAdapter;
    private MainGoodsAdapter hotGoodsAdapter;
    private RecyclerView rc_often;
    private RecyclerView rc_all;
    private RecyclerView rc_hot;
    private LinearLayout ll_both;
    private LinearLayout ll_often;
    private LinearLayout ll_hot;
    private OftenShowInfo showHotInfo;
    private OftenShowInfo showBothInfo;
    private OftenShowInfo showOftenInfo;
    private ScroolRecyleView recyclerView;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private double latitude;
    private double longitude;
    private PoiSearch mPoiSearch;
    private boolean loginStaus;
    public static MainFragment mInstance;
    private TextView tv_time;
    @Bind(R.id.ll_helpme)
    LinearLayout ll_helpme;
    @Bind(R.id.rl_runlist)
    LinearLayout rl_runlist;
    @Bind(R.id.rl_minerun)
    RelativeLayout rl_minerun;
    @Bind(R.id.rc_runlist)
    RecyclerView rc_runlist;
    @Bind(R.id.rl_lookmore)
    RelativeLayout rl_lookmore;
    @Bind(R.id.scrollTextView)
    AutoVerticalScrollTextView scrollTextView;
    @Bind(R.id.refreshlayout)
    public TwoLevelSmoothRefreshLayout mRefreshLayout;
    private RunListAdapter runListAdapter;
    public RunListRquestInfo runListRquestInfo;
    private UserLikeInfo userLikeInfo;
    private String[] stringss;
    private int number = 0;
    private Thread thread;
    private ProgressDialog pd;
    @Bind(R.id.banner)
    MZBannerView banner;
    @Bind(R.id.rl_location)
    RelativeLayout rl_location;
    private BannerInfo bannerInfo;
    private WebBannerAdapter bannerAdapter;

    public RunListRquestInfo.DataBean.ListBean getRunlist(int poition) {
        return runListRquestInfo.getData().getList().get(poition);
    }


    private void initHeadView() {
        LunXunInfo lunXunInfo = new LunXunInfo();
        lunXunInfo.setFunctionName("ListSchoolFreeOrder");
        LunXunInfo.ParametersBean parametersBean = new LunXunInfo.ParametersBean();
        parametersBean.setSchoolId(schoolIId);
        lunXunInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(lunXunInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final LunBoOkInfo lunBoOkInfo = JSON.parseObject(response, LunBoOkInfo.class);
                if (lunBoOkInfo.getCode() == 0) {
                    stringss = null;
                    stringss = new String[lunBoOkInfo.getData().getList().size()];
                    for (int i = 0; i < lunBoOkInfo.getData().getList().size(); i++) {
                        stringss[i] = "恭喜" + ZeroZeroSevenUtils.phoneClose(lunBoOkInfo.getData().getList().get(i).getPhone()) + "用户获得了一次免单机会";
                    }
                    if (stringss.length > 0) {
                        scrollTextView.setText(stringss[stringss.length - 1]);
                    }
                }

            }
        });


//
//
    }

    @Bind(R.id.rl_top_bg)
    RelativeLayout rl_top_bg;

    private Drawable loadImageFromNetwork(String imageUrl) {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
        }
        return drawable;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);


        mInstance = this;
        mLocationClient = new LocationClient(BaseAppApplication.context);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedLocationPoiList(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        mLocationClient.setLocOption(option);
        if (SharePrefUtils.getInt(bfCxt, "isLocation", 0) != 1) {
            mLocationClient.start();
        }
        banner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("url", bannerInfo.getData().getList().get(position).getLink());
                bundle.putString("title", bannerInfo.getData().getList().get(position).getTitle());
                if (!TextUtils.isEmpty(bannerInfo.getData().getList().get(position).getLink())) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MrsunWebActivity.class, bundle);
                }
            }
        });
        banner.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Glide.with(bfCxt).load(bannerInfo.getData().getList().get(position).getPicUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable drawable = new BitmapDrawable(getResources(),resource);
                        rl_top_bg.setBackground(drawable);
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        iv_centertext = view.findViewById(R.id.iv_centertext);
        view.findViewById(R.id.rl_become).setOnClickListener(this);
        view.findViewById(R.id.bt_release).setOnClickListener(this);
        view.findViewById(R.id.rl_minerun).setOnClickListener(this);
        view.findViewById(R.id.tv_more).setOnClickListener(this);
        view.findViewById(R.id.iv_up).setOnClickListener(this);
        tv_time = view.findViewById(R.id.tv_time);
        ll_both = view.findViewById(R.id.ll_both);
        ll_often = view.findViewById(R.id.ll_often);
        ll_hot = view.findViewById(R.id.ll_hot);
        recyclerView = view.findViewById(R.id.rc_activityview);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(bfCxt));
        userLikeAdapter = new UserLikeAdapter(bfCxt);
        userLikeAdapter.setOnItemClickListener(new BaseRecyclerTallAniAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                //修改跳转 单个帖子界面
                Bundle bundle = new Bundle();
                bundle.putInt("id", userLikeAdapter.getItem(position).getId());
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, SinggerSchoolTalkActivity.class, bundle);
            }
        });
        recyclerView.setAdapter(userLikeAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    pageNo += 1;
                    LogUtils.D("MainFragment", "我是rc的监听事件" + pageNo);
                    requestpopularList();
                }
            }
        });


        rc_often = view.findViewById(R.id.rc_often);
        rc_often.setLayoutManager(new GridLayoutManager(bfCxt, 3));
        rc_often.addItemDecoration(new GridSpacingItemDecoration(3, 15, false));
        mainGoodsAdapter = new MainGoodsAdapter(bfCxt);
        rc_often.setAdapter(mainGoodsAdapter);
        mainGoodsAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                goToDetils(position, mainGoodsAdapter);
            }
        });


        rc_all = view.findViewById(R.id.rc_all);
        rc_all.setLayoutManager(new GridLayoutManager(bfCxt, 3));
        rc_all.addItemDecoration(new GridSpacingItemDecoration(3, 15, false));
        bothGoodsAdapter = new MainGoodsAdapter(bfCxt);
        rc_all.setAdapter(bothGoodsAdapter);
        bothGoodsAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                goToDetils(position, bothGoodsAdapter);
            }
        });


        rc_hot = view.findViewById(R.id.rc_hot);
        rc_hot.setLayoutManager(new GridLayoutManager(bfCxt, 3));
        rc_hot.addItemDecoration(new GridSpacingItemDecoration(3, 15, false));
        hotGoodsAdapter = new MainGoodsAdapter(bfCxt);
        rc_hot.setAdapter(hotGoodsAdapter);
        hotGoodsAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                goToDetils(position, hotGoodsAdapter);
            }
        });

        rc_runlist.setLayoutManager(new LinearLayoutManager(bfCxt));
        rc_runlist.addItemDecoration(new SpaceItemDecoration(10));
        runListAdapter = new RunListAdapter(bfCxt);
        rc_runlist.setAdapter(runListAdapter);
        runListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("type", "main");
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, RunDetilsActivity.class, bundle);
            }
        });
        runListAdapter.setOnItemImgViewClick(new RunListAdapter.OnItemImgClick() {
            @Override
            public void onClick(View view, int position) {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showLoadProgress();
                    }
                });
                QiangRunRequsetInfo qiangRunRequsetInfo = new QiangRunRequsetInfo();
                qiangRunRequsetInfo.setFunctionName("GrabErrandOrder");
                QiangRunRequsetInfo.ParametersBean parametersBean = new QiangRunRequsetInfo.ParametersBean();
                parametersBean.setId(String.valueOf(runListAdapter.getItem(position).getId()));
                qiangRunRequsetInfo.setParameters(parametersBean);
                httpPostJSON(qiangRunRequsetInfo, true);
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
                        final QiangDanOkInfo qiangDanOkInfo = JSON.parseObject(response.body().string(), QiangDanOkInfo.class);
                        BaseAppApplication.mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                disLoadProgress();
                                if (qiangDanOkInfo.getCode() == 0) {
                                    ZeroZeroSevenUtils.showCustonPop(HomeActivity.mInstance, "抢单成功", recyclerView);
                                } else {
                                    ZeroZeroSevenUtils.showCustonPop(HomeActivity.mInstance, qiangDanOkInfo.getMessage(), recyclerView);
                                }
                            }
                        });

                    }
                });
            }
        });
        mRefreshLayout.setHeaderView(new CustomTwoLevelHeader(bfCxt));
        mRefreshLayout.setEnableKeepRefreshView(true);
        //设置保持头部的Offset（占头部的高度比）
        mRefreshLayout.setRatioToKeepHeader(.12f);
        //设置触发刷新的头部高度比
        mRefreshLayout.setRatioOfHeaderToRefresh(.12f);
        //设置滚动到保持二级刷新的头部位置的时长
        mRefreshLayout.setDurationOfBackToKeepTwoLevel(1000);
        //设置关闭二级刷新头部回滚到起始位置的时长
        mRefreshLayout.setDurationToCloseTwoLevel(1000);
        //设置刷新时保持头部的Offset(占头部的高度比)
        mRefreshLayout.setRatioToKeepTwoLevelHeader(1f);
        //设置触发提示二级刷新的头部高度比
        mRefreshLayout.setRatioOfHeaderToHintTwoLevel(.45f);
        //设置触发二级刷新的头部高度比
        mRefreshLayout.setRatioOfHeaderToTwoLevel(0.5f);

        //TwoLevelRefreshingListenerAdapter
        mRefreshLayout.setOnRefreshListener(new TwoLevelRefreshingListenerAdapter() {
            @Override
            public void onTwoLevelRefreshBegin() {
                mRefreshLayout.setEnableInterceptEventWhileLoading(true);
            }

            @Override
            public void onRefreshBegin(boolean isRefresh) {
                reQuest();
            }

            @Override
            public void onRefreshComplete(boolean isSuccessful) {
                mRefreshLayout.setEnableInterceptEventWhileLoading(false);
            }
        });
        checkVersion();
    }

    private void checkVersion() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                pd = new ProgressDialog(bfCxt);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setTitle("正在更新");
                pd.setCancelable(false);
                pd.setCanceledOnTouchOutside(false);
            }
        });
        AppUpdateInfo updateInfo = new AppUpdateInfo();
        updateInfo.setFunctionName("QueryLatestAppVersion");
        httpPostJSON(updateInfo, true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.E("updateApp", "失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final AppVersionInfo appVersionInfo = JSON.parseObject(response.body().string(), AppVersionInfo.class);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (appVersionInfo.getCode() == 0) {
                            LogUtils.E("apk", JSON.toJSONString(appVersionInfo));
                            if (!ZeroZeroSevenUtils.getAppVersionName(bfCxt).equals(appVersionInfo.getData().getLatestVersion())) {
                                final ConfirmDialog dialog = new ConfirmDialog(bfCxt);
                                dialog.setTitles("软件升级");
                                dialog.setMessages("发现新版本,建议立即更新使用");
                                dialog.setConfirmButtonText("" +
                                        "");
                                dialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
                                    @Override
                                    public void doConfirm() {
                                        dialog.dismiss();
                                        requestSomePermission();
                                        downLoadApk(appVersionInfo.getData().getDownloadUrl());
                                    }

                                    @Override
                                    public void doCancel() {
                                        dialog.dismiss();
                                    }
                                });

                            }
                        }
                    }
                });
            }
        });
    }

    public void goYing() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=com.ffn.zerozeroseven");
        intent.setData(content_url);
        startActivity(intent);
    }

    private void requestSomePermission() {
        //
        // 先判断是否有权限。
        if (!AndPermission.hasPermission(bfCxt, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // 申请权限。
            AndPermission.with(getActivity())
                    .requestCode(100)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .send();
        }
    }

    private void downLoadApk(final String apkUrl) {
        new Thread() {
            @Override
            public void run() {
                try {
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            pd.show();
                        }
                    });
                    File file = DownLoadManager.getFileFromServer(apkUrl, pd, bfCxt);
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                        }
                    });
                    installApk(file);
                } catch (Exception e) {
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showShort("下载新版本失败,将为您跳转应用宝下载");
                        }
                    });
                    goYing();
                }
            }
        }.start();

    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Bind(R.id.rl_bri)
    RelativeLayout rl_bri;
    public MediaPlayer mediaPlayer;

    private void initMedie() {
        try {
            if (!TextUtils.isEmpty(userInfo.getBirthday())) {
                if (ZeroZeroSevenUtils.getCurTime("MM.dd").replaceAll("0", "").equals(userInfo.getBirthday().substring(5, userInfo.getBirthday().length()).replaceAll("0", ""))) {
                    if (SharePrefUtils.getBoolean(bfCxt, "bri", true)) {
                        openMusic();
                    }
                }
            }
            if (!TextUtils.isEmpty(userInfo.getBirthday())) {
                if (ZeroZeroSevenUtils.getCurTime("MM-dd").replaceAll("0", "").equals(userInfo.getBirthday().substring(5, userInfo.getBirthday().length()).replaceAll("0", ""))) {
                    if (SharePrefUtils.getBoolean(bfCxt, "bri", true)) {
                        openMusic();
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    @Bind(R.id.tv_briname)
    TextView tv_briname;

    private void openMusic() {
        SharePrefUtils.setBoolean(bfCxt, "bri", false);
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                rl_bri.setVisibility(View.VISIBLE);
                tv_briname.setText("亲爱的用户,今天是您的生日，零零7为您送来祝福");
            }
        });
        mediaPlayer = MediaPlayer.create(bfCxt, R.raw.happy);
        mediaPlayer.start();
    }

    public void requestTime() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                //    05:00 - 09:00  09:00 - 11:30 11:30 - 14:00 14:00-17:00 17:00-19:00 19:00-24:00 24:00-05:00
                if (ZeroZeroSevenUtils.DateTodate(5, 9)) {
                    tv_time.setText("05:00 - 09:00");
                } else if (ZeroZeroSevenUtils.DateTodate(9, 11)) {
                    tv_time.setText("09:00 - 11:30");
                } else if (ZeroZeroSevenUtils.DateTodate(11, 14)) {
                    tv_time.setText("11:30 - 14:00");
                } else if (ZeroZeroSevenUtils.DateTodate(14, 17)) {
                    tv_time.setText("14:00-17:00");
                } else if (ZeroZeroSevenUtils.DateTodate(17, 19)) {
                    tv_time.setText("17:00-19:00");
                } else if (ZeroZeroSevenUtils.DateTodate(19, 24)) {
                    tv_time.setText("19:00-24:00");
                } else {
                    tv_time.setText("24:00-04:00");
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.pause();//暂停轮播
        if (userLikeInfo != null && userLikeInfo.getData().getPosts().size() > 3) {
            recyclerView.stop();
        }
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        } catch (Exception e) {
        }


    }

    public void goToDetils(final int position, final MainGoodsAdapter adapter) {
        RequeseGoods requeseGoods = new RequeseGoods();
        requeseGoods.setFunctionName("QueryGoods");
        RequeseGoods.ParametersBean parametersBean = new RequeseGoods.ParametersBean();
        parametersBean.setGoodsId(adapter.getItem(position).getId());
        requeseGoods.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(getActivity());
        okGoUtils.httpPostJSON(requeseGoods, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final GoodsDetilsInfo goodsDetilsInfo = JSON.parseObject(response, GoodsDetilsInfo.class);
                if (goodsDetilsInfo.getCode() == 0) {
                    GoodsContentShowInfo.DataBean.ProductsBean goodsInfo = new GoodsContentShowInfo.DataBean.ProductsBean();
                    goodsInfo.setId(adapter.getItem(position).getId());
                    goodsInfo.setGoodsName(adapter.getItem(position).getGoodsName());
                    goodsInfo.setThumbnail(adapter.getItem(position).getThumbnail());
                    goodsInfo.setStockNum(goodsDetilsInfo.getData().getStockNum());
                    goodsInfo.setPrice(goodsDetilsInfo.getData().getPrice());
//                          goodsInfo.setPromotionPrice(goodsDetilsInfo.getData().getPromotionPrice());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("shopInfo", goodsInfo);
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, ShopDetilsActivity.class, bundle);
                } else {
                    ToastUtils.showShort(goodsDetilsInfo.getMessage());
                }
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onGetPoiResult(final PoiResult poiResult) {
        if (poiResult != null) {
            if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
                String poi = poiResult.getAllPoi().get(0).name;
                if (poi.indexOf("学") != -1) {
                    poi.substring(0, poi.lastIndexOf("学"));
                    FindSchoolId(poi.substring(0, poi.indexOf("学") + 1));
                } else {
                    BaseAppApplication.mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            disLoadProgress();
                            tv_school.setText("去选择学校");
                        }
                    });
                }

            } else {

            }
        }
    }

    private void FindSchoolId(final String name) {
        SearchSchoolInfo schoolInfo = new SearchSchoolInfo();
        schoolInfo.setFunctionName("QuerySchoolByName");
        SearchSchoolInfo.ParametersBean parametersBean = new SearchSchoolInfo.ParametersBean();
        parametersBean.setSchoolName(name);
        schoolInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(getActivity());
        okGoUtils.httpPostJSON(schoolInfo, true, true);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final FindSchoolInfo findSchoolInfo = JSON.parseObject(response, FindSchoolInfo.class);
                if (findSchoolInfo.getCode() == 0) {
                    if (findSchoolInfo.getData() != null) {
                        if (TextUtils.isEmpty(findSchoolInfo.getData().getName())) {
                            tv_school.setText("去选择学校");
                            ZeroZeroSevenUtils.showCustonPop(HomeActivity.mInstance, "请手动定位", tv_school);
                        } else {
                            tv_school.setText(findSchoolInfo.getData().getName());
                            BaseAppApplication.userInfo.setSchoolName(name);
                            BaseAppApplication.userInfo.setSchoolId(findSchoolInfo.getData().getId() + "");
                            SharePrefUtils.saveObject(bfCxt, "userInfo", BaseAppApplication.getInstance().getLoginUser());
                            SharePrefUtils.setInt(bfCxt, "isLocation", 1);
                        }

                        reQuest();
                    } else {
                        ZeroZeroSevenUtils.showCustonPop(HomeActivity.mInstance, "请手动定位", tv_school);
                        tv_school.setText("去选择学校");
                        reQuest();
                    }
                } else {
                    reQuest();
                }
            }
        });

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

//    @Override
//    public void onRefresh(RefreshLayout refreshlayout) {
//        reQuest();
//    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取周边POI信息相关的结果
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            searchNeayBy(latitude, longitude);
        }
    }

    private void searchNeayBy(double latitude1, double longitude1) {
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption();
        nearbySearchOption.location(new LatLng(latitude1, longitude1));
        nearbySearchOption.keyword("大学");
        nearbySearchOption.radius(3000);
        nearbySearchOption.sortType(PoiSortType.distance_from_near_to_far);
        mPoiSearch.searchNearby(nearbySearchOption);
    }

    @Override
    public void initDate() {
        reQuest();
    }

    @Override
    public void doOther() {
        initMedie();
    }

    @Bind(R.id.tv_hot)
    TextView tv_hot;
    @Bind(R.id.tv_both)
    TextView tv_both;
    @Bind(R.id.tv_often)
    TextView tv_often;

    public void reQuest() {
        if (userInfo != null) {
            schoolIId = BaseAppApplication.getInstance().getLoginUser().getSchoolId();
        }
        if (userInfo != null) {
            requestpopularList();
        }
//        openZhiLingService();
        initHeadView();
        requestBaner();
        //    05:00 - 09:00  09:00 - 11:30 11:30 - 14:00 14:00-17:00 17:00-19:00 19:00-24:00 24:00-05:00
        if (ZeroZeroSevenUtils.DateTodate(5, 9)) {
            requestHotBuyList("05:00", "09:00");
        } else if (ZeroZeroSevenUtils.DateTodate(9, 11)) {
            requestHotBuyList("09:00", "11:30");
        } else if (ZeroZeroSevenUtils.DateTodate(11, 14)) {
            requestHotBuyList("11:30", "14:00");
        } else if (ZeroZeroSevenUtils.DateTodate(14, 17)) {
            requestHotBuyList("14:00", "17:00");
        } else if (ZeroZeroSevenUtils.DateTodate(17, 19)) {
            requestHotBuyList("17:00", "19:00");
        } else if (ZeroZeroSevenUtils.DateTodate(19, 24)) {
            requestHotBuyList("19:00", "24:00");
        } else {
            requestHotBuyList("17:00", "19:00");
        }
        requestOftenBuyList();
        requestBothBuyList();
//        requestRunList();
    }

    List<String> images;

    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);
            mImageView =  view.findViewById(R.id.image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            Glide.with(context).load(data).into(mImageView);
        }
    }

    private void requestBaner() {
        LunBoInfo lunBoInfo = new LunBoInfo();
        lunBoInfo.setFunctionName("ListAd");
        LunBoInfo.ParametersBean parametersBean = new LunBoInfo.ParametersBean();
        parametersBean.setStatus("1");
        lunBoInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(lunBoInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                bannerInfo = JSON.parseObject(response, BannerInfo.class);
                if (bannerInfo.getCode() == 0) {
                    mRefreshLayout.refreshComplete();
                    if (bannerInfo.getData().getList().size() > 0) {
                        images = new ArrayList<>();
                        for (int i = 0; i < bannerInfo.getData().getList().size(); i++) {
                            images.add(bannerInfo.getData().getList().get(i).getPicUrl());
                        }
                        Glide.with(bfCxt).load(bannerInfo.getData().getList().get(0).getPicUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                Drawable drawable = new BitmapDrawable(getResources(),resource);
                                rl_top_bg.setBackground(drawable);
                            }
                        });
                        banner.setPages(images, new MZHolderCreator() {
                            @Override
                            public BannerViewHolder createViewHolder() {
                                return new BannerViewHolder();
                            }
                        });
                        banner.start();
                    }
                }
            }
        });

    }


//    private void requestRunList() {
//        RunListInfo runListInfo = new RunListInfo();
//        runListInfo.setFunctionName("ListSchoolErrandOrder");
//        RunListInfo.ParametersBean parametersBean = new RunListInfo.ParametersBean();
//        parametersBean.setPageIndex(0);
//        parametersBean.setPageSize(3);
//        if (!TextUtils.isEmpty(MrsunAppCacheUtils.get(bfCxt).getAsString("schoolId"))) {
//            parametersBean.setSchoolId(Integer.parseInt(MrsunAppCacheUtils.get(bfCxt).getAsString("schoolId")));
//        }
//        runListInfo.setParameters(parametersBean);
//        httpPostJSON(runListInfo, true);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                BaseAppApplication.mainHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        rc_runlist.setVisibility(View.GONE);
//                        rl_minerun.setVisibility(View.VISIBLE);
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                runListRquestInfo = JSON.parseObject(response.body().string(), RunListRquestInfo.class);
//                BaseAppApplication.mainHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (runListRquestInfo.getCode() == 0 && runListRquestInfo.getData().getList().size() > 0) {
//                            rc_runlist.setVisibility(View.VISIBLE);
//                            rl_minerun.setVisibility(View.GONE);
//                            runListAdapter.addAll(runListRquestInfo.getData().getList());
//                        } else {
//                            rc_runlist.setVisibility(View.GONE);
//                            rl_minerun.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
//            }
//        });
//    }

    //    05:00 - 09:00  09:00 - 11:30 11:30 - 14:00 14:00-17:00 17:00-19:00 19:00-24:00 24:00-05:00
    private void requestHotBuyList(String beforeTime, String afterTime) {
//        if (!ZeroZeroSevenUtils.Date2date()) {
//            BaseAppApplication.mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    ll_hot.setVisibility(View.VISIBLE);
//                    tv_hot.setText("商铺已经打烊");
//                    rc_hot.setVisibility(View.GONE);
//                }
//            });
//        } else {
        GoodsOftenInfo oftenInfo = new GoodsOftenInfo();
        oftenInfo.setFunctionName("ListGoodsHotSales");
        GoodsOftenInfo.ParametersBean parametersBean = new GoodsOftenInfo.ParametersBean();
        parametersBean.setPageIndex(0);
        parametersBean.setPageSize(6);
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        parametersBean.setHotStartTime(beforeTime);
        parametersBean.setHotEndTime(afterTime);
        oftenInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(getActivity());
        okGoUtils.httpPostJSON(oftenInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                showHotInfo = JSON.parseObject(response, OftenShowInfo.class);
                if (showHotInfo.getCode() == 0) {
                    if (showHotInfo.getData().getGoods().size() > 0) {
                        hotGoodsAdapter.cleanDates();
                        hotGoodsAdapter.addAll(showHotInfo.getData().getGoods());
                        ll_hot.setVisibility(View.GONE);
                        rc_hot.setVisibility(View.VISIBLE);
                    } else {
                        hotGoodsAdapter.cleanDates();
                        ll_hot.setVisibility(View.VISIBLE);
                        rc_hot.setVisibility(View.GONE);
                    }

                } else {
                    hotGoodsAdapter.cleanDates();
                    ll_hot.setVisibility(View.VISIBLE);
                }
            }
        });

    }
//    }


    private void requestBothBuyList() {
//        if (!ZeroZeroSevenUtils.Date2date()) {
//            BaseAppApplication.mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    ll_both.setVisibility(View.VISIBLE);
//                    tv_both.setText("商铺已经打烊");
//                    rc_all.setVisibility(View.GONE);
//                }
//            });
//
//        } else {
        GoodsOftenInfo oftenInfo = new GoodsOftenInfo();
        oftenInfo.setFunctionName("ListGoodsHotSales");
        GoodsOftenInfo.ParametersBean parametersBean = new GoodsOftenInfo.ParametersBean();
        parametersBean.setPageIndex(0);
        parametersBean.setPageSize(3);
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        oftenInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(getActivity());
        okGoUtils.httpPostJSON(oftenInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                showBothInfo = JSON.parseObject(response, OftenShowInfo.class);
                if (showBothInfo.getCode() == 0) {
                    if (showBothInfo.getData().getGoods().size() > 0) {
                        bothGoodsAdapter.cleanDates();
                        bothGoodsAdapter.addAll(showBothInfo.getData().getGoods());
                        ll_both.setVisibility(View.GONE);
                        rc_all.setVisibility(View.VISIBLE);
                    } else {
                        bothGoodsAdapter.cleanDates();
                        ll_both.setVisibility(View.VISIBLE);
                        rc_all.setVisibility(View.GONE);
                    }

                } else {
                    bothGoodsAdapter.cleanDates();
                    ll_both.setVisibility(View.VISIBLE);
                }
            }
        });
    }
//    }

    private void requestOftenBuyList() {
//        if (!ZeroZeroSevenUtils.Date2date()) {
//            BaseAppApplication.mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    ll_often.setVisibility(View.VISIBLE);
//                    tv_often.setText("商铺已经打烊");
//                    rc_often.setVisibility(View.GONE);
//                }
//            });
//
//        } else {
        GoodsOftenInfo oftenInfo = new GoodsOftenInfo();
        oftenInfo.setFunctionName("ListUserRegularPurchase");
        GoodsOftenInfo.ParametersBean parametersBean = new GoodsOftenInfo.ParametersBean();
        parametersBean.setPageIndex(0);
        parametersBean.setPageSize(3);
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        oftenInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(getActivity());
        okGoUtils.httpPostJSON(oftenInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                showOftenInfo = JSON.parseObject(response, OftenShowInfo.class);
                if (showOftenInfo.getCode() == 0) {
                    if (showOftenInfo.getData().getGoods().size() > 0) {
                        mainGoodsAdapter.cleanDates();
                        mainGoodsAdapter.addAll(showOftenInfo.getData().getGoods());
                        ll_often.setVisibility(View.GONE);
                        rc_often.setVisibility(View.VISIBLE);
                    } else {
                        mainGoodsAdapter.cleanDates();
                        ll_often.setVisibility(View.VISIBLE);
                        rc_often.setVisibility(View.GONE);
                    }

                } else {
                    mainGoodsAdapter.cleanDates();
                    ll_often.setVisibility(View.VISIBLE);
                }
            }
        });
    }
//    }

    int pageNo = 0;
    int haveData = 0;

    private void requestpopularList() {
        PoppurlarListInfo poppurlarListInfo = new PoppurlarListInfo();
        poppurlarListInfo.setFunctionName("ListPopularPost");
        PoppurlarListInfo.ParametersBean parametersBean = new PoppurlarListInfo.ParametersBean();
        parametersBean.setPageSize(20);
        parametersBean.setPageIndex(pageNo);
        poppurlarListInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(getActivity());
        okGoUtils.httpPostJSON(poppurlarListInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                try {
                    userLikeInfo = JSON.parseObject(response, UserLikeInfo.class);
//                    refreshLayout.finishRefresh(1000);
                    if (userLikeInfo.getCode() == 0) {
                        if (userLikeInfo.getData().getPosts().size() > 0) {
                            haveData = 2;
                            userLikeAdapter.addAll(userLikeInfo.getData().getPosts());
                            recyclerView.setVisibility(View.VISIBLE);
                            iv_centertext.setVisibility(View.GONE);
                            if (userLikeInfo.getData().getPosts().size() > 3) {
                                recyclerView.start();
                            }
                        } else {
                            haveData = 1;
                            if (pageNo == 0) {
                                iv_centertext.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            } else {
                                pageNo = 0;
                                new Thread(new MyRunnable()).start();
                            }

                        }

                    } else {
                        haveData = 1;
                        iv_centertext.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        LogUtils.D("MainFragment", "nodate");
                    }

                } catch (Exception e) {
                    haveData = 1;
                }
            }
        });
//        okGoUtils.setOnLoadError(new OkGoUtils.OnLoadError() {
//            @Override
//            public void onErrorLoad() {
//                haveData = 1;
//                refreshLayout.finishRefresh(1000);
//                if (pageNo == 0) {
//                    iv_centertext.setVisibility(View.VISIBLE);
//                    recyclerView.setVisibility(View.GONE);
//                }
//            }
//        });

    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            requestpopularList();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_become:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, ToBeAGoodPeople.class);
                break;
            case R.id.bt_release:
                Bundle bundle = new Bundle();
                bundle.putString("type", "其他");
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, HelpmeRunActivity.class, bundle);
                break;
            case R.id.rl_minerun:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, MineRunActivity.class);
                break;
            case R.id.tv_more:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, SchoolnewCardActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
            case R.id.iv_up:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MineWantGoQiangActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
        }
    }

    @Bind(R.id.bt_helpme)
    Button helpme;
    @Bind(R.id.bt_helpother)
    Button helpother;
    @Bind(R.id.tv_school)
    TextView tv_school;

    @OnClick({R.id.rl_snack, R.id.rl_computer, R.id.rl_integer, R.id.rl_local, R.id.iv_guanggao, R.id.bt_helpother, R.id.bt_helpme, R.id.rl_kuaidi, R.id.rl_file, R.id.rl_other, R.id.rl_lookmore, R.id.rl_location, R.id.tv_school})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.rl_snack:
                HomeActivity.getmInstance().go2Fragment(1);
                break;
            case R.id.rl_computer:
                break;
            case R.id.rl_integer:
                break;
            case R.id.rl_local:
                break;
            case R.id.iv_guanggao:
                HomeActivity.getmInstance().go2Fragment(1);
                break;
            case R.id.tv_school:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, SearchSchoolActivity.class);
                break;
            case R.id.rl_location:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MessAgeActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.bt_helpother:
                helpother.setBackgroundResource(R.drawable.selected);
                helpother.setTextColor(bfCxt.getResources().getColor(R.color.white));
                helpme.setTextColor(bfCxt.getResources().getColor(R.color.tab_color));
                helpme.setBackgroundResource(R.drawable.unselected);
                if (runListRquestInfo.getData().getList().size() > 0) {
                    rl_runlist.setVisibility(View.VISIBLE);
                    rl_minerun.setVisibility(View.GONE);
                } else {
                    rl_runlist.setVisibility(View.GONE);
                    rl_minerun.setVisibility(View.VISIBLE);
                }
                ll_helpme.setVisibility(View.GONE);
                break;
            case R.id.bt_helpme:
                helpme.setTextColor(bfCxt.getResources().getColor(R.color.white));
                helpother.setTextColor(bfCxt.getResources().getColor(R.color.tab_color));
                helpme.setBackgroundResource(R.drawable.selected);
                helpother.setBackgroundResource(R.drawable.unselected);
                rl_runlist.setVisibility(View.GONE);
                rl_minerun.setVisibility(View.GONE);
                ll_helpme.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_kuaidi:
                Bundle bundle = new Bundle();
                bundle.putString("type", "快递");
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, HelpmeRunActivity.class, bundle);
                break;
            case R.id.rl_file:
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "文件");
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, HelpmeRunActivity.class, bundle1);
                break;
            case R.id.rl_other:
                Bundle bundle2 = new Bundle();
                bundle2.putString("type", "其他");
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, HelpmeRunActivity.class, bundle2);
                break;
            case R.id.rl_lookmore:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, LookMoreRunActivity.class);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.start();//开始轮播
//        if (ZeroZeroSevenUtils.Date2date()) {
//
//            if (hotGoodsAdapter.getCount() == 0) {
//                ll_hot.setVisibility(View.VISIBLE);
//                rc_hot.setVisibility(View.GONE);
//                tv_hot.setText("暂无数据");
//            } else {
//                ll_hot.setVisibility(View.GONE);
//                rc_hot.setVisibility(View.VISIBLE);
//            }
//            if (bothGoodsAdapter.getCount() == 0) {
//                ll_both.setVisibility(View.VISIBLE);
//                rc_all.setVisibility(View.GONE);
//                tv_both.setText("暂无数据");
//            } else {
//                ll_both.setVisibility(View.GONE);
//                rc_all.setVisibility(View.VISIBLE);
//            }
//            if (mainGoodsAdapter.getCount() == 0) {
//                ll_often.setVisibility(View.VISIBLE);
//                rc_often.setVisibility(View.GONE);
//                tv_often.setText("暂无数据");
//            } else {
//                ll_often.setVisibility(View.GONE);
//                rc_often.setVisibility(View.VISIBLE);
//            }
//
//        } else {
//            ll_hot.setVisibility(View.VISIBLE);
//            tv_hot.setText("商铺已经打烊");
//            rc_hot.setVisibility(View.GONE);
//            ll_both.setVisibility(View.VISIBLE);
//            tv_both.setText("商铺已经打烊");
//            rc_all.setVisibility(View.GONE);
//            ll_often.setVisibility(View.VISIBLE);
//            tv_often.setText("商铺已经打烊");
//            rc_often.setVisibility(View.GONE);
//        }
        if (userLikeInfo != null && userLikeInfo.getData().getPosts().size() > 3) {
            recyclerView.start();
        }
        if (haveData == 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    requestpopularList();
                }
            }).start();
        }
        requestTime();
        userInfo = BaseAppApplication.getInstance().getLoginUser();
        if (userInfo != null) {
            if (!TextUtils.isEmpty(userInfo.getSchoolName())) {
                tv_school.setText(userInfo.getSchoolName());
            } else {
                tv_school.setText("请选择学校");
            }
        } else {
            tv_school.setText("请选择学校");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
