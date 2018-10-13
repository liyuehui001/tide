package com.predict.tide.black.tide.tideCode.module.circleFriend.adapter.bean;

/**
 * Created by 86084423 on 2018/4/23.
 */

public class UserInfo {
    private int userid;
    private String username;
    private String headimage;

    public UserInfo(int userid, String username, String headimage) {
        this.userid = userid;
        this.username = username;
        this.headimage = headimage;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadimage() {
        return headimage == null ? "" : headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }
}
