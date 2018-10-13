package com.predict.tide.black.tide.tideCode.module.mainActivity.bean;

import com.contrarywind.interfaces.IPickerViewData;
import com.predict.tide.black.tide.tideCode.bean.HttpResponse;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/17.
 */

public class OutStateBean extends HttpResponse implements IPickerViewData{
    private ArrayList<OutCountryBean> state;

    public ArrayList<OutCountryBean> getState() {
        return state;
    }

    public void setState(ArrayList<OutCountryBean> state) {
        this.state = state;
    }

    @Override
    public String getPickerViewText() {
        return this.getMessage();
    }
}
