package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.QiangShowInfo;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.bean.requsetbean.DafenInfo;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

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
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final QiangShowInfo.DataBean.ItemsBean item, int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_phone.setText(TextUtils.isEmpty(item.getUserName())? ZeroZeroSevenUtils.phoneClose(item.getUserPhone()):item.getUserName());
        mHolder.tv_time.setText(item.getCreateTime());
        mHolder.tv_like.setText(item.getLikeCount());
        mHolder.tv_content.setText(TextUtils.isEmpty(item.getContent())?"加载失败":item.getContent());
        selectType(mHolder.tv_type,item.getPostType());
        if(!TextUtils.isEmpty(item.getAvatar())){
            Glide.with(mContext).load(item.getAvatar()).override(50,50).into(mHolder.user_icon);
        }
        mHolder.rl_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnimationone(mHolder.tv_like);
                if(item.getIsLike()==1){
                    ToastUtils.showShort("你已经点过赞了");
                    return;
                }
                likebitis(mHolder.tv_like,item);
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
    private void likebitis(final TextView tv,final QiangShowInfo.DataBean.ItemsBean item) {
        DafenInfo dafenInfo1 = new DafenInfo();
        dafenInfo1.setFunctionName("UpdatePostLike");
        DafenInfo.ParametersBean parametersBean1 = new DafenInfo.ParametersBean();
        parametersBean1.setPostId(String.valueOf(item.getId()));
        parametersBean1.setEvent("ADD");
        dafenInfo1.setParameters(parametersBean1);
        OkGoUtils okGoUtils=new OkGoUtils(mContext);
        okGoUtils.httpPostJSON(dafenInfo1,true,false);
        okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
            @Override
            public void onSuccLoad(String response) {
                        item.setIsLike(1);
                        tv.setText(String.valueOf(Integer.parseInt(item.getLikeCount())+1));
                    }
                });

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
        RelativeLayout rl_like;
        CircleImageView user_icon;
        MViewHolder(View itemView) {
            super(itemView);
            rl_like = itemView.findViewById(R.id.rl_like);
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
