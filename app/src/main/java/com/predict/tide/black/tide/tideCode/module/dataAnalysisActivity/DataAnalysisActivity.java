package com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.base.TideBaseActivity;
import com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.bean.JingWeiDu;
import com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.bean.MyjwData;
import com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.bean.Result;
import com.predict.tide.black.tide.tideCode.module.loginActivity.LoginActivity;
import com.predict.tide.black.tide.tideCode.module.loginActivity.bean.UserInfoDto;
import com.predict.tide.black.tide.tideCode.module.mainActivity.MainActivity;
import com.predict.tide.black.tide.tideCode.utils.DynamicLineChartManager;
import com.predict.tide.black.tide.tideCode.utils.MUtils;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Created by black on 2018/4/18.
 */

public class DataAnalysisActivity extends TideBaseActivity<DataAnalysisPresenter> implements DataAnaysisContract{

    public static final String DataAnalysisstatusOk = "DataAnalysisstatusok";
    public static final String DataAnalysisstatusNotok = "DataAnalysisstatusnotok";


    private DynamicLineChartManager dynamicLineChartManager1;

    private LineChart dynamic_chart1;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_analysis_activity);
        _init();
    }

    @Override
    public DataAnalysisPresenter getmPresenter() {
        return new DataAnalysisPresenter(this);
    }

    private void _init(){
        dynamic_chart1 = (LineChart)findViewById(R.id.dynamic_chart1);

        dynamicLineChartManager1 = new DynamicLineChartManager(dynamic_chart1, "计算数据", getResources().getColor(R.color.top_bar_normal_bg),new ArrayList<String>());
        dynamicLineChartManager1.setYAxis(70, 0, 15);
        String heightzhen = getIntent().getStringExtra("heightmax");

        String address = getIntent().getStringExtra("address");
        WeakHashMap<String,String> map = new WeakHashMap<>();

        map.put("address",address);
        this.getWeiDuByCity(map);
    }

    @Override
    public void getWeiDuByCity(WeakHashMap<String, String> map) {
        map.put("output","json");
        map.put("key","37492c0ee6f924cb5e934fa08c6b1676");
        mPresenter.getWeiDuByCity(map);
    }


    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(DataAnalysisActivity.DataAnalysisstatusOk)
            }
    )
    public void getjingweidu(MyjwData result){
        Log.e("weidu",String.valueOf(result.getData().getPointy()));

        ArrayList<Integer> listaddress = MUtils.getlistByAddress(Double.parseDouble(result.getData().getPointy()));

        Log.e("list",listaddress.toString());
        dynamicLineChartManager1.addEntrys(listaddress);

    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(DataAnalysisActivity.DataAnalysisstatusNotok)
            }
    )
    public void getjingweiduerror(MyjwData result){
        Toast.makeText(this,"不能查找到这个城市的经纬度",Toast.LENGTH_SHORT).show();

    }

}
