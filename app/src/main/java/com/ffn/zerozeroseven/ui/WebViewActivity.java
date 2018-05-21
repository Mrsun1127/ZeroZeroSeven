package com.ffn.zerozeroseven.ui;

import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.bean.WebInfo;
import com.ffn.zerozeroseven.bean.requsetbean.WebUserInfo;
import com.ffn.zerozeroseven.utlis.LogUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {
    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.activity_showweb;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        webView.addJavascriptInterface(new JSHook(),"hello");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getIntent().getStringExtra("url"));
    }

    @Override
    protected void doMain() {

    }
    public class JSHook{
        @JavascriptInterface
        public Map<String, String> requestDate(){
            Map<String,String> map=new HashMap<>();
            map.put("id",schoolIId);
            map.put("phone",userInfo.getPhone());
            map.put("token",userInfo.getToken());
            LogUtils.D("tol",schoolIId+userInfo.getPhone()+userInfo.getToken());
            return  map;
        }
    }
}
