package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;

/**
 * Created by GT on 2017/11/27.
 */

public class DriverOneAdapter extends BaseRecyclerAdapter<String> {
    public int clickPosition = 0;

    public DriverOneAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new DriverOneAdapter.MViewHolder(mInflater.inflate(R.layout.item_driver_one, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;

    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {


        MViewHolder(View itemView) {
            super(itemView);

        }
    }


}
