package com.predict.tide.black.tide.tideCode.tideManager.volleyManager;

import android.util.Log;

import com.predict.tide.black.tide.volley.Response;
import com.predict.tide.black.tide.volley.VolleyError;


/**
 * Created by 小黑 on 2017/11/1.
 */

public class VolleyErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Internet-ERROR", error.getMessage(), error);
        error.printStackTrace();
    }
}
