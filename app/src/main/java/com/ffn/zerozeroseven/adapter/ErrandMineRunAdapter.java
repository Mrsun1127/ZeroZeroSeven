package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberLevelInfo;
import com.ffn.zerozeroseven.bean.RunnerListInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by GT on 2017/11/27.
 */

public class ErrandMineRunAdapter extends BaseRecyclerAdapter<RunnerListInfo.DataBean.ErrandOrdersBean> {
    public ErrandMineRunAdapter(Context context) {
        super(context);
    }


    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ErrandMineRunAdapter.MViewHolder(mInflater.inflate(R.layout.errand_item_minerun, parent, false));
    }


    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, RunnerListInfo.DataBean.ErrandOrdersBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_time.setText(item.getCreateTime());
        mHolder.tv_type.setText(item.getGoodsType());
        mHolder.tv_money.setText(String.valueOf(item.getErrandIncome()) + "元");
        mHolder.tv_letadr.setText(item.getDeliveryAddress());
        mHolder.tv_getadr.setText(item.getReceiverAddress());
        mHolder.tv_letinfo.setText(item.getDeliveryName().substring(0, 1) + "* " + ZeroZeroSevenUtils.phoneClose(item.getDeliveryPhone()));
        mHolder.tv_getinfo.setText(item.getReceiverName().substring(0, 1) + "* " + ZeroZeroSevenUtils.phoneClose(item.getReceiverPhone()));
    }


    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_type;
        TextView tv_money;
        TextView tv_letadr;
        TextView tv_letinfo;
        TextView tv_getadr;
        TextView tv_getinfo;
        CountdownView cd_time;

        MViewHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_letadr = itemView.findViewById(R.id.tv_letadr);
            tv_letinfo = itemView.findViewById(R.id.tv_letinfo);
            tv_getadr = itemView.findViewById(R.id.tv_getadr);
            tv_getinfo = itemView.findViewById(R.id.tv_getinfo);
            cd_time = itemView.findViewById(R.id.cd_time);
        }
    }

}
