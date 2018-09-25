package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.BitisInfo;
import com.ffn.zerozeroseven.bean.QiangShowInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DafenInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.AllItemDecoration;
import com.ffn.zerozeroseven.view.CommentDialog;
import com.ffn.zerozeroseven.view.GridSpacingItemDecoration;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

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
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final BitisInfo.DataBean.ItemsBean item, int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext).load(item.getAvatar()).override(60, 60).into(mHolder.user_icon);
        mHolder.tv_phone.setText(TextUtils.isEmpty(item.getUserName()) ? ZeroZeroSevenUtils.phoneClose(item.getUserPhone()) : item.getUserName());
        mHolder.tv_time.setText(item.getCreateTime());
        mHolder.tv_like.setText(String.valueOf(item.getLikeCount()));
        mHolder.tv_talk.setText(String.valueOf(item.getMessages() == null ? 0 : item.getMessages().size()));
        mHolder.tv_content.setText(TextUtils.isEmpty(item.getContent()) ? "加载失败" : item.getContent());
        if (item.getIsLike() == 1) {
            Glide.with(mContext).load(R.drawable.bit_like).override(50, 50).into(mHolder.iv_like);
            mHolder.tv_like.setTextColor(getResource().getColor(R.color.money));
        } else {
            Glide.with(mContext).load(R.drawable.bt_like_nor).override(50, 50).into(mHolder.iv_like);
            mHolder.tv_like.setTextColor(getResource().getColor(R.color.line6));
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        mHolder.rc_photo.setLayoutManager(gridLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = mHolder.rc_photo.getItemDecorationAt(0);
        if (itemDecoration == null) {
            mHolder.rc_photo.addItemDecoration(new GridSpacingItemDecoration(3, 20, false));
        }
        BitisImageAdapter bitisImageAdapter = new BitisImageAdapter(mContext);
        mHolder.rc_photo.setAdapter(bitisImageAdapter);
        if (item.getImages() != null && item.getImages().size() > 0) {
            bitisImageAdapter.cleanDates();
            bitisImageAdapter.addAll(item.getImages());
        }
        mHolder.rc_talk.setLayoutManager(new LinearLayoutManager(mContext));
        mHolder.rc_talk.addItemDecoration(new AllItemDecoration(0, 2));
        TalkAdapter talkAdapter = new TalkAdapter(mContext);
        mHolder.rc_talk.setAdapter(talkAdapter);
        talkAdapter.cleanDates();
        talkAdapter.addAll(item.getMessages());
        mHolder.ll_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnimationone(mHolder.tv_like);
                if (item.getIsLike() == 1) {
                    ToastUtils.showShort("你已经点过赞了");
                    return;
                }
                likebitis(mHolder.tv_like, mHolder.iv_like, item);
            }
        });
        final CommentDialog commentDialog = new CommentDialog(mContext);
        commentDialog.setOnCommitListener(new CommentDialog.OnCommitListener() {
            @Override
            public void onCommit(EditText et, View v) {
                commentDialog.dismiss();
            }
        });
        commentDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                    commentDialog.cancel();
                return false;
            }
        });


        mHolder.ll_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentDialog.show();
            }
        });
    }

    private void likebitis(final TextView tv, final ImageView iv, final BitisInfo.DataBean.ItemsBean item) {
        DafenInfo dafenInfo1 = new DafenInfo();
        dafenInfo1.setFunctionName("UpdatePostLike");
        DafenInfo.ParametersBean parametersBean1 = new DafenInfo.ParametersBean();
        parametersBean1.setPostId(String.valueOf(item.getId()));
        parametersBean1.setEvent("ADD");
        dafenInfo1.setParameters(parametersBean1);
        OkGoUtils okGoUtils = new OkGoUtils(mContext);
        okGoUtils.httpPostJSON(dafenInfo1, true, false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                item.setIsLike(1);
                tv.setText(String.valueOf(item.getLikeCount() + 1));
                tv.setTextColor(getResource().getColor(R.color.money));
                Glide.with(mContext).load(R.drawable.bit_like).override(50, 50).into(iv);
            }
        });

    }

    public void setAnimationone(final View convertView) {
        ViewHelper.setScaleX(convertView, 2f);
        ViewHelper.setScaleY(convertView, 2f);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewPropertyAnimator.animate(convertView).scaleX(1f).setDuration(500).setInterpolator(new OvershootInterpolator());
                ViewPropertyAnimator.animate(convertView).scaleY(1f).setDuration(500).setInterpolator(new OvershootInterpolator());
            }
        }, 50);

    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        CircleImageView user_icon;
        TextView tv_talk;
        TextView tv_phone;
        TextView tv_time;
        TextView tv_content;
        TextView tv_like;
        ImageView iv_like;
        RecyclerView rc_photo;
        RecyclerView rc_talk;
        LinearLayout ll_talk;
        LinearLayout ll_like;
        LinearLayout ll_share;

        MViewHolder(View itemView) {
            super(itemView);
            rc_talk = itemView.findViewById(R.id.rc_talk);
            tv_talk = itemView.findViewById(R.id.tv_talk);
            iv_like = itemView.findViewById(R.id.iv_like);
            user_icon = itemView.findViewById(R.id.user_icon);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            rc_photo = itemView.findViewById(R.id.rc_photo);
            ll_talk = itemView.findViewById(R.id.ll_talk);
            ll_like = itemView.findViewById(R.id.ll_like);
            ll_share = itemView.findViewById(R.id.ll_share);
        }
    }


}
