package com.predict.tide.black.tide.tideCode.module;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.predict.tide.black.tide.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListTideLocalActivity extends AppCompatActivity {

    private ListView listLocal;
    private ArrayList<LocalData> list = new ArrayList<>();

    private ArrayList<LocalData> listTemp = new ArrayList<>();
    private EditText etDate;
    private ImageView ivSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tide_local);
        listLocal = (ListView) findViewById(R.id.listLocal);
        ivSearch = (ImageView)findViewById(R.id.ivSearch);
        etDate = (EditText)findViewById(R.id.etDate);
        SharedPreferences sharedPreferences = getSharedPreferences("data_tide", Context.MODE_PRIVATE);
        final ListAdapter adapter = new ListAdapter();
        listLocal.setAdapter(adapter);


        Map<String,?> map = sharedPreferences.getAll();
        for(Map.Entry<String, ?>  entry : map.entrySet()){
            LocalData data = new LocalData(entry.getKey(),entry.getValue().toString());

            list.add(data);
            listTemp.add(data);
        }
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etDate.getText().toString();
                if (str.equals("")){
                    listTemp = list;
                }else{
                    listTemp.clear();
                    for (int i = 0; i < listTemp.size(); i++) {

                        if (list.get(i).getName_date().contains(str)){
                            listTemp.add(list.get(i));
                        }
                    }

                }
                adapter.notifyDataSetChanged();

            }
        });


    }


    public class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listTemp.size();
        }

        @Override
        public Object getItem(int position) {
            return listTemp.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(ListTideLocalActivity.this).inflate(R.layout.item_list_local,null);
            TextView tv = (TextView) view.findViewById(R.id.tvText);
            tv.setText(listTemp.get(position).getName_date());

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListTideLocalActivity.this,ListItemActivity.class);
                    intent.putExtra("tide_data",listTemp.get(position).getTide_data());
                    startActivity(intent);
                }
            });

            return view;
        }
    }
}


