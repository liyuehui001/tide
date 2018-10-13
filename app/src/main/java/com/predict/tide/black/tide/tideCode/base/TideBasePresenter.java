package com.predict.tide.black.tide.tideCode.base;

import android.content.Context;

import com.hwangjr.rxbus.AnRxBus;
import com.hwangjr.rxbus.Bus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by black on 2018/3/6.
 */

public class TideBasePresenter implements Serializable{
    protected Context mContext;
    protected Bus mRxBus;
    protected HashMap<String, String> headersMap;

    public TideBasePresenter(Context context){
        this.mContext = context;
        mRxBus = AnRxBus.getBus();
        mRxBus.register(this);
        initHeader();

    }
    protected void initHeader(){
        headersMap = new HashMap<>();
    }
}
