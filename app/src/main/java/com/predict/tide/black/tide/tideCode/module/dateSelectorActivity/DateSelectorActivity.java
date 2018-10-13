package com.predict.tide.black.tide.tideCode.module.dateSelectorActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.staticVar.RequestStr;
import com.predict.tide.black.tide.tideCode.base.TideBaseActivity;
import com.predict.tide.black.tide.tideCode.module.dateSelectorActivity.adapter.DateListAdapter;
import com.predict.tide.black.tide.tideCode.module.mainActivity.MainActivity;
import com.predict.tide.black.tide.tideCode.utils.MUtils;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/21.
 */

public class DateSelectorActivity extends TideBaseActivity<DateSelectorPresenter> implements DateSelectorContract{

    private EditText etDate;
    private ListView lvDates;
    private ArrayList<String> listdates;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selector);
        _init();
    }

    private void _init(){
        etDate = findViewById(R.id.etDate);
        lvDates = findViewById(R.id.lvDates);
        listdates = MUtils.getDateList(15);
        lvDates.setAdapter(new DateListAdapter(this, listdates));
        lvDates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etDate.setText(listdates.get(position));
                Intent mIntent = new Intent(DateSelectorActivity.this, MainActivity.class);
                mIntent.putExtra(RequestStr.startActivityForResultReqeustCode.returnDate,listdates.get(position));
                setResult(RequestStr.startActivityForResultReqeustCode.resultCode1,mIntent);
                DateSelectorActivity.this.finish();
            }
        });
    }

    @Override
    public DateSelectorPresenter getmPresenter() {
        return null;
    }



}
