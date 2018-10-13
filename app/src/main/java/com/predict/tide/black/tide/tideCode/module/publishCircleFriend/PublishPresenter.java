package com.predict.tide.black.tide.tideCode.module.publishCircleFriend;

import android.content.Context;

import com.predict.tide.black.tide.staticVar.IUrl;
import com.predict.tide.black.tide.staticVar.RequestStr;
import com.predict.tide.black.tide.tideCode.base.TideBasePresenter;
import com.predict.tide.black.tide.tideCode.module.loginActivity.LoginActivity;
import com.predict.tide.black.tide.tideCode.module.loginActivity.bean.UserInfoDto;
import com.predict.tide.black.tide.tideCode.module.publishCircleFriend.bean.MyCirclrFriendId;
import com.predict.tide.black.tide.tideCode.tideManager.volleyManager.BaseSubscriber;
import com.predict.tide.black.tide.tideCode.tideManager.volleyManager.VolleyManager;
import com.predict.tide.black.tide.tideCode.utils.GsonHelper;

import org.json.JSONObject;

import java.util.WeakHashMap;

/**
 * Created by black on 2018/4/29.
 */

public class PublishPresenter extends TideBasePresenter implements PublishContract {
    public PublishPresenter(Context context) {
        super(context);
    }

    @Override
    public void publishCircleFriend(WeakHashMap<String,String> map) {
        VolleyManager.schedulerThread(VolleyManager.RxVolleyRequest(IUrl.addCircleFriendItem,map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        String str = jsonObject.toString();

                        MyCirclrFriendId circlrFriendId = GsonHelper.convertEntity(str, MyCirclrFriendId.class);

                        if (circlrFriendId.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                            mRxBus.post(PublishAvtivity.PUBLISH_TEXT_SUCCESS,circlrFriendId);
                        }else{
                            mRxBus.post(PublishAvtivity.PUBLISH_TEXT_FAIL,circlrFriendId);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });

    }
}
