package com.tianzunchina.android.api.widget.photo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tianzunchina.android.api.R;

/**
 * 照片控件
 * CraetTime 2016-3-14
 * @author SunLiang
 */
public class TZPhotoBoxView extends RelativeLayout {
    public ImageView ivPhoto;
    public ImageView ivDel;

    public TZPhotoBoxView(Context context) {
        super(context);
        init(context);
    }

    public TZPhotoBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.view_photo_box, this, true);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        ivDel = (ImageView) findViewById(R.id.ivDel);
    }
}
