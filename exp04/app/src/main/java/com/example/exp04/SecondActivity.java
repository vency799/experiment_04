package com.example.exp04;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        WebView webView = (WebView)findViewById(R.id.mywebview);

        webView.getSettings().getJavaScriptEnabled();

        Uri uri = getIntent().getData();

        String url = uri.getHost();

        webView.loadUrl(url);
    }
}