package com.ffn.zerozeroseven.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommentDialog extends Dialog implements View.OnClickListener {
    private Context context;
    @Bind(R.id.tv_commit)
    TextView tv_commit;
    @Bind(R.id.et_comment)
    EditText et_comment;
    private OnCommitListener listener;
    private String toName;

    public void setOnCommitListener(OnCommitListener listener) {
        this.listener = listener;
    }

    public interface OnCommitListener {
        void onCommit(EditText et, View v);
    }


    public CommentDialog(Context context) {
        this(context, R.style.inputDialog);
        this.context = context;
    }

    public void setEt_comment(String s) {
        if (!TextUtils.isEmpty(s)) {
            et_comment.setHint("回复 " + s);
        }else{
            et_comment.setHint("");
        }
    }

    public CommentDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputbitis);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        //设置显示对话框时的返回键的监听
        this.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                    CommentDialog.this.cancel();
                return false;
            }
        });

        tv_commit.setOnClickListener(this);//提交
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                if (null != listener) {
                    listener.onCommit(et_comment, view);
                }
                break;
        }
    }
}
