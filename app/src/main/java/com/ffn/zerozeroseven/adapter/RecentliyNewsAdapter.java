package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RecentliyNewsInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class RecentliyNewsAdapter extends BaseRecyclerAdapter<RecentliyNewsInfo.DataBean.MessagesBean> {
    public int clickPosition = 0;

    public RecentliyNewsAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new RecentliyNewsAdapter.MViewHolder(mInflater.inflate(R.layout.item_rencentliy_news, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, RecentliyNewsInfo.DataBean.MessagesBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_fromName.setText(item.getFromUname());
        mHolder.tv_content.setText(item.getContent());
        mHolder.tv_time.setText(item.getCreateTime());
    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fromName;
        TextView tv_time;
        TextView tv_content;

        MViewHolder(View itemView) {
            super(itemView);
            tv_fromName = itemView.findViewById(R.id.tv_fromName);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }


}
