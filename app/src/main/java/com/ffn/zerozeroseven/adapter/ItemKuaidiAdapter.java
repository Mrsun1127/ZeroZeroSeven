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
import com.ffn.zerozeroseven.bean.PeiSongShowInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

/**
 * Created by GT on 2017/11/27.
 */

public class ItemKuaidiAdapter extends BaseRecyclerAdapter<PeiSongShowInfo.DataBean.ListBean.DetailsBean> {
    public ItemKuaidiAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ItemKuaidiAdapter.MViewHolder(mInflater.inflate(R.layout.item_dingdan_shop, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, PeiSongShowInfo.DataBean.ListBean.DetailsBean info, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext)
                .load(info.getGoodsImage())
                .error(R.drawable.oops)
                .into(mHolder.iv_shop);
        mHolder.iv_add.setVisibility(View.GONE);
        mHolder.iv_close.setVisibility(View.GONE);
        mHolder.tv_shopname.setText(info.getGoodsName());
        mHolder.tv_shopcount.setText("x" + info.getGoodsCount());
        mHolder.tv_money.setText("¥" + ZeroZeroSevenUtils.reactMoney(info.getGoodsCount() * info.getGoodsPrice()));
        mHolder.rl_delete.setVisibility(View.GONE);
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_shop;
        ImageView iv_close;
        ImageView iv_add;
        TextView tv_shopname;
        TextView tv_shopcount;
        TextView tv_money;
        RelativeLayout rl_delete;

        MViewHolder(View itemView) {
            super(itemView);
            iv_shop = itemView.findViewById(R.id.iv_shop);
            iv_close = itemView.findViewById(R.id.iv_close);
            iv_add = itemView.findViewById(R.id.iv_add);
            tv_shopname = itemView.findViewById(R.id.tv_shopname);
            tv_shopcount = itemView.findViewById(R.id.tv_shopcount);
            tv_money = itemView.findViewById(R.id.tv_peoplephone);
            rl_delete = itemView.findViewById(R.id.rl_delete);

        }
    }
}
