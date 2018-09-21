package com.ffn.zerozeroseven.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.adapter.BestNewGoodsAdapter;
import com.ffn.zerozeroseven.adapter.HotTimeAdapter;
import com.ffn.zerozeroseven.adapter.UserLikeAdapter;
import com.ffn.zerozeroseven.adapter.WebBannerAdapter;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseFragment;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.AppVersionInfo;
import com.ffn.zerozeroseven.bean.BannerInfo;
import com.ffn.zerozeroseven.bean.GoodsContentShowInfo;
import com.ffn.zerozeroseven.bean.GoodsDetilsInfo;
import com.ffn.zerozeroseven.bean.HotInfo;
import com.ffn.zerozeroseven.bean.RunListRquestInfo;
import com.ffn.zerozeroseven.bean.ShangChangShowInfo;
import com.ffn.zerozeroseven.bean.TongzhiInfo;
import com.ffn.zerozeroseven.bean.UserLikeInfo;
import com.ffn.zerozeroseven.bean.requsetbean.AppUpdateInfo;
import com.ffn.zerozeroseven.bean.requsetbean.BestNewShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.GoodsOftenInfo;
import com.ffn.zerozeroseven.bean.requsetbean.LunBoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.OftenShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.PoppurlarListInfo;
import com.ffn.zerozeroseven.bean.requsetbean.RequeseGoods;
import com.ffn.zerozeroseven.bean.requsetbean.ShangchangInfo;
import com.ffn.zerozeroseven.bean.requsetbean.TongZhiShowInfo;
import com.ffn.zerozeroseven.ui.BitisDetils;
import com.ffn.zerozeroseven.ui.BitisNewActivity;
import com.ffn.zerozeroseven.ui.DrivingSchoolActivity;
import com.ffn.zerozeroseven.ui.ErrandHomeActivity;
import com.ffn.zerozeroseven.ui.HomeActivity;
import com.ffn.zerozeroseven.ui.IntegralDrawActivity;
import com.ffn.zerozeroseven.ui.JumpShopActivity;
import com.ffn.zerozeroseven.ui.LeaseActivity;
import com.ffn.zerozeroseven.ui.LoginActivity;
import com.ffn.zerozeroseven.ui.MessAgeActivity;
import com.ffn.zerozeroseven.ui.MrsunWebActivity;
import com.ffn.zerozeroseven.ui.MyBitisActivity;
import com.ffn.zerozeroseven.ui.NumberRicalActivity;
import com.ffn.zerozeroseven.ui.SearchSchoolActivity;
import com.ffn.zerozeroseven.ui.ShopDetilsActivity;
import com.ffn.zerozeroseven.ui.TakeAwayFoodActivity;
import com.ffn.zerozeroseven.ui.UserSelectSchoolListActivity;
import com.ffn.zerozeroseven.ui.WebViewActivity;
import com.ffn.zerozeroseven.utlis.DownLoadManager;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ScreenUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.ScroolRecyleView;
import com.ffn.zerozeroseven.view.SmartScrollView;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;
import com.ffn.zerozeroseven.view.mainscroll.CustomTwoLevelHeader;
import com.ffn.zerozeroseven.view.mainscroll.TwoLevelRefreshingListenerAdapter;
import com.ffn.zerozeroseven.view.mainscroll.TwoLevelSmoothRefreshLayout;
import com.ffn.zerozeroseven.view.pop.UpdatePopWindow;
import com.paradoxie.autoscrolltextview.VerticalTextview;
import com.yanzhenjie.permission.AndPermission;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by GT on 2017/11/15.
 */

public class MainFragment extends BaseFragment {

    //    private RelativeLayout et_select;
    private UserLikeAdapter userLikeAdapter;
    private BestNewGoodsAdapter bothGoodsAdapter;
    private HotTimeAdapter hotGoodsAdapter;
    private RecyclerView rc_all;
    private RecyclerView rc_hot;
    private LinearLayout ll_both;
    private LinearLayout ll_hot;
    private HotInfo showHotInfo;
    private BestNewShowInfo showBothInfo;
    private OftenShowInfo showOftenInfo;
    private ScroolRecyleView recyclerView;
    //    public LocationClient mLocationClient = null;
//    private MyLocationListener myListener = new MyLocationListener();
//    private double latitude;
//    private double longitude;
//    private PoiSearch mPoiSearch;
//    private boolean loginStaus;
    public static WeakReference<MainFragment> mInstance;
    private TextView tv_time;


