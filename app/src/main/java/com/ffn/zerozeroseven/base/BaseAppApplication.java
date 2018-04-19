package com.ffn.zerozeroseven.base;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import com.aitangba.swipeback.ActivityLifecycleHelper;
import com.baidu.mapapi.SDKInitializer;
import com.ffn.zerozeroseven.R;
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
import com.wanjian.cockroach.Cockroach;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by GT on 2017/11/15.
 */

public class BaseAppApplication extends MultiDexApplication {
    public static Context context;
    private static BaseAppApplication instance;
    private List<Activity> activityList = new ArrayList<>();
    public static Handler mainHandler;//主线程的handler
    public static UserInfo.DataBean userInfo;
    //判断是否被回收
    public static int flag = -1;
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


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Cockroach.install(new Cockroach.ExceptionHandler() {
            @Override
            public void handlerException(Thread thread, Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ToastUtils.showShort("服务器网络异常，请双击返回退出重进，或者重新登录");
                        } catch (Throwable e) {

                        }
                    }
                });
            }
        });
        //发布版本的时候把下面代码打开
        LogUtils.isDebug=false;
        mainHandler = new Handler();
        new ToastUtils(getApplicationContext());
        registerActivityLifecycleCallbacks(ActivityLifecycleHelper.build());
        //初始化百度地图
        SDKInitializer.initialize(this);

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
        activityList.add(activity);
    }


    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }


    public void clearActivityList() {
        if (activityList.size() > 0) {
            for (Activity activity : activityList) {
                activity.finish();
            }
        }
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
