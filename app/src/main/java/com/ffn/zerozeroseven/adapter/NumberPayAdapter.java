package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;
import com.ffn.zerozeroseven.bean.SchoolJsonInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class NumberPayAdapter extends BaseRecyclerAdapter<NumberRicalInfo.RicalInfo> {
    public NumberPayAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new NumberPayAdapter.MViewHolder(mInflater.inflate(R.layout.item_numbericaldingdan, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, NumberRicalInfo.RicalInfo item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext).load(item.getImgUrl()).into(mHolder.iv_product);
        mHolder.tv_name.setText(item.getName());
        mHolder.tv_desigh.setText(item.getConfiguration());
        mHolder.tv_count.setText("x" + item.getCount());
        mHolder.tv_money.setText(String.valueOf(item.getNeedsMoney()));

    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_desigh;
        TextView tv_money;
        TextView tv_count;
        ImageView iv_product;

        MViewHolder(View itemView) {
            super(itemView);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_count = itemView.findViewById(R.id.tv_count);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_desigh = itemView.findViewById(R.id.tv_desigh);
            iv_product = itemView.findViewById(R.id.iv_product);
        }
    }


}