    @Bind(R.id.scrollTextView)
    VerticalTextview scrollTextView;
    @Bind(R.id.refreshlayout)
    public TwoLevelSmoothRefreshLayout mRefreshLayout;
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
    @Bind(R.id.iv_bg)
    ImageView iv_bg;
    private BannerInfo bannerInfo;
    private WebBannerAdapter bannerAdapter;
    private CustomTwoLevelHeader header;
    private String projectUrl;
    private TongzhiInfo tongzhiInfo;
    private AppVersionInfo appVersionInfo;
    private OkGoUtils okGoUtilspop;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public RunListRquestInfo.DataBean.ListBean getRunlist(int poition) {
        return runListRquestInfo.getData().getList().get(poition);
    }

    ArrayList<String> titles;

    private void initHeadView() {
        TongZhiShowInfo lunXunInfo = new TongZhiShowInfo();
        lunXunInfo.setFunctionName("ListPushLetters");
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(lunXunInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                tongzhiInfo = JSON.parseObject(response, TongzhiInfo.class);

                if (tongzhiInfo.getCode() == 0) {
                    if (titles == null) {
                        titles = new ArrayList<>();
                    }
                    titles.clear();
                    if (tongzhiInfo.getData().getList().size() > 0) {
                        for (int i = 0; i < tongzhiInfo.getData().getList().size(); i++) {
                            if (tongzhiInfo.getData().getList().get(i).getTitle().length() > 30) {
                                titles.add(tongzhiInfo.getData().getList().get(i).getTitle().substring(0, 29) + "...");
                            } else {
                                titles.add(tongzhiInfo.getData().getList().get(i).getTitle());
                            }
                        }
                        scrollTextView.setTextList(titles);
                        scrollTextView.setText(11, 5, Color.BLACK);//设置属性,具体跟踪源码
                        scrollTextView.setTextStillTime(3000);//设置停留时长间隔
                        scrollTextView.setAnimTime(300);
                        scrollTextView.startAutoScroll();
                    }

                }
            }
        });


