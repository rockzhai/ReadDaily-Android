package com.rockzhai.readdaily.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.rockzhai.readdaily.BuildConfig;
import com.rockzhai.readdaily.R;
import com.rockzhai.readdaily.ui.base.BasePresenter;
import com.rockzhai.readdaily.ui.base.MVPBaseActivity;
import com.rockzhai.readdaily.widget.SplashView;

import java.util.Random;

import static com.rockzhai.readdaily.R.id.splash_view;

/**
 * Created by rockzhai on 2017/5/4.
 * Contact Me : zhaidyan@gmail.com
 */

public class SplashActivity extends MVPBaseActivity {

    private static final String TAG = "SplashActivity";

    private Handler mHandler = new Handler();

    private SplashView splash_view;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splash_view = (SplashView) findViewById(R.id.splash_view);
        startLoadingData();
    }

    /**
     * start splash animation
     */
    private void startLoadingData(){
        // finish "loading data" in a random time between 1 and 3 seconds
        Random random = new Random();
        mHandler.postDelayed(this::onLoadingDataEnded, 1000 + random.nextInt(2000));
    }

    private void onLoadingDataEnded(){
        // start the splash animation
        splash_view.splashAndDisappear(new SplashView.ISplashListener(){
            @Override
            public void onStart(){
                // log the animation start event
                if(BuildConfig.DEBUG){
                    Log.e(TAG, "splash started");
                }
            }

            @Override
            public void onUpdate(float completionFraction){
                // log animation update events
                if(BuildConfig.DEBUG){
                    Log.d(TAG, "splash at " + String.format("%.2f", (completionFraction * 100)) + "%");
                }
            }

            @Override
            public void onEnd(){
                // log the animation end event
                if(BuildConfig.DEBUG){
                    Log.d(TAG, "splash ended");
                }
                // free the view so that it turns into garbage
                splash_view = null;
                goToMain();
            }
        });
    }

    public void goToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,0);
    }
}
