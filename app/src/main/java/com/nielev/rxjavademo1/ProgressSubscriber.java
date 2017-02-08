package com.nielev.rxjavademo1;

import android.content.Context;
import android.widget.Toast;

import com.nielev.rxjavademo1.listener.ProgressCancelListener;
import com.nielev.rxjavademo1.listener.SubscribeOnNextListener;

import rx.Subscriber;

/**
 * Created by Neo on 2016/8/26.
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {
    private SubscribeOnNextListener mSubscribeOnNextListener;
    private Context mContext;
    public ProgressSubscriber(SubscribeOnNextListener subscribeOnNextListener, Context context){
        this.mSubscribeOnNextListener = subscribeOnNextListener;
        this.mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
        Toast.makeText(mContext, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(mContext, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T t) {
        mSubscribeOnNextListener.onNext(t);
    }


    @Override
    public void onCancelProgress() {
        if(!this.isUnsubscribed()){
            this.unsubscribe();
        }
    }
}
