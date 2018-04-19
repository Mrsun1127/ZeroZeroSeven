package com.ffn.zerozeroseven.view.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.utlis.ToastUtils;


/**
 * Created by GT on 2017/10/31.
 */

public class BeZZSPopWindow extends PopupWindow implements View.OnClickListener {
    private LayoutInflater mInflater;
    private View mContentView;
    private Context context;
    OnButonClikListener mlistener;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_school;

    public interface OnButonClikListener {
        void OnBtSub(String name, String phoneNumber, String school);
    }

    public void setMlistener(OnButonClikListener mListener) {
        this.mlistener = mListener;
    }

    public BeZZSPopWindow(Activity context) {
        super(context);
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = mInflater.inflate(R.layout.pop_becomezzs, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        setContentView(mContentView);

        //设置宽与高
        setWidth(w/2+100);
        setHeight(h/2);
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

    private void initView(View mContentView) {
        Button bt_sub = mContentView.findViewById(R.id.bt_sub);
        et_name = mContentView.findViewById(R.id.et_name);
        et_phone = mContentView.findViewById(R.id.et_phone);
        et_school = mContentView.findViewById(R.id.et_school);
        bt_sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sub:
                if (et_name.getText().toString().isEmpty() || et_phone.getText().toString().isEmpty() || et_school.getText().toString().isEmpty()) {
                    ToastUtils.showShort("请将信息填写完整", context);
                    return;
                }
                mlistener.OnBtSub(et_name.getText().toString(), et_phone.getText().toString(), et_school.getText().toString());
                break;
        }
    }

}
