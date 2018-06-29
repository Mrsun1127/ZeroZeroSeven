package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.JiangChiInfo;
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

public class InteralAdapter extends BaseRecyclerAdapter<JiangChiInfo.DataBean.JackpotPrizesBean> {
    public InteralAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new InteralAdapter.MViewHolder(mInflater.inflate(R.layout.item_interal, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final JiangChiInfo.DataBean.JackpotPrizesBean item, int position) {
        final  MViewHolder mHolder = (MViewHolder) holder;
        mHolder.progressBar.setMax(item.getPrizePoint());
        mHolder.progressBar.setProgress(item.getContributionPoint());
        mHolder.tv_product_detils.setText("【第"+item.getPrizeIssue()+"期】"+item.getPrizeName());
        mHolder.tv_progress.setText("开奖进度 "+(((item.getContributionPoint()/item.getPrizePoint()))*100)+"%");
        mHolder.tv_count.setText(String.valueOf(item.getPrizePoint()-item.getContributionPoint()));
        Glide.with(mContext).load(item.getPrizePic()).into(mHolder.iv_product);
    }


    private class MViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        ImageView iv_product;
        TextView tv_product_detils;
        TextView tv_count;
        TextView tv_progress;
        MViewHolder(View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.pb_watch);
            iv_product=itemView.findViewById(R.id.iv_product);
            tv_product_detils=itemView.findViewById(R.id.tv_product_detils);
            tv_count=itemView.findViewById(R.id.tv_count);
            tv_progress=itemView.findViewById(R.id.tv_progress);
        }
    }


}
