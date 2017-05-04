package com.rockzhai.readdaily;

import android.app.Application;
import android.content.Context;

import com.rockzhai.readdaily.util.DBOpenHelper;

/**
 * Created by rockzhai on 2017/4/25.
 * Contact Me : zhaidyan@gamail.com
 */

public class MyApp extends Application {
    public static final  String DB_NAME = "daily.db";

    public static Context mContext;
    public static DBOpenHelper dbOpenHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        dbOpenHelper = new DBOpenHelper(mContext,DB_NAME,null,1);
    }
}
