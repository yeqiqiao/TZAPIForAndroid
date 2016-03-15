package com.tianzunchina.android.api.utils;

import android.app.Activity;

import com.tianzunchina.android.api.R;

import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Picker;

import java.util.ArrayList;

/**
 * Created by admin on 2016/3/11 0011.
 */
public class Camera extends Activity{
    private void pickImages(){

        //You can change many settings in builder like limit , Pick mode and colors
        new Picker.Builder(this,new MyPickListener(), R.style.MIP_theme)
                .build()
                .startActivity();

    }

    private class MyPickListener implements Picker.PickListener {

        @Override
        public void onPickedSuccessfully(final ArrayList<ImageEntry> images) {
//            doSomethingWithImages(images);
        }

        @Override
        public void onCancel() {
            //User canceled the pick activity
        }
    }
}
