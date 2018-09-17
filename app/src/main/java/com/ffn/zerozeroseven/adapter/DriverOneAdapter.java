package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.DriverDetilsInfo;

import butterknife.Bind;

/**
 * Created by GT on 2017/11/27.
 */

public class DriverOneAdapter extends BaseRecyclerAdapter<DriverDetilsInfo.DataBean.DrivingSchoolBean.DrivingClassListBean> {
    public int clickPosition = 0;

    public DriverOneAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new DriverOneAdapter.MViewHolder(mInflater.inflate(R.layout.item_driver_one, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, DriverDetilsInfo.DataBean.DrivingSchoolBean.DrivingClassListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_xing.setText(item.getName());
        mHolder.tv_price.setText(String.valueOf(item.getTotalPrice()));
    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_xing;
        TextView tv_price;

        MViewHolder(View itemView) {
            super(itemView);
            tv_xing = itemView.findViewById(R.id.tv_xing);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }


}
