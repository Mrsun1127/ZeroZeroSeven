package com.ffn.zerozeroseven.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * the base adapter for RecyclerView
 * Created by huanghaibin on 16-5-3.
 */
public abstract class BaseRecyclerTallAniAdapter<T> extends RecyclerView.Adapter {
    protected List<T> mItems;
    public Context mContext;
    protected LayoutInflater mInflater;


    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    private OnClickListener onClickListener;
    private OnLongClickListener onLongClickListener;


    public BaseRecyclerTallAniAdapter(Context context) {
        mItems = new ArrayList<>();
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        initListener();
    }

    /**
     * 初始化listener
     */
    private void initListener() {
        onClickListener = new OnClickListener() {
            @Override
            public void onClick(int position, long itemId) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(position, itemId);
            }
        };

        onLongClickListener = new OnLongClickListener() {
            @Override
            public boolean onLongClick(int position, long itemId) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onLongClick(position, itemId);
                    return true;
            }
                return false;
            }
        };
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder = onCreateDefaultViewHolder(parent, viewType);
        if (holder != null) {
            holder.itemView.setTag(holder);
            holder.itemView.setOnLongClickListener(onLongClickListener);
            holder.itemView.setOnClickListener(onClickListener);
        }

        return holder;
    }

    public void setAnimation(final View convertView) {
        ViewHelper.setScaleX(convertView, 0.1f);
        ViewHelper.setScaleY(convertView, 0.1f);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewPropertyAnimator.animate(convertView).scaleX(0.9f).setDuration(1000).setInterpolator(new OvershootInterpolator());
                ViewPropertyAnimator.animate(convertView).scaleY(0.9f).setDuration(1000).setInterpolator(new OvershootInterpolator());
            }
        }, 50);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewPropertyAnimator.animate(convertView).scaleX(0.8f).setDuration(2000).setInterpolator(new OvershootInterpolator());
                ViewPropertyAnimator.animate(convertView).scaleY(0.8f).setDuration(2000).setInterpolator(new OvershootInterpolator());
            }
        }, 1150);

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindDefaultViewHolder(holder, getItems().get(position), position);
        setAnimation(holder.itemView);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public int getCount() {
        return mItems.size();
    }

    protected abstract RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type);

    protected abstract void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, T item, int position);


    public final List<T> getItems() {
        return mItems;
    }


    public void addAll(List<T> items) {
        if (items != null) {
            this.mItems.addAll(items);
            notifyItemRangeInserted(this.mItems.size(), items.size());
        }
    }
    public void cleanDates(){
        mItems.clear();
        notifyDataSetChanged();
    }
    public final void addItem(T item) {
        if (item != null) {
            this.mItems.add(item);
            notifyItemChanged(mItems.size());
        }
    }


    public void addItem(int position, T item) {
        if (item != null) {
            this.mItems.add(position, item);
            notifyItemInserted(position);
        }
    }

    public void replaceItem(int position, T item) {
        if (item != null) {
            this.mItems.set(position, item);
            notifyItemChanged(position);
        }
    }

    public void updateItem(int position) {
        if (getItemCount() > position) {
            notifyItemChanged(position);
        }
    }


    public final void removeItem(T item) {
        if (this.mItems.contains(item)) {
            int position = mItems.indexOf(item);
            this.mItems.remove(item);
            notifyItemRemoved(position);
        }
    }

    public final void removeItem(int position) {
        if (this.getItemCount() > position) {
            this.mItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public final T getItem(int position) {
//        int p = getIndex(position);
        if (position < 0 || position >= mItems.size())
            return null;
        return mItems.get(position);
    }

    public final void resetItem(List<T> items) {
        if (items != null) {
            clear();
            addAll(items);
        }
    }

    public final void clear() {
        this.mItems.clear();
        notifyDataSetChanged();
    }


    /**
     * 可以共用同一个listener，相对高效
     */
    public static abstract class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) v.getTag();
            onClick(holder.getAdapterPosition(), holder.getItemId());
        }

        public abstract void onClick(int position, long itemId);
    }


    /**
     * 可以共用同一个listener，相对高效
     */
    public static abstract class OnLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) v.getTag();
            return onLongClick(holder.getAdapterPosition(), holder.getItemId());
        }

        public abstract boolean onLongClick(int position, long itemId);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, long itemId);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }


    public interface OnItemLongClickListener {
        void onLongClick(int position, long itemId);
    }

    protected Resources getResource() {

        return mContext.getResources();

    }


}