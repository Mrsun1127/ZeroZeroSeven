package com.ffn.zerozeroseven.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.ffn.zerozeroseven.R;
import com.ffn.zerozeroseven.base.AppConfig;
import com.ffn.zerozeroseven.base.BaseActivity;
import com.ffn.zerozeroseven.base.BaseAppApplication;
import com.ffn.zerozeroseven.bean.WebInfo;
import com.ffn.zerozeroseven.utlis.ScreenUtils;
import com.ffn.zerozeroseven.view.TopView;


import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class WebViewActivity extends BaseActivity {
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.topView)
    TopView topView;
    private Bitmap bit;
    @Bind(R.id.pb_watch)
    ProgressBar pb_watch;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getIntent().getStringExtra("url"));
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    pb_watch.setVisibility(View.GONE);
                } else {
                    // 加载中
                    pb_watch.setVisibility(View.VISIBLE);
                    pb_watch.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)) {
            topView.setTopText(title);
        }
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

    public static Bitmap loadBitmapFromViewBySystem(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight() - 100, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(screenshot);
        canvas.translate(-v.getScrollX(), -v.getScrollY());//我们在用滑动View获得它的Bitmap时候，获得的是整个View的区域（包括隐藏的），如果想得到当前区域，需要重新定位到当前可显示的区域
        v.draw(canvas);// 将 view 画到画布上
        return screenshot;
    }

    @Override
    protected void doMain() {
    }

    private void showShare(final String filename) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("https://www.baidu.com/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(filename);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if (platform.getName().equalsIgnoreCase(QQ.NAME)) {
                    paramsToShare.setText(null);
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                    paramsToShare.setImagePath(filename);
                } else if (platform.getName().equalsIgnoreCase(QZone.NAME)) {
                    paramsToShare.setText(null);
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                    paramsToShare.setImagePath(filename);
                } else if (platform.getName().equalsIgnoreCase(Wechat.NAME)) {
                    paramsToShare.setText(null);
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                    paramsToShare.setImagePath(filename);
                } else if (platform.getName().equalsIgnoreCase(WechatMoments.NAME)) {
                    paramsToShare.setText(null);
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                    paramsToShare.setImagePath(filename);
                }

            }
        });
        oks.setUrl("https://www.baidu.com/");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("test");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://www.baidu.com/");

// 启动分享GUI
        oks.show(this);
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

        @JavascriptInterface
        public void sharePhoto() {
            BaseAppApplication.mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bit = loadBitmapFromViewBySystem(webView);
                    String fileUrl = ScreenUtils.saveMyBitmap(System.currentTimeMillis() + "", bit);
                    showShare(fileUrl);
                }
            }, 500);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bit != null && !bit.isRecycled()) {
            bit.recycle();
        }
        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearCache(true);
    }
}
