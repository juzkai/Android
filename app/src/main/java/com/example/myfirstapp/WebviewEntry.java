package com.example.myfirstapp;

import android.content.Loader;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebviewEntry extends AppCompatActivity {
    WebView mWebView;
    ProgressBar webViewProgress;
    private Boolean firstInit;
    private HashMap<String, String> webViewData;
    private String webViewEntryData;
    AndroidInterfaceForJS androidInterfaceForJS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_entry);
        mWebView = (WebView) findViewById(R.id.webView);
        webViewProgress = (ProgressBar)findViewById(R.id.webViewProgress);
        WebSettings webSettings = mWebView.getSettings();
        // 设置支持JavaScript
        webSettings.setJavaScriptEnabled(true);

        // 设置自适应屏幕，下面两者合用
        webSettings.setUseWideViewPort(true); // 将图片调整到适合WebView的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        // 支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 开启缓存功能
        webSettings.setDomStorageEnabled(true); // 开启DOM storage 功能
        webSettings.setDatabaseEnabled(true); // 开启DOM database storage 功能
        webSettings.setAppCacheEnabled(true); // 开启Application Caches 功能
        webViewData = new HashMap<String, String>();
        webViewData.put("token", "e25afb0e1eb81bfd0ba2071eea27b99a");
        webViewData.put("theme", "my56");
        JSONObject jsonObj = new JSONObject(webViewData);
        webViewEntryData = jsonObj.toString();
        firstInit = true;
        mWebView.loadUrl("http://192.168.2.110:8086");
        // 设置不用自带浏览器打开，直接显示在当前的WebView
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            // onPageFinished 会执行两次，加个firstInit控制
            @Override
            public void onPageFinished(WebView view, String url) {
                if (firstInit) {
                    final int version = Build.VERSION.SDK_INT;
                    Log.d("向JS传递数据----", webViewEntryData);
                    // evaluateJavascript 在Android 4.4+ 才可使用,所以需要进行版本判定
                    if (version < 18) {
                        view.loadUrl("javascript:mainEntry(" + webViewEntryData +")");
                    } else {
                        view.evaluateJavascript("javascript:mainEntry(" + webViewEntryData +")", null);
                    }
                    firstInit = false;
                }
                super.onPageFinished(view, url);
            }
        });
        // WebChromeClient类：辅助WebView处理JavaScript的对话框，网站图标，网站标题等
        // 设置为默认，否则JS中的alert框不会弹出
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    // 设置加载进度条
                    webViewProgress.setProgress(newProgress);
                } else {
                    // 进度完成，隐藏进度条
                    webViewProgress.setVisibility(View.INVISIBLE);
                }
            }
        });
        androidInterfaceForJS = new AndroidInterfaceForJS(WebviewEntry.this);
        mWebView.addJavascriptInterface(androidInterfaceForJS, "WebViewApp");
    }
    // 点击返回上一页而不是退出当前WebView
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    // 销毁WebView
    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup)mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
