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
import com.ffn.zerozeroseven.bean.ZhongJiangListInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class GoInMySignGoodAdapter extends BaseRecyclerAdapter<ZhongJiangListInfo.DataBean.PointPrizeContributionListBean> {
    public GoInMySignGoodAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new GoInMySignGoodAdapter.MViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mysigngoodgoin, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, ZhongJiangListInfo.DataBean.PointPrizeContributionListBean item, int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext)
                .load(item.getPrizePic())
                .into(mHolder.imageView);
        mHolder.tv_name.setText("【第"+item.getPrizeIssue()+"期】"+item.getPrizeName());
        mHolder.tv_go.setText("贡献了"+item.getContributionPoint()+"积分");

    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name;
        TextView tv_go;

        MViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_product);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_go = itemView.findViewById(R.id.tv_go);
        }
    }


}
