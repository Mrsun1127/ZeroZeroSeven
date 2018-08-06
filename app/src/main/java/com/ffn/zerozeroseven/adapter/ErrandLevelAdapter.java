package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.SchoolJsonInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class ErrandLevelAdapter extends BaseRecyclerAdapter<String> {
    public ErrandLevelAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ErrandLevelAdapter.MViewHolder(mInflater.inflate(R.layout.item_errand_level, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
    }

    private class MViewHolder extends RecyclerView.ViewHolder {

        MViewHolder(View itemView) {
            super(itemView);
        }
    }


}
