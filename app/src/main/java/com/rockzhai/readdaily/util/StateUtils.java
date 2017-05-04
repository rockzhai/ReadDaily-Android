package com.rockzhai.readdaily.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by rockzhai on 2017/4/25.
 *
 * 工具类：状态
 *
 */

public class StateUtils {
    // 网络
    public static boolean isNetworkAvailable(Context context) {
        if (context!=null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info !=null) {
                return info.isAvailable();
            }
        }
        return false;
    }
}
