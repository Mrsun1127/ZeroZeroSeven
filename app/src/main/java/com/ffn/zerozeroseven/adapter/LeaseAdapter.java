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
import com.ffn.zerozeroseven.bean.LeaseGoodsInfo;
import com.ffn.zerozeroseven.fragment.LeaseFragment;
import com.ffn.zerozeroseven.utlis.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GT on 2017/11/27.
 */

public class LeaseAdapter extends BaseRecyclerAdapter<LeaseGoodsInfo.DataBean.ListBean> {
    private CarShopInfo lastCarShopInfo;
    private Double runMoney;
    private String storeId;
    private boolean isClear = false;

    public LeaseAdapter(Context context) {
        super(context);

    }
    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new LeaseAdapter.MViewHolder(mInflater.inflate(R.layout.item_goods, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, final LeaseGoodsInfo.DataBean.ListBean info, final int position) {
        final MViewHolder mHolder = (MViewHolder) holder;
        mHolder.tv_name.setText(info.getGoodsName());
        if (!TextUtils.isEmpty(info.getGoodsPrice() + "")) {
            mHolder.tv_price.setText("¥" + info.getGoodsPrice());
        } else {
            mHolder.tv_price.setText("网络异常请刷新");
        }
        Glide.with(mContext)
                .load(info.getGoodsThumb())
                .skipMemoryCache(true)
                .error(R.drawable.oops)
                .override((int) ScreenUtils.getScreenWidth() / 5, (int) ScreenUtils.getScreenWidth() / 5 - 50)
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
                LeaseFragment.mInstance.get().addAction(mHolder.rl_add);
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
        if (info.getStoreCount() == 0) {
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

    private void closeCarInfo(LeaseGoodsInfo.DataBean.ListBean goodsInfo, TextView tv_count) {
        //减少的时候购物车里面是一定有东西的
        try {
            lastCarShopInfo = BaseAppApplication.getInstance().getLeasecarShopInfo();
            List<CarShopInfo.ShopInfo> list = lastCarShopInfo.getShopInfos();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getGoodsId() == goodsInfo.getId()) {
                    lastCarShopInfo.getShopInfos().get(i).setBuyCount(list.get(i).getBuyCount() - 1);
                }
                if (lastCarShopInfo.getShopInfos().get(i).getBuyCount() == 0) {
                    lastCarShopInfo.getShopInfos().remove(i);
                }
                BaseAppApplication.getInstance().setLeasecarShopInfo(lastCarShopInfo);
                LeaseFragment.mInstance.get().notifyCar();
            }
        } catch (Exception e) {
            try {
                lastCarShopInfo = BaseAppApplication.getInstance().getLeasecarShopInfo();
                List<CarShopInfo.ShopInfo> list = lastCarShopInfo.getShopInfos();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getGoodsId() == goodsInfo.getId()) {
                        lastCarShopInfo.getShopInfos().get(i).setBuyCount(list.get(i).getBuyCount() - 1);
                        BaseAppApplication.getInstance().setLeasecarShopInfo(lastCarShopInfo);
                        LeaseFragment.mInstance.get().notifyCar();
                        return;
                    }
                }
            } catch (Exception e1) {
                clearCount();
            }

        }
    }

    private void AddCarInfo(LeaseGoodsInfo.DataBean.ListBean goodsInfo, TextView tv_count) {
        try {
            lastCarShopInfo = BaseAppApplication.getInstance().getLeasecarShopInfo();
            if (lastCarShopInfo.getShopInfos().size() > 0) {//说明购物车里面有东西
                List<CarShopInfo.ShopInfo> list = lastCarShopInfo.getShopInfos();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getGoodsId() == goodsInfo.getId()) {
                        lastCarShopInfo.getShopInfos().get(i).setBuyCount(list.get(i).getBuyCount() + 1);
                        BaseAppApplication.getInstance().setLeasecarShopInfo(lastCarShopInfo);
                        LeaseFragment.mInstance.get().notifyCar();
                        return;
                    }
                }
                CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
                shopInfo.setImagUrl(goodsInfo.getGoodsThumb());
                shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
                shopInfo.setGoodsId(goodsInfo.getId());
                shopInfo.setShopName(goodsInfo.getGoodsName());
                shopInfo.setShopMoney(goodsInfo.getGoodsPrice());
                list.add(shopInfo);
                lastCarShopInfo.setShopInfos(list);
                BaseAppApplication.getInstance().setLeasecarShopInfo(lastCarShopInfo);
                LeaseFragment.mInstance.get().notifyCar();
            } else {//购物车里面的东西是空的
                List<CarShopInfo.ShopInfo> list = new ArrayList<>();
                CarShopInfo carShopInfo = new CarShopInfo();
                CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
                shopInfo.setImagUrl(goodsInfo.getGoodsThumb());
                shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
                shopInfo.setGoodsId(goodsInfo.getId());
                shopInfo.setShopName(goodsInfo.getGoodsName());
                shopInfo.setShopMoney(goodsInfo.getGoodsPrice());
                list.add(shopInfo);
                carShopInfo.setShopInfos(list);
                BaseAppApplication.getInstance().setLeasecarShopInfo(carShopInfo);
                LeaseFragment.mInstance.get().notifyCar();
            }
        } catch (Exception e) {
            List<CarShopInfo.ShopInfo> list = new ArrayList<>();
            CarShopInfo carShopInfo = new CarShopInfo();
            CarShopInfo.ShopInfo shopInfo = new CarShopInfo.ShopInfo();
            shopInfo.setImagUrl(goodsInfo.getGoodsThumb());
            shopInfo.setBuyCount(Integer.parseInt(tv_count.getText().toString()));
            shopInfo.setGoodsId(goodsInfo.getId());
            shopInfo.setShopName(goodsInfo.getGoodsName());
            shopInfo.setShopMoney(goodsInfo.getGoodsPrice());
            list.add(shopInfo);
            carShopInfo.setShopInfos(list);
            BaseAppApplication.getInstance().setLeasecarShopInfo(carShopInfo);
            LeaseFragment.mInstance.get().notifyCar();
        }

    }
}
