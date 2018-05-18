package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.HotInfo;
import com.ffn.zerozeroseven.bean.requsetbean.OftenShowInfo;
import com.ffn.zerozeroseven.ui.HomeActivity;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GT on 2017/11/27.
 */

public class HotTimeAdapter extends BaseRecyclerAdapter<HotInfo.DataBean.ProductsBean> {
    private CarShopInfo lastCarShopInfo;
    public HotTimeAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new HotTimeAdapter.MViewHolder(mInflater.inflate(R.layout.item_oftengoods, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder,final HotInfo.DataBean.ProductsBean info,final int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext)
                .load(info.getThumbnail())
                .error(R.drawable.oops)
                .into(mHolder.imageView);
        mHolder.tv_name.setText(info.getGoodsName());
        mHolder.tv_money.setText("￥"+String.valueOf(info.getPrice()));
        mHolder.rl_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddCar(info);
                HomeActivity.getmInstance().get().addAction(mHolder.imageView);
            }
        });
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name;
        TextView tv_money;
        RelativeLayout rl_add;
        MViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.iv_icon);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_money=itemView.findViewById(R.id.tv_money);
            rl_add=itemView.findViewById(R.id.rl_add);

        }
    }
    private void AddCar(HotInfo.DataBean.ProductsBean goodsInfo) {
        try {//
            lastCarShopInfo=(CarShopInfo) SharePrefUtils.readObject(mContext, "carShopInfo");
            if (lastCarShopInfo.getShopInfos().size() > 0) {//说明购物车里面有东西
                List<CarShopInfo.ShopInfo> list = lastCarShopInfo.getShopInfos();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getGoodsId() == goodsInfo.getId()) {
                        lastCarShopInfo.getShopInfos().get(i).setBuyCount(list.get(i).getBuyCount() + 1);
                        SharePrefUtils.saveObject(mContext, "carShopInfo", lastCarShopInfo);
                        return;
                    }
                }
                CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
                shopInfo.setImagUrl(goodsInfo.getThumbnail());
                shopInfo.setBuyCount(Integer.parseInt("1"));
                shopInfo.setRunMoney(goodsInfo.getExtraFee());
                shopInfo.setShopId(String.valueOf(goodsInfo.getStoreId()));
                shopInfo.setGoodsId(goodsInfo.getId());
                shopInfo.setShopName(goodsInfo.getGoodsName());
                shopInfo.setShopMoney(goodsInfo.getPrice());
                list.add(shopInfo);
                lastCarShopInfo.setShopInfos(list);
                SharePrefUtils.saveObject(mContext, "carShopInfo", lastCarShopInfo);
            } else {//购物车里面的东西是空的
                List<CarShopInfo.ShopInfo> list = new ArrayList<>();
                CarShopInfo carShopInfo = new CarShopInfo();
                CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
                shopInfo.setImagUrl(goodsInfo.getThumbnail());
                shopInfo.setBuyCount(Integer.parseInt("1"));
                shopInfo.setRunMoney(goodsInfo.getExtraFee());
                shopInfo.setShopId(String.valueOf(goodsInfo.getStoreId()));
                shopInfo.setGoodsId(goodsInfo.getId());
                shopInfo.setShopName(goodsInfo.getGoodsName());
                shopInfo.setShopMoney(goodsInfo.getPrice());
                list.add(shopInfo);
                carShopInfo.setShopInfos(list);
                SharePrefUtils.saveObject(mContext, "carShopInfo", carShopInfo);
            }
        } catch (Exception e) {
            List<CarShopInfo.ShopInfo> list = new ArrayList<>();
            CarShopInfo carShopInfo = new CarShopInfo();
            CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
            shopInfo.setImagUrl(goodsInfo.getThumbnail());
            shopInfo.setBuyCount(Integer.parseInt("1"));
            shopInfo.setRunMoney(goodsInfo.getExtraFee());
            shopInfo.setShopId(String.valueOf(goodsInfo.getStoreId()));
            shopInfo.setGoodsId(goodsInfo.getId());
            shopInfo.setShopName(goodsInfo.getGoodsName());
            shopInfo.setShopMoney(goodsInfo.getPrice());
            list.add(shopInfo);
            carShopInfo.setShopInfos(list);
            SharePrefUtils.saveObject(mContext, "carShopInfo", carShopInfo);
        }
    }
}
