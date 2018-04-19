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

public class PeiSongAdapter extends BaseRecyclerAdapter<PeiSongShowInfo.DataBean.ListBean> {
    public PeiSongAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new PeiSongAdapter.MViewHolder(mInflater.inflate(R.layout.item_courier_dingdan, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final PeiSongShowInfo.DataBean.ListBean info, final int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_no.setText("订单编号: " + info.getOrderNo());
//        mHolder.tv_time.setText(info.getPickupTime().substring(11, info.getPickupTime().length()) + "前送达");
        if (!TextUtils.isEmpty(info.getPickupTime())) {
            mHolder.tv_lanjie.setVisibility(View.VISIBLE);
            mHolder.tv_lanjie.setText("揽件时间:" + info.getPickupTime().substring(11, info.getPickupTime().length()));
        } else {
            mHolder.tv_lanjie.setVisibility(View.GONE);
        }
        mHolder.tv_phone.setText(info.getReceiverPhone());

        mHolder.tv_location.setText(info.getReceiverAddress());
        switch (info.getStatus()) {
            case 1:
                mHolder.bt_qiang.setText("抢单");
                break;
            case 2:
                mHolder.bt_qiang.setText("取货中");
                break;
            case 3:
                mHolder.bt_qiang.setText("配送中");
                break;
            case 4:
                mHolder.bt_qiang.setText("已完成");
                break;
            case 5:
                mHolder.bt_qiang.setText("异常单");
                break;
        }
        mHolder.tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ImageClick1 != null) {
                    ImageClick1.onClick(view, position);
                }
            }
        });

        mHolder.bt_right.setTag(mHolder);
        mHolder.bt_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ImageClick != null) {
                    ImageClick.onClick(v, position);
                }
            }
        });
        ItemKuaidiAdapter adapter = new ItemKuaidiAdapter(mContext);
        mHolder.rc_shop.setAdapter(adapter);
        adapter.addAll(info.getDetails());
    }

    private OnItemImageClick ImageClick;

    public void setOnItemImageViewClick(OnItemImageClick ImageClick) {
        this.ImageClick = ImageClick;
    }

    public interface OnItemImageClick {
        void onClick(View view, int position);
    }


    private OnItemCallClick ImageClick1;

    public void setOnItemCallViewClick(OnItemCallClick ImageClick1) {
        this.ImageClick1 = ImageClick1;
    }

    public interface OnItemCallClick {
        void onClick(View view, int position);
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no;
        TextView tv_time;
        TextView tv_phone;
        TextView tv_lanjie;
        TextView tv_location;
        RecyclerView rc_shop;
        Button bt_right;
        Button bt_qiang;

        MViewHolder(View itemView) {
            super(itemView);
            tv_no = itemView.findViewById(R.id.tv_no);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_lanjie = itemView.findViewById(R.id.tv_lanjie);
            tv_location = itemView.findViewById(R.id.tv_location);
            rc_shop = itemView.findViewById(R.id.rc_shop);
            rc_shop.setLayoutManager(new LinearLayoutManager(mContext));
            rc_shop.addItemDecoration(new SpaceItemDecoration(5));
            bt_right = itemView.findViewById(R.id.bt_right);
            bt_qiang = itemView.findViewById(R.id.bt_qiang);
        }
    }
}
