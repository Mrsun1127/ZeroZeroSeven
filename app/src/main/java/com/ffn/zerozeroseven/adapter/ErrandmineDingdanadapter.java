package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RunDingdanInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class ErrandmineDingdanadapter extends BaseRecyclerAdapter<RunDingdanInfo.DataBean.ErrandOrdersBean> {
    public int clickPosition = 0;

    public ErrandmineDingdanadapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ErrandmineDingdanadapter.MViewHolder(mInflater.inflate(R.layout.item_dingdan_minerun, parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, RunDingdanInfo.DataBean.ErrandOrdersBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_createtime.setText(item.getCreateTime());
        mHolder.tv_type.setText(item.getGoodsType());
        mHolder.tv_letadr.setText(item.getDeliveryAddress());
        mHolder.tv_getadr.setText(item.getReceiverAddress());
        mHolder.tv_shipeeFee.setText(String.valueOf(item.getShippingFee()));
        mHolder.tv_letinfo.setText(item.getDeliveryName() + "   " + item.getDeliveryPhone());
        mHolder.tv_getinfo.setText(item.getReceiverName() + "   " + item.getReceiverPhone());

    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_createtime;
        TextView tv_type;
        TextView tv_shipeeFee;
        TextView tv_letadr;
        TextView tv_letinfo;
        TextView tv_getadr;
        TextView tv_getinfo;

        MViewHolder(View itemView) {
            super(itemView);
            tv_createtime = itemView.findViewById(R.id.tv_createtime);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_shipeeFee = itemView.findViewById(R.id.tv_shipeeFee);
            tv_letadr = itemView.findViewById(R.id.tv_letadr);
            tv_letinfo = itemView.findViewById(R.id.tv_letinfo);
            tv_getadr = itemView.findViewById(R.id.tv_getadr);
            tv_getinfo = itemView.findViewById(R.id.tv_getinfo);
        }
    }


}
