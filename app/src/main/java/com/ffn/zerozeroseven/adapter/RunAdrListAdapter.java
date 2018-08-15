package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.RFaHuoInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class RunAdrListAdapter extends BaseRecyclerAdapter<RFaHuoInfo.DataBean.ListBean> {
    public int clickPosition=0;
    public RunAdrListAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new RunAdrListAdapter.MViewHolder(mInflater.inflate(R.layout.item_run_adr, parent,false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, RFaHuoInfo.DataBean.ListBean item, final int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_name.setText(item.getName());
        mHolder.tv_phone.setText(item.getPhone());
        mHolder.tv_adr.setText(item.getAddress());
        mHolder.rl_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ImageClick != null) {
                    ImageClick.onClick(v, position);
                }
            }
        });
    }
    public void setClickPosition(int position){
        clickPosition=position;
        notifyDataSetChanged();
    }
    private OnItemImageClick ImageClick;

    public void setOnItemImageViewClick(OnItemImageClick ImageClick) {
        this.ImageClick = ImageClick;
    }

    public interface OnItemImageClick {
        void onClick(View view, int position);
    }
    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_sex;
        TextView tv_phone;
        TextView tv_adr;
        RelativeLayout rl_edit;

        MViewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_sex=itemView.findViewById(R.id.tv_sex);
            tv_phone=itemView.findViewById(R.id.tv_phone);
            tv_adr=itemView.findViewById(R.id.tv_adr);
            rl_edit=itemView.findViewById(R.id.rl_edit);
        }
    }


}
