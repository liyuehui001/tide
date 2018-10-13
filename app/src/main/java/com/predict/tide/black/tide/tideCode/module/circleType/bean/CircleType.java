package com.predict.tide.black.tide.tideCode.module.circleType.bean;

import java.io.Serializable;

/**
 * Created by black on 2018/4/30.
 */
public class CircleType implements Serializable{
    private int id;
    private String typename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
