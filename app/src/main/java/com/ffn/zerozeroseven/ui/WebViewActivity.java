package com.ffn.zerozeroseven.ui;

import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.WebInfo;
import com.ffn.zerozeroseven.view.TopView;



import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.topView)
    TopView topView;

    @Override
    protected int setLayout() {
        return R.layout.activity_showweb;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        webView.addJavascriptInterface(new JSHook(), "hello");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getIntent().getStringExtra("url"));
        topView.setOnTitleListener(new TopView.OnTitleClickListener() {
            @Override
            public void Right() {

            }

            @Override
            public void Back() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void doMain() {

    }

    public class JSHook {
        @JavascriptInterface
        public String requestDate() {
            WebInfo webInfo = new WebInfo();
            webInfo.setId(schoolIId);
            webInfo.setToken(userInfo.getToken());
            webInfo.setPhone(userInfo.getPhone());
            return JSON.toJSONString(webInfo);
        }

        @JavascriptInterface
        public void payMoney() {
            BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    HomeActivity.getmInstance().get().go2Fragment(1);
                }
            }, 500);
            finish();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearCache(true);
    }
}
