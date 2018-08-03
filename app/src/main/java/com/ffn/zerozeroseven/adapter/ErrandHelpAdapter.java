package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RunTypeInfo;
import com.ffn.zerozeroseven.bean.SchoolJsonInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class ErrandHelpAdapter extends BaseRecyclerAdapter<RunTypeInfo.PlacesBean> {
    public int clickPosition = 0;

    public ErrandHelpAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ErrandHelpAdapter.MViewHolder(mInflater.inflate(R.layout.item_type, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, RunTypeInfo.PlacesBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;

        if (holder.getAdapterPosition() == clickPosition) {
            Glide.with(mContext).load(item.getDrawable()).into(mHolder.iv_type);
        } else {
            Glide.with(mContext).load(item.getDrawablenor()).into(mHolder.iv_type);
        }
        mHolder.tv_title.setText(item.getTitle());
    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_type;
        TextView tv_title;

        MViewHolder(View itemView) {
            super(itemView);
            iv_type = itemView.findViewById(R.id.iv_type);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }


}
