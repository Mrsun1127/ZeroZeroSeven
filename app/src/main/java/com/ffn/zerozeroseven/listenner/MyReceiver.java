package com.ffn.zerozeroseven.listenner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.ui.MessAgeActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
//    public MediaPlayer mediaPlayer;
    @Override
    public void onReceive(final Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        if(intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)){
            Log.i(TAG, "接收到了通知");
            String title=bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content=bundle.getString(JPushInterface.EXTRA_ALERT);
            String extra=bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.i(TAG, "标题:【"+title+"】，内容：【"+content+"】，附加参数:【"+extra+"】");
//            mediaPlayer = MediaPlayer.create(context, R.raw.herewego);
//            mediaPlayer.start();
        }else if(intent.getAction().equals(JPushInterface.ACTION_MESSAGE_RECEIVED)){
            Log.i(TAG, "接收到了消息");
            Log.i(TAG, "接收到的消息是:");
        }else if(intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_OPENED)){
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            Intent i = new Intent(context, MessAgeActivity.class);
            i.putExtra("pushId", 1);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        }

    }


}
