package com.predict.tide.black.tide.tideCode.module.mainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.module.ListItemActivity;
import com.predict.tide.black.tide.tideCode.module.ListTideLocalActivity;
import com.predict.tide.black.tide.tideCode.module.mainActivity.bean.TideData;
import com.predict.tide.black.tide.tideCode.utils.MUtils;

import java.util.ArrayList;

public class ShowDateActivity extends AppCompatActivity {

    private ListView listViewItem;
    private TextView tvName_Date;
    private ArrayList<Integer> easylist  = new ArrayList<>();
    private  ArrayList<String> easystr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_date);
        tvName_Date=(TextView)findViewById(R.id.tvName_Date);
        listViewItem = (ListView)findViewById(R.id.listViewItem);

        easystr =  getIntent().getStringArrayListExtra("tide_str");
        easylist = getIntent().getIntegerArrayListExtra("tide_list");
        String name = getIntent().getStringExtra("name");
        tvName_Date.setText(name);

        Log.e("easylist",easylist.toString());


        listViewItem.setAdapter(new LIstviewAdapter());
    }

    public class LIstviewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return easylist.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(ShowDateActivity.this).inflate(R.layout.item_list_local,null);
            TextView tv = (TextView) view.findViewById(R.id.tvText);
            tv.setText("时间：第"+easystr.get(position)+"小时            潮汐高度"+easylist.get(position)+"cm");

            return view;
        }
    }
}