//
//
    }

    @Bind(R.id.iv_show)
    ImageView iv_show;
    @Bind(R.id.scrollview)
    SmartScrollView scrollview;
    @Bind(R.id.rl_top)
    RelativeLayout rl_top;
    @Bind(R.id.iv_location)
    ImageView iv_location;
    @Bind(R.id.iv_sjx)
    ImageView iv_sjx;
    @Bind(R.id.iv_one)
    ImageView iv_one;
    @Bind(R.id.iv_two)
    ImageView iv_two;
    @Bind(R.id.iv_four)
    ImageView iv_four;
    @Bind(R.id.iv_five)
    ImageView iv_five;
    @Bind(R.id.iv_six)
    ImageView iv_six;
    @Bind(R.id.iv_seven)
    ImageView iv_seven;
    @Bind(R.id.iv_eight)
    ImageView iv_eight;
    @Bind(R.id.iv_nine)
    ImageView iv_nine;
    @Bind(R.id.iv_shi)
    ImageView iv_shi;
    @Bind(R.id.iv_in)
    ImageView iv_in;
    @Bind(R.id.iv_left)
    ImageView iv_left;
    @Bind(R.id.iv_time_left)
    ImageView iv_time_left;
    @Bind(R.id.iv_time_right)
    ImageView iv_time_right;
    @Bind(R.id.iv_xinpin)
    ImageView iv_xinpin;

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        tv_time = view.findViewById(R.id.tv_time);

        Glide.with(this).load(R.drawable.sjx).into(iv_sjx);
        Glide.with(this).load(R.drawable.main_one).into(iv_one);
        Glide.with(this).load(R.drawable.main_two).into(iv_two);
        Glide.with(this).load(R.drawable.main_three).into(iv_four);
        Glide.with(this).load(R.drawable.main_four).into(iv_five);
        Glide.with(this).load(R.drawable.main_number).into(iv_six);
        Glide.with(this).load(R.drawable.main_waimai).into(iv_seven);
        Glide.with(this).load(R.drawable.main_runner).into(iv_eight);
        Glide.with(this).load(R.drawable.main_lease).into(iv_nine);
        Glide.with(this).load(R.drawable.main_lease).into(iv_shi);
        Glide.with(this).load(R.drawable.main_notify).into(iv_in);
        Glide.with(this).load(R.drawable.main_laba).into(iv_left);
        Glide.with(this).load(R.drawable.xinpin).into(iv_xinpin);
        mInstance = new WeakReference<>(this);
        scrollview.setScrollViewListener(new SmartScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(SmartScrollView scrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float offsetY = getResources().getDimension(R.dimen.main_top) - getResources().getDimension(R.dimen.img_top);
                //计算滑动距离的偏移量
                float offset = 1 - Math.max((offsetY - scrollY) / offsetY, 0f);
                float absOffset = Math.abs(offset);
                //如果滑动距离大于1就设置完全不透明
                if (absOffset >= 1) {
                    absOffset = 1;
                    rl_top.setBackgroundColor(getResources().getColor(R.color.white));
                    tv_school.setTextColor(getResources().getColor(R.color.black));
                    Glide.with(bfCxt).load(R.mipmap.shoplocation).into(iv_location);
                } else {
                    rl_top.setBackgroundColor(Color.argb((int) (absOffset * 255), 255, 255, 255));
                    tv_school.setTextColor(getResources().getColor(R.color.white));
                    Glide.with(bfCxt).load(R.drawable.main_location).into(iv_location);
                }
            }
        });


        scrollview.setScanScrollChangedListener(new SmartScrollView.ISmartScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {

            }

            @Override
            public void onScrolledToTop() {
                scrollview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        ToastUtils.showShort("我滑动到底部了");
                        scrollview.scrollTo(0, 1);
                    }
                }, 1000);
            }
        });
        scrollview.postDelayed(new Runnable() {
            @Override
            public void run() {
//                        ToastUtils.showShort("我滑动到底部了");
                scrollview.scrollTo(0, 1);
            }
        }, 500);
        banner.setDelayedTime(3500);
        banner.setDuration(2000);
        banner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("url", urlList.get(position));
                bundle.putString("title", titleList.get(position));
                if (!TextUtils.isEmpty(urlList.get(position))) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MrsunWebActivity.class, bundle);
                }
            }
        });
        scrollTextView.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                if (tongzhiInfo.getData().getList().size() > 0) {
                    if (!TextUtils.isEmpty(tongzhiInfo.getData().getList().get(i).getLink())) {
                        Bundle bundle = new Bundle();
                        bundle.putString("title", tongzhiInfo.getData().getList().get(i).getTitle());
                        bundle.putString("url", tongzhiInfo.getData().getList().get(i).getLink() + "?id=" + tongzhiInfo.getData().getList().get(i).getId());
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, MrsunWebActivity.class, bundle);
                    }
                }
            }
        });
        banner.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                try {
                    Glide.with(bfCxt)
                            .load(images.get(position))
                            .override(10, 10)
                            .into(iv_bg);
                } catch (Exception e) {
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ll_both = view.findViewById(R.id.ll_both);
        ll_hot = view.findViewById(R.id.ll_hot);
        recyclerView = view.findViewById(R.id.rc_activityview);
        recyclerView.setOnTouchListener(rcViewOnTouch);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
        staggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        userLikeAdapter = new UserLikeAdapter(bfCxt);
        userLikeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                //修改跳转 单个帖子界面
                if (Math.abs(yMove
                ) < 1) {
                    Bundle bundle = new Bundle();
                    bundle.putString("clickType", "singer");
                    bundle.putInt("id", userLikeAdapter.getItem(position).getId());
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, BitisDetils.class, bundle);

                }

            }
        });
        iv_bg.setScaleX(1.5f);
        iv_bg.setScaleY(1.5f);
        recyclerView.setAdapter(userLikeAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollHorizontally(1)) {
                    pageNo += 1;
                    LogUtils.D("MainFragment", "我是rc的监听事件" + pageNo);
                    requestpopularList();
                }
            }
        });


        rc_all = view.findViewById(R.id.rc_all);
        rc_all.setLayoutManager(new LinearLayoutManager(bfCxt));
        rc_all.addItemDecoration(new SpaceItemDecoration(15));
        bothGoodsAdapter = new BestNewGoodsAdapter(bfCxt);
        rc_all.setAdapter(bothGoodsAdapter);
        bothGoodsAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                goToDetils(position, bothGoodsAdapter);
            }
        });


        rc_hot = view.findViewById(R.id.rc_hot);
        rc_hot.setLayoutManager(new LinearLayoutManager(bfCxt));
        rc_hot.addItemDecoration(new SpaceItemDecoration(15));
        hotGoodsAdapter = new HotTimeAdapter(bfCxt);
        rc_hot.setAdapter(hotGoodsAdapter);
        hotGoodsAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                goToDetils(position, hotGoodsAdapter);
            }
        });
        header = new CustomTwoLevelHeader(bfCxt);
        mRefreshLayout.setHeaderView(header);
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
        mRefreshLayout.setRatioOfHeaderToTwoLevel(0.4f);

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
        checkUserData();
    }

    private void checkUserData() {
        if (BaseAppApplication.getInstance().getLoginUser() != null) {
            if (!TextUtils.isEmpty(BaseAppApplication.getInstance().getLoginUser().getSchoolId())) {
                if ("0".equals(BaseAppApplication.getInstance().getLoginUser().getSchoolId())) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, UserSelectSchoolListActivity.class);
                    BaseAppApplication.getInstance().finishActivity(getActivity());
                }
            } else {
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, UserSelectSchoolListActivity.class);
                BaseAppApplication.getInstance().finishActivity(getActivity());
            }
        } else {
            BaseAppApplication.getInstance().setLoginUser(null);
            SharePrefUtils.saveObject(bfCxt, "userInfo", BaseAppApplication.getInstance().getLoginUser());
            ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
            BaseAppApplication.getInstance().clearActivityList();
        }


    }


    int yMove = 0;
    int yDowm = 0;
    private View.OnTouchListener rcViewOnTouch = new View.OnTouchListener() {
        int lastX, lastY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int ea = event.getAction();
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int screenWidth = dm.widthPixels;
            int screenHeight = dm.heightPixels - 177;//需要减掉图片的高度
            switch (ea) {
                case MotionEvent.ACTION_DOWN:
                    lastX = (int) event.getRawX();//获取触摸事件触摸位置的原始X坐标
                    lastY = (int) event.getRawY();
                    yDowm = (int) event.getRawY();
                case MotionEvent.ACTION_MOVE:
                    //event.getRawX();获得移动的位置
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;
                    int l = v.getLeft() + dx;
                    int b = v.getBottom() + dy;
                    int r = v.getRight() + dx;
                    int t = v.getTop() + dy;

                    //下面判断移动是否超出屏幕
                    if (l < 0) {
                        l = 0;
                        r = l + v.getWidth();
                    }
                    if (t < 0) {
                        t = 0;
                        b = t + v.getHeight();
                    }
                    if (r > screenWidth) {
                        r = screenWidth;
                        l = r - v.getWidth();
                    }
                    if (b > screenHeight) {
                        b = screenHeight;
                        t = b - v.getHeight();
                    }
                    v.layout(l, t, r, b);
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    v.postInvalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    recyclerView.start();
                    yMove = (int) event.getRawY() - yDowm;
                    break;
            }
            if (Math.abs(yMove
            ) < 1) {
                LogUtils.D("touch", "fasle");
                return false;
            } else {
                LogUtils.D("touch", "true");
                return true;
            }
        }
    };

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
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(updateInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                appVersionInfo = JSON.parseObject(response, AppVersionInfo.class);
                if (appVersionInfo.getCode() == 0) {
                    int curVersion = Integer.parseInt(ZeroZeroSevenUtils.getAppVersionName(bfCxt).replace(".", ""));
                    int lastVersion = Integer.parseInt(appVersionInfo.getData().getLatestVersion().replace(".", ""));
                    LogUtils.D("curVersion", curVersion + ":::::" + lastVersion);
                    if (lastVersion > curVersion) {
                        Tanchuang(appVersionInfo.getData().getLatestVersion(), appVersionInfo.getData().getReleaseNote(), appVersionInfo.getData().getTargetSize(), appVersionInfo.getData().getConstraint());
                    }
                }

            }
        });

    }

    private void Tanchuang(String title, String content, String size, int isupdate) {
        final UpdatePopWindow popWindow = new UpdatePopWindow(getActivity());
        popWindow.showAtLocation(ll_both, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popWindow.setContent(content);
        popWindow.setSize(size);
        popWindow.setTitle(title);
        popWindow.setGone(isupdate);
        popWindow.setMlistener(new UpdatePopWindow.OnButonClikListener() {
            @Override
            public void PopClose() {
                popWindow.dismiss();
            }

            @Override
            public void UpdateApp() {
                popWindow.dismiss();
                requestSomePermission();
                downLoadApk(appVersionInfo.getData().getDownloadUrl());
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

        if (userInfo != null) {
            if (tongzhiInfo != null && tongzhiInfo.getData().getList().size() >= 1) {
                scrollTextView.stopAutoScroll();
            }
            banner.pause();//暂停轮播
        }

    }


    public void goToDetils(final int position, final BestNewGoodsAdapter adapter) {
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
                    HomeActivity.getmInstance().get().go2Fragment(1);
                    GoodsContentShowInfo.DataBean.ProductsBean goodsInfo = new GoodsContentShowInfo.DataBean.ProductsBean();
                    goodsInfo.setId(adapter.getItem(position).getId());
                    goodsInfo.setGoodsName(adapter.getItem(position).getGoodsName());
                    goodsInfo.setThumbnail(adapter.getItem(position).getThumbnail());
                    goodsInfo.setStockNum(goodsDetilsInfo.getData().getStockNum());
                    goodsInfo.setPrice(goodsDetilsInfo.getData().getPrice());
//                          goodsInfo.setPromotionPrice(goodsDetilsInfo.getData().getPromotionPrice());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("shopInfo", goodsInfo);
                    bundle.putString("back", "main");
                    bundle.putString("type","shop");
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, ShopDetilsActivity.class, bundle);
                } else {
                    ToastUtils.showShort(goodsDetilsInfo.getMessage());
                }

            }
        });
    }

    public void goToDetils(final int position, final HotTimeAdapter adapter) {
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
                    HomeActivity.getmInstance().get().go2Fragment(1);
                    GoodsContentShowInfo.DataBean.ProductsBean goodsInfo = new GoodsContentShowInfo.DataBean.ProductsBean();
                    goodsInfo.setId(adapter.getItem(position).getId());
                    goodsInfo.setGoodsName(adapter.getItem(position).getGoodsName());
                    goodsInfo.setThumbnail(adapter.getItem(position).getThumbnail());
                    goodsInfo.setStockNum(goodsDetilsInfo.getData().getStockNum());
                    goodsInfo.setPrice(goodsDetilsInfo.getData().getPrice());
//                          goodsInfo.setPromotionPrice(goodsDetilsInfo.getData().getPromotionPrice());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("shopInfo", goodsInfo);
                    bundle.putString("back", "main");
                    bundle.putString("type", "shop");
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
        LogUtils.D("lazyLoad", "lazyLoad");
    }

