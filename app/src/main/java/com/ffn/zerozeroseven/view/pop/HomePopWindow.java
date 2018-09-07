package com.ffn.zerozeroseven.view.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;


/**
 * Created by GT on 2017/10/31.
 */

public class HomePopWindow extends PopupWindow implements View.OnClickListener {
    private LayoutInflater mInflater;
    private View mContentView;
    private Context context;
    OnButonClikListener mlistener;
    private TextView tv_tanchuang;
    private TextView tv_close;
    private LinearLayout ll_pop;

    public interface OnButonClikListener {
        void BtAgain();
    }

    public void setMlistener(OnButonClikListener mListener) {
        this.mlistener = mListener;
    }

    public HomePopWindow(Activity context) {
        super(context);
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = mInflater.inflate(R.layout.main_tanchuangshow, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        setContentView(mContentView);
        //设置宽与高
        setWidth(w);
        setHeight(h );
        /**
         * 设置进出动画
         */
        setAnimationStyle(R.style.NewPopView);
        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        setBackgroundDrawable(dw);
        /**
         * 设置可以获取集点
         */
        setFocusable(true);

        /**
         * 设置点击外边可以消失
         */
        setOutsideTouchable(false);
        /**
         *设置可以触摸
         */
        setTouchable(true);
        /**
         * 设置点击外部可以消失
         */

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                /**
                 * 判断是不是点击了外部
                 */
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    return true;
                }
                //不是点击外部
                return false;
            }
        });
        initView(mContentView);
        initListener();
    }

    private void initListener() {

    }

    public void setContent(String s) {
        tv_tanchuang.setText(s);
    }

    private void initView(View mContentView) {
        tv_close = mContentView.findViewById(R.id.tv_close);
        tv_close.setOnClickListener(this);
        tv_tanchuang = mContentView.findViewById(R.id.tv_tanchuang);
        ll_pop = mContentView.findViewById(R.id.ll_pop);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                mlistener.BtAgain();
                break;
        }
    }

}
