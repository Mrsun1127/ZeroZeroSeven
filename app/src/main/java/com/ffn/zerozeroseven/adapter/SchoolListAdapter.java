package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.SchoolListInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class SchoolListAdapter extends BaseRecyclerAdapter<SchoolListInfo.DataBean.SchoolsBean> {
    public int clickPosition=0;
    public SchoolListAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new SchoolListAdapter.MViewHolder(mInflater.inflate(R.layout.item_school, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, SchoolListInfo.DataBean.SchoolsBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        if(holder.getAdapterPosition()==clickPosition){
            mHolder.rl_all.setBackgroundResource(R.color.tab_color);
            mHolder.tv_name.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            mHolder.rl_all.setBackgroundResource(R.color.white);
            mHolder.tv_name.setTextColor(mContext.getResources().getColor(R.color.text_show_color));
        }
        if (item.getFullName().length() > 11) {
            mHolder.tv_name.setText(item.getName().substring(0, 10) + "..");
        } else {
            mHolder.tv_name.setText(item.getName());
        }
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        RelativeLayout rl_all;

        MViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            rl_all = itemView.findViewById(R.id.rl_all);
        }
    }
    public void setClickPosition(int position){
        clickPosition=position;
        notifyDataSetChanged();
    }

}
