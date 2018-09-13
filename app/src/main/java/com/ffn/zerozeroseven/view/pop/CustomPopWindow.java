package com.ffn.zerozeroseven.view.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;


/**
 * Created by GT on 2017/10/31.
 */

public class CustomPopWindow extends PopupWindow implements View.OnClickListener {
    private LayoutInflater mInflater;
    private View mContentView;
    private Context context;
    OnButonClikListener mlistener;
    private TextView title;
    private TextView tv_text;
    private TextView content;
    private Button bt_sub;
    private LinearLayout ll_pop;
    private View view_line1;
    public interface OnButonClikListener {
        void BtAgain();
    }

    public void setMlistener(OnButonClikListener mListener) {
        this.mlistener = mListener;
    }

    public CustomPopWindow(Activity context) {
        super(context);
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = mInflater.inflate(R.layout.pop_releaserun, null);
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
        setOutsideTouchable(true);
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
    public void setTitle(String s){
        title.setText(s);
    }
    public void setContent(String s){
        content.setText(s);
    }
    public void setContentText(String s){
        tv_text.setText(s);
    }
    public void setBtText(String s){
        bt_sub.setText(s);
    }
    public void setBackGroud(int res){
        ll_pop.setBackgroundResource(res);
    }
    public void setgoneLine(){
        view_line1.setVisibility(View.GONE);
    }
    private void initView(View mContentView) {
        bt_sub = mContentView.findViewById(R.id.bt_sub);
        bt_sub.setOnClickListener(this);
        title = mContentView.findViewById(R.id.tv_title);
        tv_text = mContentView.findViewById(R.id.tv_text);
        content = mContentView.findViewById(R.id.tv_content);
        ll_pop = mContentView.findViewById(R.id.ll_pop);
        view_line1 = mContentView.findViewById(R.id.view_line1);

    }
    public void setMargin(){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)content.getLayoutParams();
        layoutParams.topMargin=100;
        content.setLayoutParams(layoutParams);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sub:
                mlistener.BtAgain();
                break;
        }
    }

}
