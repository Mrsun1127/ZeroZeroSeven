package com.ffn.zerozeroseven.utlis;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.ui.HomeActivity;
import com.ffn.zerozeroseven.ui.LoginActivity;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 二次封装的okhttp网络工具类
 * 集中处理错误码 只需要传入参数对象 调用成功的回调即可
 * Created by GT on 2017/11/29.
 */

public class OkGoUtils {
    private Call call;
    private KProgressHUD hud;
    private Context context;
    private String json;

    public OkGoUtils(final Context context) {
        this.context = context;
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                hud = KProgressHUD.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true)
                        .setWindowColor(context.getResources().getColor(R.color.text_secondary_color))
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f);
            }
        });

    }

    protected void showLoadProgress() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
                hud.setDetailsLabel("正在加载中")
                        .show();
            }
        });
    }

    protected void disLoadProgress() {
        BaseAppApplication.mainHandler.post(new Runnable() {
            @Override
            public void run() {
               try {
                   if (hud != null) {
                       hud.dismiss();
                   }
               }catch (Exception e){}
            }
        });
    }

    public void httpPostJSON(Object obj, boolean isToken, final boolean showLoad) {
        if(showLoad){
            showLoadProgress();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //换成自己的ip就行
        String url = AppConfig.BaseUrl;
        OkHttpClient client = new OkHttpClient();//创建okhttp实例
        RequestBody body = RequestBody.create(JSON, JsonUtil.parseBeanToJson(obj));
        if (isToken) {
            String token="";
            if(BaseAppApplication.getInstance().getLoginUser()!=null){
                token=BaseAppApplication.getInstance().getLoginUser().getToken();
            }
//            if(TextUtils.isEmpty(token)){
//                gotoLogin();
//                return;
//            }
            Request request = new Request.Builder()
                    .addHeader("platform", "android")
                    .addHeader("Authorization", "Bearer " + token)
                    .url(url)
                    .post(body)
                    .build();
            call = client.newCall(request);
        } else {
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            call = client.newCall(request);
        }

        call.enqueue(new Callback() {
            //请求失败时调用
            @Override
            public void onFailure(Call call, IOException e) {
                if(showLoad){
                    disLoadProgress();
                }
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort("网络异常，请稍后再试！");
//                        loadError.onErrorLoad();
//                        ZeroZeroSevenUtils.openSetting((Activity) context);
                    }
                });

            }

            //请求成功时调用
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(showLoad){
                    disLoadProgress();
                }
                json = response.body().string();
                String code = JsonUtil.getFieldValue(json, "code");
//                if ("401".equals(code)) {
//                    gotoLogin();
//                }
                if("-101".equals(code)){
                    HomeActivity.getmInstance().get().showpop();
                }
                LogUtils.E("response", json);
                BaseAppApplication.mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                            try {
                                loadSuccess.onSuccLoad(json);
                            }catch (Exception e){
                            }

                    }
                });
            }
        });

    }

    public void gotoLogin() {
        BaseAppApplication.getInstance().setLoginUser(null);
        ZeroZeroSevenUtils.SwitchActivity(context, LoginActivity.class);
        BaseAppApplication.getInstance().clearActivityList();
    }

    private OnLoadSuccess loadSuccess;

    public void setOnLoadSuccess(OnLoadSuccess loadSuccess) {
        this.loadSuccess = loadSuccess;
    }

    public interface OnLoadSuccess {
        void onSuccLoad(String response);
    }


    private OnLoadError loadError;

    public void setOnLoadError(OnLoadError loadError) {
        this.loadError = loadError;
    }

    public interface OnLoadError {
        void onErrorLoad();
    }
}
