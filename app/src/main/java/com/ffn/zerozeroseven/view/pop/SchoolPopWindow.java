package com.ffn.zerozeroseven.view.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;

/**
 * Created by GT on 2017/11/23.
 */

public class SchoolPopWindow extends PopupWindow {
    private View conentView;
    private Context context;
    public SchoolPopWindow(final Activity context) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.pop_selecttiezitype, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 2 - 100);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(h/2-100);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimTools);
        TextView all=conentView.findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.All();
            }
        });
        TextView good=conentView.findViewById(R.id.good);
        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.Good();
            }
        });
        TextView find=conentView.findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.Find();
            }
        });
        TextView friend=conentView.findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.Friend();
            }
        });
        TextView love=conentView.findViewById(R.id.love);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.Love();
            }
        });

    }
    public interface OnPopClickListener {
        void All();
        void Love();
        void Good();
        void Find();
        void Friend();
    }

    private OnPopClickListener mlistener;

    public void OnPopClickListener(OnPopClickListener mlistener) {
        this.mlistener = mlistener;
    }
    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {

        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);

        } else {
            this.dismiss();
        }
    }
}
