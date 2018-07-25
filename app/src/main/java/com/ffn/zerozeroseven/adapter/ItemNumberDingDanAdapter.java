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
import com.ffn.zerozeroseven.bean.MyDingDanShowInfo;
import com.ffn.zerozeroseven.bean.NumberDingDanInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class ItemNumberDingDanAdapter extends BaseRecyclerAdapter<NumberDingDanInfo.DataBean.ListBean.OrderGoodsListBean> {
    public ItemNumberDingDanAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ItemNumberDingDanAdapter.MViewHolder(mInflater.inflate(R.layout.item_numberdingdanitem, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, NumberDingDanInfo.DataBean.ListBean.OrderGoodsListBean info, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext)
                .load(info.getGoodsImage())
                .error(R.drawable.oops)
                .into(mHolder.iv_product);
        mHolder.tv_name.setText(info.getGoodsName());
        mHolder.tv_count.setText("x" + info.getGoodsCount());
        mHolder.tv_desc.setText(info.getSpecKeyName());
        mHolder.tv_price.setText(String.valueOf(info.getShopPrice()));
        mHolder.tv_oldprice.setText(String.valueOf(info.getMarketPrice()));
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product;
        TextView tv_name;
        TextView tv_desc;
        TextView tv_price;
        TextView tv_oldprice;
        TextView tv_count;

        MViewHolder(View itemView) {
            super(itemView);
            iv_product = itemView.findViewById(R.id.iv_product);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_oldprice = itemView.findViewById(R.id.tv_oldprice);
            tv_count = itemView.findViewById(R.id.tv_count);

        }
    }
}
