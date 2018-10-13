package com.predict.tide.black.tide.tideCode.module.circleType;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.base.TideBaseActivity;
import com.predict.tide.black.tide.tideCode.module.circleType.bean.CircleType;
import com.predict.tide.black.tide.tideCode.module.circleType.bean.CircleTypes;
import com.predict.tide.black.tide.tideCode.module.publishCircleFriend.PublishAvtivity;

import java.util.WeakHashMap;

/**
 * Created by black on 2018/4/29.
 */

public class CircleTypeActivity extends TideBaseActivity<CircleTypePresenter> implements CircleTypeContract{

    public static final String GETCIRCLETYPELIST_SUCCESS  = "GETCIRCLETYPELIST_SUCCESS";
    public static final String GETCIRCLETYPELIST_FAIL = "GETCIRCLETYPELIST_FAIL";

    private LinearLayout ll_list;
    private ListView lvData;
    private TextView tvLoading;


    @Override
    public CircleTypePresenter getmPresenter() {
        return new CircleTypePresenter(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_type);

        _init();

        WeakHashMap<String,String> map = new WeakHashMap<>();
        this.getAllList(map);
    }
    private void _init(){
        ll_list = findViewById(R.id.ll_list);
        lvData = findViewById(R.id.lvData);
        tvLoading = findViewById(R.id.tvLoading);

        ll_list.setVisibility(View.GONE);
        tvLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void getAllList(WeakHashMap<String, String> map) {
        mPresenter.getAllList(map);
    }



    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(GETCIRCLETYPELIST_SUCCESS)
            }
    )
    public void getCircleSuccessList(final CircleTypes cricleType){

        lvData.setAdapter(new CircleTypeListAdapter(cricleType,CircleTypeActivity.this));

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CircleType type= cricleType.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra("type",type);
                setResult(RESULT_OK,intent);
                CircleTypeActivity.this.finish();
            }
        });

        ll_list.setVisibility(View.VISIBLE);
        tvLoading.setVisibility(View.GONE);

    }
}
