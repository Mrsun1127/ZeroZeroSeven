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
import com.ffn.zerozeroseven.bean.DingDanDetlsInfo;
import com.ffn.zerozeroseven.bean.LeaseBodyInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class LeaseDingDanDetisAdapter extends BaseRecyclerAdapter<LeaseBodyInfo.DataBean.OrderGoodsListBean> {
    public LeaseDingDanDetisAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new LeaseDingDanDetisAdapter.MViewHolder(mInflater.inflate(R.layout.item_dingdan_shop, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, LeaseBodyInfo.DataBean.OrderGoodsListBean info, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.rl_delete.setVisibility(View.GONE);
        Glide.with(mContext)
                .load(info.getGoodsThumb())
                .error(R.drawable.oops)
                .override(70, 45)
                .into(mHolder.iv_shop);
        mHolder.iv_add.setVisibility(View.GONE);
        mHolder.iv_close.setVisibility(View.GONE);
        mHolder.tv_shopname.setText(info.getGoodsName());
        mHolder.tv_shopcount.setText("x" + info.getGoodsCount());
        mHolder.tv_money.setText("¥" + info.getGoodsCount() * info.getGoodsPrice());
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
            rl_delete = itemView.findViewById(R.id.rl_delete);
            iv_shop = itemView.findViewById(R.id.iv_shop);
            iv_close = itemView.findViewById(R.id.iv_close);
            iv_add = itemView.findViewById(R.id.iv_add);
            tv_shopname = itemView.findViewById(R.id.tv_shopname);
            tv_shopcount = itemView.findViewById(R.id.tv_shopcount);
            tv_money = itemView.findViewById(R.id.tv_peoplephone);

        }
    }
}
