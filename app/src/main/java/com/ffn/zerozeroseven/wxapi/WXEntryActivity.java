package com.ffn.zerozeroseven.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ffn.zerozeroseven.utlis.LogUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by Admin on 2018/4/12.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private String access_token;
    private String refresh_token;
    private String openid;
    private String thirdInfo;
    private String unionid;
    private IWXAPI api;
    public static String code;
    public static final String TAG = WXEntryActivity.class.getSimpleName();
    public static BaseResp resp = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, "wx189141e4085fa0d1", false);
        api.registerApp("wx189141e4085fa0d1");
        //如果没回调onResp，八成是这句没有写
//        api.handleIntent(getIntent(), this);
        boolean handleIntent = api.handleIntent(getIntent(), this);
        //下面代码是判断微信分享后返回WXEnteryActivity的，如果handleIntent==false,说明没有调用IWXAPIEventHandler，则需要在这里销毁这个透明的Activity;
        if (handleIntent == false) {
            Log.d(TAG, "onCreate: " + handleIntent);
//            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
//        Log.e("req.errStr=",req+"");
//        Log.d("onResp", "onResp: 成功");
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
        Log.d("onResp", "onResp: 成功");
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                switch (resp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) resp).code;
                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求
                        OkHttpUtils.get().url("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx189141e4085fa0d1&secret=e745b9918c909160a71a7296b5ddc0b0&grant_type=authorization_code&code=" + code)
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("response", response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    access_token = jsonObject.getString("access_token");
                                    refresh_token = jsonObject.getString("refresh_token");
                                    openid = jsonObject.getString("openid");
                                    info();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        break;

                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Log.d(TAG, "onResp: 用户取消");
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Log.d(TAG, "onResp: 发送请求被拒绝");
                finish();
                break;
        }

    }

    private void info() {
        OkHttpUtils.get().url("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid)
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    unionid = jsonObject.getString("unionid");
                    thirdInfo = response;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getuser();
            }
        });
    }

    //登录的方法
    private void getuser() {
        LogUtils.D("response","调用登录接口");
    }
}

