package com.ffn.zerozeroseven.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.aitangba.swipeback.SwipeBackActivity;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.kaopiz.kprogresshud.KProgressHUD;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by GT on 2017/11/19.
 */

public abstract class BaseActivity extends SwipeBackActivity {
    protected Context baContext;
    public Call call;
    public String userId;
    public String schoolIId;
    public UserInfo.DataBean userInfo;
    private KProgressHUD hud;
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("userInfo",BaseAppApplication.getInstance().getLoginUser());
        savedInstanceState.putSerializable("carShopInfo", BaseAppApplication.getInstance().getCarShopInfo());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.D("logString", "onRestoreInstanceState");
        BaseAppApplication.getInstance().setLoginUser((UserInfo.DataBean)savedInstanceState.getSerializable("userInfo"));
        BaseAppApplication.getInstance().setCarShopInfo((CarShopInfo) savedInstanceState.getSerializable("carShopInfo"));
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            LogUtils.D("logString", "savedInstanceState!=null");
            BaseAppApplication.getInstance().setLoginUser((UserInfo.DataBean) savedInstanceState.getSerializable("userInfo"));
            BaseAppApplication.getInstance().setCarShopInfo((CarShopInfo) savedInstanceState.getSerializable("carShopInfo"));
        }
        LogUtils.D("logString", "savedInstanceState==null");
        setContentView(setLayout());
        BaseAppApplication.getInstance().addActivity(this);
        ImmersionBar.with(this).init();
        baContext = BaseActivity.this;
        userInfo = BaseAppApplication.getInstance().getLoginUser();
        if (userInfo != null) {
            userId = "" + userInfo.getId();
            schoolIId = userInfo.getSchoolId();
        } else {
            userId = "";
            schoolIId = "943478288";
        }

        hud = KProgressHUD.create(BaseActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setWindowColor(getResources().getColor(R.color.text_secondary_color))
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
        initView();
        bindData();
        doMain();
    }



    public void bindData() {

    }

    public void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        LogUtils.D("neicunxiaohao","我走了Base内存消耗");
//        if(BaseAppApplication.flag==-1){
//            LogUtils.D("neicunxiaohao","我走了Base内存消耗-1-1-1-1-1");
//            protectApp();
//        }
    }

    protected abstract int setLayout();

    protected abstract void doMain();


    protected void showLoadProgress() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                hud.setDetailsLabel("正在加载中")
                        .show();
            }
        });
    }

    protected void disLoadProgress() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (hud != null) {
                    hud.dismiss();
                } else {
                }
            }
        });
    }

    /**
     * 显示时屏幕变暗
     */
    public void lightOff() {

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();

        layoutParams.alpha = 0.3f;

        getWindow().setAttributes(layoutParams);

    }

    public void httpPostJSON(Object obj) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //换成自己的ip就行
        String url = AppConfig.BaseUrl;
        OkHttpClient client = new OkHttpClient();//创建okhttp实例
        RequestBody body = RequestBody.create(JSON, JsonUtil.parseBeanToJson(obj));
        Request request = new Request.Builder()
                .addHeader("platform", "android")
                .url(url)
                .post(body)
                .build();
        call = client.newCall(request);


    }

    public void httpPostJSON(Object obj, boolean isToken) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //换成自己的ip就行
        String url = AppConfig.BaseUrl;
        OkHttpClient client = new OkHttpClient();//创建okhttp实例
        RequestBody body = RequestBody.create(JSON, JsonUtil.parseBeanToJson(obj));
        if (isToken) {
            String token = "";
            if (userInfo != null) {
                token = userInfo.getToken();
            }

            Request request = new Request.Builder()
                    .addHeader("platform", "android")
                    .addHeader("Authorization", "Bearer " + token)
                    .addHeader("version", "1.1.0")
                    .url(url)
                    .post(body)
                    .build();

            call = client.newCall(request);

        } else {
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            call = client.newCall(request);

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseAppApplication.getInstance().finishActivity(this);
        ImmersionBar.with(this).destroy();
    }
}
