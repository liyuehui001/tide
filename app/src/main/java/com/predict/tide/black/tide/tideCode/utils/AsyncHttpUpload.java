package com.predict.tide.black.tide.tideCode.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.predict.tide.black.tide.staticVar.IUrl;
import com.predict.tide.black.tide.tideCode.module.publishCircleFriend.PublishAvtivity;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by black on 2018/4/30.
 */

public class AsyncHttpUpload {
    public static void UploadImage(RequestParams params,final Context context) throws FileNotFoundException{
        //创建异步请求对象
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(30000);
        client.addHeader("ContentType", "multipart/form-MyjwData")	;

//        client.post(IUrl.BASE_URL+IUrl.uploadMuliFile, params,new AsyncHttpResponseHandler() {
//            //上传失败监听方法
//
//
//            
//        });

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        try {
            asyncHttpClient.post(IUrl.BASE_URL+IUrl.uploadMuliFile, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
                    Log.e("success","上传成功");
                }

                @Override
                public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {
                    Log.e("failure","上传失败");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void post(ArrayList<File> files,String circleId) throws ClientProtocolException, IOException {



    }
}
