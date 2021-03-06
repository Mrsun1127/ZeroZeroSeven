package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.HistoryInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class HistorySwipeAdapter extends BaseRecyclerAdapter<HistoryInfo.DataBean.PostsBean> {
    public HistorySwipeAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new HistorySwipeAdapter.MViewHolder(mInflater.inflate(R.layout.item_historytalk, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, HistoryInfo.DataBean.PostsBean info, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_time.setText(info.getCreateTime());
        mHolder.tv_title.setText(info.getContent());
        switch (info.getStatus()) {
            case 0:
                mHolder.tv_type.setText(info.getPostType() + "(未审核)");
                break;
            case 1:
                mHolder.tv_type.setText(info.getPostType() + "(已审核)");
                break;
        }

    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_title;
        TextView tv_type;
        TextView tv_content;
        TextView tv_count;


        MViewHolder(View view) {
            super(view);
            tv_time = view.findViewById(R.id.tv_time);
            tv_title = view.findViewById(R.id.tv_title);
            tv_type = view.findViewById(R.id.tv_type);
            tv_content = view.findViewById(R.id.tv_content);
            tv_count = view.findViewById(R.id.tv_count);
        }
    }
}
