package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.BitisInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by GT on 2017/11/27.
 */

public class BitisNewAdapter extends BaseRecyclerAdapter<BitisInfo.DataBean.ItemsBean> {
    public int clickPosition = 0;

    public BitisNewAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new BitisNewAdapter.MViewHolder(mInflater.inflate(R.layout.item_bitis_new, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, BitisInfo.DataBean.ItemsBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext).load(item.getAvatar()).override(60, 60).into(mHolder.user_icon);
        mHolder.tv_phone.setText(TextUtils.isEmpty(item.getUserName()) ? ZeroZeroSevenUtils.phoneClose(item.getUserPhone()) : item.getUserName());
        mHolder.tv_time.setText(item.getCreateTime());
        mHolder.tv_like.setText(String.valueOf(item.getLikeCount()));
        mHolder.tv_content.setText(TextUtils.isEmpty(item.getContent()) ? "加载失败" : item.getContent());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        mHolder.rc_photo.setLayoutManager(gridLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = mHolder.rc_photo.getItemDecorationAt(0);
        if (itemDecoration == null) {
            mHolder.rc_photo.addItemDecoration(new GridSpacingItemDecoration(3, 20, false));
        }
        BitisImageAdapter bitisImageAdapter = new BitisImageAdapter(mContext);
        mHolder.rc_photo.setAdapter(bitisImageAdapter);
        if (item.getImages() != null && item.getImages().size() > 0) {
            bitisImageAdapter.addAll(item.getImages());
        }

    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        CircleImageView user_icon;
        TextView tv_phone;
        TextView tv_time;
        TextView tv_content;
        TextView tv_like;
        RecyclerView rc_photo;
        RelativeLayout rl_talk;
        RelativeLayout rl_like;
        RelativeLayout rl_share;

        MViewHolder(View itemView) {
            super(itemView);
            user_icon = itemView.findViewById(R.id.user_icon);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            rc_photo = itemView.findViewById(R.id.rc_photo);
            rl_talk = itemView.findViewById(R.id.rl_talk);
            rl_like = itemView.findViewById(R.id.rl_like);
            rl_share = itemView.findViewById(R.id.rl_share);
        }
    }


}
