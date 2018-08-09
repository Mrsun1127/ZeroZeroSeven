package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.SchoolJsonInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class PopWeightAdapter extends BaseRecyclerAdapter<String> {
    public int clickPosition = -1;

    public PopWeightAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new PopWeightAdapter.MViewHolder(mInflater.inflate(R.layout.item_pop_weight, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        if (holder.getAdapterPosition() == clickPosition) {
            mHolder.rl_all.setBackgroundResource(R.color.tab_color);
            mHolder.tv_weight.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            mHolder.rl_all.setBackgroundResource(R.color.white);
            mHolder.tv_weight.setTextColor(mContext.getResources().getColor(R.color.text_show_color));
        }
        mHolder.tv_weight.setText(item);
    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    public int getClickPosition() {
        return clickPosition;
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_weight;
        RelativeLayout rl_all;

        MViewHolder(View itemView) {
            super(itemView);
            tv_weight = itemView.findViewById(R.id.tv_weight);
            rl_all = itemView.findViewById(R.id.rl_all);
        }
    }


}
