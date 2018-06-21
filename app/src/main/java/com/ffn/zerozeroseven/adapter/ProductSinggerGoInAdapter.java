package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ProductDetilsInfo;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by GT on 2017/11/27.
 */

public class ProductSinggerGoInAdapter extends BaseRecyclerAdapter<ProductDetilsInfo.DataBean.UserContributionListBean> {
    public ProductSinggerGoInAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ProductSinggerGoInAdapter.MViewHolder(mInflater.inflate(R.layout.item_goin, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, ProductDetilsInfo.DataBean.UserContributionListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;

    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_icon;
        TextView tv_phone;
        TextView tv_count;
        TextView tv_number;
        TextView tv_time;

        MViewHolder(View itemView) {
            super(itemView);
            iv_icon=itemView.findViewById(R.id.iv_icon);
            tv_phone=itemView.findViewById(R.id.tv_phone);
            tv_count=itemView.findViewById(R.id.tv_count);
            tv_number=itemView.findViewById(R.id.tv_number);
            tv_time=itemView.findViewById(R.id.tv_time);
        }
    }


}
