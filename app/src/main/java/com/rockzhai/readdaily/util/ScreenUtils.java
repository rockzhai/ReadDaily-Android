package com.rockzhai.readdaily.util;

import android.content.Context;

/**
 * Created by rockzhai on 2017/4/25.
 * Contact Me : zhaidyan@gmail.com
 */

public class ScreenUtils {
    private  static  ScreenUtils mScreenTools;
    private Context context;
     private  ScreenUtils(Context context) {
         this.context = context.getApplicationContext();
     }

     public static  ScreenUtils instance(Context context) {
         if (mScreenTools == null) {
             mScreenTools = new ScreenUtils(context);
         }
         return mScreenTools;
     }
    // dip to px
    public int dip2px(float f) {
        return (int) (0.5D + (double) (f * getDensity(context)));
    }

    public int dip2px(int i) {
        return (int) (0.5D + (double) (getDensity(context) * (float) i));
    }




    // 获取屏幕数据
    public float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public int getScal() {
        return (100 * getScreenWidth()) / 480;
    }

    public int getScreenDensityDpi() {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public int getScreenHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public int getScreenWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public float getXdpi() {
        return context.getResources().getDisplayMetrics().xdpi;
    }

    public float getYdpi() {
        return context.getResources().getDisplayMetrics().ydpi;
    }

    public int px2dip(float f) {
        float f1 = getDensity(context);
        return (int) (((double) f - 0.5D) / (double) f1);
    }

    public int px2dip(int i) {
        float f = getDensity(context);
        return (int) (((double) i - 0.5D) / (double) f);
    }
    public int get480Height(int i) {
        return (i * getScreenWidth()) / 480;
    }

}
