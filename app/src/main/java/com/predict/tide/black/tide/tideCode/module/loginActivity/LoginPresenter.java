package com.predict.tide.black.tide.tideCode.module.loginActivity;

import android.content.Context;

import com.predict.tide.black.tide.staticVar.IUrl;
import com.predict.tide.black.tide.staticVar.RequestStr;
import com.predict.tide.black.tide.tideCode.base.TideBasePresenter;
import com.predict.tide.black.tide.tideCode.module.loginActivity.bean.UserInfoDto;
import com.predict.tide.black.tide.tideCode.tideManager.volleyManager.BaseSubscriber;
import com.predict.tide.black.tide.tideCode.tideManager.volleyManager.VolleyManager;
import com.predict.tide.black.tide.tideCode.utils.GsonHelper;

import org.json.JSONObject;

import java.util.WeakHashMap;

/**
 * Created by black on 2018/4/16.
 */

public class LoginPresenter extends TideBasePresenter implements LoginContract {


    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    public void login(WeakHashMap<String, String> map) {

        VolleyManager.schedulerThread(VolleyManager.RxVolleyRequest(IUrl.LOGIN,map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        String str = jsonObject.toString();

                        UserInfoDto userInfoDto = GsonHelper.convertEntity(str, UserInfoDto.class);

                        if (userInfoDto.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                            mRxBus.post(LoginActivity.LOGIN_SUCCESS,userInfoDto);
                        }else{
                            mRxBus.post(LoginActivity.LOGIN_FAIL,userInfoDto);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
