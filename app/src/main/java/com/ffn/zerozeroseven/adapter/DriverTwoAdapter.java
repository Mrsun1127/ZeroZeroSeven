package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.DriverDetilsInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class DriverTwoAdapter extends BaseRecyclerAdapter<DriverDetilsInfo.DataBean.DrivingSchoolBean.DrivingPlaceListBean> {
    public int clickPosition = 0;

    public DriverTwoAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new DriverTwoAdapter.MViewHolder(mInflater.inflate(R.layout.item_driver_two, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, DriverDetilsInfo.DataBean.DrivingSchoolBean.DrivingPlaceListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_name.setText(item.getName());
        mHolder.tv_adr.setText(item.getAddress());
        Glide.with(mContext).load(item.getThumb()).override(100, 100).into(mHolder.iv_icon);
    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_adr;
        ImageView iv_icon;

        MViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_adr = itemView.findViewById(R.id.tv_adr);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }


}
