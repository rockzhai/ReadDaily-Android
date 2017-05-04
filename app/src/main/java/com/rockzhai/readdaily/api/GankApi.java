package com.rockzhai.readdaily.api;

import com.rockzhai.readdaily.bean.gank.Gank;
import com.rockzhai.readdaily.bean.gank.GankData;
import com.rockzhai.readdaily.bean.gank.Meizhi;
import com.rockzhai.readdaily.bean.gank.Video;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rockzhai on 2017/4/25.
 * Contact Me : zhaidyan@gmail.com
 */

public interface GankApi {

    @GET("data/福利/10/{page}")
    Observable<Meizhi> getMeizhiData(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getGankData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/休息视频/10/{page}")
    Observable<Video> getVideoData(@Path("page") int page);



}
