package com.tianzunchina.android.api.widget.photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tianzunchina.android.api.R;
import com.tianzunchina.android.api.widget.gesture.GestureImageView;

import java.io.File;

/**
 * Created by admin on 2015/5/25.
 */
public class PreviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        Intent intent = getIntent();
        GestureImageView imageView = new GestureImageView(this);
        imageView.setAdjustViewBounds(true);
        String path = intent.getStringExtra("path");
        if(path == null){
            String url = intent.getStringExtra("imageUrl");
            Picasso.with(this).load(url).error(R.mipmap.pic_loading).resize(800, 600).into(imageView);
        } else {
            File file = new File(path);
            Picasso.with(this).load(file).error(R.mipmap.pic_loading).resize(800, 600).into(imageView);
        }
        setContentView(imageView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }
}