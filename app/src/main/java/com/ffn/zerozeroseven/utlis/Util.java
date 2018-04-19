package com.ffn.zerozeroseven.utlis;

import android.content.Context;
import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * 常用函数
 * Created by CrazyPumPkin on 2016/12/3.
 */

public class Util {
    /**
     * dp转px
     * @param dp
     */
    public static int dp2px(float dp){
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }

    /**
     * sp转px
     * @param sp
     * @return
     */
    public static int sp2px(float sp){
        return (int) (sp * Resources.getSystem().getDisplayMetrics().scaledDensity + 0.5f);
    }
    public static String getTextFromAssets(Context context, String fileName){
        String result = "";
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            result = new String(buffer,"utf-8");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
