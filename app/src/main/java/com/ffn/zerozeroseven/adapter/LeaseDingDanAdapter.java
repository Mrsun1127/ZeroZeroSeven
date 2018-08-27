package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.LeaseDingDanListINfo;
import com.ffn.zerozeroseven.bean.MyDingDanShowInfo;
import com.ffn.zerozeroseven.ui.DingDanBobyActivity;
import com.ffn.zerozeroseven.ui.LeaseDingDanBobyActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;

/**
 * Created by GT on 2017/11/27.
 */

public class LeaseDingDanAdapter extends BaseRecyclerAdapter<LeaseDingDanListINfo.DataBean.ListBean> {

    private ItemLeaseDingDanAdapter adapter;

    public LeaseDingDanAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new LeaseDingDanAdapter.MViewHolder(mInflater.inflate(R.layout.item_dingdan, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final LeaseDingDanListINfo.DataBean.ListBean info, final int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_time.setText("下单时间: " + info.getCreateTime());
        mHolder.tv_allmoney.setText("共" + info.getOrderGoodsList().size() + "个商品，合计¥: " + (info.getGoodsPrice()));
        //订单状态：0=待确认,1=（后台）已确认,2=已收货,3=已取消，4=已完成，5=已作废
        String s = "";
        switch (info.getOrderStatus()) {
            case 0:
                s = "待确认";
                break;
            case 1:
                s = "已确认";
                break;
            case 2:
                s = "已收货";
                break;
            case 3:
                s = "已取消";
                break;
            case 4:
                s = "已完成";
                break;
            case 5:
                s = "已作废";
                break;

        }
        if(info.getPayStatus()==2){
            s = "已退款";
        }
        mHolder.tv_status.setText(s);

        adapter = new ItemLeaseDingDanAdapter(mContext);
        mHolder.rc_shop.setAdapter(adapter);
        adapter.addAll(info.getOrderGoodsList());
        mHolder.rc_shop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ImageClick != null) {
                    ImageClick.onClick(v, position);
                }
            }
        });

        mHolder.bt_again.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (itemAgainClick != null) {
                    itemAgainClick.onClick(v, position);
                }
            }
        });
        mHolder.bt_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (itemDeleteClick != null) {
                    itemDeleteClick.onClick(v, position);
                }
            }
        });
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (mHolder.rc_shop != null && mHolder.rc_shop.getAdapter() == null) {
                    mHolder.rc_shop.setAdapter(adapter);
                }
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", info.getId());
                ZeroZeroSevenUtils.SwitchActivity(mContext, LeaseDingDanBobyActivity.class, bundle);
            }
        });
    }

    private OnItemImageClick ImageClick;

    public void setOnItemImageViewClick(OnItemImageClick ImageClick) {
        this.ImageClick = ImageClick;
    }

    public interface OnItemImageClick {
        void onClick(View view, int position);
    }


    private OnItemAgainClick itemAgainClick;

    public interface OnItemAgainClick {
        void onClick(View view, int position);
    }

    public void setOnItemAgainClick(OnItemAgainClick itemAgainClick) {
        this.itemAgainClick = itemAgainClick;
    }


    private OnItemDeleteClick itemDeleteClick;

    public interface OnItemDeleteClick {
        void onClick(View view, int position);
    }

    public void setOnItemDeleteClick(OnItemDeleteClick itemDeleteClick) {
        this.itemDeleteClick = itemDeleteClick;
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_status;
        TextView tv_allmoney;
        RecyclerView rc_shop;
        Button bt_again;
        Button bt_delete;

        MViewHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_allmoney = itemView.findViewById(R.id.tv_allmoney);
            rc_shop = itemView.findViewById(R.id.rc_shop);
            bt_again = itemView.findViewById(R.id.bt_again);
            bt_delete = itemView.findViewById(R.id.bt_delete);
            rc_shop.setLayoutManager(new LinearLayoutManager(mContext));
            rc_shop.addItemDecoration(new SpaceItemDecoration(15));

        }
    }
}
