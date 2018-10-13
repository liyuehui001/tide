package com.predict.tide.black.tide.tideCode.module.uploadFileActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.staticVar.IUrl;
import com.predict.tide.black.tide.tideCode.utils.MUtils;

/**
 * Created by black on 2018/4/21.
 */

public class UploadFileActivity extends Activity {

    public static final String IMAGE_UNSPECIFIED = "image/*";//随意图片类型
    private static final int CHOOSE_PHOTO=0;
    private ProgressDialog proBar;
    private Button btnUpload;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);

        btnUpload = (Button)findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPictureFromAlbum();
            }
        });
    }


    /**
     * 从系统相冊中选取照片上传
     */
    public void selectPictureFromAlbum( ){
        // 调用系统的相冊
        Intent intent = new Intent( Intent.ACTION_PICK);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                IMAGE_UNSPECIFIED);
        startActivityForResult(intent, CHOOSE_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    String path = getDataColumn(uri,null,null);

                    try {

                        proBar = ProgressDialog.show(this,"ceshi","jindu");
                        proBar.create();
//                        MUtils.uploadFile(this,path, IUrl.BASE_URL+IUrl.uploadFile,"demo",proBar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
                break;
            default:
                break;
        }
    }


    /**
     * 根据uri获取当前路径
     */
    public String getDataColumn(Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";//路径保存在downloads表中的_data字段
        final String[] projection = {column};
        try {
            cursor = getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}
