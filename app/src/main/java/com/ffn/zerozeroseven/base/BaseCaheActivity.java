package com.ffn.zerozeroseven.base;

import android.os.Bundle;
import android.text.TextUtils;

import com.aitangba.swipeback.SwipeBackActivity;
import com.ffn.zerozeroseven.utlis.JsonUtil;
import com.ffn.zerozeroseven.utlis.MrsunAppCacheUtils;
import com.ffn.zerozeroseven.view.WaitingDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * 一个强大的网络数据缓存机制界面
 * Created by Mrsun on 2017/9/15.
 */
public abstract class BaseCaheActivity extends SwipeBackActivity {
    HashMap<String, String> okMap = new HashMap<>();
    HashMap<String, String> requestMap = new HashMap<>();
    public Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ImmersionBar.with(this).init();
        runMainThread();
    }

    public void DocacheOrRequest() {
        String cache = MrsunAppCacheUtils.get(BaseCaheActivity.this).getAsString(setCacheKey());
        if (!TextUtils.isEmpty(cache)) {
            resoluTion(cache);
        } else {
            RequestCacheJson();
        }
    }

    private WaitingDialog dialog;

    protected void showLoadProgress() {
        dialog = new WaitingDialog(BaseCaheActivity.this);
        dialog.show();
    }
    protected void showLoadProgress(String s) {
        dialog = new WaitingDialog(BaseCaheActivity.this);
        dialog.showInfo(s);
    }
    protected void disLoadProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
    public void RequestCacheJson() {
        OkHttpUtils.post()
                .url(setUrl().get("url"))
                .params(setRequstMap())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        doError();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MrsunAppCacheUtils.get(BaseCaheActivity.this).put(setCacheKey(), response, setCacheTime());
                        doSuccess(response);
                    }
                });
    }

    /**
     * 主线程走的方法
     */
    protected abstract void runMainThread();

    /**
     * 缓存不为空时走的方法
     *
     * @param cache
     */
    protected abstract void resoluTion(String cache);

    /**
     * 缓存处理时设置缓存时间
     *
     * @return
     */
    protected abstract int setCacheTime();

    /**
     * 缓存处理时设置key值
     *
     * @return
     */
    protected abstract String setCacheKey();

    /**
     * 对okMap的url键 设置url的值
     * 没有请求数据的界面传null
     *
     * @return
     */
    protected abstract HashMap<String, String> setUrl();

    /**
     * 对requestMap设置请求参数值
     * 没有请求数据的界面传null
     *
     * @return
     */
    protected abstract HashMap<String, String> setRequstMap();

    /**
     * 设置Activity的布局
     *
     * @return 返回的是xml布局
     */
    protected abstract int setLayout();

    /**
     * 访问接口成功时对respone做的操作
     * 没有请求数据的界面默认空实现
     *
     * @param response
     */
    protected abstract void doSuccess(String response);

    /**
     * 访问接口失败时走的方法
     * 没有请求数据的界面默认空实现
     */
    protected abstract void doError();
    public void httpPostJSON(Object obj){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //换成自己的ip就行
        String url = AppConfig.BaseUrl;
        OkHttpClient client = new OkHttpClient();//创建okhttp实例
        RequestBody body=RequestBody.create(JSON, JsonUtil.parseBeanToJson(obj));
        Request request = new Request.Builder()
                .addHeader("platform","android")
                .url(url)
                .post(body)
                .build();
        call = client.newCall(request);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
