package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.NumberLevelInfo;
import com.ffn.zerozeroseven.bean.NumberListInfo;
import com.ffn.zerozeroseven.ui.NumberRicalActivity;
import com.ffn.zerozeroseven.ui.WebViewActivity;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;
import com.ffn.zerozeroseven.view.FullyLinearLayoutManager;
import com.ffn.zerozeroseven.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GT on 2017/11/27.
 */

public class NumberRicalVerticalAdapter extends BaseRecyclerAdapter<NumberListInfo.DataBean.ListBean> {
    public NumberRicalVerticalAdapter(Context context) {
        super(context);
    }

    private List<String> stringList;

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new NumberRicalVerticalAdapter.MViewHolder(mInflater.inflate(R.layout.item_numbericalvertical, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final NumberListInfo.DataBean.ListBean item, int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_name.setText(item.getGoodsName());
        Glide.with(mContext)
                .load(item.getGoodsThumb())
                .into(mHolder.iv_product);
        mHolder.tv_shopmoney.setText(item.getShopPrice() + "");
        mHolder.tv_desc.setText(item.getGoodsBrief());
        mHolder.tv_yuyuemoney.setText("预约加价"+item.getDepositFee());
        mHolder.tv_oldmoney.setText("原价 " + item.getMarketPrice() + "");

//        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
//        mHolder.rc_top.setLayoutManager(linearLayoutManager);
//        VerticalItemAdapter topAdapter = new VerticalItemAdapter(mContext);
//        mHolder.rc_top.setAdapter(topAdapter);
//        stringList = new ArrayList<>();
//        stringList.add("CPU I7");
//        stringList.add("2GB 独显");
//        stringList.add("256G固态");
//        topAdapter.addAll(stringList);
//        topAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, long itemId) {
//                Bundle bundle = new Bundle();
//                bundle.putString("url", AppConfig.NUMBERICALURL + item.getId());
//                bundle.putString("title", "商品详情");
//                ZeroZeroSevenUtils.SwitchActivity(mContext, WebViewActivity.class, bundle);
//            }
//        });
//        FullyLinearLayoutManager linearLayoutManager1 = new FullyLinearLayoutManager(mContext);
//        linearLayoutManager1.setOrientation(LinearLayout.HORIZONTAL);
//        mHolder.rc_bot.setLayoutManager(linearLayoutManager1);
//        VerticalItemBotAdapter botAdapter = new VerticalItemBotAdapter(mContext);
//        mHolder.rc_bot.setAdapter(botAdapter);
//        botAdapter.addAll(stringList);
//        botAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, long itemId) {
//                Bundle bundle = new Bundle();
//                bundle.putString("url", AppConfig.NUMBERICALURL + item.getId());
//                bundle.putString("title", "商品详情");
//                ZeroZeroSevenUtils.SwitchActivity(mContext, WebViewActivity.class, bundle);
//            }
//        });
        mHolder.tv_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("url", AppConfig.NUMBERICALURL + item.getId());
                bundle.putString("title", "商品详情");
                ZeroZeroSevenUtils.SwitchActivity(mContext, WebViewActivity.class, bundle);
            }
        });
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product;
        TextView tv_name;
        TextView tv_oldmoney;
        TextView tv_shopmoney;
        TextView tv_yuyuemoney;
        TextView tv_go;
        TextView tv_desc;
//        RecyclerView rc_bot;
//        RecyclerView rc_top;

        MViewHolder(View itemView) {
            super(itemView);
            tv_yuyuemoney = itemView.findViewById(R.id.tv_yuyuemoney);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            iv_product = itemView.findViewById(R.id.iv_product);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_oldmoney = itemView.findViewById(R.id.tv_oldmoney);
            tv_go = itemView.findViewById(R.id.tv_go);
            tv_shopmoney = itemView.findViewById(R.id.tv_shopmoney);
//            rc_top = itemView.findViewById(R.id.rc_top);
//            rc_bot = itemView.findViewById(R.id.rc_bot);
        }
    }


}
