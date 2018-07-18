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

import java.io.File;

/**
 * Created by GT on 2017/11/27.
 */

public class JumpotoScrollAdapter extends BaseRecyclerAdapter<String> {
    public JumpotoScrollAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new JumpotoScrollAdapter.MViewHolder(mInflater.inflate(R.layout.item_jumphoto, null,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        File file = new File(item);
        Glide.with(mContext)
                .load(file)
                .into(mHolder.iv_photo);

    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_photo;

        MViewHolder(View itemView) {
            super(itemView);
            iv_photo = itemView.findViewById(R.id.iv_photo);
        }
    }


}
