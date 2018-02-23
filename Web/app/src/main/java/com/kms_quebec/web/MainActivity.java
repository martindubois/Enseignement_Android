package com.kms_quebec.web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.webkit.WebSettings.LOAD_NO_CACHE;
import static com.kms_quebec.web.R.id.idWebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView lWV = ( WebView )( findViewById(idWebView) ); // dynamic_cast

        lWV.setWebViewClient( new WebViewClient() );
        lWV.loadUrl( "http://www.google.ca" );
    }
}
