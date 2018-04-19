package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.SchoolLikeListInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class SchoolLikeAdapter extends BaseRecyclerAdapter<SchoolLikeListInfo.DataBean.SchoolsBean> {
    public SchoolLikeAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new SchoolLikeAdapter.MViewHolder(mInflater.inflate(R.layout.item_schoollike, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, SchoolLikeListInfo.DataBean.SchoolsBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_name.setText(item.getName());
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;

        MViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }


}
