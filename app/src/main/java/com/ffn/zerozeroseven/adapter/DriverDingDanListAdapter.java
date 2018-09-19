package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.DriverDingDanListInfo;

/**
 * Created by GT on 2017/11/27.
 */

public class DriverDingDanListAdapter extends BaseRecyclerAdapter<DriverDingDanListInfo.DataBean.ListBean> {
    public int clickPosition = 0;

    public DriverDingDanListAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new DriverDingDanListAdapter.MViewHolder(mInflater.inflate(R.layout.item_driver_dingdan_list, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, DriverDingDanListInfo.DataBean.ListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_time.setText("下单时间：" + item.getCreateTime());
        if (item.getStatus() == 1) {
            mHolder.tv_status.setText("支付成功");
        } else {
            mHolder.tv_status.setText("" + item.getStatus());
        }
        mHolder.tv_driverName.setText("报考驾校:" + item.getDrivingName());
        mHolder.tv_orderNo.setText("订单号:" + item.getOrderNo());
        mHolder.tv_className.setText("报考班级:" + item.getClassName());
    }

    public void setClickPosition(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_status;
        TextView tv_orderNo;
        TextView tv_driverName;
        TextView tv_className;

        MViewHolder(View itemView) {
            super(itemView);
            tv_className = itemView.findViewById(R.id.tv_className);
            tv_orderNo = itemView.findViewById(R.id.tv_orderNo);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_driverName = itemView.findViewById(R.id.tv_driverName);
        }
    }


}
