package com.predict.tide.black.tide.tideCode.module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.TideData;
import com.predict.tide.black.tide.tideCode.utils.DynamicLineChartManager;
import com.predict.tide.black.tide.tideCode.utils.GsonHelper;
import com.predict.tide.black.tide.tideCode.utils.MUtils;

import java.util.ArrayList;

public class ListItemActivity extends AppCompatActivity {
    private LineChart dynamic_chart1;
    private ArrayList<Integer> listsy;
    private ArrayList<String> strs;
    private  ArrayList<Integer> maxmin = new ArrayList<>();

    private DynamicLineChartManager dynamicLineChartManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        dynamic_chart1 = (LineChart)findViewById(R.id.dynamic_chart1);
        dynamicLineChartManager1 = new DynamicLineChartManager(dynamic_chart1, "存储数据", getResources().getColor(R.color.top_bar_normal_bg),new ArrayList<String>());


        String str = getIntent().getStringExtra("tide_data");
        Log.e("str",str);
        TideData tideData = GsonHelper.convertEntity(str, TideData.class);

        _handleData(tideData);
    }

    private void _handleData(TideData tideData){
        listsy = new ArrayList<>();
        strs = new ArrayList<>();


        for (int i = 0; i < tideData.getData().getData().size(); i++) {
            String name = tideData.getData().getData().get(i).get(0);
            strs.add(MUtils.changeData(name));
            listsy.add(Integer.parseInt(tideData.getData().getData().get(i).get(1)));

        }
        maxmin = MUtils.getMaxMin(listsy);
        ArrayList<Integer> easylist = new ArrayList<>();
        ArrayList<String> easystr = new ArrayList<>();
        for (int i = 0; i < listsy.size(); i++) {
            if (i%2 == 0){
                easylist.add(listsy.get(i));
                easystr.add(strs.get(i));
            }
        }
        dynamicLineChartManager1 = new DynamicLineChartManager(dynamic_chart1,"存储数据", getResources().getColor(R.color.top_bar_normal_bg),new ArrayList<String>());
        dynamicLineChartManager1.setYAxis(maxmin.get(0),maxmin.get(1),10);
        Log.e("list.size",String.valueOf(easylist.size()));
        dynamicLineChartManager1.addEntrys(easylist);
    }
}
