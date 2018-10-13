package com.predict.tide.black.tide.tideCode.module.circleType.bean;

import com.predict.tide.black.tide.tideCode.bean.HttpResponse;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/30.
 */

public class CircleTypes extends HttpResponse {
    private ArrayList<CircleType> data;

    public ArrayList<CircleType> getData() {
        return data;
    }

    public void setData(ArrayList<CircleType> data) {
        this.data = data;
    }

}
