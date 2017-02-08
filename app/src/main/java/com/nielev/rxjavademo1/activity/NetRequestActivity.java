package com.nielev.rxjavademo1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nielev.rxjavademo1.HttpMethods;
import com.nielev.rxjavademo1.ProgressSubscriber;
import com.nielev.rxjavademo1.R;
import com.nielev.rxjavademo1.dao.Service;
import com.nielev.rxjavademo1.entity.MovieEntity;
import com.nielev.rxjavademo1.entity.Subject;
import com.nielev.rxjavademo1.listener.SubscribeOnNextListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络请求
 * Created by Neo on 2017/2/8.
 */

public class NetRequestActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_request);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                retrofitGetMovies();
                break;
            case R.id.btn_2:
                retrofit_RxJava_GetMovies();
                break;
            case R.id.btn_3:
                retrofit_RxJava_Encapsulating();
                break;
            case R.id.btn_4:
                retrofit_RxJava_Encapsulating_Response();
                break;
        }
    }

    /**
     * 网络请求响应数据的封装
     */
    private void retrofit_RxJava_Encapsulating_Response() {
        SubscribeOnNextListener getTopMovieOnNext = new SubscribeOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                Toast.makeText(NetRequestActivity.this, subjects.toString(), Toast.LENGTH_SHORT).show();
//                mTv.setText(subjects.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie_Response(new ProgressSubscriber<List<Subject>>(getTopMovieOnNext, NetRequestActivity.this), 0, 10);
    }


    /**
     * 封装网络请求
     */
    private void retrofit_RxJava_Encapsulating () {
        Subscriber mSubscriber = new Subscriber<List<Subject>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(NetRequestActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Subject> s) {

            }
        };
        HttpMethods.getInstance().getTopMovies(mSubscriber, 0, 10);
    }

    /**
     * Retrofit和RxJava结合请求
     */
    private void retrofit_RxJava_GetMovies() {
        String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Service movieService = retrofit.create(Service.class);

        movieService.getTopMovie_2(0, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(NetRequestActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        Toast.makeText(NetRequestActivity.this, movieEntity.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 单纯retrofit请求
     */
    private void retrofitGetMovies() {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service movieService = retrofit.create(Service.class);
        Call<MovieEntity> call = movieService.getTopMovie_1(0, 3);
        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                Toast.makeText(NetRequestActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {

            }
        });
//        movieService.getTopMovie(0, 10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<MovieEntity.Subject>>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(NetRequestActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<MovieEntity.Subject> movieEntity) {
//                       Log.e("Retrofit", movieEntity.toString());
//                    }
//                });
    }
}
