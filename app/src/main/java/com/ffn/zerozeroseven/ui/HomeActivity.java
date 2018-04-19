package com.ffn.zerozeroseven.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.fragment.MainFragment;
import com.ffn.zerozeroseven.fragment.MineFragment;
import com.ffn.zerozeroseven.fragment.ShopFragment;
import com.ffn.zerozeroseven.service.LocalService;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.gyf.barlibrary.ImmersionBar;

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
    View view_main;
    View view_shop;
    View view_mine;
    private ArrayList<RelativeLayout> rlArrayList = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public static HomeActivity mInstance;

    public static HomeActivity getmInstance() {
        return mInstance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImmersionBar.with(this).init();
        BaseAppApplication.getInstance().addActivity(this);
        mInstance = this;
        initRadio();
        initFragments();
        showFragment(0);
        openAliveService();
        txst();
    }

    private void txst() {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
            int i=checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(i!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==101){
            if(permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)&& grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }else{
                ToastUtils.showShort("亲爱的用户你好，如果不打开权限下次将无法更新App");
                txst();
            }
        }
    }

    protected void protectApp() {
        BaseAppApplication.getInstance().clearActivityList();
//        startActivity(new Intent(this, SplashActivity.class));
//        finish();
    }

    private void initRadio() {
        rl_main = this.findViewById(R.id.rl_main);
        rl_shop = this.findViewById(R.id.rl_shop);
        rl_mine = this.findViewById(R.id.rl_mine);
        view_main = this.findViewById(R.id.view_main);
        view_shop = this.findViewById(R.id.view_shop);
        view_mine = this.findViewById(R.id.view_mine);
        rlArrayList.add((RelativeLayout) findViewById(R.id.rl_main));
        rlArrayList.add((RelativeLayout) findViewById(R.id.rl_shop));
        rlArrayList.add((RelativeLayout) findViewById(R.id.rl_mine));
        rlArrayList.get(0).setOnClickListener(new MyRlClickListener(0));
        rlArrayList.get(1).setOnClickListener(new MyRlClickListener(1));
        rlArrayList.get(2).setOnClickListener(new MyRlClickListener(2));
    }

    private void initFragments() {
        fm = getSupportFragmentManager();
        fragments.add(new MainFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MineFragment());
    }



    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.D("neicunxiaohao","我走了Home内存消耗");
    }

    private class MyRlClickListener implements View.OnClickListener {
        private int index;

        MyRlClickListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            if (index == 0) {
                view_main.setVisibility(View.VISIBLE);
                view_shop.setVisibility(View.GONE);
                view_mine.setVisibility(View.GONE);
            } else if (index == 1) {
                view_shop.setVisibility(View.VISIBLE);
                view_main.setVisibility(View.GONE);
                view_mine.setVisibility(View.GONE);
            } else if (index == 2) {
                view_mine.setVisibility(View.VISIBLE);
                view_main.setVisibility(View.GONE);
                view_shop.setVisibility(View.GONE);
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
        showContentFragment(pos);
        view_shop.setVisibility(View.VISIBLE);
        view_main.setVisibility(View.GONE);
        view_mine.setVisibility(View.GONE);
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
            BaseAppApplication.getInstance().exit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    public void openAliveService() {
        startService(new Intent(HomeActivity.this, LocalService.class));
    }
    public void showpop(){
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                ZeroZeroSevenUtils.showSleepPop(HomeActivity.this,rl_mine);
            }
        });
    }
}
