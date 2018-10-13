package com.predict.tide.black.tide.tideCode.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by black on 2018/3/6.
 */

public class TideRecyclerViewHolder extends RecyclerView.ViewHolder{
    private int viewType;
    private View view;

    public TideRecyclerViewHolder(View viewitem){
        super(viewitem);
        this.view = viewitem;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
