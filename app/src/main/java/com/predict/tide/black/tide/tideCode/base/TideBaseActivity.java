package com.predict.tide.black.tide.tideCode.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.hwangjr.rxbus.AnRxBus;
import com.hwangjr.rxbus.Bus;

/**
 * Created by black on 2018/3/6.
 */

public abstract class TideBaseActivity<T> extends FragmentActivity {
    protected Bus mRxBus;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = getmPresenter();
        mRxBus = AnRxBus.getBus();
        mRxBus.register(this);
    }

    public abstract T getmPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxBus.unregister(this);
    }
}
