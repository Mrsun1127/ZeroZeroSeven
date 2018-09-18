package com.ffn.zerozeroseven.utlis;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.UserInfo;
import com.ffn.zerozeroseven.view.ConfirmDialog;
import com.ffn.zerozeroseven.view.pop.CustomPopWindow;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by GT on 2017/11/15.
 */

public class ZeroZeroSevenUtils {
    /*
    更改屏幕窗口透明度
 */
    public static  void changeWindowAlfa(Activity context ,float alfa){
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = alfa;
        context.getWindow().setAttributes(params);

    }
    public static String getCacheSize(Context context) {
        try {
            return getFormatSize(getFolderSize(new File(context.getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    private static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    private static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 判断是否在当前主线程
     *
     * @return
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 对手机号码中间四位加密
     *
     * @param s
     * @return
     */
    public static String phoneClose(String s) {
        return s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 返回当前程序版本名  build.gradle
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            int versioncode = pi.versionCode;
            LogUtils.E("versionName:---" + versionName, "versioncode:---" + versioncode);
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }

    //保留两位小数
    public static Double reactMoney(double i) {
        return Double.valueOf(new java.text.DecimalFormat("#.00").format(i));
    }

    //判断是否在 早上9.30 到晚上22.30区间
    public static boolean Date2date() {
        Date date = new Date();
        int h = date.getHours();
        int m = date.getMinutes();
        if (h >= 9 && h < 23) {
            if (h == 9 && m < 30) {
                return false;
            }
            if (h == 22 && m > 30) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean DateTodate(int i, int j) {
        Date date = new Date();
        int h = date.getHours();
        if (h >= i && h < j) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取当前时间
     */
    public static String getCurTime(String req) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(req);// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //去除字符串里面的空格和换行
    public static String replaceBlank(String src) {
        String dest = "";
        if (src != null) {
            Pattern pattern = Pattern.compile("\t|\r|\n|\\s*");
            Matcher matcher = pattern.matcher(src);
            dest = matcher.replaceAll("");
        }
        return dest;
    }

    /**
     * MD5加密
     *
     * @param str
     * @return
     */
    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static File getBitmapFile(Context context, Bitmap bitmap, int i) {
        File file = new File(context.getCacheDir(), System.currentTimeMillis() + "_" + i + ".jpeg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
    public static void requestCallMainifest(Activity context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            int i = context.checkSelfPermission(Manifest.permission.CALL_PHONE);
            if (i != PackageManager.PERMISSION_GRANTED) {
                context.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);
            }
        }
    }
    /**
     * 拨打电话
     *
     * @param context
     * @param phoneNum
     */
    public static void MakingCalls(final Context context, final String phoneNum) {
        final ConfirmDialog dialog = new ConfirmDialog(context);
        dialog.setTitles("拨打电话");
        dialog.setMessages(phoneNum);
        dialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                dialog.dismiss();


                Intent phoneIntent = new Intent("android.intent.action.CALL",
                        Uri.parse("tel:" + phoneNum));
                context.startActivity(phoneIntent);
            }

            @Override
            public void doCancel() {
                dialog.dismiss();
            }
        });
    }

    /**
     * 强制隐藏输入法键盘
     */
    public static void hideInput(Context context, View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */

    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * @param context      当前
     * @param contextClass 目标
     * @param bundle       数据
     */
    public static void SwitchActivity(Context context, Class<?> contextClass, Bundle bundle) {
        Intent intent = new Intent(context, contextClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

    }

    public static void SwitchActivity(Context context, Class<?> contextClass) {
        Intent intent = new Intent(context, contextClass);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

    }

    /**
     * @param context      当前
     * @param contextClass 目标
     * @param bundle       数据
     */
    public static void SwitchActivity(Context context, Class<?> contextClass, Bundle bundle, int code) {
        Intent intent = new Intent(context, contextClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(intent, code);
        ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

    }
    public static void FragmentSwitchActivity(Context context, Class<?> contextClass, android.support.v4.app.Fragment fragment, Bundle bundle, int code) {
        Intent intent = new Intent(context, contextClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        fragment.startActivityForResult(intent, code);
        ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

    }
    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 设置下划线
     *
     * @param tv  控件
     * @param str 内容
     */
    public static void setUnderline(Context context, TextView tv, String str) {
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        tv.setText(str);
        tv.setTextColor(context.getResources().getColor(R.color.underLine));
        tv.getPaint().setFilterBitmap(true);
    }

    /**
     * 判断字符串是否为null
     *
     * @param str
     * @return true 不为空  反之
     */
    public static boolean isNotEmpty(String str) {
        return !TextUtils.isEmpty(str);
    }

    /**
     * 判断是否是手机号码格式
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^1[1-9]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 提示弹出框
     *
     * @param context
     * @param message 提示信息
     */
    public static void hintIsSave(final Context context, String message) {
        final ConfirmDialog dialog = new ConfirmDialog(context);
//        dialog.hideView(ConfirmSingerDialog.Type.LEFT_BUTTON);
        dialog.setTitles("提示");
        dialog.setMessages(message);
        dialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                dialog.dismiss();
                ((Activity) context).finish();
                ((Activity) context).overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
            }

            @Override
            public void doCancel() {
                dialog.dismiss();
            }
        });
    }

    /**
     * 提示弹出框
     *
     * @param context
     * @param message 提示信息
     */
    public static void showCustonPop(final Context context, String message, View view) {
        final CustomPopWindow popWindow = new CustomPopWindow((Activity) context);
        popWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popWindow.setContent(message);
        popWindow.setMlistener(new CustomPopWindow.OnButonClikListener() {
            @Override
            public void BtAgain() {
                popWindow.dismiss();
            }
        });
    }
    /**
     * 提示弹出框
     *
     * @param context
     * @param message 提示信息
     */
    public static void showCustonPop(final Context context, String message, View view, final Activity cls) {
        final CustomPopWindow popWindow = new CustomPopWindow((Activity) context);
        popWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popWindow.setContent(message);
        popWindow.setMlistener(new CustomPopWindow.OnButonClikListener() {
            @Override
            public void BtAgain() {
                BaseAppApplication.getInstance().finishActivity(cls);
                popWindow.dismiss();
            }
        });
    }
    public static void showSleepPop(final Context context, View view) {
        final CustomPopWindow popWindow = new CustomPopWindow((Activity) context);
        popWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popWindow.setBackGroud(R.drawable.sleep);
        popWindow.setgoneLine();
        popWindow.setMlistener(new CustomPopWindow.OnButonClikListener() {
            @Override
            public void BtAgain() {
                popWindow.dismiss();
            }
        });
    }

    public static void showSleepPop(final Context context, String s, View view) {
        final CustomPopWindow popWindow = new CustomPopWindow((Activity) context);
        popWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popWindow.setBackGroud(R.drawable.dayangluo);
        popWindow.setContentText(s);
        popWindow.setgoneLine();
        popWindow.setMlistener(new CustomPopWindow.OnButonClikListener() {
            @Override
            public void BtAgain() {
                popWindow.dismiss();
            }
        });
    }

    public static void showCustonPopToActivity(final Context context, String message, View view, final Class cls) {
        final CustomPopWindow popWindow = new CustomPopWindow((Activity) context);
        popWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popWindow.setContent(message);
        popWindow.setMlistener(new CustomPopWindow.OnButonClikListener() {
            @Override
            public void BtAgain() {
                popWindow.dismiss();
                SwitchActivity(context, cls);
            }
        });
    }

    public static void showCustonPopToShare(final Context context, String message, View view, final UserInfo.DataBean userInfo) {
        final CustomPopWindow popWindow = new CustomPopWindow((Activity) context);
        popWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        popWindow.setContent(message);
        popWindow.setBtText("分享");
        popWindow.setMlistener(new CustomPopWindow.OnButonClikListener() {
            @Override
            public void BtAgain() {
                popWindow.dismiss();
                showShare(context, userInfo);
            }
        });
    }

    private static void showShare(Context context, UserInfo.DataBean userInfo) {
        if (TextUtils.isEmpty(userInfo.getInviteCode())) {
            ToastUtils.showShort("请重新登录");
            return;
        }
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(userInfo.getInviteCode());
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(AppConfig.SHAREURL);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我的邀请码是" + userInfo.getInviteCode());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(AppConfig.LOGOURL);//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(AppConfig.SHAREURL);
        // 启动分享GUI
        oks.show(context);
    }


    /**
     * 程序是否在前台运行
     */
    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = BaseAppApplication.context.getPackageName();
/**
 * 获取Android设备中所有正在运行的App
 */
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
// The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    public static BitmapDrawable getBitmapDraw(Activity activity, int draw) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = activity.getResources().openRawResource(draw);
        Bitmap bm = BitmapFactory.decodeStream(is, null, opt);
        BitmapDrawable bd = new BitmapDrawable(activity.getResources(), bm);
        return bd;
    }
}
