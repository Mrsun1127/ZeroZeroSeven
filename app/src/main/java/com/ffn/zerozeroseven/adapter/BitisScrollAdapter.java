package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;

import java.io.File;

/**
 * Created by GT on 2017/11/27.
 */

public class BitisScrollAdapter extends BaseRecyclerAdapter<String> {
    public BitisScrollAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new BitisScrollAdapter.MViewHolder(mInflater.inflate(R.layout.item_jumphoto, null, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, final int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        File file = new File(item);
        Glide.with(mContext)
                .load(file)
                .override(100, 100)
                .into(mHolder.iv_photo);
        mHolder.rl_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (closeClick != null) {
                    closeClick.onClick(view, position);
                }
            }
        });
    }

    private OnItemCloseClick closeClick;

    public void setOnItemCloseViewClick(OnItemCloseClick closeClick) {
        this.closeClick = closeClick;
    }

    public interface OnItemCloseClick {
        void onClick(View view, int position);
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_photo;
        RelativeLayout rl_delete;

        MViewHolder(View itemView) {
            super(itemView);
            rl_delete = itemView.findViewById(R.id.rl_delete);
            iv_photo = itemView.findViewById(R.id.iv_photo);
        }
    }


}
