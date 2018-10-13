package com.predict.tide.black.tide.tideCode.tideManager.volleyManager;

import android.content.Context;

import com.predict.tide.black.tide.staticVar.IUrl;
import com.predict.tide.black.tide.tideCode.application.TideApplication;
import com.predict.tide.black.tide.tideCode.utils.NetworkState;
import com.predict.tide.black.tide.volley.AuthFailureError;
import com.predict.tide.black.tide.volley.Request;
import com.predict.tide.black.tide.volley.RequestQueue;
import com.predict.tide.black.tide.volley.VolleyError;
import com.predict.tide.black.tide.volley.toolbox.JsonObjectRequest;
import com.predict.tide.black.tide.volley.toolbox.RequestFuture;
import com.predict.tide.black.tide.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by 小黑 on 2017/11/1.
 */

public class VolleyManager {
    private static final String volleyBaseUrl = IUrl.BASE_URL;
    private static RequestQueue mRequestQueue;

    public static byte[] syncObj=new byte[0];

    private VolleyManager(){}

    public static void init(Context context){
        if (mRequestQueue == null){
            synchronized (syncObj){
                mRequestQueue = Volley.newRequestQueue(context);
            }
        }
        mRequestQueue.start();
    }
    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public static Observable<JSONObject> RxVolleyRequest(final String url, final Map<String,String> map,
                                                         final Map<String, String> headersMap){
        //表示这个被观察者被订阅的时候，调用这个call方法，

        return Observable.create(new Observable.OnSubscribe<JSONObject>(){
            @Override
            public void call(Subscriber<? super JSONObject> subscriber) {

                try{
                    subscriber.onNext(postRequest(subscriber,url,map,headersMap));
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }

    public static Observable<JSONObject> RxVolleyRequestGet(final String url, final Map<String,String> map,
                                                         final Map<String, String> headersMap){
        //表示这个被观察者被订阅的时候，调用这个call方法，

        return Observable.create(new Observable.OnSubscribe<JSONObject>(){
            @Override
            public void call(Subscriber<? super JSONObject> subscriber) {

                try{
                    subscriber.onNext(getRequest(subscriber,url,map,headersMap));
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }



    public static JSONObject postRequest(final Subscriber subscriber,
                                         String url,
                                         Map<String,String> map,
                                         final Map<String, String> headersMap) throws Exception {

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,volleyBaseUrl+url,
                new JSONObject(map), future,
                new VolleyErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        subscriber.onError(error);
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headersMap;
            }
        };
        mRequestQueue.add(request);
        return future.get();
    }



    public static JSONObject getRequest(final Subscriber subscriber,
                                         String url,
                                         Map<String,String> map,
                                         final Map<String, String> headersMap) throws Exception {

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,IUrl.baiduMap+url,
                new JSONObject(map), future,
                new VolleyErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        subscriber.onError(error);
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headersMap;
            }
        };
        mRequestQueue.add(request);
        return future.get();
    }




    public static <T>Observable<T>schedulerThread(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //检查网络情况，
                        if (!NetworkState.NetworkIsConnect(TideApplication.getContext()))
                        {
                            throw new ApiException("网络未连接");
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }




}
