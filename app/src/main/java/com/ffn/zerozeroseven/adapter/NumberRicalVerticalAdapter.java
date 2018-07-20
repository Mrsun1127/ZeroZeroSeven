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
import com.ffn.zerozeroseven.bean.NumberLevelInfo;
import com.ffn.zerozeroseven.bean.NumberListInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class NumberRicalVerticalAdapter extends BaseRecyclerAdapter<NumberListInfo.DataBean.GoodsListBean.ListBean> {
    public NumberRicalVerticalAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new NumberRicalVerticalAdapter.MViewHolder(mInflater.inflate(R.layout.item_numbericalvertical, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, NumberListInfo.DataBean.GoodsListBean.ListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_name.setText(item.getGoodsName());
        Glide.with(mContext)
                .load(item.getGoodsImg())
                .into(mHolder.iv_product);
        mHolder.tv_shopmoney.setText(item.getShopPrice()+"");
        mHolder.tv_oldmoney.setText(item.getMarketPrize()+"");
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product;
        TextView tv_name;
        TextView tv_oldmoney;
        TextView tv_shopmoney;

        MViewHolder(View itemView) {
            super(itemView);
            iv_product = itemView.findViewById(R.id.iv_product);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_oldmoney = itemView.findViewById(R.id.tv_oldmoney);
            tv_shopmoney = itemView.findViewById(R.id.tv_shopmoney);
        }
    }


}
