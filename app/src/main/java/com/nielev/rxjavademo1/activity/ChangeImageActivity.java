package com.nielev.rxjavademo1.activity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nielev.rxjavademo1.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Neo on 2016/8/26.
 */
public class ChangeImageActivity extends AppCompatActivity {

    private ImageView iv;
    private Observable<Drawable> mBtnObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image);
        iv = (ImageView) findViewById(R.id.iv);
        mBtnObservable = Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getApplicationContext().getResources().getDrawable(R.mipmap.iv_nielev);
                subscriber.onNext(drawable);
            }
        });
        mBtnObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnObservable.subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        iv.setImageDrawable(drawable);
                    }
                });
            }
        });

    }

}
