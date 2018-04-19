package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ShouyiOkInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class ShouyiAdapter extends BaseRecyclerAdapter<ShouyiOkInfo.DataBean.ListBean> {
    public ShouyiAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ShouyiAdapter.MViewHolder(mInflater.inflate(R.layout.item_shouyi, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, ShouyiOkInfo.DataBean.ListBean info, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_type.setText(info.getIncomeType());
        mHolder.tv_time.setText(info.getCreateTime());
        mHolder.tv_money.setText("+"+info.getIncome()+"元");
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_type;
        TextView tv_money;
        TextView tv_time;

        MViewHolder(View itemView) {
            super(itemView);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }


}
