package com.ffn.zerozeroseven.view;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.ffn.zerozeroseven.R;

import java.util.List;

/*
* MyTextWatcher 监听edittext输入值变化
* @author archerlee
* @time 16/3/24 16:17
*/
public class MyTextWatcher implements TextWatcher {

    List<EditText> list;
    Button button;
    EditText editText;

    public MyTextWatcher(List<EditText> list, Button button) {
        this.list = list;
        this.button = button;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        for (int i = 0; i < list.size(); i++) {
            editText = list.get(i);
            if (TextUtils.isEmpty(editText.getText().toString())) {
                button.setBackgroundResource(R.drawable.bt_default_bg);
                button.setClickable(false);
            } else {
                button.setBackgroundResource(R.drawable.login_bt_bg);
                button.setClickable(true);
            }
        }

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        for (int i = 0; i < list.size(); i++) {
            editText = list.get(i);
            if (TextUtils.isEmpty(editText.getText().toString())) {
                button.setBackgroundResource(R.drawable.bt_default_bg);
                button.setClickable(false);
            } else {
                button.setBackgroundResource(R.drawable.login_bt_bg);
                button.setClickable(true);
            }
        }

    }

    @Override
    public void afterTextChanged(Editable s) {
        for (int i = 0; i < list.size(); i++) {
            editText = list.get(i);
            if (TextUtils.isEmpty(editText.getText().toString())) {
                button.setBackgroundResource(R.drawable.bt_default_bg);
                button.setClickable(false);
            } else {
                Selection.setSelection(s, s.length());
                button.setBackgroundResource(R.drawable.login_bt_bg);
                button.setClickable(true);
            }
        }
    }
}
