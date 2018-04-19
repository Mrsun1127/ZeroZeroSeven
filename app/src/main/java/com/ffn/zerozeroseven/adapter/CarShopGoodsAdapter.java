package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseRecyclerAdapter;
import com.ffn.zerozeroseven.bean.CarShopInfo;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

import java.util.HashMap;

/**
 * Created by GT on 2017/11/27.
 */

public class CarShopGoodsAdapter extends BaseRecyclerAdapter<CarShopInfo.ShopInfo> {
    public static HashMap<Integer, Boolean> isSelected;

    public CarShopGoodsAdapter(Context context) {
        super(context);
    }

    public void init(int size) {
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < size; i++) {
            isSelected.put(i, false);
        }
    }
    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        CarShopGoodsAdapter.isSelected = isSelected;
    }
    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new CarShopGoodsAdapter.MViewHolder(mInflater.inflate(R.layout.item_shopcar, null));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, CarShopInfo.ShopInfo info, final int position) {
        MViewHolder mHolder = (MViewHolder) holder;
        Glide.with(mContext)
                .load(info.getImagUrl())
                .error(R.drawable.oops)
                .into(mHolder.iv_shop);
        mHolder.tv_shopname.setText(info.getShopName());
        mHolder.tv_shopcount.setText("x" + info.getBuyCount());
        mHolder.tv_money.setText("¥" + ZeroZeroSevenUtils.reactMoney((info.getBuyCount() * info.getShopMoney())));
        mHolder.iv_close.setTag(mHolder);
        mHolder.rl_delete.setVisibility(View.VISIBLE);
        mHolder.cb_select.setTag(mHolder);
        mHolder.cb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSelected.get(position)) {
                    isSelected.put(position, false);
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(position, true);
                    setIsSelected(isSelected);
                }

            }

        });
        try {
            mHolder.cb_select.setChecked(getIsSelected().get(position));
        } catch (Exception e) {

        }
        mHolder.rl_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (closeClick != null) {
                    closeClick.onClick(v, position);
                }
            }
        });
        mHolder.rl_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (addClick != null) {
                    addClick.onClick(v, position);
                }
            }
        });
        mHolder.rl_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgClick != null) {
                    imgClick.onClick(view, position);
                }
            }
        });
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_shop;
        TextView tv_shopname;
        TextView tv_shopcount;
        TextView tv_money;
        ImageView iv_add;
        ImageView iv_close;
        RelativeLayout rl_delete;
        RelativeLayout rl_add;
        RelativeLayout rl_close;
        CheckBox cb_select;

        MViewHolder(View itemView) {
            super(itemView);

            iv_shop = itemView.findViewById(R.id.iv_shop);
            iv_add = itemView.findViewById(R.id.iv_add);
            iv_close = itemView.findViewById(R.id.iv_close);
            tv_shopname = itemView.findViewById(R.id.tv_shopname);
            tv_shopcount = itemView.findViewById(R.id.tv_shopcount);
            tv_money = itemView.findViewById(R.id.tv_peoplephone);
            rl_delete = itemView.findViewById(R.id.rl_delete);
            rl_add = itemView.findViewById(R.id.rl_add);
            rl_close = itemView.findViewById(R.id.rl_close);
            cb_select = itemView.findViewById(R.id.cb_select);
        }
    }

    private OnItemCloseClick closeClick;

    public void setOnItemCloseViewClick(OnItemCloseClick closeClick) {
        this.closeClick = closeClick;
    }

    public interface OnItemCloseClick {
        void onClick(View view, int position);
    }


    private OnItemAddClick addClick;

    public void setOnItemAddViewClick(OnItemAddClick addClick) {
        this.addClick = addClick;
    }

    public interface OnItemAddClick {
        void onClick(View view, int position);
    }


    private OnItemImgClick imgClick;

    public void setOnItemImgViewClick(OnItemImgClick imgClick) {
        this.imgClick = imgClick;
    }

    public interface OnItemImgClick {
        void onClick(View view, int position);
    }
}
