package com.ffn.zerozeroseven.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.ffn.zerozeroseven.R;

public class MrsunStatusView extends RelativeLayout {


    public MrsunStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.mrsun_statusview, this, true);
    }



}
