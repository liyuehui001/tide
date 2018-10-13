package com.predict.tide.black.tide.tideCode.module.circleFriend.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.module.circleFriend.ImageViewActivity;
import com.predict.tide.black.tide.tideCode.module.circleFriend.adapter.bean.Comment;
import com.predict.tide.black.tide.tideCode.module.circleFriend.adapter.bean.FriendCircle;
import com.predict.tide.black.tide.tideCode.utils.MUtils;
import com.predict.tide.black.tide.wieght.CommentsView;
import com.predict.tide.black.tide.wieght.MultiImageView;

import java.util.ArrayList;

/**
 * Created by 86084423 on 2018/4/23.
 */

public class MyselfAdapter extends RecyclerView.Adapter<MyselfAdapter.RecyclerViewHolder>{

    private ArrayList<FriendCircle> lists;
    private Context mContext;
    private ImageLoader imageLoader;
    public MyselfAdapter(Context ctx, ArrayList<FriendCircle> mLists, ImageLoader loader){
        this.mContext = ctx;
        this.lists = mLists;
        this.imageLoader = loader;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_recyclerview_item_friend_circle,null);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.tvTime.setText(lists.get(position).getTime());
        holder.tvName.setText(lists.get(position).getName());
//        holder.ivHead
        imageLoader.displayImage(lists.get(position).getHeadImg(),holder.ivHead, MUtils.returnOptions());

        holder.multi_image.setList(lists.get(position).getImageList());
        holder.tvMainText.setText(lists.get(position).getMainText());
        holder.multi_image.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent mintent = new Intent(mContext, ImageViewActivity.class);
                mintent.putExtra("image_uri",lists.get(position).getImageList().get(i));
                mContext.startActivity(mintent);
            }
        });
        holder.ivEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.etComment.setText("");
            }
        });
        holder.commentView.setList(lists.get(position).getComments());
        holder.commentView.setOnItemClickListener(new CommentsView.onItemClickListener() {
            @Override
            public void onItemClick(int position, Comment bean) {

            }
        });
        holder.commentView.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivHead;
        private TextView tvName;
        private TextView tvTime;
        private ImageView ivZan;
        private MultiImageView multi_image;
        private TextView tvMainText;
        private ImageView ivEnter;
        private TextView etComment;
        private CommentsView commentView;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ivHead = (ImageView) itemView.findViewById(R.id.ivHead);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            ivZan = (ImageView) itemView.findViewById(R.id.ivZan);
            tvMainText = (TextView) itemView.findViewById(R.id.tvMainText);
            ivEnter = (ImageView)itemView.findViewById(R.id.ivEnter);
            multi_image = (MultiImageView)itemView.findViewById(R.id.multi_image);
            etComment = (TextView)itemView.findViewById(R.id.etComment);
            commentView = (CommentsView)itemView.findViewById(R.id.commentView);
        }
    }
}