//    @Override
//    public void onGetPoiResult(final PoiResult poiResult) {
//        try {
//            if (poiResult != null) {
//                if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
//                    String poi = poiResult.getAllPoi().get(0).name;
//                    if (poi.indexOf("学") != -1) {
//                        poi.substring(0, poi.lastIndexOf("学"));
//                        FindSchoolId(poi.substring(0, poi.indexOf("学") + 1));
//                    } else {
//                        tv_school.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                tv_school.setText("去选择学校");
//                            }
//                        });
//
//                    }
//
//                }
//            }
//        } catch (Exception e) {
//            tv_school.post(new Runnable() {
//                @Override
//                public void run() {
//                    tv_school.setText("去选择学校");
//                }
//            });
//        }
//    }
//
//    private void FindSchoolId(final String name) {
//        SearchSchoolInfo schoolInfo = new SearchSchoolInfo();
//        schoolInfo.setFunctionName("QuerySchoolByName");
//        SearchSchoolInfo.ParametersBean parametersBean = new SearchSchoolInfo.ParametersBean();
//        parametersBean.setSchoolName(name);
//        schoolInfo.setParameters(parametersBean);
//
//        OkGoUtils okGoUtils = new OkGoUtils(getActivity());
//        okGoUtils.httpPostJSON(schoolInfo, true, false);
//        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
//            @Override
//            public void onSuccLoad(String response) {
//                final FindSchoolInfo findSchoolInfo = JSON.parseObject(response, FindSchoolInfo.class);
//                if (findSchoolInfo.getCode() == 0) {
//                    if (findSchoolInfo.getData() != null) {
//                        if (TextUtils.isEmpty(findSchoolInfo.getData().getName())) {
//                            tv_school.setText("去选择学校");
//                        } else {
//                            tv_school.setText(findSchoolInfo.getData().getName());
//                            BaseAppApplication.userInfo.setSchoolName(name);
//                            BaseAppApplication.userInfo.setLocationSchoolId(findSchoolInfo.getData().getId() + "");
//                            SharePrefUtils.saveObject(bfCxt, "userInfo", BaseAppApplication.getInstance().getLoginUser());
//                            SharePrefUtils.setInt(bfCxt, "isLocation", 1);
//                        }
//                        reQuest();
//                    } else {
//                        tv_school.setText("去选择学校");
//                        reQuest();
//                    }
//                } else {
//                    reQuest();
//                }
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
//    }
//
//    @Override
//    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//
//    }

