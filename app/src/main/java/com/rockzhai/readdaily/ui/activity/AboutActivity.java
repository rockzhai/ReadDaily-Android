package com.rockzhai.readdaily.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.widget.TextView;

import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.base.MVPBaseActivity;

/**
 * Created by rockzhai on 2017/5/1.
 * Contact Me : zhaidyan@gmail.com
 */

public class AboutActivity extends MVPBaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        tv_blog.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tv_blog.getText().toString()));
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            startActivity(intent);
        });
        tv_github.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tv_github.getText().toString()));
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            startActivity(intent);
        });
    }


    private void initView() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("欢迎 | 有梦");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.coll_color));
        tv_blog = (TextView) findViewById(R.id.tv_blog);
        tv_github = (TextView) findViewById(R.id.tv_github);

    }

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView tv_github;
    private TextView tv_blog;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about;
    }

}
