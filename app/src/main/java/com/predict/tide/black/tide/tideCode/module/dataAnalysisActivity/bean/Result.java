package com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.bean;



/**
 * Created by black on 2018/5/26.
 */

public class Result{
    private Location location;
    private int precise;
    private int confidence;
    private String level;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPrecise() {
        return precise;
    }

    public void setPrecise(int precise) {
        this.precise = precise;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
