package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.fragment.MainFragment;
import com.ffn.zerozeroseven.fragment.MineFragment;
import com.ffn.zerozeroseven.fragment.ShopFragment;
import com.ffn.zerozeroseven.service.LocalService;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.NXHooldeView;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 11
 * Created by GT on 2017//15.
 */

public class HomeActivity extends AppCompatActivity {
    RelativeLayout rl_main;
    RelativeLayout rl_shop;
    RelativeLayout rl_mine;
    ImageView main;
    ImageView shop;
    ImageView mine;
    public LinearLayout Ll_bot;
    private ArrayList<RelativeLayout> rlArrayList = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public static WeakReference<HomeActivity> mInstance;
    private static final String TAG_EXIT = "exit";
    private MainFragment mainFragment;
    private ShopFragment shopFragment;
    private MineFragment mineFragment;

    public static WeakReference<HomeActivity> getmInstance() {
        return mInstance;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                this.finish();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        LogUtils.D("logString", "onSaveInstanceState");
        savedInstanceState.putSerializable("userInfo", BaseAppApplication.getInstance().getLoginUser());
        savedInstanceState.putSerializable("carShopInfo", BaseAppApplication.getInstance().getCarShopInfo());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.D("logString", "onRestoreInstanceState");
        BaseAppApplication.getInstance().setLoginUser((UserInfo.DataBean) savedInstanceState.getSerializable("userInfo"));
        BaseAppApplication.getInstance().setCarShopInfo((CarShopInfo) savedInstanceState.getSerializable("carShopInfo"));

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (mainFragment == null && fragment instanceof MainFragment) {
            mainFragment = (MainFragment) fragment;
            getSupportFragmentManager().beginTransaction().hide(mainFragment).commit();
        } else if (shopFragment == null && fragment instanceof ShopFragment) {
            shopFragment = (ShopFragment) fragment;
            getSupportFragmentManager().beginTransaction().hide(shopFragment).commit();
        } else if (mineFragment == null && fragment instanceof MineFragment) {
            mineFragment = (MineFragment) fragment;
            getSupportFragmentManager().beginTransaction().hide(mineFragment).commit();
        }
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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        BaseAppApplication.getInstance().addActivity(this);
        mInstance = new WeakReference<>(this);
        initRadio();
        initFragments();
        showFragment(0);
//        openAliveService();
        txst();
    }

