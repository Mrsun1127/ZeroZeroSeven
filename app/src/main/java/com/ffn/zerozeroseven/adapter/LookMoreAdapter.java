package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RunListRquestInfo;

/**
 * Created by GT on 2018/1/26.
 */

public class LookMoreAdapter extends BaseRecyclerAdapter<RunListRquestInfo.DataBean.ListBean> {
    public LookMoreAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new LookMoreAdapter.MViewHolder(mInflater.inflate(R.layout.item_minerun, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, RunListRquestInfo.DataBean.ListBean info, final int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_phone.setText(info.getPublisherPhone());
        mHolder.tv_time.setText("取件时间" + info.getPickupTime());
        mHolder.tv_type.setText(info.getType());
        mHolder.tv_smallmoney.setText(info.getPrice());
        mHolder.tv_getadr.setText(info.getPickupAddress());
        mHolder.tv_arradr.setText(info.getDeliverAddress());
        mHolder.tv_gettime.setText(info.getPickupTime());
        mHolder.bt_status.setText("去接单");
        mHolder.bt_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHolder.bt_status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (imgClick != null) {
                            imgClick.onClick(view, position);
                        }


                    }
                });
            }
        });
//        switch (info.getStatus()) {
//            case 1:
//                mHolder.bt_status.setText("待接单");
//                break;
//            case 2:
//                mHolder.bt_status.setText("派送中");
//                break;
//            case 3:
//                mHolder.bt_status.setText("已完成");
//                mHolder.bt_status.setBackgroundResource(R.drawable.condition_done);
//                break;
//            default:
//                mHolder.bt_status.setText("其他");
//                break;
//        }

    }
    private OnItemImgClick imgClick;

    public void setOnItemImgViewClick(OnItemImgClick imgClick) {
        this.imgClick = imgClick;
    }

    public interface OnItemImgClick {
        void onClick(View view, int position);
    }
    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_phone;
        TextView tv_time;
        TextView tv_type;
        TextView tv_smallmoney;
        TextView tv_getadr;
        TextView tv_arradr;
        TextView tv_goodsweight;
        TextView tv_money;
        TextView tv_gettime;
        Button bt_status;

        MViewHolder(View itemView) {
            super(itemView);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_smallmoney = itemView.findViewById(R.id.tv_smallmoney);
            tv_getadr = itemView.findViewById(R.id.tv_getadr);
            tv_arradr = itemView.findViewById(R.id.tv_arradr);
            tv_goodsweight = itemView.findViewById(R.id.tv_goodsweight);
            tv_money = itemView.findViewById(R.id.tv_peoplephone);
            tv_gettime = itemView.findViewById(R.id.tv_gettime);
            bt_status = itemView.findViewById(R.id.bt_status);

        }
    }
}
