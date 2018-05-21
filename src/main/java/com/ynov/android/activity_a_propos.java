package com.ynov.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class activity_a_propos extends android.app.Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_propos);

        WebView myWebView = (WebView) findViewById(R.id.activity_a_propos_web_view);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://extranet.ynov.com/home");
    }
}
