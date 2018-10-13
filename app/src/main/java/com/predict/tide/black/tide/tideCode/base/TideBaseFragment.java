package com.predict.tide.black.tide.tideCode.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hwangjr.rxbus.AnRxBus;
import com.hwangjr.rxbus.Bus;

/**
 * Created by black on 2018/3/6.
 */

public abstract class TideBaseFragment<T> extends Fragment {

    protected Bus mRxBus;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxBus = AnRxBus.getBus();
        mRxBus.register(this);
        mPresenter = getmPresenter();
    }

    public abstract T getmPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxBus.unregister(this);
    }
}