//    @Override
//    public void onRefresh(RefreshLayout refreshlayout) {
//        reQuest();
//    }

//    public class MyLocationListener extends BDAbstractLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
//            //以下只列举部分获取周边POI信息相关的结果
//            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//            searchNeayBy(latitude, longitude);
//        }
//    }

//    private void searchNeayBy(double latitude1, double longitude1) {
//        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption();
//        nearbySearchOption.location(new LatLng(latitude1, longitude1));
//        nearbySearchOption.keyword("大学");
//        nearbySearchOption.radius(3000);
//        nearbySearchOption.sortType(PoiSortType.distance_from_near_to_far);
//        mPoiSearch.searchNearby(nearbySearchOption);
//    }

    @Override
    public void initDate() {
        reQuest();
    }


    @Bind(R.id.tv_hot)
    TextView tv_hot;
    @Bind(R.id.tv_both)
    TextView tv_both;


    public void reQuest() {
        if (userInfo != null) {
            schoolIId = BaseAppApplication.getInstance().getLoginUser().getSchoolId();
            if (!TextUtils.isEmpty(userInfo.getSchoolName())) {
                tv_school.setText(userInfo.getSchoolName());
            }
        }
        initHeadView();
        requestBaner();
        requestShop();
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
        requestBothBuyList();
    }

    private void requestShop() {
        final ShangchangInfo shangchangInfo = new ShangchangInfo();
        shangchangInfo.setFunctionName("QuerySchoolStore");
        ShangchangInfo.ParametersBean parametersBean = new ShangchangInfo.ParametersBean();
        parametersBean.setSchoolId(Integer.parseInt(schoolIId));
        parametersBean.setCate("ZH");
        shangchangInfo.setParameters(parametersBean);
        OkGoUtils okGoUtils = new OkGoUtils(bfCxt);
        okGoUtils.httpPostJSON(shangchangInfo, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                final ShangChangShowInfo shangChangShowInfo = JSON.parseObject(response, ShangChangShowInfo.class);
                if (shangChangShowInfo.getCode() == 0) {
                    if (!TextUtils.isEmpty(shangChangShowInfo.getData().getServicePhone())) {
                        userInfo.setServicePhone(shangChangShowInfo.getData().getServicePhone());
                        BaseAppApplication.getInstance().setLoginUser(userInfo);
                        SharePrefUtils.saveObject(bfCxt, "userInfo", userInfo);
                    }
                }
            }
        });
    }

    List<String> images;

    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);
            mImageView = view.findViewById(R.id.image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            Glide.with(context).load(data).skipMemoryCache(true).into(mImageView);
        }
    }

    @Bind(R.id.rl_top_bg)
    RelativeLayout rl_top_bg;
    String upUrl = "";

    public String getUpurl() {
        return upUrl;
    }

    boolean showTwo = false;
    private List<String> urlList;
    private List<String> titleList;

    private void requestBaner() {
        LunBoInfo lunBoInfo = new LunBoInfo();
        lunBoInfo.setFunctionName("ListAd");
        LunBoInfo.ParametersBean parametersBean = new LunBoInfo.ParametersBean();
        parametersBean.setStatus("1");
        parametersBean.setType("00");
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
                        urlList = new ArrayList<>();
                        titleList = new ArrayList<>();
                        for (int i = 0; i < bannerInfo.getData().getList().size(); i++) {
                            if (bannerInfo.getData().getList().get(i).getType().equals("横幅广告")) {//横幅
                                images.add(bannerInfo.getData().getList().get(i).getPicUrl());
                                urlList.add(bannerInfo.getData().getList().get(i).getLink());
                                titleList.add(bannerInfo.getData().getList().get(i).getTitle());
                            } else if (bannerInfo.getData().getList().get(i).getType().equals("下拉广告")) {//下拉
                                showTwo = true;
                                header.loadImage(bannerInfo.getData().getList().get(i).getPicUrl());
                                upUrl = bannerInfo.getData().getList().get(i).getLink();
                            } else if (bannerInfo.getData().getList().get(i).getType().equals("启动广告")) {//启动
                                userInfo.setDowmPoster(bannerInfo.getData().getList().get(i).getPicUrl());
                                SharePrefUtils.saveObject(bfCxt, "userInfo", userInfo);
                            } else if (bannerInfo.getData().getList().get(i).getType().equals("专题广告")) {
                                iv_guanggao.setVisibility(View.VISIBLE);
                                projectUrl = bannerInfo.getData().getList().get(i).getLink();
                                Glide.with(bfCxt).load(bannerInfo.getData().getList().get(i).getPicUrl()).into(iv_guanggao);
                            }
                        }
                        if (!showTwo) {
                            mRefreshLayout.setResistance(3f);
                        }
                        Glide.with(bfCxt)
                                .load(images.get(0))
                                .skipMemoryCache(true)
                                .override(10, 10)
                                .into(iv_bg);
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


    //    05:00 - 09:00  09:00 - 11:30 11:30 - 14:00 14:00-17:00 17:00-19:00 19:00-24:00 24:00-05:00
    private void requestHotBuyList(String beforeTime, String afterTime) {
        GoodsOftenInfo oftenInfo = new GoodsOftenInfo();
        oftenInfo.setFunctionName("ListSchoolHotGoods");
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
                showHotInfo = JSON.parseObject(response, HotInfo.class);
                if (showHotInfo.getCode() == 0) {
                    if (!showHotInfo.getData().getStores().isIsClosing()) {
                        if (showHotInfo.getData().getProducts().size() > 0) {
                            hotGoodsAdapter.cleanDates();
                            hotGoodsAdapter.addAll(showHotInfo.getData().getProducts());
                            ll_hot.setVisibility(View.GONE);
                            rc_hot.setVisibility(View.VISIBLE);
                        } else {
                            hotGoodsAdapter.cleanDates();
                            ll_hot.setVisibility(View.VISIBLE);
                            tv_hot.setText("暂无商品");
                            rc_hot.setVisibility(View.GONE);
                        }
                    } else {
                        rc_hot.setVisibility(View.GONE);
                        hotGoodsAdapter.cleanDates();
                        ll_hot.setVisibility(View.VISIBLE);
                        tv_hot.setText("小七打烊中");
                    }


                } else if (showHotInfo.getCode() == -102) {
                    rc_hot.setVisibility(View.GONE);
                    hotGoodsAdapter.cleanDates();
                    ll_hot.setVisibility(View.VISIBLE);
                    tv_hot.setText("暂无商铺信息");
                } else {
                    hotGoodsAdapter.cleanDates();
                    ll_hot.setVisibility(View.VISIBLE);
                }

            }
        });

    }


    private void requestBothBuyList() {
        GoodsOftenInfo oftenInfo = new GoodsOftenInfo();
        oftenInfo.setFunctionName("ListLatestGoods");
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
                showBothInfo = JSON.parseObject(response, BestNewShowInfo.class);
                if (showBothInfo.getCode() == 0) {
                    if (showBothInfo.getData().getStores().isIsClosing()) {
                        bothGoodsAdapter.cleanDates();
                        ll_both.setVisibility(View.VISIBLE);
                        tv_both.setText("小七打烊中");
                        rc_all.setVisibility(View.GONE);
                    } else {
                        if (showBothInfo.getData().getLatestGoods().size() > 0) {
                            bothGoodsAdapter.cleanDates();
                            bothGoodsAdapter.addAll(showBothInfo.getData().getLatestGoods());
                            ll_both.setVisibility(View.GONE);
                            rc_all.setVisibility(View.VISIBLE);
                        } else {
                            bothGoodsAdapter.cleanDates();
                            ll_both.setVisibility(View.VISIBLE);
                            tv_both.setText("暂无商品");
                            rc_all.setVisibility(View.GONE);
                        }
                    }
                } else if (showBothInfo.getCode() == -102) {
                    bothGoodsAdapter.cleanDates();
                    ll_both.setVisibility(View.VISIBLE);
                    tv_both.setText("暂无商铺信息");
                    rc_all.setVisibility(View.GONE);
                } else {
                    bothGoodsAdapter.cleanDates();
                    ll_both.setVisibility(View.VISIBLE);
                }

            }
        });
    }
