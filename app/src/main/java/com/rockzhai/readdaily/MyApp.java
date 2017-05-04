package com.rockzhai.readdaily;

import android.app.Application;
import android.content.Context;

/**
 * Created by rockzhai on 2017/4/25.
 * Contact Me : zhaidyan@gamail.com
 */

public class MyApp extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
