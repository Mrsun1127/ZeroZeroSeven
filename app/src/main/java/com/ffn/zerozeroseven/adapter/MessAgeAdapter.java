package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.PushMessAgeOkInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class MessAgeAdapter extends BaseRecyclerAdapter<PushMessAgeOkInfo.DataBean.ListBean> {
    public MessAgeAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MessAgeAdapter.MViewHolder(mInflater.inflate(R.layout.item_message, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, PushMessAgeOkInfo.DataBean.ListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_title.setText(item.getTitle());
        mHolder.tv_body.setText(item.getSummary());
        mHolder.tv_time.setText(item.getCreateTime());
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_body;
        TextView tv_time;

        MViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_body = itemView.findViewById(R.id.tv_body);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }


}
