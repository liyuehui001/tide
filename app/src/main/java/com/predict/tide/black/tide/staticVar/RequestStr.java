package com.predict.tide.black.tide.staticVar;

/**
 * Created by 小黑 on 2017/11/3.
 */

public interface RequestStr {
    interface requestCode{
        String requestSuccess = "1";
        String requestFail = "0";
    }

    interface userInfo{
        String username = "USERNAME";
        String password = "PASSWORD";
    }

    interface mainViewType{
        int TYPE_NORMAL = 0;
        int TYPE_GUANGGAO = 1;
    }

    interface portRequest{
        String request_portname = "portname";
        String reqeust_date = "date";
    }

    interface startActivityForResultReqeustCode{
        int requestCode1 = 1;

        int resultCode1 = 1;


        String returnDate = "date";
    }

    interface UserInfoStore{
        String USER_NAME = "username_store";
        String PASS_WORD = "password_store";
        String isRemeberPass = "remember_password";
        String zidong_login = "auto_login";
    }

    interface STATUS{
        String mstatus="ok";
    }

}
