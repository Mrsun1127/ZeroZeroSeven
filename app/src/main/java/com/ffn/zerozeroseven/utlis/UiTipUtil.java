package com.ffn.zerozeroseven.utlis;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public abstract class UiTipUtil {

	private static Toast toast;

	/**
	 * 显示Toast弹窗
	 * 
	 * @param context
	 *            上下文
	 * @param text
	 *            显示内容
	 */
	public static void showToast(Context context, String text) {
		if (toast != null) {
			toast.cancel();
		}
		toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		toast.show();
	}

	public static void showToast(Context context, int resId) {
		if (toast != null) {
			toast.cancel();
		}
		toast = Toast.makeText(context, context.getString(resId),
				Toast.LENGTH_SHORT);
		toast.show();
	}

	public static void showLongToast(Context context, int resId) {
		if (toast != null) {
			toast.cancel();
		}
		toast = Toast.makeText(context, context.getString(resId),
				Toast.LENGTH_LONG);
		toast.show();
	}


	public static void showLongToast(Context context, String text) {
		if (toast != null) {
			toast.cancel();
		}
		toast = Toast.makeText(context,text,
				Toast.LENGTH_LONG);
		toast.show();
	}

	public static void showLongSnack(View view,String msg){
		Snackbar.make(view,msg,Snackbar.LENGTH_LONG).show();
	}

	public static void showLongSnack(View view,int msgId){
		Snackbar.make(view,msgId,Snackbar.LENGTH_LONG).show();
	}

	public static void showShortSnack(View view,String msg){
		Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
	}
}
