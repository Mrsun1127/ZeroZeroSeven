package com.ffn.zerozeroseven.listenner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ffn.zerozeroseven.utlis.ToastUtils;
import com.ffn.zerozeroseven.utlis.ZeroZeroSevenUtils;

/**
 * Created by GT on 2018/2/2.
 */

public class TimeRecever extends BroadcastReceiver {
    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        RequestDate();

    }
    private void RequestDate() {
        if(ZeroZeroSevenUtils.Date2date()){
            ToastUtils.showShort("现在是营业时间");
        }else{
            ToastUtils.showShort("现在是打烊时间");
        }
    }

}
