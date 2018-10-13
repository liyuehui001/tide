package com.predict.tide.black.tide.tideCode.module.circleFriend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.base.TideBaseActivity;
import com.predict.tide.black.tide.tideCode.module.circleFriend.adapter.MyselfAdapter;
import com.predict.tide.black.tide.tideCode.module.circleFriend.adapter.bean.FriendCircle;
import com.predict.tide.black.tide.tideCode.module.publishCircleFriend.PublishAvtivity;
import com.predict.tide.black.tide.wieght.TopActionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by black on 2018/4/29.
 */

public class CircleFriendAvtivity extends TideBaseActivity<CircleFriendPresenter> implements CircleFriendContract {
    private TopActionBar topActionBar;
    @Override
    public CircleFriendPresenter getmPresenter() {
        return new CircleFriendPresenter(this);
    }


    private PullToRefreshLayout pullToRefreshLayout;
    private RecyclerView recyclerView;
    //    private RecyclerViewAdapter adapter;
    private MyselfAdapter adapterMy;
    private List<String> list;
    private ArrayList<FriendCircle> circleList = new ArrayList<>();

    private static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_friend_activity);

        init();
    }

    private void init(){
        topActionBar = findViewById(R.id.topActionBar);
        topActionBar.getRightSecondTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //限数量的多选(比喻最多9张)
//                比喻最多9张ImageSelectorUtils.openPhoto(CircleFriendAvtivity.this, REQUEST_CODE, false, 9);
                startActivity(new Intent(CircleFriendAvtivity.this, PublishAvtivity.class));
            }
        });
    }

}
