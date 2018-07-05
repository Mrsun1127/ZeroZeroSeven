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

public class MySignGoodAdapter extends BaseRecyclerAdapter<ZhongJiangListInfo.DataBean.PointPrizeListBean> {
    public MySignGoodAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MySignGoodAdapter.MViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mysigngood, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, ZhongJiangListInfo.DataBean.PointPrizeListBean item, int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext)
                .load(item.getPrizePic())
                .into(mHolder.imageView);
        mHolder.tv_name.setText("【第" + item.getPrizeIssue() + "期】" + item.getPrizeName());
        if (!item.isAccept()) {//没有填写信息的
            mHolder.tv_status.setText("去领奖");
        } else {
            mHolder.tv_status.setText("完善信息");
        }
        mHolder.tv_time.setText(item.getLotteryTime());
        switch (item.getIssuePrizeStatus()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name;
        TextView tv_time;
        TextView tv_status;
        TextView tv_zhong;

        MViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_product);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_zhong = itemView.findViewById(R.id.tv_zhong);
        }
    }


}
