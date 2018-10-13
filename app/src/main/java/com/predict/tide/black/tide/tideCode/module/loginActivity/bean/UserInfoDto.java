package com.predict.tide.black.tide.tideCode.module.loginActivity.bean;

import com.predict.tide.black.tide.tideCode.bean.HttpResponse;

/**
 * Created by black on 2018/4/16.
 */

public class UserInfoDto extends HttpResponse {
    private String name;
    private String password;

    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
