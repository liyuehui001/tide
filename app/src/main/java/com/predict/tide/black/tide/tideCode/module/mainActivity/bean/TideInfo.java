package com.predict.tide.black.tide.tideCode.module.mainActivity.bean;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/17.
 */

public class TideInfo {
    private String name;
    private ArrayList<ArrayList<String>> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }
}
