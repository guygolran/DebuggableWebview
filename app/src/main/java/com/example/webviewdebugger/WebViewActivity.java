package com.example.webviewdebugger;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.webview);

        WebView.setWebContentsDebuggingEnabled(true);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://www.facebook.com");

        String newUrl = "https://www.facebook.com";
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            newUrl = (extras.getString("YO") != null) ? extras.getString("YO") : newUrl;
        }
        if (!newUrl.contains("www.")) {
            newUrl = "www." + newUrl;
        }
        if (!newUrl.contains("http://") && !newUrl.contains("https://")) {
            newUrl = "https://" + newUrl;
        }

        mWebView.loadUrl(newUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}