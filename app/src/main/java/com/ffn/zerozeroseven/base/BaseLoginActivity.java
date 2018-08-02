package com.ffn.zerozeroseven.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.aitangba.swipeback.SwipeBackActivity;
import com.ffn.zerozeroseven.R;
import com.gyf.barlibrary.ImmersionBar;
import com.kaopiz.kprogresshud.KProgressHUD;


/**
 * Created by GT on 2017/11/19.
 */

public abstract class BaseLoginActivity extends SwipeBackActivity {
    protected Context baContext;
    private KProgressHUD hud;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        BaseAppApplication.getInstance().addActivity(this);
        ImmersionBar.with(this).init();
        baContext = BaseLoginActivity.this;
        hud = KProgressHUD.create(BaseLoginActivity.this)
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseAppApplication.getInstance().finishActivity(this);
        ImmersionBar.with(this).destroy();
    }
}
