package com.predict.tide.black.tide.tideCode.module.mainActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.predict.tide.black.tide.staticVar.IUrl;
import com.predict.tide.black.tide.staticVar.RequestStr;
import com.predict.tide.black.tide.tideCode.base.TideBasePresenter;
import com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.DataAnalysisActivity;
import com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.bean.MyjwData;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.InChinaBean;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.OutStateBean;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.TideData;
import com.predict.tide.black.tide.tideCode.tideManager.volleyManager.BaseSubscriber;
import com.predict.tide.black.tide.tideCode.tideManager.volleyManager.VolleyManager;
import com.predict.tide.black.tide.tideCode.utils.GsonHelper;
import com.predict.tide.black.tide.tideCode.utils.MUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by black on 2018/4/17.
 */

public class MainPresenter extends TideBasePresenter implements MainContract {
    private Context context;
    public MainPresenter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void getPortData(WeakHashMap<String, String> map) {
        //获取国外港口
        VolleyManager.schedulerThread(VolleyManager.RxVolleyRequest(IUrl.getOutPortList,map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        String str = jsonObject.toString();
                        System.out.println(str);
                        OutStateBean outStateBean = GsonHelper.convertEntity(str, OutStateBean.class);

                        if (outStateBean.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                            mRxBus.post(MainActivity.PORT_LIST_SUCCESS,outStateBean);
                        }else{
                            mRxBus.post(MainActivity.PORT_LIST_FAIL,outStateBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });

    }

    @Override
    public void getInChinaData(WeakHashMap<String, String> map) {
        //获取国内港口
        VolleyManager.schedulerThread(VolleyManager.RxVolleyRequest(IUrl.getOutPortInList,map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        String str = jsonObject.toString();
                        System.out.println(str);

                        InChinaBean inChinaBean = GsonHelper.convertEntity(str, InChinaBean.class);

                        if (inChinaBean.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                            mRxBus.post(MainActivity.PORT_LIST_IN_SUCCESS,inChinaBean);
                        }else{
                            mRxBus.post(MainActivity.PORT_LIST_IN_FAIL,inChinaBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void getTideData(final WeakHashMap<String,String> map) {


        VolleyManager.schedulerThread(VolleyManager.RxVolleyRequest(IUrl.gettidedata, map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        String str = jsonObject.toString();
                        Log.e("str",str);
                        SharedPreferences sharedPreferences = context.getSharedPreferences("data_tide",Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString(map.get("portname")+"-"+map.get("date"),str).commit();

                        TideData tideData = GsonHelper.convertEntity(str, TideData.class);
                        if (tideData.getData()==null){
                            mRxBus.post(MainActivity.TIDE_DATA_FAIL_NO_DATA,"");
                        }else{
                            if (tideData.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                                mRxBus.post(MainActivity.TIDE_DATA_SUCCESS,tideData);
                            }else{
                                mRxBus.post(MainActivity.TIDE_DATA_FAIL,tideData);
                            }
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void getlat(WeakHashMap<String, String> map) {
        VolleyManager.schedulerThread(VolleyManager.RxVolleyRequest(IUrl.geocoder,map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        String str = jsonObject.toString();
                        MyjwData data = null;
                        try{
                            data = GsonHelper.convertEntity1(str, MyjwData.class);
                            if (data.getResultCode().equalsIgnoreCase(RequestStr.requestCode.requestSuccess) && data.getData().getPointy()!=null &&data.getData().getPointy()!="null"){
                                mRxBus.post(MainActivity.GET_LAT_BY_ADDRESS_SUCCESS,data);
                            }else{
                                mRxBus.post(MainActivity.GET_LAT_BY_ADDRESS_FAIL,data);
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
