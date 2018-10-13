package com.predict.tide.black.tide.tideCode.module.circleType;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.module.circleType.bean.CircleTypes;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/30.
 */

public class CircleTypeListAdapter extends BaseAdapter {
    private CircleTypes types;
    private Context mContext;

    public CircleTypeListAdapter(CircleTypes circleTypes,Context ctx){
        this.types = circleTypes;
        this.mContext = ctx;

    }
    @Override
    public int getCount() {
        return types.getData().size();
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
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_item_circle_type,null,false);
        ((TextView)view.findViewById(R.id.tvType)).setText(types.getData().get(position).getTypename());
        return view;
    }

}
