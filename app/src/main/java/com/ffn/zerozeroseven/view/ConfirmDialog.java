package com.ffn.zerozeroseven.view;

/*
* ConfirmDialog 提醒框
* @author archerlee
* @time 16/3/23 16:16
*/

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;


public class ConfirmDialog extends Dialog {

    private Context context;
    private String title = "提示";
    private String confirmButtonText = "确认";
    private String cacelButtonText = "取消";
    private ClickListenerInterface clickListenerInterface;
    private View content;
    private String message;
    private TextView tvTitle, tvMessage;
    private Button btConfirm, btCancel;

    /**
     * 外部接口
     */
    public interface ClickListenerInterface {
        /*
        * 确认
        */
        void doConfirm();

        /**
         * 取消
         */
        void doCancel();
    }

    public ConfirmDialog(Context context) {
        super(context, R.style.Dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    /**
     * 初始化
     */
    public void init() {
        setContentView(R.layout.custom_dialog_layout);
        tvTitle = (TextView) findViewById(R.id.title);
        btConfirm = (Button) findViewById(R.id.commit_bt);
        btCancel = (Button) findViewById(R.id.cancel_bt);
        tvMessage = (TextView) findViewById(R.id.message);
        content = (LinearLayout) findViewById(R.id.content);

        tvTitle.setText(title);
        btConfirm.setText(confirmButtonText);
        btCancel.setText(cacelButtonText);
        tvMessage.setText(message);

        btConfirm.setOnClickListener(new clickListener());
        btCancel.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    /**
     * 设置监听器
     *
     * @param clickListenerInterface
     */
    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
        show();
    }

    /**
     * 设置title
     *
     * @param title
     */
    public void setTitles(String title) {
        if (!ZeroZeroSevenUtils.isNotEmpty(title)) {
            this.title = "提示";
        }
        this.title = title;
    }

    /**
     * 设置content
     *
     * @param contentView
     */
    public void setContentView(View contentView) {

        this.content = contentView;
    }

    /**
     * 设置message
     *
     * @param message
     */
    public void setMessages(String message) {
        if (!ZeroZeroSevenUtils.isNotEmpty(message)) {
//            hideView(Type.MESSAGE);
            this.message = "请选择投保险种";
        }
        this.message = message;
    }

    /**
     * 确认按钮文字
     * 默认文字是确认
     *
     * @param confirmButtonText
     */
    public void setConfirmButtonText(String confirmButtonText) {
        if (!ZeroZeroSevenUtils.isNotEmpty(confirmButtonText)) {
            confirmButtonText = "确认";
        }
        this.confirmButtonText = confirmButtonText;
    }

    /**
     * 取消按钮文字
     * 默认文字是取消
     *
     * @param cacelButtonText
     */
    public void setCacelButtonText(String cacelButtonText) {
        if (!ZeroZeroSevenUtils.isNotEmpty(cacelButtonText)) {
            cacelButtonText = "取消";
        }
        this.cacelButtonText = cacelButtonText;
    }

    /**
     * 隐藏view
     *
     * @param type 枚举类型
     */
    public void hideView(Type type) {
        switch (type) {
            case TITLE:
                tvTitle.setVisibility(View.GONE);
                break;
            case MESSAGE:
                tvMessage.setVisibility(View.GONE);
                break;
            case LEFT_BUTTON:
                btCancel.setVisibility(View.GONE);
                break;
            case RIGHT_BUTTON:
                btConfirm.setVisibility(View.GONE);
            case CONTENT:
                content.setVisibility(View.GONE);
                break;
        }
    }


    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.commit_bt:
                    clickListenerInterface.doConfirm();
                    dismiss();
                    break;
                case R.id.cancel_bt:
                    clickListenerInterface.doCancel();
                    dismiss();
                    break;
            }
        }

    }

    /**
     * 枚举
     */
    public enum Type {
        TITLE, MESSAGE, LEFT_BUTTON, RIGHT_BUTTON, CONTENT
    }
}

