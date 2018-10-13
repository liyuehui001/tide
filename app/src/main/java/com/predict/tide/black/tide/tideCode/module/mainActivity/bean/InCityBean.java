package com.predict.tide.black.tide.tideCode.module.mainActivity.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/17.
 */

public class InCityBean implements IPickerViewData{
    private String city;
    private ArrayList<String> ports = new ArrayList<>();

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<String> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<String> ports) {
        this.ports = ports;
    }

    @Override
    public String getPickerViewText() {
        return city;
    }
}
