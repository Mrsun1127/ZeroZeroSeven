package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberDetlsInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class TuiKuanItemAdapter extends BaseRecyclerAdapter<NumberDetlsInfo.DataBean.OrderGoodsListBean> {
    public TuiKuanItemAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new TuiKuanItemAdapter.MViewHolder(mInflater.inflate(R.layout.item_tuikuan, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, NumberDetlsInfo.DataBean.OrderGoodsListBean info, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext)
                .load(info.getGoodsImage())
                .error(R.drawable.oops)
                .into(mHolder.iv_product);
        mHolder.tv_name.setText(info.getGoodsName());
        mHolder.tv_desc.setText(info.getSpecKeyName());
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product;
        TextView tv_name;
        TextView tv_desc;

        MViewHolder(View itemView) {
            super(itemView);
            iv_product = itemView.findViewById(R.id.iv_product);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_desc = itemView.findViewById(R.id.tv_desc);

        }
    }
}
