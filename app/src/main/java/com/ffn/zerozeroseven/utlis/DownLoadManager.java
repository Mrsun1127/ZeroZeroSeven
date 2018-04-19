package com.ffn.zerozeroseven.utlis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.fragment.MainFragment;
import com.ffn.zerozeroseven.ui.HomeActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by USER on 2017/1/4.
 */
public class DownLoadManager {

    private static int total;
    private static HttpURLConnection conn;

    public static File getFileFromServer(String path, final ProgressDialog pd, Context context) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            String apkPath = BaseAppApplication.context.getExternalCacheDir() + "zerozeroseven.apk";
            File file = new File(apkPath);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else
            BaseAppApplication.mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showShort("当前应用存储状态不可挂载，请手动到应用市场下载零零7");
                }
            });
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=com.ffn.zerozeroseven");
        intent.setData(content_url);
        context.startActivity(intent);
        {
            return null;
        }
    }

}
