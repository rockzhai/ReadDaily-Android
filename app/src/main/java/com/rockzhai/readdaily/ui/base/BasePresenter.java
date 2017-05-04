package com.rockzhai.readdaily.ui.base;

import com.rockzhai.readdaily.api.ApiFactory;
import com.rockzhai.readdaily.api.DailyApi;
import com.rockzhai.readdaily.api.GankApi;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by rockzhai on 2017/4/21.
 */

public class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public static final GankApi gankApi = ApiFactory.getGankApiSingleton();

    public static final DailyApi dailyApi = ApiFactory.getDailyApiSingleton();

    public void attachView(V view){
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView(){
        return mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef != null&&mViewRef.get()!=null;
    }

    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
