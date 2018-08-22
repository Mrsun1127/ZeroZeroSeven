package com.ffn.zerozeroseven.base;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.aitangba.swipeback.ActivityLifecycleHelper;
import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.mob.MobSDK;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.commonsdk.UMConfigure;

import java.util.ArrayList;
import java.util.Stack;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by GT on 2017/11/15.
 */

public class BaseAppApplication extends MultiDexApplication {
    public static Context context;
    private static BaseAppApplication instance;
    private Stack<Activity> activityList;
    public static Handler mainHandler;//主线程的handler
    public static UserInfo.DataBean userInfo;
    private static ArrayList<Integer> readList;
    private static CarShopInfo carShopInfo = new CarShopInfo();
    private static CarShopInfo foodcarShopInfo = new CarShopInfo();
    private static NumberRicalInfo numberRicalInfo = new NumberRicalInfo();
    //判断是否被回收
    public static String clearType;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.transparent, android.R.color.darker_gray);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public CarShopInfo getCarShopInfo() {
        return carShopInfo;
    }

    public void setCarShopInfo(CarShopInfo carShopInfo1) {
        if (carShopInfo1 != null) {
            carShopInfo = carShopInfo1;
        }
    }

    public CarShopInfo getFoodcarShopInfo() {
        return foodcarShopInfo;
    }

    public void setFoodcarShopInfo(CarShopInfo carShopInfo1) {
        if (carShopInfo1 != null) {
            foodcarShopInfo = carShopInfo1;
        }
    }

    public NumberRicalInfo getNumberRicalInfo() {
        return numberRicalInfo;
    }

    public void setNumberRicalInfo(NumberRicalInfo numberRicalInfo1) {
        if (numberRicalInfo1 != null) {
            numberRicalInfo = numberRicalInfo1;
        }
    }

    public ArrayList<Integer> getReadList() {
        if (null == readList) {
            readList = new ArrayList<>();
        }
        return readList;
    }

    public void setReadList(ArrayList<Integer> readList1) {
        readList = readList1;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
//        Cockroach.install(new Cockroach.ExceptionHandler() {
//            @Override
//            public void handlerException(Thread thread, Throwable throwable) {
//                new Handler(Looper.getMainLooper()).post(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
////                            ToastUtils.showShort("出了点小问题 请您双击返回退出app，重新进入");
//                        } catch (Throwable e) {
//
//                        }
//                    }
//                });
//            }
//        });
        //发布版本的时候把下面代码打开
//        LogUtils.isDebug=false
        mainHandler = new Handler();
        new ToastUtils(getApplicationContext());
        registerActivityLifecycleCallbacks(ActivityLifecycleHelper.build());
        //初始化百度地图
//        SDKInitializer.initialize(this);
        //初始化极光
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        MobSDK.init(this);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5a9a40568f4a9d688300016c");
        UMConfigure.setLogEnabled(true);
    }

    public static BaseAppApplication getInstance() {
        if (null == instance) {
            instance = new BaseAppApplication();
        }
        return instance;

    }

    public void clearList() {
        activityList.clear();
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activityList == null) {
            activityList = new Stack<>();
        }
        activityList.add(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityList.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    // 遍历所有Activity并finish
    public void exit() {
        if (activityList != null) {
            for (int i = 0, size = activityList.size(); i < size; i++) {
                if (null != activityList.get(i)) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
        }
        System.exit(0);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        LogUtils.D("lowMemory", String.valueOf(level));
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Glide.get(context).clearMemory();
                }
            });
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearMemory();
            }
        });
    }

    public void clearActivityList() {
        if (activityList != null) {
            for (int i = 0, size = activityList.size(); i < size; i++) {
                if (null != activityList.get(i)) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
        }
//        mainHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                System.exit(0);
//            }
//        }, 500);
    }

    public UserInfo.DataBean getLoginUser() {
        return userInfo;
    }

    public void setLoginUser(UserInfo.DataBean userInfo) {

        this.userInfo = userInfo;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
