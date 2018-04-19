package com.ffn.zerozeroseven.utlis;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;


/*
* MyCountTimer 倒计时
* @author archerlee
* @time 16/3/24 10:19
*/
public class MyCountTimer extends CountDownTimer {
    public static final int TIME_COUNT = 60000;//（以倒计时60s为例子）
    private TextView btn;
    private int endStrRid;
    private int normalColor;//未计时的文字颜色
    int timingColor = Color.RED;//计时期间的文字颜色

    /**
     * 参数 millisInFuture         倒计时总时间（如60S等）
     * 参数 countDownInterval    渐变时间（每次倒计1s） 
     * 参数 btn   点击的按钮(因为Button是TextView子类，为了通用我的参数设置为TextView） 
     * 参数 endStrRid   倒计时结束后，按钮对应显示的文字
     */
    public MyCountTimer(long millisInFuture, long countDownInterval, TextView btn, int endStrRid) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }

    /**
     *  
     *  参数上面有注释
     */
    public MyCountTimer(TextView btn, int endStrRid) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }


    public MyCountTimer(TextView btn) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = R.string.send_again;
    }


    public MyCountTimer(TextView tv_varify, int normalColor, int timingColor) {
        this(tv_varify);
        this.normalColor = normalColor;
        this.timingColor = timingColor;
    }


    // 计时完毕时触发
    @Override
    public void onFinish() {
        if (normalColor > 0) {
            btn.setTextColor(normalColor);
        }
        btn.setText(endStrRid);
        btn.setEnabled(true);
    }


    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
        if (timingColor > 0) {
            btn.setTextColor(timingColor);
        }
        btn.setEnabled(false);
        btn.setText("(" + millisUntilFinished / 1000 + "s)");
    }
}
