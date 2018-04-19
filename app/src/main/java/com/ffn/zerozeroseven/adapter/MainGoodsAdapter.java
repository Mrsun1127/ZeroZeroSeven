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
import com.ffn.zerozeroseven.bean.requsetbean.OftenShowInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class MainGoodsAdapter extends BaseRecyclerAdapter<OftenShowInfo.DataBean.GoodsBean> {
    public MainGoodsAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MainGoodsAdapter.MViewHolder(mInflater.inflate(R.layout.item_oftengoods, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, OftenShowInfo.DataBean.GoodsBean info, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext)
                .load(info.getThumbnail())
                .error(R.drawable.oops)
                .into(mHolder.imageView);
        mHolder.tv_name.setText(info.getGoodsName());
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name;
        MViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.iv_icon);
            tv_name=itemView.findViewById(R.id.tv_name);

        }
    }
}
