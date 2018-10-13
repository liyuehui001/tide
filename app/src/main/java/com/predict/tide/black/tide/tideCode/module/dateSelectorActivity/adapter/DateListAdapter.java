package com.predict.tide.black.tide.tideCode.module.dateSelectorActivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.predict.tide.black.tide.R;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/21.
 */

public class DateListAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> mListDates;
    public DateListAdapter(Context context, ArrayList<String> listdates){
        this.mContext = context;
        this.mListDates = listdates;
    }

    @Override
    public int getCount() {
        return mListDates.size();
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

        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_date_list,null);
            viewHolder.tvDate = convertView.findViewById(R.id.tvDate);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvDate.setText(mListDates.get(position));
        return convertView;
    }

    class ViewHolder{
        public TextView tvDate;

    }
}
