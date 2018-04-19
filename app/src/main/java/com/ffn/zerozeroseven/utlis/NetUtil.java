package com.ffn.zerozeroseven.utlis;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by dell on 2017/3/2.
 */

public class NetUtil {


    public static boolean hasNetConnect(Context context){

        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return  networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();

    }


}
