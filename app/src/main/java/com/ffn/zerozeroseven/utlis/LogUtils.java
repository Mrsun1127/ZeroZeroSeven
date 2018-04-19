package com.ffn.zerozeroseven.utlis;

import android.util.Log;

/**
 * Created by archerlee on 16/3/18.
 */
public class LogUtils {
    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static String TAG = "CHH";

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    // 下面四个是默认tag的函数
    public static void I(String msg) {

        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void D(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void E(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void V(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void I(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void D(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void E(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void V(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

}
