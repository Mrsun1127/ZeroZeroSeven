package com.ffn.zerozeroseven.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.ShouHuoInfo;
import com.ffn.zerozeroseven.bean.requsetbean.UpDateAdrInfo;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.OkGoUtils;
import com.ffn.zerozeroseven.utlis.ToastUtils;

import java.util.List;


/**
 * Created by pin on 2016/5/11.
 */
public class AllAdrAdapter extends BaseAdapter {

    List<ShouHuoInfo.DataBean.AddressesBean> list;
    final Context context;
    boolean isShow;

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
    public AllAdrAdapter(List<ShouHuoInfo.DataBean.AddressesBean> list, Context context,boolean isShow) {
        this.list = list;
        this.context = context;
        this.isShow = isShow;
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adr, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(list.get(position).getContactName());
        holder.tv_phone.setText(list.get(position).getContactPhone());
        if (list.get(position).getContactBuilding() == null) {
            list.get(position).setContactBuilding(" ");
        }
        if(isShow){
            holder.tv_edit.setVisibility(View.INVISIBLE);
            holder.tv_set.setVisibility(View.GONE);
        }
        if(list.get(position).getIsDefault()==1){
            holder.tv_set.setText("默认地址");
        }
        holder.tv_adr.setText(list.get(position).getContactSchool() + list.get(position).getContactBuilding() + list.get(position).getContactDorm());
        holder.tv_set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UpDateAdrInfo upDateAdrInfo = new UpDateAdrInfo();
                upDateAdrInfo.setFunctionName("UpdateUserAddress");
                UpDateAdrInfo.ParametersBean parametersBean = new UpDateAdrInfo.ParametersBean();
                parametersBean.setId(list.get(position).getId());
                parametersBean.setContactSchool(list.get(position).getContactSchool());
                parametersBean.setContactName(list.get(position).getContactName());
                parametersBean.setContactPhone(list.get(position).getContactPhone());
                parametersBean.setContactBuilding(list.get(position).getContactBuilding());
                parametersBean.setContactDorm(list.get(position).getContactDorm());
                parametersBean.setIsDefault(1);
                upDateAdrInfo.setParameters(parametersBean);
                OkGoUtils okGoUtils = new OkGoUtils(context);
                okGoUtils.httpPostJSON(upDateAdrInfo, true, true,holder.tv_set);
                okGoUtils.setOnLoadSuccess(new OkGoUtils.OnLoadSuccess() {
                    @Override
                    public void onSuccLoad(String response) {
                        final String code = JsonUtil.getFieldValue(response, "code");
                        holder.tv_set.post(new Runnable() {
                            @Override
                            public void run() {
                                if ("0".equals(code)) {
                                    holder.tv_set.setText("默认地址");
                                    ToastUtils.showShort("设置成功");
                                }
                            }
                        });
                    }
                });
            }
        });
        return convertView;
    }


    static class ViewHolder {
        TextView tv_name;
        TextView tv_phone;
        TextView tv_edit;
        TextView tv_set;
        TextView tv_adr;


        ViewHolder(View view) {
            tv_set = view.findViewById(R.id.tv_set);
            tv_name = view.findViewById(R.id.tv_name);
            tv_edit = view.findViewById(R.id.tv_edit);
            tv_phone = view.findViewById(R.id.tv_phone);
            tv_adr = view.findViewById(R.id.tv_adr);
        }
    }

}