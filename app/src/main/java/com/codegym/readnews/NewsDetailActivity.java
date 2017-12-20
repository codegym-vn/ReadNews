package com.codegym.readnews;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

public class NewsDetailActivity extends AppCompatActivity {
    private WebView wvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String titleCategory = getIntent().getStringExtra("CATEGORY");
        getSupportActionBar().setTitle(titleCategory);
        wvData = findViewById(R.id.wvData);
        String url = getIntent().getStringExtra("LINK");
        if (url != null) {
            wvData.loadUrl(url);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