//    }


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
        okGoUtilspop = new OkGoUtils(getActivity());
        okGoUtilspop.httpPostJSON(poppurlarListInfo, true, false);
        okGoUtilspop.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(final String response) {
                userLikeInfo = JSON.parseObject(response, UserLikeInfo.class);
                try {
                    if (userLikeInfo.getCode() == 0) {
                        if (userLikeInfo.getData().getPosts().size() > 0) {
                            recyclerView.start();
                            haveData = 2;
                            userLikeAdapter.addAll(userLikeInfo.getData().getPosts());
                        } else {
                            haveData = 1;
                            if (pageNo == 0) {
                            } else {
                                pageNo = 0;
                                requestpopularList();
                            }

                        }

                    } else {
                        haveData = 1;
                        LogUtils.D("MainFragment", "nodate");
                    }

                } catch (Exception e) {
                    haveData = 1;
                }

            }
        });


    }


    @Bind(R.id.tv_school)
    TextView tv_school;
    boolean open = true;
    @Bind(R.id.iv_guanggao)
    ImageView iv_guanggao;


    @OnClick({R.id.rl_drive, R.id.rl_lease, R.id.rl_errand, R.id.rl_jump_shop, R.id.rl_numberrical, R.id.iv_show, R.id.rl_snack, R.id.rl_computer, R.id.rl_integer, R.id.rl_local, R.id.iv_guanggao, R.id.rl_location, R.id.tv_school})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.rl_drive:
                if (userInfo != null) {
                    if ("943478288".equals(schoolIId)) {
                        ZeroZeroSevenUtils.showCustonPop(bfCxt, "请先选择学校", recyclerView);
                    } else {
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, DrivingSchoolActivity.class);
                    }
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
            case R.id.rl_lease:
                if (userInfo != null) {
                    if ("943478288".equals(schoolIId)) {
                        ZeroZeroSevenUtils.showCustonPop(bfCxt, "请先选择学校", recyclerView);
                    } else {
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, LeaseActivity.class);
                    }
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
            case R.id.rl_errand:
                if (userInfo != null) {
                    if ("943478288".equals(schoolIId)) {
                        ZeroZeroSevenUtils.showCustonPop(bfCxt, "请先选择学校", recyclerView);
                    } else {
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, ErrandHomeActivity.class);
                    }
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
            case R.id.rl_jump_shop:
                if (userInfo != null) {
                    if ("943478288".equals(schoolIId)) {
                        ZeroZeroSevenUtils.showCustonPop(bfCxt, "请先选择学校", recyclerView);
                    } else {
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, TakeAwayFoodActivity.class);
                    }
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
            case R.id.rl_numberrical:
                ZeroZeroSevenUtils.SwitchActivity(bfCxt, NumberRicalActivity.class);
                break;
            case R.id.iv_show:
                if (open) {
                    open = false;
                    Glide.with(bfCxt).load(R.drawable.dmopen).into(iv_show);
                    recyclerView.setVisibility(View.VISIBLE);
                    requestpopularList();
                } else {
                    open = true;
                    Glide.with(bfCxt).load(R.drawable.dmooff).into(iv_show);
                    okGoUtilspop.cancel();
                    recyclerView.stop();
                    recyclerView.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_snack:
                HomeActivity.getmInstance().get().go2Fragment(1);
                break;
            case R.id.rl_computer:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, BitisNewActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
            case R.id.rl_integer:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, IntegralDrawActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
            case R.id.rl_local:
                if (userInfo != null) {
                    if ("943478288".equals(schoolIId)) {
                        ZeroZeroSevenUtils.showCustonPop(bfCxt, "请先选择学校", recyclerView);
                    } else {
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("title", "土豪吃货榜");
                        bundle3.putString("url", "https://www.lingling7.com/lingling7-res/app/dist/index.html#/");
                        ZeroZeroSevenUtils.SwitchActivity(bfCxt, WebViewActivity.class, bundle3);
                    }
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class);
                }
                break;
            case R.id.iv_guanggao:
                if (!TextUtils.isEmpty(projectUrl)) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MrsunWebActivity.class);
                }
                break;
            case R.id.tv_school:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, SearchSchoolActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;
            case R.id.rl_location:
                if (userInfo != null) {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, MessAgeActivity.class);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(bfCxt, LoginActivity.class, null);
                }
                break;


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        userInfo = BaseAppApplication.getInstance().getLoginUser();
        if (userInfo != null) {
            banner.start();//开始轮播
            if (tongzhiInfo != null && tongzhiInfo.getData().getList().size() >= 1) {
                scrollTextView.startAutoScroll();
            }
            requestTime();
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
