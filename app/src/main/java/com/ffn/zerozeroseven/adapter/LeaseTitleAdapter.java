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
import com.ffn.zerozeroseven.bean.LeaseTabInfo;
import com.ffn.zerozeroseven.bean.ShopTitleInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class LeaseTitleAdapter extends BaseRecyclerAdapter<LeaseTabInfo.DataBean.CateListBean> {
    public int clickPosition = 0;

    public LeaseTitleAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new LeaseTitleAdapter.MViewHolder(mInflater.inflate(R.layout.item_scroll, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, LeaseTabInfo.DataBean.CateListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_type.setText(item.getCateName());
        if (clickPosition == position) {
            mHolder.tv_type.setTextColor(getResource().getColor(R.color.money));
        } else {
            mHolder.tv_type.setTextColor(getResource().getColor(R.color.line_color));
        }
        Glide.with(mContext).load(R.drawable.deletefour).into(mHolder.iv_product);
    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product;
        TextView tv_type;

        MViewHolder(View itemView) {
            super(itemView);
            iv_product = itemView.findViewById(R.id.iv_product);
            tv_type = itemView.findViewById(R.id.tv_type);
        }
    }


}
