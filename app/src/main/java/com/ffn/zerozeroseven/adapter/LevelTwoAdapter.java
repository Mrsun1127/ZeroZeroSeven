package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberLevelInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class LevelTwoAdapter extends BaseRecyclerAdapter<NumberLevelInfo.DataBean.CategoriesBean> {
    public LevelTwoAdapter(Context context) {
        super(context);
    }

    public int clickPosition = 0;

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new LevelTwoAdapter.MViewHolder(mInflater.inflate(R.layout.item_scroll, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, NumberLevelInfo.DataBean.CategoriesBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_type.setText(item.getName());
    }

    public void setClickPosition(int i) {
        clickPosition = i;
        notifyDataSetChanged();
    }

    public int getClickPosition() {
        return clickPosition;
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
