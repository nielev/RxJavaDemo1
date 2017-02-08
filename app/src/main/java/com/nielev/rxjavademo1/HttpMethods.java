package com.nielev.rxjavademo1;

import com.nielev.rxjavademo1.Exception.ApiException;
import com.nielev.rxjavademo1.dao.Service;
import com.nielev.rxjavademo1.entity.HttpResult;
import com.nielev.rxjavademo1.entity.MovieEntity;
import com.nielev.rxjavademo1.entity.Subject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 请求过程封装类
 * Created by Neo on 2016/8/25.
 */
public class HttpMethods {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private Service mMovieService;

    //构造私有方法
    private HttpMethods(){
        //手动创建一个OKHttp并设置时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mMovieService = mRetrofit.create(Service.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingeletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }
    //获取单例
    public static HttpMethods getInstance(){
        return SingeletonHolder.INSTANCE;
    }

    /**
     * 用于获取豆瓣电影top250的数据,Http请求数据的封装
     * @param subscriber 由调用者传过来的观察者对象
     * @param start 起始位置
     * @param count 获取长度
     */
    public void getTopMovie_Response(Subscriber<List<Subject>> subscriber, int start, int count){
        mMovieService.getTopMovie_3(start, count)
                .map(new HttpResultFunc<List<Subject>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 用于获取豆瓣电影Top250的数据
     * @param subscriber 由调用者传过来的观察者对象
     * @param start 起始位置
     * @param count 获取长度
     */
    public void getTopMovies(Subscriber<MovieEntity> subscriber, int start, int count){
        mMovieService.getTopMovie_2(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     * @param <T> Subscriber 真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T> ,T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            if(httpResult.getCount() <= 0){
                throw new ApiException(100);
            }
            return httpResult.getSubjects();
        }
    }

}
