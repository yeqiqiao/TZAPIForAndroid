package com.tianzunchina.android.api.control;

import android.app.Application;

/**
 * Created by admin on 2016/3/14 0014.
 */
public class TZApplication extends Application {
    private static TZApplication instence;

    public static TZApplication getInstence(){
        return instence;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instence = this;
    }
}
