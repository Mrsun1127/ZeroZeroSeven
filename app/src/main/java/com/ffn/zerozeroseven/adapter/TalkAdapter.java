package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.BitisInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class TalkAdapter extends BaseRecyclerAdapter<BitisInfo.DataBean.ItemsBean.MessagesBean> {
    public int clickPosition = 0;

    public TalkAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new TalkAdapter.MViewHolder(mInflater.inflate(R.layout.item_talk, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, BitisInfo.DataBean.ItemsBean.MessagesBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        if (TextUtils.isEmpty(item.getToUname())) {
            mHolder.tv_fromName.setText(item.getFromUname()+":");
            mHolder.tv_center.setVisibility(View.GONE);
        } else {
            mHolder.tv_ToName.setText(item.getToUname()+":");
            mHolder.tv_fromName.setText(item.getFromUname());
            mHolder.tv_center.setVisibility(View.VISIBLE);
        }
        mHolder.tv_content.setText(item.getContent());
    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fromName;
        TextView tv_center;
        TextView tv_ToName;
        TextView tv_content;

        MViewHolder(View itemView) {
            super(itemView);
            tv_fromName = itemView.findViewById(R.id.tv_fromName);
            tv_center = itemView.findViewById(R.id.tv_center);
            tv_ToName = itemView.findViewById(R.id.tv_ToName);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }


}
