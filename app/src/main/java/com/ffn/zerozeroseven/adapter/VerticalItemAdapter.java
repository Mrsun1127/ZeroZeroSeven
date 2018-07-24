package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;

/**
 * Created by GT on 2017/11/27.
 */

public class VerticalItemAdapter extends BaseRecyclerAdapter<String> {
    public VerticalItemAdapter(Context context) {
        super(context);
    }


    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new VerticalItemAdapter.MViewHolder(mInflater.inflate(R.layout.item_numberical_sign_top, null));
    }


    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_tv.setText(item);

    }


    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tv;

        MViewHolder(View itemView) {
            super(itemView);
            tv_tv = itemView.findViewById(R.id.tv_tv);
        }
    }

}
