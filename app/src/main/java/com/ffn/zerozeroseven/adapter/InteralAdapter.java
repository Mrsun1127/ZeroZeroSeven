package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.QiangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DafenInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by GT on 2017/11/27.
 */

public class InteralAdapter extends BaseRecyclerAdapter<String> {
    public InteralAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new InteralAdapter.MViewHolder(mInflater.inflate(R.layout.item_interal, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final String item, int position) {
        final  MViewHolder mHolder = (MViewHolder) holder;
        mHolder.progressBar.setProgress(50);
    }


    private class MViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        MViewHolder(View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.pb_watch);
        }
    }


}
