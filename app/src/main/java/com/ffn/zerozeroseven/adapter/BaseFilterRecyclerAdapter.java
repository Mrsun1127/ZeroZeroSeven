package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ffn.zerozeroseven.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFilterRecyclerAdapter<T> extends RecyclerView.Adapter {

    public Context mContext;
    protected LayoutInflater mInflater;
    private static final String TAG = "公用筛选RecyclerAdapter";
    public List<T> datas;
    private boolean haveExpand;
    private int lastSize = 0;

    public BaseFilterRecyclerAdapter(Context context, boolean haveExpand) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.datas = new ArrayList<>();
        this.haveExpand = haveExpand;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, long itemId);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        RecyclerView.ViewHolder holder = onCreateDefaultViewHolder(viewGroup,i);
        holder.itemView.setTag(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) v.getTag();
                    onItemClickListener.onItemClick(holder.getAdapterPosition(), holder.getItemId());
                }
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder mFilterViewHolder, int i) {
        onBindDefaultViewHolder(mFilterViewHolder, getDatas().get(i), i);
    }

    @Override
    public int getItemCount() {
//        if (haveExpand) {
//            if (datas.size() >= 6) {
//                return 6;
//            } else {
//                return datas.size();
//            }
//        } else {
//            return datas.size();
//        }
        return datas.size();
    }


    protected abstract void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, T item, int position);
    protected abstract RecyclerView.ViewHolder  onCreateDefaultViewHolder(ViewGroup parent, int type);

    public final List<T> getDatas() {
        return datas;
    }


    public T getLastCheckEntity() {

        return checkEntity;
    }

    private T checkEntity;

    /**
     * 保存选择的对象
     */
    public void setCheckEntity(T entity) {
        checkEntity = entity;
    }

    /**
     * 选择哪一个
     */
    public abstract void setCheckPos(int pos);


    public int getColorCheck() {
        return mContext.getResources().getColor(R.color.white);
    }

    public int getColorUnCheck() {
        return mContext.getResources().getColor(R.color.text_show_color);
    }

    public void setHaveExpand(boolean haveExpand) {
        this.haveExpand = haveExpand;
        notifyDataSetChanged();

    }

    public void addAll(List<T> items) {
        if (items != null) {
//            lastSize=items.size();
            this.datas.addAll(items);
            notifyItemRangeInserted(this.datas.size(), items.size());
        }
    }
    public final void resetItem(List<T> items) {
        if (items != null) {
            clear();
            addAll(items);
        }
    }

    public final void clear() {
        this.datas.clear();
        notifyDataSetChanged();
    }


}
