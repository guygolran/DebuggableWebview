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

        String urlString = "https://www.google.com";
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            urlString = (extras.getString(Constants.URL_KEY) != null) ? extras.getString(Constants.URL_KEY) : urlString;
        }

        if(!urlString.startsWith("www.") && !urlString.startsWith("http://") && !urlString.startsWith("https://")) {
            urlString = "www." + urlString;
        }
        if(!urlString.startsWith("http://") && !urlString.startsWith("https://")){
            urlString = "http://" + urlString;
        }

        mWebView.loadUrl(urlString);
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