    private void txst() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            int i = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (i != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                ToastUtils.showShort("亲爱的用户你好，如果不打开权限下次将无法更新App");
                txst();
            }
        }
    }


    private void initRadio() {
        Ll_bot = this.findViewById(R.id.Ll_bot);
        rl_main = this.findViewById(R.id.rl_main);
        rl_shop = this.findViewById(R.id.rl_shop);
        rl_mine = this.findViewById(R.id.rl_mine);
        main = this.findViewById(R.id.iv_main);
        shop = this.findViewById(R.id.iv_shop);
        mine = this.findViewById(R.id.iv_mine);
        rlArrayList.add((RelativeLayout) findViewById(R.id.rl_main));
        rlArrayList.add((RelativeLayout) findViewById(R.id.rl_shop));
        rlArrayList.add((RelativeLayout) findViewById(R.id.rl_mine));
        rlArrayList.get(0).setOnClickListener(new MyRlClickListener(0));
        rlArrayList.get(1).setOnClickListener(new MyRlClickListener(1));
        rlArrayList.get(2).setOnClickListener(new MyRlClickListener(2));
        Glide.with(HomeActivity.this).load(R.drawable.selected_home).into(main);
        Glide.with(HomeActivity.this).load(R.drawable.normal_classification).into(shop);
        Glide.with(HomeActivity.this).load(R.drawable.normal_center).into(mine);
    }

    private void initFragments() {
        fm = getSupportFragmentManager();
        mainFragment = MainFragment.newInstance();
        shopFragment = ShopFragment.newInstance();
        mineFragment = MineFragment.newInstance();
        fragments.add(mainFragment);
        fragments.add(shopFragment);
        fragments.add(mineFragment);
    }

    public void ll_Show(boolean s) {
        if (s) {
            Ll_bot.setVisibility(View.VISIBLE);
        } else {
            Ll_bot.setVisibility(View.GONE);
        }
    }


    private class MyRlClickListener implements View.OnClickListener {
        private int index;

        MyRlClickListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            if (index == 0) {
                Glide.with(HomeActivity.this).load(R.drawable.selected_home).into(main);
                Glide.with(HomeActivity.this).load(R.drawable.normal_classification).into(shop);
                Glide.with(HomeActivity.this).load(R.drawable.normal_center).into(mine);
            } else if (index == 1) {
                Glide.with(HomeActivity.this).load(R.drawable.normal_home).into(main);
                Glide.with(HomeActivity.this).load(R.drawable.selected_classification).into(shop);
                Glide.with(HomeActivity.this).load(R.drawable.normal_center).into(mine);
            } else if (index == 2) {
                Glide.with(HomeActivity.this).load(R.drawable.normal_home).into(main);
                Glide.with(HomeActivity.this).load(R.drawable.normal_classification).into(shop);
                Glide.with(HomeActivity.this).load(R.drawable.selected_center).into(mine);
            }
            showFragment(index);
        }
    }

    private FragmentManager fm;
    private Fragment newContentFragment;
    private Fragment curContentFragment;

    private void showContentFragment(int pos) {
        FragmentTransaction ft = fm.beginTransaction();
        newContentFragment = fragments.get(pos);
        if (curContentFragment != null) {
            if (curContentFragment != newContentFragment) {
                ft.hide(curContentFragment);
                if (newContentFragment.isAdded()) {
                    ft.show(newContentFragment);
                } else {
                    ft.add(R.id.fl_content, newContentFragment);
                }
                curContentFragment = newContentFragment;
            }
        } else {
            ft.add(R.id.fl_content, newContentFragment);
            curContentFragment = newContentFragment;
        }
        ft.commit();
    }

    private void showFragment(int pos) {
        showContentFragment(pos);
    }

    public void go2Fragment(int pos) {
        Glide.with(HomeActivity.this).load(R.drawable.normal_home).into(main);
        Glide.with(HomeActivity.this).load(R.drawable.selected_classification).into(shop);
        Glide.with(HomeActivity.this).load(R.drawable.normal_center).into(mine);
        showContentFragment(pos);
    }


    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {//退出程序
            exitApp();
        }
    }

    public void exitApp() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(HomeActivity.TAG_EXIT, true);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.D("logss", "onDestroy");
        SharePrefUtils.saveObject(HomeActivity.this, "carShopInfo", BaseAppApplication.getInstance().getCarShopInfo());
        SharePrefUtils.saveObject(HomeActivity.this, "numberRicalInfo", BaseAppApplication.getInstance().getNumberRicalInfo());
        BaseAppApplication.getInstance().finishActivity(this);
        System.gc();
        System.runFinalization();
        RefWatcher refWatcher = BaseAppApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }

    public void openAliveService() {
        startService(new Intent(HomeActivity.this, LocalService.class));
    }

    public void showpop() {
        rl_main.post(new Runnable() {
            @Override
            public void run() {
                ZeroZeroSevenUtils.showSleepPop(HomeActivity.this, rl_mine);
            }
        });

    }

    public void addAction(View startView) {
        NXHooldeView nxHooldeView = new NXHooldeView(HomeActivity.this);
        int position[] = new int[2];
        startView.getLocationInWindow(position);
        nxHooldeView.setStartPosition(new Point(position[0], position[1]));
        ViewGroup rootView = (ViewGroup) getWindow().getDecorView();
        rootView.addView(nxHooldeView);
        int endPosition[] = new int[2];
        shop.getLocationInWindow(endPosition);
        nxHooldeView.setEndPosition(new Point(endPosition[0], endPosition[1]));
        nxHooldeView.startBeizerAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
