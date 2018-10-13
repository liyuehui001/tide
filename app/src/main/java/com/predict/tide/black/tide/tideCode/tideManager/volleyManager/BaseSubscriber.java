package com.predict.tide.black.tide.tideCode.tideManager.volleyManager;

import rx.Subscriber;

import static com.predict.tide.black.tide.tideCode.tideManager.volleyManager.ApiErrorHelper.handleError;


/**
 * Created by 小黑 on 2017/11/3.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    public BaseSubscriber() {
    }
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        handleError(e);
    }

    @Override
    public void onCompleted() {

    }
}
