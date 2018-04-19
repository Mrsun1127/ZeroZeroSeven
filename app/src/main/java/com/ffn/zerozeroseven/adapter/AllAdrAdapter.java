package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.bean.ShouHuoInfo;

import java.util.List;


/**
 * Created by pin on 2016/5/11.
 */
public class AllAdrAdapter extends BaseAdapter {

    List<ShouHuoInfo.DataBean.AddressesBean> list;
    final Context context;

    public List<ShouHuoInfo.DataBean.AddressesBean> getList() {
        return list;
    }

    public void setList(List<ShouHuoInfo.DataBean.AddressesBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public AllAdrAdapter(List<ShouHuoInfo.DataBean.AddressesBean> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adr, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(list.get(position).getContactName());
        holder.tv_phone.setText(list.get(position).getContactPhone());
        if(list.get(position).getContactBuilding()==null){
            list.get(position).setContactBuilding(" ");
        }
        holder.tv_adr.setText(list.get(position).getContactSchool()+list.get(position).getContactBuilding()+list.get(position).getContactDorm());
        return convertView;
    }


    static class ViewHolder {
        TextView tv_name;
        TextView tv_phone;
        TextView tv_adr;


        ViewHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
            tv_adr = view.findViewById(R.id.tv_adr);
        }
    }

}