package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RunListRquestInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class RunListAdapter extends BaseRecyclerAdapter<RunListRquestInfo.DataBean.ListBean> {
    public RunListAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new RunListAdapter.MViewHolder(mInflater.inflate(R.layout.item_runlist, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final RunListRquestInfo.DataBean.ListBean item, final int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_phone.setText(item.getPublisherPhone());
        if (!TextUtils.isEmpty(item.getType())) {
            mHolder.tv_type.setText(item.getType());
            switch (item.getType()) {
                case "快递":
                    mHolder.iv_left.setBackgroundResource(R.drawable.xpackage);
                    break;
                case "文件":
                    mHolder.iv_left.setBackgroundResource(R.drawable.file);
                    break;
                default:
                    mHolder.iv_left.setBackgroundResource(R.drawable.xelse);
                    break;
            }
        } else {
            mHolder.tv_type.setText("其他");
            mHolder.iv_left.setBackgroundResource(R.drawable.xelse);
        }
        mHolder.iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgClick != null) {
                    imgClick.onClick(view, position);
                }


            }
        });

    }
    private OnItemImgClick imgClick;

    public void setOnItemImgViewClick(OnItemImgClick imgClick) {
        this.imgClick = imgClick;
    }

    public interface OnItemImgClick {
        void onClick(View view, int position);
    }
    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_phone;
        TextView tv_type;
        ImageView iv_left;
        ImageView iv_right;

        MViewHolder(View itemView) {
            super(itemView);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_type = itemView.findViewById(R.id.tv_type);
            iv_left = itemView.findViewById(R.id.iv_left);
            iv_right = itemView.findViewById(R.id.iv_right);
        }
    }


}
