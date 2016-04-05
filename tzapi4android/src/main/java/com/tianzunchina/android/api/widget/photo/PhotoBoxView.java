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
public class PhotoBoxView extends RelativeLayout {
    public ImageView ivThumb;
    public ImageView ivDel;

    public PhotoBoxView(Context context) {
        super(context);
    }

    public PhotoBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_photo_box, this, true);
        ivThumb = (ImageView) findViewById(R.id.ivPhoto);
        ivDel = (ImageView) findViewById(R.id.ivDel);
    }
}
