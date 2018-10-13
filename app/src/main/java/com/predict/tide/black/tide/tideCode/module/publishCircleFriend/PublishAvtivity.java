package com.predict.tide.black.tide.tideCode.module.publishCircleFriend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.loopj.android.http.RequestParams;
import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.base.TideBaseActivity;
import com.predict.tide.black.tide.tideCode.module.circleType.CircleTypeActivity;
import com.predict.tide.black.tide.tideCode.module.circleType.bean.CircleType;
import com.predict.tide.black.tide.tideCode.module.loginActivity.LoginActivity;
import com.predict.tide.black.tide.tideCode.module.loginActivity.bean.UserInfoDto;
import com.predict.tide.black.tide.tideCode.module.publishCircleFriend.bean.MyCirclrFriendId;
import com.predict.tide.black.tide.tideCode.utils.AsyncHttpUpload;
import com.predict.tide.black.tide.tideCode.utils.MUtils;
import com.predict.tide.black.tide.wieght.ItemContainer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Created by black on 2018/4/29.
 */

public class PublishAvtivity extends TideBaseActivity<PublishPresenter> implements PublishContract,View.OnClickListener {

    public static int PublishReqeustCode = 2;
    public static int REQUEST_CODE_circle_type= 1;

    public static final String PUBLISH_TEXT_SUCCESS = "PUBLISH_TEXT_SUCCESS";
    public static final String PUBLISH_TEXT_FAIL = "PUBLISH_TEXT_FAIL";

    public static final String PUBLISH_IMAGE_SUCCESS="PUBLISH_IMAGE_SUCCESS";
    public static final String PUBLISH_IMAGE_FAIL = "PUBLISH_IMAGE_FAIL";

    private TextView tvCancel;
    private TextView tvPublish;
    private EditText etInput;
    private RelativeLayout rl_bottom;

    private ItemContainer ll_add_image;
    private ImageView ivAddImage;
    private TextView tvCircleSelectType;

    private CircleType type;
    private ArrayList<String> images;

    @Override
    public PublishPresenter getmPresenter() {
        return new PublishPresenter(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_activity);

        init();

        setOnClick();
    }

    private void init(){
        etInput = (EditText)findViewById(R.id.etInput);

        tvCancel = (TextView)findViewById(R.id.tvCancel);
        tvPublish = (TextView)findViewById(R.id.tvPublish);
        rl_bottom = (RelativeLayout)findViewById(R.id.rl_bottom);
        tvCircleSelectType = findViewById(R.id.tvCircleSelectType);

        ll_add_image = findViewById(R.id.ll_add_image);
        ivAddImage = findViewById(R.id.ivAddImage);
    }

    private void setOnClick(){
        tvCancel.setOnClickListener(this);
        tvPublish.setOnClickListener(this);
        rl_bottom.setOnClickListener(this);

        ll_add_image.setOnClickListener(this);
        ivAddImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        switch (viewid){
            case R.id.tvCancel:
                PublishAvtivity.this.finish();
                break;
            case R.id.tvPublish:
                //发送数据给后台
                WeakHashMap<String ,String> map = new WeakHashMap<>();
                this.publishCircleFriend(map);
                break;
            case R.id.rl_bottom:
                startactivityforMyList();
                break;
            case R.id.ivAddImage:
                intoAddImage();
                break;
                default:

        }
    }

    private void startactivityforMyList(){
        Intent intent = new Intent(PublishAvtivity.this,CircleTypeActivity.class);
        startActivityForResult(intent, REQUEST_CODE_circle_type);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode ==RESULT_OK){
            if (requestCode == REQUEST_CODE_circle_type){
                //从circletype跳转过来的
                type = (CircleType) data.getSerializableExtra("type");
                tvCircleSelectType.setText(type.getTypename());

            }else if (requestCode == PublishReqeustCode && data!=null){
                images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                MUtils.addImageIntoView(images,ll_add_image,this);
            }
        }
    }

    private void intoAddImage(){
        ImageSelectorUtils.openPhoto(PublishAvtivity.this, PublishReqeustCode, false, 9);
    }


    @Override
    public void publishCircleFriend(WeakHashMap<String, String> map) {
        if (type == null){
            Toast.makeText(PublishAvtivity.this,"您还没有选择类型",Toast.LENGTH_SHORT).show();
            return ;
        }
        int mycircleFriendType;
        if (images == null || images.size()==0){//没有选择图片
            mycircleFriendType = 0;
        }else{
            mycircleFriendType = 1;
        }

        map.put("userid","1");
        map.put("content",etInput.getText().toString().trim());
        map.put("circle_type_id",String.valueOf(type.getId()));
        map.put("type",String.valueOf(mycircleFriendType));
        mPresenter.publishCircleFriend(map);

    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(PublishAvtivity.PUBLISH_TEXT_SUCCESS)
            }
    )
    public void addTextSuccess(MyCirclrFriendId circlrFriendId){


        if (images == null && images.size() == 0){
            return;
        }else{
            try{

                RequestParams params = new RequestParams();
                for (int i = 0; i < images.size(); i++) {

                    File file = new File(images.get(i));
                    params.put("upload["+i+"]",file);
                }
                params.put("circle_id",circlrFriendId.getCircleFriendId());

                AsyncHttpUpload.UploadImage(params,PublishAvtivity.this);
            }catch (FileNotFoundException e){
                Log.e("filenotfound","找不到文件");
            }
        }
    }

    




}
