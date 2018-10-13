package com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.predict.tide.black.tide.staticVar.IUrl;
import com.predict.tide.black.tide.staticVar.RequestStr;
import com.predict.tide.black.tide.tideCode.base.TideBasePresenter;
import com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.bean.MyjwData;
import com.predict.tide.black.tide.tideCode.tideManager.volleyManager.BaseSubscriber;
import com.predict.tide.black.tide.tideCode.tideManager.volleyManager.VolleyManager;
import com.predict.tide.black.tide.tideCode.utils.GsonHelper;

import org.json.JSONObject;

import java.util.WeakHashMap;

/**
 * Created by black on 2018/5/26.
 */

public class DataAnalysisPresenter extends TideBasePresenter implements DataAnaysisContract{
    public DataAnalysisPresenter(Context context) {
        super(context);
    }

    @Override
    public void getWeiDuByCity(WeakHashMap<String, String> map) {

        VolleyManager.schedulerThread(VolleyManager.RxVolleyRequest(IUrl.geocoder,map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        String str = jsonObject.toString();
                        MyjwData data = null;
                        try{
                            data = GsonHelper.convertEntity1(str, MyjwData.class);
                            if (data.getResultCode().equalsIgnoreCase(RequestStr.requestCode.requestSuccess)
                                    && data.getData().getPointy()!=null && !data.getData().getPointy().equalsIgnoreCase("null")){
                                mRxBus.post(DataAnalysisActivity.DataAnalysisstatusOk,data);
                            }else{
                                mRxBus.post(DataAnalysisActivity.DataAnalysisstatusNotok,data);
                            }
                        }catch (JsonSyntaxException e){
                            mRxBus.post(DataAnalysisActivity.DataAnalysisstatusNotok,1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });

    }
}
