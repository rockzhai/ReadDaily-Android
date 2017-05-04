package com.rockzhai.readdaily.api;

/**
 * Created by rockzhai on 2017/4/25.
 * Contact Me : zhaidyan@gmail.com
 */

public class ApiFactory {
    protected static final Object monitor = new Object();
    static GankApi gankApiSingleton = null;
    static DailyApi dailyApiSingleton = null;

    //return Singleton
    public static GankApi getGankApiSingleton() {
        synchronized (monitor) {
            if (gankApiSingleton == null) {
                gankApiSingleton = new ApiRetrofit().getGankApiService();
            }
            return gankApiSingleton;
        }
    }

    public static DailyApi getDailyApiSingleton() {
        synchronized (monitor) {
            if (dailyApiSingleton == null) {
                dailyApiSingleton = new ApiRetrofit().getDailyApiService();
            }
            return dailyApiSingleton;
        }
    }


}
