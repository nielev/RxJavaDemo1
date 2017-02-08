package com.nielev.rxjavademo1.listener;

/**
 * Created by Neo on 2016/8/26.
 */
public interface SubscribeOnNextListener<T> {
    void onNext(T t);
}
