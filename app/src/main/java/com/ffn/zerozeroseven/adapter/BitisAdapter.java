package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.QiangShowInfo;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by GT on 2017/11/27.
 */

public class BitisAdapter extends BaseRecyclerAdapter<QiangShowInfo.DataBean.ItemsBean> {
    public BitisAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new BitisAdapter.MViewHolder(mInflater.inflate(R.layout.item_bitis, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, QiangShowInfo.DataBean.ItemsBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_phone.setText(TextUtils.isEmpty(item.getUserName())?"该用户还未起名字":item.getUserName());
        mHolder.tv_time.setText(item.getCreateTime());
        mHolder.tv_like.setText(item.getLikeCount());
        mHolder.tv_content.setText(TextUtils.isEmpty(item.getContent())?"加载失败":item.getContent());
        selectType(mHolder.tv_type,item.getPostType());
    }
    private void selectType(TextView view,String type){
        switch (type){
            case "01":
                view.setText("表白帖");
                break;
            case "02":
                view.setText("技术帖");
                break;
            case "03":
                view.setText("寻物帖");
                break;
            case "04":
                view.setText("交友帖");
                break;
        }
    }
    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_like;
        TextView tv_share;
        TextView tv_content;
        TextView tv_phone;
        TextView tv_type;
        TextView tv_time;
        CircleImageView user_icon;
        MViewHolder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_share = itemView.findViewById(R.id.tv_share);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            user_icon = itemView.findViewById(R.id.user_icon);
        }
    }


}
