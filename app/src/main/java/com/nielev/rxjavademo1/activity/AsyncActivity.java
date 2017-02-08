package com.nielev.rxjavademo1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nielev.rxjavademo1.R;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Neo on 2016/8/26.
 */
public class AsyncActivity extends AppCompatActivity {

    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                async();
            }
        });
    }

    /**
     * 异步调用
     */
    private void async() {
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())//指定subscribe()发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//指定subscribe的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i("RXJAVA_2",integer+"");
                        Toast.makeText(AsyncActivity.this, integer+"", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
