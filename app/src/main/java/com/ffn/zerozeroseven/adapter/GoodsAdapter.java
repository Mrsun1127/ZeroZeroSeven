package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.bean.GoodsContentShowInfo;
import com.ffn.zerozeroseven.fragment.ShopFragment;
import com.ffn.zerozeroseven.utlis.SharePrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GT on 2017/11/27.
 */

public class GoodsAdapter extends BaseRecyclerAdapter<GoodsContentShowInfo.DataBean.ProductsBean> {
    private CarShopInfo lastCarShopInfo;
    private Double runMoney;
    private String storeId;
    private boolean isClear = false;

    public GoodsAdapter(Context context) {
        super(context);

    }

    public void setLastCarShopInfo(Object object) {
        lastCarShopInfo = (CarShopInfo) object;
    }

    public void setRunMoneyAndStoreId(Double money, String id) {
        runMoney = money;
        storeId = id;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new GoodsAdapter.MViewHolder(mInflater.inflate(R.layout.item_goods, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final GoodsContentShowInfo.DataBean.ProductsBean info, final int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_name.setText(info.getGoodsName());
        if (!TextUtils.isEmpty(info.getPrice() + "")) {
            mHolder.tv_price.setText("¥" + info.getPrice());
        } else {
            mHolder.tv_price.setText("网络异常请刷新");
        }
        Glide.with(mContext)
                .load(info.getThumbnail())
                .skipMemoryCache(true)
                .error(R.drawable.oops)
                .into(mHolder.iv_icon);
        if (isClear) {
            mHolder.tv_count.setText("0");
        }
        mHolder.rl_close.setTag(mHolder);
        mHolder.rl_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Integer.parseInt(mHolder.tv_count.getText().toString()) < 1) {
                    return;
                }
                mHolder.tv_count.setText((Integer.parseInt(mHolder.tv_count.getText().toString()) - 1) + "");
                closeCarInfo(info, mHolder.tv_count);
            }
        });
        mHolder.rl_add.setTag(mHolder);
        mHolder.rl_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mHolder.tv_count.setText((Integer.parseInt(mHolder.tv_count.getText().toString()) + 1) + "");
                AddCarInfo(info, mHolder.tv_count);
                ShopFragment.mInstance.get().addAction(mHolder.rl_add);
            }
        });

        mHolder.iv_icon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ImageClick != null) {
                    ImageClick.onClick(v, position);
                }
            }
        });
        if (info.getStockNum() == 0) {
            mHolder.rl_add.setVisibility(View.INVISIBLE);
            mHolder.rl_close.setVisibility(View.INVISIBLE);
            mHolder.tv_count.setVisibility(View.INVISIBLE);
            mHolder.rl_none.setVisibility(View.VISIBLE);
        } else {
            mHolder.rl_add.setVisibility(View.VISIBLE);
            mHolder.rl_close.setVisibility(View.VISIBLE);
            mHolder.tv_count.setVisibility(View.VISIBLE);
            mHolder.rl_none.setVisibility(View.GONE);
        }
    }

    public void clearCount() {
        isClear = true;
        notifyDataSetChanged();
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_price;
        TextView tv_count;
        RelativeLayout rl_add;
        RelativeLayout rl_close;
        RelativeLayout rl_none;

        MViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_count = itemView.findViewById(R.id.tv_count);
            rl_add = itemView.findViewById(R.id.rl_add);
            rl_close = itemView.findViewById(R.id.rl_close);
            rl_none = itemView.findViewById(R.id.rl_none);


        }
    }

    private OnItemImageClick ImageClick;

    public void setOnItemImageViewClick(OnItemImageClick ImageClick) {
        this.ImageClick = ImageClick;
    }

    public interface OnItemImageClick {
        void onClick(View view, int position);
    }

    private void closeCarInfo(GoodsContentShowInfo.DataBean.ProductsBean goodsInfo, TextView tv_count) {
        //减少的时候购物车里面是一定有东西的
        try {
            lastCarShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
            List<CarShopInfo.ShopInfo> list = lastCarShopInfo.getShopInfos();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getGoodsId() == goodsInfo.getId()) {
                    lastCarShopInfo.getShopInfos().get(i).setBuyCount(list.get(i).getBuyCount() - 1);
                }
                if (lastCarShopInfo.getShopInfos().get(i).getBuyCount() == 0) {
                    lastCarShopInfo.getShopInfos().remove(i);
                }
                BaseAppApplication.getInstance().setCarShopInfo(lastCarShopInfo);
                ShopFragment.mInstance.get().notifyCar();
            }
        } catch (Exception e) {
            try {
                lastCarShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
                List<CarShopInfo.ShopInfo> list = lastCarShopInfo.getShopInfos();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getGoodsId() == goodsInfo.getId()) {
                        lastCarShopInfo.getShopInfos().get(i).setBuyCount(list.get(i).getBuyCount() - 1);
                        BaseAppApplication.getInstance().setCarShopInfo(lastCarShopInfo);
                        ShopFragment.mInstance.get().notifyCar();
                        return;
                    }
                }
            } catch (Exception e1) {
                clearCount();
            }

        }
    }

    private void AddCarInfo(GoodsContentShowInfo.DataBean.ProductsBean goodsInfo, TextView tv_count) {
        try {
            lastCarShopInfo = BaseAppApplication.getInstance().getCarShopInfo();
            if (lastCarShopInfo.getShopInfos().size() > 0) {//说明购物车里面有东西
                List<CarShopInfo.ShopInfo> list = lastCarShopInfo.getShopInfos();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getGoodsId() == goodsInfo.getId()) {
                        lastCarShopInfo.getShopInfos().get(i).setBuyCount(list.get(i).getBuyCount() + 1);
                        BaseAppApplication.getInstance().setCarShopInfo(lastCarShopInfo);
                        ShopFragment.mInstance.get().notifyCar();
                        return;
                    }
                }
                CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
                shopInfo.setImagUrl(goodsInfo.getThumbnail());
                shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
                shopInfo.setRunMoney(runMoney);
                shopInfo.setShopId(storeId);
                shopInfo.setGoodsId(goodsInfo.getId());
                shopInfo.setShopName(goodsInfo.getGoodsName());
                shopInfo.setShopMoney(goodsInfo.getPrice());
                list.add(shopInfo);
                lastCarShopInfo.setShopInfos(list);
                BaseAppApplication.getInstance().setCarShopInfo(lastCarShopInfo);
                ShopFragment.mInstance.get().notifyCar();
            } else {//购物车里面的东西是空的
                List<CarShopInfo.ShopInfo> list = new ArrayList<>();
                CarShopInfo carShopInfo = new CarShopInfo();
                CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
                shopInfo.setImagUrl(goodsInfo.getThumbnail());
                shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
                shopInfo.setRunMoney(runMoney);
                shopInfo.setShopId(storeId);
                shopInfo.setGoodsId(goodsInfo.getId());
                shopInfo.setShopName(goodsInfo.getGoodsName());
                shopInfo.setShopMoney(goodsInfo.getPrice());
                list.add(shopInfo);
                carShopInfo.setShopInfos(list);
                BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
                ShopFragment.mInstance.get().notifyCar();
            }
        } catch (Exception e) {
            List<CarShopInfo.ShopInfo> list = new ArrayList<>();
            CarShopInfo carShopInfo = new CarShopInfo();
            CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
            shopInfo.setImagUrl(goodsInfo.getThumbnail());
            shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
            shopInfo.setRunMoney(runMoney);
            shopInfo.setShopId(storeId);
            shopInfo.setGoodsId(goodsInfo.getId());
            shopInfo.setShopName(goodsInfo.getGoodsName());
            shopInfo.setShopMoney(goodsInfo.getPrice());
            list.add(shopInfo);
            carShopInfo.setShopInfos(list);
            BaseAppApplication.getInstance().setCarShopInfo(carShopInfo);
            ShopFragment.mInstance.get().notifyCar();
        }

    }
}
