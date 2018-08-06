package com.ffn.zerozeroseven.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ffn.zerozeroseven.R;

public class ShopStatusView extends RelativeLayout {
    private ImageView iv_jiedan;
    private ImageView iv_quhuo;
    private ImageView iv_peisong;
    private ImageView iv_finish;
    private TextView tv_jiedan;
    private TextView tv_quhuo;
    private TextView tv_peisong;
    private TextView tv_finish;
    private View view_left;
    private View view_center;
    private View view_right;
    private Context context;

    public ShopStatusView(Context context) {
        super(context);
    }

    public ShopStatusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.shop_statusview, this, true);
        iv_jiedan = view.findViewById(R.id.iv_jiedan);
        iv_quhuo = view.findViewById(R.id.iv_quhuo);
        iv_peisong = view.findViewById(R.id.iv_peisong);
        iv_finish = view.findViewById(R.id.iv_finish);
        tv_jiedan = view.findViewById(R.id.tv_jiedan);
        tv_quhuo = view.findViewById(R.id.tv_quhuo);
        tv_peisong = view.findViewById(R.id.tv_peisong);
        tv_finish = view.findViewById(R.id.tv_finish);
        view_left = view.findViewById(R.id.view_left);
        view_center = view.findViewById(R.id.view_center);
        view_right = view.findViewById(R.id.view_right);
    }

    public void procressTo(int position) {
        switch (position) {
            case 1:
                view_left.setBackgroundColor(getResources().getColor(R.color.money));
                tv_quhuo.setTextColor(getResources().getColor(R.color.money));
                Glide.with(context).load(R.drawable.run_shopstatus_quhuo).into(iv_quhuo);
                break;
            case 2:
                view_left.setBackgroundColor(getResources().getColor(R.color.money));
                view_center.setBackgroundColor(getResources().getColor(R.color.money));
                tv_quhuo.setTextColor(getResources().getColor(R.color.money));
                tv_peisong.setTextColor(getResources().getColor(R.color.money));
                Glide.with(context).load(R.drawable.run_shopstatus_quhuo).into(iv_quhuo);
                Glide.with(context).load(R.drawable.run_shopstatus_peisong).into(iv_peisong);
                break;
            case 3:
                view_left.setBackgroundColor(getResources().getColor(R.color.money));
                view_center.setBackgroundColor(getResources().getColor(R.color.money));
                view_right.setBackgroundColor(getResources().getColor(R.color.money));
                tv_quhuo.setTextColor(getResources().getColor(R.color.money));
                tv_peisong.setTextColor(getResources().getColor(R.color.money));
                tv_finish.setTextColor(getResources().getColor(R.color.money));
                Glide.with(context).load(R.drawable.run_shopstatus_quhuo).into(iv_quhuo);
                Glide.with(context).load(R.drawable.run_shopstatus_peisong).into(iv_peisong);
                Glide.with(context).load(R.drawable.run_shopstatus_finish).into(iv_finish);
                break;
            default:
                break;
        }
    }
}
