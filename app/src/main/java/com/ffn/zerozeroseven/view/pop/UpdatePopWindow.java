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
import android.widget.TextView;

import com.ffn.zerozeroseven.R;


/**
 * Created by GT on 2017/10/31.
 */

public class UpdatePopWindow extends PopupWindow implements View.OnClickListener {
    private LayoutInflater mInflater;
    private View mContentView;
    private Context context;
    OnButonClikListener mlistener;
    private TextView tv_up_title;
    private TextView tv_size;
    private TextView tv_up_content;
    private ImageView iv_close;
    private TextView tv_ignore;
    private LinearLayout ll_close;
    private Button bt_update;

    public interface OnButonClikListener {
        void PopClose();

        void UpdateApp();
    }

    public void setMlistener(OnButonClikListener mListener) {
        this.mlistener = mListener;
    }

    public UpdatePopWindow(Activity context) {
        super(context);
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = mInflater.inflate(R.layout.pop_dowmload, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        setContentView(mContentView);
        //设置宽与高
        setWidth(w);
        setHeight(h);
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
        tv_up_content.setText(s);
    }

    public void setTitle(String s) {
        tv_up_title.setText("是否升级到" + s + "版本？");
    }

    public void setSize(String s) {
        tv_size.setText("升级大小" + s + "M");
    }

    public void setGone(int b) {
        if (b == 1) {
            ll_close.setVisibility(View.GONE);
            tv_ignore.setVisibility(View.GONE);
            return;
        }
        ll_close.setVisibility(View.VISIBLE);
        tv_ignore.setVisibility(View.VISIBLE);
    }

    private void initView(View mContentView) {
        iv_close = mContentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        tv_up_title = mContentView.findViewById(R.id.tv_up_title);
        tv_size = mContentView.findViewById(R.id.tv_size);
        tv_up_content = mContentView.findViewById(R.id.tv_up_content);
        ll_close = mContentView.findViewById(R.id.ll_close);
        ll_close.setOnClickListener(this);
        tv_ignore = mContentView.findViewById(R.id.tv_ignore);
        tv_ignore.setOnClickListener(this);
        bt_update = mContentView.findViewById(R.id.bt_update);
        bt_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                mlistener.PopClose();
                break;
            case R.id.ll_close:
                mlistener.PopClose();
                break;
            case R.id.tv_ignore:
                mlistener.PopClose();
                break;
            case R.id.bt_update:
                mlistener.UpdateApp();
                break;
        }
    }

}
