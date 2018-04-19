package com.ffn.zerozeroseven.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;

/**
 * Created by GT on 2017/11/19.
 */

public class TitleView extends RelativeLayout implements View.OnClickListener {
    Context context;
    private final RelativeLayout iv_back;
    private final RelativeLayout rl_arrow_down;
    private final RelativeLayout rl_message;
    private final RelativeLayout rl_init;
    private final TextView tv_top;
    private final TextView tv_right;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.title_layout, this, true);
        view.findViewById(R.id.iv_back).setOnClickListener(this);
        view.findViewById(R.id.rl_arrow_down).setOnClickListener(this);
        view.findViewById(R.id.rl_message).setOnClickListener(this);
        iv_back = view.findViewById(R.id.iv_back);
        rl_arrow_down = view.findViewById(R.id.rl_arrow_down);
        rl_init = view.findViewById(R.id.rl_init);
        rl_message = view.findViewById(R.id.rl_message);
        tv_top = view.findViewById(R.id.tv_top);
        tv_right = view.findViewById(R.id.tv_right);
        tv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.tvRight();
            }
        });

    }


    public void setTopText(String str) {
        tv_top.setText(str);
    }
    public void setTvRightText(String str) {
        tv_right.setText(str);
    }

    public void setBackUnSHow() {
        iv_back.setVisibility(View.GONE);
    }

    public void setDownShow() {
        rl_init.setVisibility(View.VISIBLE);
    }

    public void setMessAgeShow() {
        rl_message.setVisibility(View.VISIBLE);
    }

    public void setTvRightShow() {
        tv_right.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                listener.ivBack();
                break;
            case R.id.rl_arrow_down:
                listener.ivDown();
                break;
            case R.id.rl_message:
                listener.ivMessAge();
                break;

        }
    }

    public interface OnTitleClickListener {
        void ivBack();

        void ivDown();

        void ivMessAge();
    }

    private OnTitleClickListener listener;

    public void setOnTitleListener(OnTitleClickListener listener) {
        this.listener = listener;
    }

    public interface OnRightClickListener {
        void tvRight();
    }

    private OnRightClickListener mlistener;

    public void setOnRightClickListener(OnRightClickListener mlistener) {
        this.mlistener = mlistener;
    }
}
