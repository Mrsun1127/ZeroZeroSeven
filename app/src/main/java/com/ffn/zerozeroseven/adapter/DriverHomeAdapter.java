package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.DriverSchoolMainInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class DriverHomeAdapter extends BaseRecyclerAdapter<DriverSchoolMainInfo.ContentsBean> {
    public int clickPosition = 0;

    public DriverHomeAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new DriverHomeAdapter.MViewHolder(mInflater.inflate(R.layout.item_driver_home, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, DriverSchoolMainInfo.ContentsBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
//        Glide.with(mContext).load(item.getThumb().getMid()).override(90, 70).into(mHolder.iv_icon);
        Glide.with(mContext).load(item.getThumb().getMid()).into(mHolder.iv_icon);
        mHolder.tv_name.setText(TextUtils.isEmpty(item.getTitle())?"加载中":item.getTitle());
        mHolder.tv_distance.setText(TextUtils.isEmpty(item.getDistance())?"加载中":item.getDistance()+"m");
        mHolder.tv_count.setText(TextUtils.isEmpty(item.getNumber())?"加载中":item.getNumber());
        mHolder.tv_money.setText(TextUtils.isEmpty(item.getPrice())?"加载中":item.getPrice());
        mHolder.tv_adr.setText(TextUtils.isEmpty(item.getAddress())?"加载中":item.getAddress());
    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_distance;
        TextView tv_count;
        TextView tv_money;
        TextView tv_adr;

        MViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_count = itemView.findViewById(R.id.tv_count);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_adr = itemView.findViewById(R.id.tv_adr);
        }
    }


}
