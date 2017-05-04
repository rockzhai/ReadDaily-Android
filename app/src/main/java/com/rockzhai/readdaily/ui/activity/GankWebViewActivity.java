package com.rockzhai.readdaily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.ui.base.MVPBaseActivity;
import com.rockzhai.readdaily.ui.presenter.GankWebPresenter;
import com.rockzhai.readdaily.ui.view.IGankWebView;

/**
 * Created by rockzhai on 2017/5/1.
 * Contact Me : zhaidyan@gmail.com
 */

public class GankWebViewActivity extends MVPBaseActivity<IGankWebView, GankWebPresenter> implements IGankWebView {
    private ProgressBar mProgressBar;
    private WebView webView;
    public static final String GANK_URL = "gank_url";
    private String gank_url;

    @Override
    protected GankWebPresenter createPresenter() {
        return new GankWebPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gank_webview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_progress);
        webView = (WebView) findViewById(R.id.url_web);

        parseIntent();
        mPresenter.setWebView(gank_url);
    }

    @Override
    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, GankWebViewActivity.class);
        intent.putExtra(GankWebViewActivity.GANK_URL, url);
        return intent;
    }

    /**
     * 得到Intent传递的数据
     */
    private void parseIntent() {
        gank_url = getIntent().getStringExtra(GANK_URL);
    }

    @Override
    public boolean canBack() {

        return true;
    }
}
