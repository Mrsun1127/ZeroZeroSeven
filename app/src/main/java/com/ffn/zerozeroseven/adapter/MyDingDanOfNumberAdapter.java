package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.MyDingDanShowInfo;
import com.ffn.zerozeroseven.bean.NumberDingDanInfo;
import com.ffn.zerozeroseven.ui.DingDanBobyActivity;
import com.ffn.zerozeroseven.ui.NumberRicalDetilsActivity;
import com.ffn.zerozeroseven.ui.NumberTuiKuanDetilsActivity;
import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;

/**
 * Created by GT on 2017/11/27.
 */

public class MyDingDanOfNumberAdapter extends BaseRecyclerAdapter<NumberDingDanInfo.DataBean.ListBean> {

    private ItemDingDanAdapter adapter;

    public MyDingDanOfNumberAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MyDingDanOfNumberAdapter.MViewHolder(mInflater.inflate(R.layout.item_numberdingdan, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final NumberDingDanInfo.DataBean.ListBean info, final int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        //1=已预约,2=已取消,3=确认收获,4=退货
        switch (info.getOrderStatus()) {
            case 1:
                mHolder.tv_status.setText("已预约");
                mHolder.bt_left.setText("退款");
                mHolder.bt_right.setText("支付尾款");
                break;
            case 2:
                mHolder.tv_status.setText("已取消");
                mHolder.rl_status.setVisibility(View.GONE);
                break;
            case 3:
                mHolder.tv_status.setText("确认收货");
                mHolder.bt_left.setText("删除订单");
                mHolder.bt_right.setText("再次购买");
                break;
            case 4:
                mHolder.tv_status.setText("退货");
                mHolder.rl_status.setVisibility(View.GONE);
                break;
        }
//         商品配送状态：0=未发货,1=已发货,2=已收货,4=退货
        switch (info.getShippingStatus()) {
            case 0:
                mHolder.bt_left.setText("退款");
                mHolder.bt_right.setText("支付尾款");
                break;
            case 1:
                mHolder.bt_left.setText("查看订单");
                mHolder.bt_right.setText("确认收货");
                break;
            case 2:
                mHolder.tv_status.setText("确认收货");
                mHolder.bt_left.setText("删除订单");
                mHolder.bt_right.setText("再次购买");
                break;
            case 4:
                mHolder.tv_status.setText("退货");
                mHolder.rl_status.setVisibility(View.GONE);
                break;
        }
        mHolder.tv_desc.setText("共" + info.getGoodsCount() + "件商品 合计：￥" + info.getOrderPrice() + "(含运费￥" + (info.getFreightPrice() == null ? 0.00 : info.getFreightPrice()) + ")");
        mHolder.tv_time.setText(info.getCreateTime());
        ItemNumberDingDanAdapter itemNumberDingDanAdapter = new ItemNumberDingDanAdapter(mContext);
        mHolder.rc_product.setAdapter(itemNumberDingDanAdapter);
        itemNumberDingDanAdapter.addAll(info.getOrderGoodsList());
        itemNumberDingDanAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", info.getId());
                if (info.isIsApplyRefund()) {
                    ZeroZeroSevenUtils.SwitchActivity(mContext, NumberTuiKuanDetilsActivity.class, bundle);
                } else {
                    ZeroZeroSevenUtils.SwitchActivity(mContext, NumberRicalDetilsActivity.class, bundle);
                }
            }
        });
        mHolder.bt_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (itemAgainClick != null) {
                    itemAgainClick.onClick(v, position);
                }
            }
        });
        mHolder.bt_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (itemDeleteClick != null) {
                    itemDeleteClick.onClick(v, position);
                }
            }
        });
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
        TextView tv_desc;
        RecyclerView rc_product;
        Button bt_right;
        Button bt_left;
        RelativeLayout rl_status;

        MViewHolder(View itemView) {
            super(itemView);
            rl_status = itemView.findViewById(R.id.rl_status);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            rc_product = itemView.findViewById(R.id.rc_product);
            bt_right = itemView.findViewById(R.id.bt_right);
            bt_left = itemView.findViewById(R.id.bt_left);
            rc_product.setLayoutManager(new LinearLayoutManager(mContext));
            rc_product.addItemDecoration(new SpaceItemDecoration(15));

        }
    }
}
