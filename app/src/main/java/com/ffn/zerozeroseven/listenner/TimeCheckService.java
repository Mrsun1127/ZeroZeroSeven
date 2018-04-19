package com.ffn.zerozeroseven.listenner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.Calendar;
import java.util.Random;


public class TimeCheckService extends Service {

    public static final String ACTION = "com.roger.rogersesiment.activity.service.PushOrderService";
    private static final String TAG = "指令服务";



    private Random random = new Random();



    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(runnable).start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Calendar cal = Calendar.getInstance();
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    if (hour >= 9 && hour <= 23) { // 白天9点-23点
                        // 每个5分钟向服务器发送一次请求
//                        1*60*1000=300000
                        startService();
                        Intent intent = new Intent();
                        intent.setAction("com.mrsun.time.go");
                        //发送广播
                        sendBroadcast(intent);
                        Thread.sleep(1000*60);//600000
//                        Thread.sleep(15000);//600000
                    }else{
                        Thread.sleep(1000*60*30);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };




    private void startService() {
        AlarmManager manager = (AlarmManager) this.getApplicationContext()
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this.getApplicationContext(),
                TimeCheckService.class);
        intent.setAction(TimeCheckService.ACTION);
        PendingIntent pendingIntent = PendingIntent.getService(
                this.getApplicationContext(), random.nextInt(), intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        // long triggerAtTime = SystemClock.elapsedRealtime() + 1000;
        /**
         * 闹钟的第一次执行时间，以毫秒为单位，可以自定义时间，不过一般使用当前时间。需要注意的是，本属性与第一个属性（type）密切相关，
         * 如果第一个参数对应的闹钟使用的是相对时间
         * （ELAPSED_REALTIME和ELAPSED_REALTIME_WAKEUP），那么本属性就得使用相对时间
         * （相对于系统启动时间来说）， 比如当前时间就表示为：SystemClock.elapsedRealtime()；
         * 如果第一个参数对应的闹钟使用的是绝对时间（RTC、RTC_WAKEUP、POWER_OFF_WAKEUP），那么本属性就得使用绝对时间，
         * 比如当前时间就表示为：System.currentTimeMillis()。
         */
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                System.currentTimeMillis(), 30 * 1000, pendingIntent);
    }
}
