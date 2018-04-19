package com.ffn.zerozeroseven.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.ffn.zerozeroseven.R;


/**
 * 加载数据显示
 * Created by dell on 2017/4/25.
 */

public class LoadProgressDialog extends ProgressDialog {
    public LoadProgressDialog(Context context) {
        this(context, R.style.CustomDialog);
    }

    public LoadProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.progress_dialog_custom);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {
        super.show();
    }
}
