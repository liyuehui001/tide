package com.predict.tide.black.tide.tideCode.module.mainActivity;

import java.util.WeakHashMap;

/**
 * Created by black on 2018/4/17.
 */

public interface MainContract {
    void getPortData(WeakHashMap<String,String> map);

    void getInChinaData(WeakHashMap<String,String> map);

    void getTideData(WeakHashMap<String,String> map);


    void getlat(WeakHashMap<String,String> map);
}
