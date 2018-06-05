package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerTallAniAdapter;
import com.ffn.zerozeroseven.bean.UserLikeInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

/**
 * Created by GT on 2017/11/27.
 */

public class UserLikeAdapter extends BaseRecyclerTallAniAdapter<UserLikeInfo.DataBean.PostsBean> {
    public UserLikeAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new UserLikeAdapter.MViewHolder(mInflater.inflate(R.layout.item_userlike, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, UserLikeInfo.DataBean.PostsBean info, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        String content="";
        if(!TextUtils.isEmpty(info.getContent())){
            content=ZeroZeroSevenUtils.replaceBlank(info.getContent());
        }else{
            content=ZeroZeroSevenUtils.replaceBlank(info.getTitle());
        }
        if(content.length()>8){
            mHolder.tv_content.setText(info.getTitle()+" "+content.substring(0,8)+"...");
        }else{
            mHolder.tv_content.setText(info.getTitle()+" "+content);
        }


    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
        TextView tv_name;


        MViewHolder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_name = itemView.findViewById(R.id.tv_name);

        }
    }
}
