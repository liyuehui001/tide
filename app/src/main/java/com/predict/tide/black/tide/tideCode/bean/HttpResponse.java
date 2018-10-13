package com.predict.tide.black.tide.tideCode.bean;

import java.io.Serializable;

/**
 * Created by black on 2018/3/6.
 */

public class HttpResponse implements Serializable {
    private String resultCode;
    private String message;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
