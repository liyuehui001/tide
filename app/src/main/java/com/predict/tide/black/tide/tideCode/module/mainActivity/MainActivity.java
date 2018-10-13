package com.predict.tide.black.tide.tideCode.module.mainActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.github.mikephil.charting.charts.LineChart;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.staticVar.RequestStr;
import com.predict.tide.black.tide.tideCode.base.TideBaseActivity;

import com.predict.tide.black.tide.tideCode.module.ListTideLocalActivity;
import com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.DataAnalysisActivity;
import com.predict.tide.black.tide.tideCode.module.dataAnalysisActivity.bean.MyjwData;
import com.predict.tide.black.tide.tideCode.module.dateSelectorActivity.DateSelectorActivity;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.InChinaBean;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.InCityBean;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.OutCountryBean;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.OutStateBean;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.TideData;
import com.predict.tide.black.tide.tideCode.utils.DynamicLineChartManager;
import com.predict.tide.black.tide.tideCode.utils.MUtils;
import com.predict.tide.black.tide.wieght.TopActionBar;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.WeakHashMap;

public class MainActivity extends TideBaseActivity<MainPresenter> implements MainContract,View.OnClickListener{
    private Button portOut;
    private Button portInChina;
    private LineChart dynamic_chart1;

    private ArrayList<OutCountryBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<Integer> listLatByAddress = new ArrayList<>();

    private ArrayList<InCityBean> optioncitys = new ArrayList<>();
    private ArrayList<ArrayList<String>> optionports = new ArrayList<>();

    private DynamicLineChartManager dynamicLineChartManager1;
    private ArrayList<String> names = new ArrayList<>(); //折线名字集合
    private ArrayList<Integer> colour = new ArrayList<>();//折线颜色集合


    public static final String PORT_LIST_SUCCESS = "LOGIN_SUCCESS";
    public static final String PORT_LIST_FAIL = "LOGIN_FAIL";
    public static final String PORT_LIST_IN_SUCCESS = "LOGIN_SUCCESS";
    public static final String PORT_LIST_IN_FAIL = "LOGIN_FAIL";
    public static final String TIDE_DATA_SUCCESS = "LOGIN_SUCCESS";
    public static final String TIDE_DATA_FAIL = "LOGIN_FAIL";
    public static final String TIDE_DATA_FAIL_NO_DATA = "TIDE_DATA_FAIL_NO_DATA";


    public static final String GET_LAT_BY_ADDRESS_SUCCESS = "GET_LAT_BY_ADDRESS_SUCCESS";
    public static final String GET_LAT_BY_ADDRESS_FAIL = "GET_LAT_BY_ADDRESS_FAIL";

    private ArrayList<Integer> listsy;
    private ArrayList<String> strs;

    private String port_name_date = "";
    private TopActionBar topActionBar;
    private TextView dataAna;
    private TextView etDate;
    private String date;

    private String address = "";
    private TextView localData;

