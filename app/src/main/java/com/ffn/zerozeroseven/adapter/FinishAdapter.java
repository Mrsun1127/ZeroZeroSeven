package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.PeiSongShowInfo;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;

/**
 * Created by GT on 2017/11/27.
 */

public class FinishAdapter extends BaseRecyclerAdapter<PeiSongShowInfo.DataBean.ListBean> {
    public FinishAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new FinishAdapter.MViewHolder(mInflater.inflate(R.layout.item_finish_dingdan, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, PeiSongShowInfo.DataBean.ListBean info, final int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_no.setText("订单编号: "+info.getOrderNo());
        mHolder.tv_phone.setText(info.getReceiverPhone());
        mHolder.tv_location.setText(info.getReceiverAddress());
        if(!TextUtils.isEmpty(info.getPickupTime())){
            mHolder.tv_createtime.setVisibility(View.VISIBLE);
            mHolder.tv_createtime.setText("揽件时间："+info.getPickupTime());
        }else{
            mHolder.tv_createtime.setVisibility(View.GONE);
        }
        ItemKuaidiAdapter adapter=new ItemKuaidiAdapter(mContext);
        mHolder.rc_shop.setAdapter(adapter);
        adapter.addAll(info.getDetails());
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no;
        TextView tv_time;
        TextView tv_phone;
        TextView tv_location;
        RecyclerView rc_shop;
        Button bt_qiang;
        TextView tv_createtime;
        MViewHolder(View itemView) {
            super(itemView);
            tv_no=itemView.findViewById(R.id.tv_no);
            tv_createtime=itemView.findViewById(R.id.tv_createtime);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_phone=itemView.findViewById(R.id.tv_phone);
            tv_location=itemView.findViewById(R.id.tv_location);
            rc_shop=itemView.findViewById(R.id.rc_shop);
            rc_shop.setLayoutManager(new LinearLayoutManager(mContext));
            rc_shop.addItemDecoration(new SpaceItemDecoration(5));
            bt_qiang=itemView.findViewById(R.id.bt_qiang);
        }
    }
}
