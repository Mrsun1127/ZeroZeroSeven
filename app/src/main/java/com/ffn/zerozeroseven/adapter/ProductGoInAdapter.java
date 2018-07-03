package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.ProductDetilsInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by GT on 2017/11/27.
 */

public class ProductGoInAdapter extends BaseRecyclerAdapter<ProductDetilsInfo.DataBean.AllUserContributionListBean> {
    public ProductGoInAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ProductGoInAdapter.MViewHolder(mInflater.inflate(R.layout.item_goin, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, ProductDetilsInfo.DataBean.AllUserContributionListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        if(!TextUtils.isEmpty(item.getUserAvatar())){
            Glide.with(mContext)
                    .load(item.getUserAvatar())
                    .into(mHolder.iv_icon);

        }
        mHolder.tv_phone.setText(ZeroZeroSevenUtils.phoneClose(item.getUserPhone()));
        mHolder.tv_time.setText(item.getCreateTime());
        mHolder.tv_count.setText("贡献了"+item.getPoint()+"积分");
        if(item.getStartPoint().equals(item.getEndPoint())){
            mHolder.tv_number.setText("积分号码 :"+item.getStartPoint());
        }else{
            mHolder.tv_number.setText("积分号码 :"+item.getStartPoint()+"--"+item.getEndPoint());
        }
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_icon;
        TextView tv_phone;
        TextView tv_count;
        TextView tv_number;
        TextView tv_time;
        MViewHolder(View itemView) {
            super(itemView);
            iv_icon=itemView.findViewById(R.id.iv_icon);
            tv_phone=itemView.findViewById(R.id.tv_phone);
            tv_count=itemView.findViewById(R.id.tv_count);
            tv_number=itemView.findViewById(R.id.tv_number);
            tv_time=itemView.findViewById(R.id.tv_time);
        }
    }


}
