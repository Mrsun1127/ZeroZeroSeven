package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.BestNewInfo;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by GT on 2017/11/27.
 */

public class MySignGoodAdapter extends BaseRecyclerAdapter<BestNewInfo.DataBean.JackpotPrizesBean> {
    public MySignGoodAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MySignGoodAdapter.MViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mysigngood,parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, BestNewInfo.DataBean.JackpotPrizesBean item, int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name;
        TextView tv_status;

        MViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_product);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_status = itemView.findViewById(R.id.tv_status);
        }
    }


}
