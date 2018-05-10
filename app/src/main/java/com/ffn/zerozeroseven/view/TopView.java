package com.ffn.zerozeroseven.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;

public class TopView extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private RelativeLayout rl_back;
    private RelativeLayout rl_post;
    private TextView tv_home;
    private TextView tv_right;
    private ImageView iv_post;

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.bitis_title, this, true);
        rl_post = view.findViewById(R.id.rl_post);
        rl_back = view.findViewById(R.id.rl_back);
        tv_home = view.findViewById(R.id.tv_home);
        tv_right = view.findViewById(R.id.tv_right);
        iv_post = view.findViewById(R.id.iv_post);
        rl_post.setOnClickListener(this);
        rl_back.setOnClickListener(this);
        setRightShow(false);
    }

    public void setTopText(String str) {
        tv_home.setText(str);
    }

    public void setTvRightText(String str) {
        tv_right.setVisibility(View.VISIBLE);
        iv_post.setVisibility(View.GONE);
        setRightShow(true);
        tv_right.setText(str);
    }

    public void setTvRightDrawable(int rid) {
        setRightShow(true);
        tv_right.setVisibility(View.GONE);
        iv_post.setVisibility(View.VISIBLE);
        Glide.with(context).load(rid).into(iv_post);
    }
    public void setRightShow(boolean show) {
        if (show) {
            rl_post.setVisibility(View.VISIBLE);
        } else {
            rl_post.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_post:
                listener.Right();
                break;
            case R.id.rl_back:
                listener.Back();
                break;
        }
    }
    public interface OnTitleClickListener {
        void Right();
        void Back();
    }
    private OnTitleClickListener listener;

    public void setOnTitleListener(OnTitleClickListener listener) {
        this.listener = listener;
    }
}
