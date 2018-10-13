package com.predict.tide.black.tide.tideCode.module.circleFriend.adapter.bean;

import java.util.ArrayList;

/**
 * Created by 86084423 on 2018/4/23.
 */

public class FriendCircle {
    private String headImg;
    private String name;
    private String time;
    private int zan;
    private String mainText;
    private ArrayList<String> imageList;
    private ArrayList<Comment> comments;

    public String getHeadImg() {
        return headImg == null ? "" : headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public ArrayList<String> getImageList() {
        if (imageList == null) {
            return new ArrayList<>();
        }
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public String getMainText() {
        return mainText == null ? "" : mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public ArrayList<Comment> getComments() {
        if (comments == null) {
            return new ArrayList<>();
        }
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
