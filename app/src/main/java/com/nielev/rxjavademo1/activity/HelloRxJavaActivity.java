package com.nielev.rxjavademo1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nielev.rxjavademo1.R;

import rx.Observable;
import rx.Subscriber;

/**
 *
 * RxJava最简单应用
 *
 * Created by Neo on 2016/8/26.
 */
public class HelloRxJavaActivity extends AppCompatActivity {

    private Observable<String> myObservable;
    private Subscriber<String> mySubscriber;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_rx_java);
        mBtn = (Button) findViewById(R.id.btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myObservable = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        Log.i("RXJAVA__","call...");
                        subscriber.onNext("Hello,World!");
                        subscriber.onNext("Hello,RXJAVA!");
                        subscriber.onNext("Hello,I'm Coming!");
                        subscriber.onCompleted();
                    }
                });
                mySubscriber = new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i("RXJAVA__","onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("RXJAVA__",s);
                        Toast.makeText(getApplication(),s,Toast.LENGTH_SHORT).show();
                    }
                };
//
                myObservable.subscribe(mySubscriber);
            }
        });
    }
}
