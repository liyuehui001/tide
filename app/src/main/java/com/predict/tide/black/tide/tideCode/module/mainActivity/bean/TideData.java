package com.predict.tide.black.tide.tideCode.module.mainActivity.bean;

import com.predict.tide.black.tide.tideCode.bean.HttpResponse;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/17.
 */

public class TideData extends HttpResponse {
    private TideInfo data;

    public TideInfo getData() {
        return data;
    }

    public void setData(TideInfo data) {
        this.data = data;
    }
}
