package com.predict.tide.black.tide.tideCode.module.mainActivity.bean;

import com.predict.tide.black.tide.tideCode.bean.HttpResponse;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/17.
 */

public class InChinaBean extends HttpResponse {
    private ArrayList<InCityBean> citys;

    public ArrayList<InCityBean> getCitys() {
        return citys;
    }

    public void setCitys(ArrayList<InCityBean> citys) {
        this.citys = citys;
    }
}
