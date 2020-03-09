package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.myfirstapp.entity.Item;

/**
 * Created by JUZENGKAI on 2018/9/6.
 */

public class AndroidInterfaceForJS {
    private Activity activity;
    private Dialog dialog;
    private WebView webView;
    public AndroidInterfaceForJS(Activity activity) {
        this.activity = activity;
    }
    public AndroidInterfaceForJS(Activity activity, WebView webView) {
        this.activity = activity;
        this.webView = webView;
    }

    /**
     *  退出当前当前WebView
     */
    @JavascriptInterface
    public void exitWebView () {
        activity.finish();
    }
    @JavascriptInterface
    public void chooseImage () {
        final ActionSheet actionSheet = new ActionSheet(activity);
        actionSheet.addItem(new Item(0, "拍照"));
        actionSheet.addItem(new Item(1,"照片"));
        actionSheet.setItemClickListener(new ActionSheet.ItemClickListener() {
            @Override
            public void onItemClick(int index) {
                if (dialog == null) {
                    dialog = new Dialog();
                }
                actionSheet.destory();
                dialog.showToast(activity, "你选择了第" + Integer.toString(index + 1) + "项！");
            }
        });
        actionSheet.show();
    }
    @JavascriptInterface
    public void loadAlipay (String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }
}
