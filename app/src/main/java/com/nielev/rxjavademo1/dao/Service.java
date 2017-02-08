package com.nielev.rxjavademo1.dao;

import com.nielev.rxjavademo1.entity.HttpResult;
import com.nielev.rxjavademo1.entity.MovieEntity;
import com.nielev.rxjavademo1.entity.Subject;

import java.util.List;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: Nielev
 * <p/>
 * Company: Oray
 * <p/>
 * Date: 2016/6/21 11:55.
 */
public interface Service {
    @FormUrlEncoded
    @POST("/api/login/{value}")
    Call doLoginIn(@Path("value") String path, @FieldMap TreeMap<String,String> paramMap);

    //单纯用Retrofit
    @GET("top250")
    Call<MovieEntity> getTopMovie_1(@Query("start") int start, @Query("count") int count);

    //Retrofit和RxJava结合
    @GET("top250")
    Observable<MovieEntity> getTopMovie_2(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie_3(@Query("start") int start, @Query("count") int count);
}
