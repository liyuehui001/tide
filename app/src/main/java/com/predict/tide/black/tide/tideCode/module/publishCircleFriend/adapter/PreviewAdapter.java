package com.predict.tide.black.tide.tideCode.module.publishCircleFriend.adapter;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.module.publishCircleFriend.adapter.viewholder.PreviewHolder;
import com.predict.tide.black.tide.tideCode.module.publishCircleFriend.bean.ImageBean;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/29.
 */

public class PreviewAdapter extends RecyclerView.Adapter<PreviewHolder> {
    private ArrayList<ImageBean> mImageLists;
    private Context mContext;
    private LayoutInflater inflater;
    public PreviewAdapter(ArrayList<ImageBean> list,Context context){
        this.mImageLists = list;
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public PreviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_preview,null);
        PreviewHolder holder = new PreviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PreviewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mImageLists.size();
    }
}
