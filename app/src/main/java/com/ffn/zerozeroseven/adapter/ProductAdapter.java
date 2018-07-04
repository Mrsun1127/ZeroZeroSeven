package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.SchoolJsonInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class ProductAdapter extends BaseRecyclerAdapter<String> {
    public int clickPosition=0;
    public ProductAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ProductAdapter.MViewHolder(mInflater.inflate(R.layout.item_issue, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        if(holder.getAdapterPosition()==clickPosition){
            mHolder.rl_all.setBackgroundResource(R.color.tab_under_line);
            mHolder.tv_city.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            mHolder.rl_all.setBackgroundResource(R.color.unselect);
            mHolder.tv_city.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        mHolder.tv_city.setText("第"+item+"期");
    }
    public void setClickPosition(int position){
        clickPosition=position;
        notifyDataSetChanged();
    }
    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_city;
        RelativeLayout rl_all;

        MViewHolder(View itemView) {
            super(itemView);
            tv_city = itemView.findViewById(R.id.tv_city);
            rl_all = itemView.findViewById(R.id.rl_all);
        }
    }


}
