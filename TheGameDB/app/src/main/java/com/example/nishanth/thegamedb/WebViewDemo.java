package com.example.nishanth.thegamedb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewDemo extends AppCompatActivity {
    String s;
    String m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);
        getSupportActionBar().setTitle("WebView");
        s = getIntent().getExtras().getString("videoURL");
        if(s!= null && s.trim().length() !=0) {
            m = Uri.parse(s).getHost();

            WebView myWebView = (WebView) findViewById(R.id.webview);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new MyWebViewClient());

            myWebView.loadUrl(s);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            //Log.d("demo",Uri.parse(url).getHost());
            return  false;

/*            if (Uri.parse(url).getHost().equals(m)) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }else {
                // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }*/
        }
    }
}
