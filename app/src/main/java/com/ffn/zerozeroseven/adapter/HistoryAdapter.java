package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.bean.HistoryInfo;

import java.util.List;


/**
 * Created by pin on 2016/5/11.
 */
public class HistoryAdapter extends BaseAdapter {

    List<HistoryInfo.DataBean.PostsBean> list;
    final Context context;

    public List<HistoryInfo.DataBean.PostsBean> getList() {
        return list;
    }

    public void setList(List<HistoryInfo.DataBean.PostsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public HistoryAdapter(List<HistoryInfo.DataBean.PostsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_historytalk, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            holder.tv_time.setText(list.get(position).getCreateTime());
            holder.tv_title.setText(list.get(position).getTitle());
            holder.tv_type.setText(list.get(position).getPostType());
            holder.tv_content.setText(list.get(position).getContent());
        return convertView;
    }


    static class ViewHolder {
        TextView tv_time;
        TextView tv_title;
        TextView tv_type;
        TextView tv_content;


        ViewHolder(View view) {
            tv_time=view.findViewById(R.id.tv_time);
            tv_title=view.findViewById(R.id.tv_title);
            tv_type=view.findViewById(R.id.tv_type);
            tv_content=view.findViewById(R.id.tv_content);
        }
    }

}