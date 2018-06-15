package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ProductDetilsInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class ProductSinggerGoInAdapter extends BaseRecyclerAdapter<ProductDetilsInfo.DataBean.ItemBean.AllUserContributionListBean> {
    public ProductSinggerGoInAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ProductSinggerGoInAdapter.MViewHolder(mInflater.inflate(R.layout.item_goin, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, ProductDetilsInfo.DataBean.ItemBean.AllUserContributionListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;

    }

    private class MViewHolder extends RecyclerView.ViewHolder {


        MViewHolder(View itemView) {
            super(itemView);

        }
    }


}
