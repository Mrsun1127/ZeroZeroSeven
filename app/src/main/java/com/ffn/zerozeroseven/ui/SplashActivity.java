package com.ffn.zerozeroseven.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppManger;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

public class SplashActivity extends AppCompatActivity {
    private boolean first;    //判断是否第一次打开软件
    private boolean loginMode;
    private SharedPreferences shared;
    private TextView tv_version;
    private SharedPreferences sharedLogin;
    private UserInfo.DataBean userInfo;
    private CarShopInfo carShopInfo;
    private CarShopInfo foodcarShopInfo;
    private CarShopInfo leasecarShopInfo;
    private NumberRicalInfo numberRicalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        BaseAppApplication.getInstance().addActivity(this);
        tv_version = findViewById(R.id.tv_version);
        userInfo = (UserInfo.DataBean) SharePrefUtils.readObject(SplashActivity.this, "userInfo");
        carShopInfo = (CarShopInfo) SharePrefUtils.readObject(SplashActivity.this, "carShopInfo");
        foodcarShopInfo = (CarShopInfo) SharePrefUtils.readObject(SplashActivity.this, "foodcarShopInfo");
        leasecarShopInfo = (CarShopInfo) SharePrefUtils.readObject(SplashActivity.this, "leasecarShopInfo");
        numberRicalInfo = (NumberRicalInfo) SharePrefUtils.readObject(SplashActivity.this, "numberRicalInfo");
       BaseAppApplication.mainHandler.postDelayed(new Runnable() {
           @Override
           public void run() {
               shared = getSharedPreferences("lead", Context.MODE_PRIVATE);
               // 如果网络可用则判断是否第一次进入，如果是第一次则进入欢迎界面
               first = shared.getBoolean("First", true);
               if (first) {
                   ZeroZeroSevenUtils.SwitchActivity(SplashActivity.this, WelcomeActivity.class);
                   finish();
               } else {
                   if (userInfo != null) {
                       BaseAppApplication.getInstance().setLoginUser(userInfo);
                       BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
                       BaseAppApplication.getInstance().setFoodcarShopInfo(foodcarShopInfo);
                       BaseAppApplication.getInstance().setNumberRicalInfo(numberRicalInfo);
                       BaseAppApplication.getInstance().setLeasecarShopInfo(leasecarShopInfo);
                       if (!TextUtils.isEmpty(userInfo.getDowmPoster())) {
                           Bundle bundle = new Bundle();
                           bundle.putString("imgurl", userInfo.getDowmPoster());
                           ZeroZeroSevenUtils.SwitchActivity(SplashActivity.this, AdvertisingActivity.class, bundle);
                       } else {
                           ZeroZeroSevenUtils.SwitchActivity(SplashActivity.this, HomeActivity.class, null);
                       }
                       finish();
                   } else {
                       ZeroZeroSevenUtils.SwitchActivity(SplashActivity.this, LoginActivity.class, null);
                       finish();
                   }
               }
           }
       },2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseAppApplication.getInstance().finishActivity(this);
    }
}
