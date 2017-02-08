package com.nielev.rxjavademo1.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nielev.rxjavademo1.HttpMethods;
import com.nielev.rxjavademo1.ProgressSubscriber;
import com.nielev.rxjavademo1.R;
import com.nielev.rxjavademo1.entity.Course;
import com.nielev.rxjavademo1.entity.Student;
import com.nielev.rxjavademo1.entity.Subject;
import com.nielev.rxjavademo1.listener.SubscribeOnNextListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author: Nielev
 *
 * Company: Oray
 *
 * Date: 2016/6/20 10:15.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Observable<String> myObservable;
    private Subscriber<String> mySubscriber;
    private Subscriber<List<Subject>> mSubscriber;
    private ImageView iv;

    private Button btn;
    private Observable btnObservable;
    private Retrofit retrofit;
    private TextView mTv;


    private SubscribeOnNextListener getTopMovieOnNext;
    private Button mBtn_1;

    //https://api.douban.com/v2/movie/top250?start=0&count=10
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mTv = (TextView) findViewById(R.id.tv);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
//                retrofitGetMovies();
//                retrofit_RxJava_GetMovies();

                break;
            case R.id.btn_1://RxJava基础调用
                startActivity(new Intent(this, HelloRxJavaActivity.class));
                break;
            case R.id.btn_2://异步调用
                startActivity(new Intent(this, AsyncActivity.class));
                break;
            case R.id.btn_3://更换图片
                startActivity(new Intent(this, ChangeImageActivity.class));
                break;
            case R.id.btn_4://处理业务逻辑
                startActivity(new Intent(this, LogicActivity.class));
                break;
            case R.id.btn_5://RXjava的网络请求
                startActivity(new Intent(this, NetRequestActivity.class));
                break;
        }
    }






    @Override
    protected void onStop() {
        super.onStop();
//        myObservable.unsafeSubscribe(mySubscriber);
    }

}