    private TextView rightData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _init();
    }
    private void _init(){
        portInChina = (Button)findViewById(R.id.portInChina);
        portOut = (Button)findViewById(R.id.portOut);
        dynamic_chart1 = (LineChart)findViewById(R.id.dynamic_chart1);
        topActionBar = (TopActionBar)findViewById(R.id.topActionBar);
        dataAna = (TextView)findViewById(R.id.dataAna);
        etDate = (TextView)findViewById(R.id.etDate);
        localData = (TextView)findViewById(R.id.localData);
        rightData = (TextView)findViewById(R.id.rightData);

        portOut.setOnClickListener(this);
        portInChina.setOnClickListener(this);
        dataAna.setOnClickListener(this);
        etDate.setOnClickListener(this);
        localData.setOnClickListener(this);
        rightData.setOnClickListener(this);

        //折线名字
        names.add("获取的潮汐");

        //折线颜色
        colour.add(getResources().getColor(R.color.top_bar_normal_bg));
        colour.add(getResources().getColor(R.color.colorCheng));

        dynamicLineChartManager1 = new DynamicLineChartManager(dynamic_chart1, names.get(0), colour.get(0),new ArrayList<String>());

        ArrayList<Integer> listdata = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            int date = (int) (Math.random() * 400);
            listdata.add(date);
        }
        ArrayList<Integer> maxmin = MUtils.getMaxMin(listdata);

        dynamicLineChartManager1.setYAxis(maxmin.get(0), maxmin.get(1), 15);
        dynamicLineChartManager1.addEntrys(listdata);
        WeakHashMap<String,String> map = new WeakHashMap<>();
        map.put("portname","丹东");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        //获取当前时间
        date = MUtils.getNowDate();
        map.put("date",date);
        this.getTideData(map);

    }

    @Override
    public MainPresenter getmPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void getPortData(WeakHashMap<String, String> map) {
        mPresenter.getPortData(map);

    }

    @Override
    public void getInChinaData(WeakHashMap<String, String> map) {
        mPresenter.getInChinaData(map);
    }

    @Override
    public void getTideData(WeakHashMap<String,String> map) {
        if(!MUtils.strNotnull(map.get(RequestStr.portRequest.request_portname)) ||
                !MUtils.strNotnull(map.get(RequestStr.portRequest.reqeust_date))){
            Toast.makeText(this,"没有选择日期或者港口",Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.getTideData(map);
    }

    @Override
    public void getlat(WeakHashMap<String, String> map) {
        map.put("output","json");
        map.put("key","37492c0ee6f924cb5e934fa08c6b1676");
        mPresenter.getlat(map);
    }
    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(MainActivity.GET_LAT_BY_ADDRESS_SUCCESS)
            }
    )
    public void getjingweidusuccess(MyjwData result){
        result.getData().getPointy();
        listLatByAddress = MUtils.getlistByAddress(Double.parseDouble(result.getData().getPointy()));
        dynamicLineChartManager1.addEntrys(listLatByAddress);
    }
    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(MainActivity.GET_LAT_BY_ADDRESS_FAIL)
            }
    )
    public void getjingweiduerror(MyjwData result){
        Toast.makeText(this,"不能查找到此城市的经纬度",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        WeakHashMap<String,String> map = new WeakHashMap<>();
        switch (viewid){
            case R.id.portInChina:
                this.getInChinaData(map);
                break;
            case R.id.portOut:
                this.getPortData(map);
                break;
            case R.id.dataAna:
                _intoDatasys();
                break;
            case R.id.etDate:
                _startNewActivityForResult();
                break;
            case R.id.localData:
                _startLocalActivity();
                break;
            case R.id.rightData:
                _startRightData();
                break;
                default:
        }
    }

    private void _startRightData(){
        Intent intent = new Intent(MainActivity.this, ShowDateActivity.class);

        intent.putExtra("name",mTideData.getData().getName());
        intent.putIntegerArrayListExtra("tide_list",easylist);
        intent.putStringArrayListExtra("tide_str",easystr);
        startActivity(intent);
    }
    private void _startLocalActivity(){
        Intent intent = new Intent(MainActivity.this, ListTideLocalActivity.class);
        startActivity(intent);
    }
    private void _startNewActivityForResult(){
        Intent mintent = new Intent(this, DateSelectorActivity.class);
        startActivityForResult(mintent,RequestStr.startActivityForResultReqeustCode.requestCode1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RequestStr.startActivityForResultReqeustCode.resultCode1 &&
                requestCode == RequestStr.startActivityForResultReqeustCode.requestCode1){
            String date = data.getStringExtra(RequestStr.startActivityForResultReqeustCode.returnDate);
            etDate.setText(date);
        }
    }

    private void _portOut(){

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

                WeakHashMap<String,String> map = new WeakHashMap<>();
                map.put("portname",options3Items.get(options1).get(options2).get(options3));
                if (etDate.getText().toString()!=null && etDate.getText().toString()!=""){
                    date = etDate.getText().toString();
                }
                map.put("date",date);
                port_name_date = options3Items.get(options1).get(options2).get(options3)+" / "+date;
                address = port_name_date;

                MainActivity.this.getTideData(map);

                Toast.makeText(MainActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
            }
        })

                .setTitleText("港口选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();

    }

    private void _portInChina(){
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = optioncitys.get(options1).getPickerViewText() +
                        optionports.get(options1).get(options2);

                WeakHashMap<String,String> map = new WeakHashMap<>();
                map.put("portname",optionports.get(options1).get(options2));
                if (etDate.getText().toString()!=null && etDate.getText().toString()!=""){
                    date = etDate.getText().toString();
                }
                map.put("date",date);
                port_name_date = optionports.get(options1).get(options2)+" / "+date;
                address = port_name_date;

                MainActivity.this.getTideData(map);

                Toast.makeText(MainActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("港口选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker( optioncitys, optionports);//三级选择器
        pvOptions.show();
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(MainActivity.PORT_LIST_SUCCESS)
            }
    )
    public void getPortListSuccess(OutStateBean outStateBean){
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        ArrayList<OutCountryBean> jsonBeans = outStateBean.getState();

        options1Items = jsonBeans;

        for (int i = 0; i < jsonBeans.size(); i++) {//遍历洲
            ArrayList<String> countryList = new ArrayList<>();//该城市列表（第二级）
            ArrayList<ArrayList<String>> portList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBeans.get(i).getCountry().size(); c++) {//遍历该省份的所有城市
                String countryName = jsonBeans.get(i).getCountry().get(c).getName();
                countryList.add(countryName);//添加城市
                ArrayList<String> country_portlist = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBeans.get(i).getCountry().get(c).getPort() == null
                        || jsonBeans.get(i).getCountry().get(c).getPort().size() == 0) {
                    country_portlist.add("--");
                } else {
                    country_portlist.addAll(jsonBeans.get(i).getCountry().get(c).getPort());
                }
                portList.add(country_portlist);//添加该省所有地区数据
            }

            options2Items.add(countryList);
            options3Items.add(portList);

        }
        _portOut();
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(MainActivity.PORT_LIST_IN_SUCCESS)
            }
    )
    public void getPortListINSuccess(InChinaBean inChinaBean){
        optioncitys = inChinaBean.getCitys();
        for (int i = 0; i < optioncitys.size(); i++) {
            ArrayList<String> ports = new ArrayList<>();
            ports.addAll(optioncitys.get(i).getPorts());
            optionports.add(ports);

        }

        _portInChina();

//        Toast.makeText(MainActivity.this,"数据加载完毕",Toast.LENGTH_SHORT).show();
    }
    private ArrayList<Integer> maxmin = new ArrayList<>();
    private TideData mTideData;

    private ArrayList<Integer> easylist = new ArrayList<>();
    private ArrayList<String> easystr = new ArrayList<>();

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(MainActivity.TIDE_DATA_SUCCESS)
            }
    )
    public void getTideDataSuccess(TideData tideData){
        this.mTideData = tideData;
        listsy = new ArrayList<>();
        strs = new ArrayList<>();

        for (int i = 0; i < tideData.getData().getData().size(); i++) {
            String name = tideData.getData().getData().get(i).get(0);
            String m = name.substring(0,name.length()-3);
            strs.add(MUtils.changeData(name));
            listsy.add(Integer.parseInt(tideData.getData().getData().get(i).get(1)));
        }
        maxmin = MUtils.getMaxMin(listsy);
        for (int i = 0; i < listsy.size(); i++) {
            if (i%2 == 0){
                easylist.add(listsy.get(i));
                easystr.add(strs.get(i));
            }
        }
        dynamicLineChartManager1.clearBefore();
        dynamicLineChartManager1 = new DynamicLineChartManager(dynamic_chart1, port_name_date, colour.get(0),easystr);
        dynamicLineChartManager1.setYAxis(maxmin.get(0),maxmin.get(1),15);
        dynamicLineChartManager1.addEntrys(easylist);





    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(MainActivity.TIDE_DATA_FAIL_NO_DATA)
            }
    )
    public void gettidedatanodata(String response){
        Toast.makeText(this,"您查询的日期离今天过于久远，不能获取潮汐数据",Toast.LENGTH_SHORT).show();
    }

    private void _intoDatasys(){
        if (address.equals("") || address ==null || maxmin ==null){
            Toast.makeText(this,"您还没有选择城市",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, DataAnalysisActivity.class);
        intent.putExtra("address",address);
        intent.putExtra("heightmax",String.valueOf(maxmin.get(0)-50));
        startActivity(intent);
    }

}
