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

public class CityAdapter extends BaseRecyclerAdapter<SchoolJsonInfo.PlacesBean.ChildrenBeanX> {
    public int clickPosition=0;
    public CityAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new CityAdapter.MViewHolder(mInflater.inflate(R.layout.item_city, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, SchoolJsonInfo.PlacesBean.ChildrenBeanX item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        if(holder.getAdapterPosition()==clickPosition){
            mHolder.rl_all.setBackgroundResource(R.color.tab_color);
            mHolder.tv_city.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            mHolder.rl_all.setBackgroundResource(R.color.white);
            mHolder.tv_city.setTextColor(mContext.getResources().getColor(R.color.text_show_color));
        }
        if(item.getName().length()<=5){
            mHolder.tv_city.setText(item.getName());
        }else{
            mHolder.tv_city.setText(item.getName().substring(0,4)+"..");
        }
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
