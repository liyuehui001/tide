package com.predict.tide.black.tide.tideCode.module.mainActivity.bean;

import com.contrarywind.interfaces.IPickerViewData;
import com.predict.tide.black.tide.tideCode.bean.HttpResponse;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/17.
 */

public class OutCountryBean implements IPickerViewData{
    private String name;
    private ArrayList<Country> country;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Country> getCountry() {
        return country;
    }

    public void setCountry(ArrayList<Country> country) {
        this.country = country;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class Country{
        private String name;
        private ArrayList<String> port;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<String> getPort() {
            return port;
        }

        public void setPort(ArrayList<String> port) {
            this.port = port;
        }
    }
}
