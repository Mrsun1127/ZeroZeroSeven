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

public class LevelOneAdapter extends BaseRecyclerAdapter<NumberLevelInfo.DataBean.CategoriesBean> {
    public LevelOneAdapter(Context context) {
        super(context);
    }

    public int clickPosition = 0;

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new LevelOneAdapter.MViewHolder(mInflater.inflate(R.layout.item_level1, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, NumberLevelInfo.DataBean.CategoriesBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        if (clickPosition == position) {
            mHolder.tv_level1.setTextColor(getResource().getColor(R.color.money));
            mHolder.view.setVisibility(View.VISIBLE);
        } else {
            mHolder.tv_level1.setTextColor(getResource().getColor(R.color.black));
            mHolder.view.setVisibility(View.GONE);
        }
        mHolder.tv_level1.setText(item.getName());
    }

    public void setClickPosition(int i) {
        clickPosition = i;
        notifyDataSetChanged();
    }

    public int getClickPosition() {
        return clickPosition;
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_level1;
        View view;

        MViewHolder(View itemView) {
            super(itemView);
            tv_level1 = itemView.findViewById(R.id.tv_level1);
            view = itemView.findViewById(R.id.view);
        }
    }


}
