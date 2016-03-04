package com.tianzunchina.android.api.control;

import android.app.Application;

/**
 * Created by admin on 2016/3/4 0004.
 */
public class TZApplication extends Application {
    private static TZApplication instance;

    public static TZApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}