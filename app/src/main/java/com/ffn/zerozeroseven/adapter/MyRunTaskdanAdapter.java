package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.MyRunDingDanInfo;

/**
 * Created by GT on 2018/1/26.
 */

public class MyRunTaskdanAdapter extends BaseRecyclerAdapter<MyRunDingDanInfo.DataBean.ListBean> {



    public MyRunTaskdanAdapter(Context context) {
        super(context);

    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MyRunTaskdanAdapter.MViewHolder(mInflater.inflate(R.layout.item_mineruntask, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final MyRunDingDanInfo.DataBean.ListBean info, final int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_peoplephone.setText(info.getPublisherPhone());
        mHolder.tv_time.setText("已等待时间" + "8:00");
        mHolder.tv_type.setText(info.getType());
        mHolder.tv_smallmoney.setText(info.getPrice());
        mHolder.tv_getadr.setText(info.getPickupAddress());
        mHolder.tv_arradr.setText(info.getDeliverAddress());
        mHolder.tv_gettime.setText(info.getPickupTime());
        switch (info.getStatus()) {
            case 1:
                mHolder.bt_status.setText("待接单");
                mHolder.tv_action.setText("撤销");
                break;
            case 2:
                mHolder.bt_status.setText("派送中");
                mHolder.tv_action.setText("签收");
                break;
            case 3:
                mHolder.bt_status.setText("已完成");
                mHolder.tv_action.setText("重新发布");
                mHolder.bt_status.setBackgroundResource(R.drawable.condition_done);
                break;
            default:
                mHolder.bt_status.setText("其他");
                mHolder.rl_action.setVisibility(View.GONE);
                break;

        }
        mHolder.rl_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgClick.onClick(view, position);
            }
        });

    }

    private  OnItemImgClick imgClick;

    public void setOnItemImgViewClick( OnItemImgClick imgClick) {
        this.imgClick = imgClick;
    }

    public interface OnItemImgClick {
        void onClick(View view, int position);
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_gettime;
        TextView tv_time;
        TextView tv_type;
        TextView tv_getadr;
        TextView tv_arradr;
        TextView tv_goodsweight;
        TextView tv_peoplephone;
        TextView tv_runmoney;
        TextView tv_smallmoney;
        TextView tv_action;
        RelativeLayout rl_action;
        Button bt_status;

        MViewHolder(View itemView) {
            super(itemView);
            rl_action = itemView.findViewById(R.id.rl_action);
            tv_action = itemView.findViewById(R.id.tv_action);
            tv_peoplephone = itemView.findViewById(R.id.tv_peoplephone);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_smallmoney = itemView.findViewById(R.id.tv_smallmoney);
            tv_getadr = itemView.findViewById(R.id.tv_getadr);
            tv_arradr = itemView.findViewById(R.id.tv_arradr);
            tv_goodsweight = itemView.findViewById(R.id.tv_goodsweight);
            tv_runmoney = itemView.findViewById(R.id.tv_runmoney);
            tv_gettime = itemView.findViewById(R.id.tv_gettime);
            bt_status = itemView.findViewById(R.id.bt_status);

        }
    }
}
