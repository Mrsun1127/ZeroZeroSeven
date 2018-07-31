package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberRicalInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class NumberRicalCarAdapter extends BaseRecyclerAdapter<NumberRicalInfo.RicalInfo> {
    public NumberRicalCarAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new NumberRicalCarAdapter.MViewHolder(mInflater.inflate(R.layout.item_numberrical_car, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, NumberRicalInfo.RicalInfo item, final int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext).load(item.getImgUrl()).into(mHolder.iv_product);
        mHolder.tv_name.setText(item.getName()!=null?item.getName():"加载失败");
        mHolder.tv_configureation.setText(item.getConfiguration()!=null?item.getConfiguration():"加载失败");
        mHolder.tv_count.setText(String.valueOf(item.getCount()));
        mHolder.tv_money.setText(String.valueOf(item.getNeedsMoney()));

        if (item.isChecked()) {
            mHolder.cb_check.setChecked(true);
        } else {
            mHolder.cb_check.setChecked(false);
        }
        mHolder.rl_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (closeClick != null) {
                    closeClick.onClick(v, position);
                }
            }
        });
        mHolder.rl_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (addClick != null) {
                    addClick.onClick(v, position);
                }
            }
        });
        mHolder.cb_check.setOnClickListener(new View.OnClickListener() {
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


    private OnItemCloseClick closeClick;

    public void setOnItemCloseViewClick(OnItemCloseClick closeClick) {
        this.closeClick = closeClick;
    }

    public interface OnItemCloseClick {
        void onClick(View view, int position);
    }


    private OnItemAddClick addClick;

    public void setOnItemAddViewClick(OnItemAddClick addClick) {
        this.addClick = addClick;
    }

    public interface OnItemAddClick {
        void onClick(View view, int position);
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb_check;
        ImageView iv_product;
        TextView tv_name;
        TextView tv_configureation;
        TextView tv_money;
        TextView tv_count;
        RelativeLayout rl_close;
        RelativeLayout rl_add;

        MViewHolder(View itemView) {
            super(itemView);
            cb_check = itemView.findViewById(R.id.cb_check);
            iv_product = itemView.findViewById(R.id.iv_product);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_configureation = itemView.findViewById(R.id.tv_configureation);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_count = itemView.findViewById(R.id.tv_count);
            rl_close = itemView.findViewById(R.id.rl_close);
            rl_add = itemView.findViewById(R.id.rl_add);
        }
    }


}
