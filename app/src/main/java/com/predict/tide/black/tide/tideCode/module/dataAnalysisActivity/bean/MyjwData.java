package com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.bean;

import com.predict.tide.black.tide.tideCode.bean.HttpResponse;

/**
 * Created by black on 2018/5/26.
 */

public class MyjwData extends HttpResponse{
    private JingWeiDu data;

    public JingWeiDu getData() {
        return data;
    }

    public void setData(JingWeiDu data) {
        this.data = data;
    }
}
