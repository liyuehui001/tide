package com.predict.tide.black.tide.tideCode.module.circleFriend.adapter.bean;

/**
 * Created by 86084423 on 2018/4/23.
 */

public class Comment {
    private UserInfo replyUser;
    private UserInfo commentUser;
    private String content;
    private String time;
    private Comment comment;

    public UserInfo getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(UserInfo replyUser) {
        this.replyUser = replyUser;
    }

    public UserInfo getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(UserInfo commentUser) {
        this.commentUser = commentUser;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
