package com.predict.tide.black.tide.tideCode.module.circleFriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.tideCode.utils.MUtils;

public class ImageViewActivity extends AppCompatActivity {
    private ImageView ivMain;
    private ImageLoader loader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ivMain  = (ImageView) findViewById(R.id.ivMain);
        String uri = getIntent().getStringExtra("image_uri");
        loader.displayImage(uri,ivMain, MUtils.returnOptions());

        ivMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageViewActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        loader.clearMemoryCache();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
