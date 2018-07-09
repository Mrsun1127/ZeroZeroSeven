package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.SelectBean;
import com.ffn.zerozeroseven.bean.ZhongJiangListInfo;
import com.ffn.zerozeroseven.ui.NumberDrawBackActivity;


/**
 * Created by GT on 2017/11/27.
 */

public class NumberTuiKuanAdapter extends BaseRecyclerAdapter<SelectBean> {

    public NumberTuiKuanAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new NumberTuiKuanAdapter.MViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_numberical_tui_reason, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, SelectBean item, int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_name.setText(item.getName());
        if (item.isCheck()) {
            mHolder.cb.setChecked(true);
        } else {
            mHolder.cb.setChecked(false);
        }
    }


    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        CheckBox cb;

        MViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            cb = itemView.findViewById(R.id.cb);
        }
    }


}
