package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberLevelInfo;
import com.ffn.zerozeroseven.bean.NumberListInfo;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GT on 2017/11/27.
 */

public class NumberRicalVerticalAdapter extends BaseRecyclerAdapter<NumberListInfo.DataBean.GoodsListBean.ListBean> {
    public NumberRicalVerticalAdapter(Context context) {
        super(context);
    }

    private List<String> stringList;

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
        mHolder.tv_shopmoney.setText(item.getShopPrice() + "");
        mHolder.tv_oldmoney.setText(item.getMarketPrize() + "");
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        mHolder.rc_top.setLayoutManager(linearLayoutManager);
        VerticalItemAdapter topAdapter = new VerticalItemAdapter(mContext);
        mHolder.rc_top.setAdapter(topAdapter);
        stringList = new ArrayList<>();
        stringList.add("CPU I7");
        stringList.add("2GB 独显");
        stringList.add("256G固态");
        topAdapter.addAll(stringList);

        FullyLinearLayoutManager linearLayoutManager1 = new FullyLinearLayoutManager(mContext);
        linearLayoutManager1.setOrientation(LinearLayout.HORIZONTAL);
        mHolder.rc_bot.setLayoutManager(linearLayoutManager1);
        VerticalItemBotAdapter botAdapter = new VerticalItemBotAdapter(mContext);
        mHolder.rc_bot.setAdapter(botAdapter);
        botAdapter.addAll(stringList);
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product;
        TextView tv_name;
        TextView tv_oldmoney;
        TextView tv_shopmoney;
        RecyclerView rc_bot;
        RecyclerView rc_top;

        MViewHolder(View itemView) {
            super(itemView);
            iv_product = itemView.findViewById(R.id.iv_product);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_oldmoney = itemView.findViewById(R.id.tv_oldmoney);
            tv_shopmoney = itemView.findViewById(R.id.tv_shopmoney);
            rc_top = itemView.findViewById(R.id.rc_top);
            rc_bot = itemView.findViewById(R.id.rc_bot);
        }
    }


}
