package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.PushMessAgeOkInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;

import java.util.ArrayList;

/**
 * Created by GT on 2017/11/27.
 */

public class MessAgeAdapter extends BaseRecyclerAdapter<PushMessAgeOkInfo.DataBean.ListBean> {
    private ArrayList<Integer> readList;
    public MessAgeAdapter(Context context) {
        super(context);
    }
    public void setReadList(ArrayList<Integer> readList1){
        readList=readList1;
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
        for (int i = 0; i < readList.size(); i++) {
            if(item.getId()==readList.get(i)){
                mHolder.iv_messAgeStatus.setVisibility(View.GONE);
            }
            LogUtils.D("readlog",readList.get(i)+"");
        }

    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_body;
        TextView tv_time;
        ImageView iv_messAgeStatus;
        MViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_body = itemView.findViewById(R.id.tv_body);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_messAgeStatus = itemView.findViewById(R.id.iv_messAgeStatus);
        }
    }


}
