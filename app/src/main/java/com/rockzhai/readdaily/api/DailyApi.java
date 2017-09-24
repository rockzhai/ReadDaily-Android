package com.rockzhai.readdaily.api;

import com.rockzhai.readdaily.bean.Essay.Essay;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by rockzhai on 2017/5/2.
 * Contact Me : zhaidyan@gmail.com
 */

public interface DailyApi {
    @GET("article/day?dev=1")
    Observable<Essay> getDailyEssay();
    @GET("article/random?dev=2")
    Observable<Essay> getDailyRssayRandom();

}
