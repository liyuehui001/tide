package com.predict.tide.black.tide.tideCode.module;

/**
 * Created by black on 2018/5/31.
 */

public class LocalData {
    private String name_date;
    private String tide_data;
    public LocalData(String name,String tide){
        this.name_date = name;
        this.tide_data = tide;
    }


    public String getName_date() {
        return name_date;
    }

    public void setName_date(String name_date) {
        this.name_date = name_date;
    }

    public String getTide_data() {
        return tide_data;
    }

    public void setTide_data(String tide_data) {
        this.tide_data = tide_data;
    }
}
