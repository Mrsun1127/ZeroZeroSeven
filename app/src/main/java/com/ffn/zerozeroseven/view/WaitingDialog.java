package com.ffn.zerozeroseven.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.ffn.zerozeroseven.R;


/**
 * Created by archerlee on 15/10/30.
 */
public class WaitingDialog extends Dialog {

    private TextView waitingInfo;
    public WaitingDialog(Context context) {
        super(context, R.style.waiting_dialog);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_waiting);
        setCanceledOnTouchOutside(false);
        waitingInfo = (TextView) findViewById(R.id.waitingInfoId);
    }
//    public TextView getTextView(){
//        return waitingInfo;
//    }

    public void showInfo(String str) {

            show();
            waitingInfo.setText(str);


    }

    public void showInfo(int str) {
        waitingInfo.setText(str);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
