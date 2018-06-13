package com.ffn.zerozeroseven.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppManger;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.utlis.MyCountTimer;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdvertisingActivity extends AppCompatActivity {
    @Bind(R.id.iv_advertisting)
    ImageView imageView;
    @Bind(R.id.bt_jump)
    Button jump;
    private Timer timer;
    int time=3;
    private String imgUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_advertising);
        BaseAppApplication.getInstance().addActivity(this);
        ButterKnife.bind(this);
        imgUrl = getIntent().getStringExtra("imgurl");
        Glide.with(this).load(imgUrl).into(imageView);
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        jump.setText("跳过（"+time+"S）");
                        time--;
                        if(time==0){
                            stopTimer();
                            ZeroZeroSevenUtils.SwitchActivity(AdvertisingActivity.this,HomeActivity.class);
                            finish();
                        }
                    }
                });
            }
        }, 0, 1000);

    }
    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            // 一定设置为null，否则定时器不会被回收
            timer = null;
        }
    }
    @OnClick({R.id.bt_jump})
    void setOnClicks(View v) {
        switch (v.getId()) {
            case R.id.bt_jump:
                stopTimer();
                ZeroZeroSevenUtils.SwitchActivity(AdvertisingActivity.this,HomeActivity.class);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        BaseAppApplication.getInstance().finishActivity(this);
        super.onDestroy();
    }
}
