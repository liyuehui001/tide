package com.predict.tide.black.tide.tideCode.application;

import android.app.Application;
import android.content.Context;

import com.predict.tide.black.tide.tideCode.tideManager.volleyManager.VolleyManager;
import com.predict.tide.black.tide.tideCode.utils.ToastUtils;

/**
 * Created by black on 2018/3/6.
 */

public class TideApplication extends Application{

    private static Context mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = getApplicationContext();
        ToastUtils.init(mcontext);
        VolleyManager.init(mcontext);
    }

    public static Context getContext(){
        return mcontext;
    }
}
