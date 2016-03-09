package com.tianzunchina.android.api.log;

import android.util.Log;

/**
 * Created by admin on 2016/3/9 0009.
 */
public class TZLog {
    private static int showLv = 0;
    private static boolean isRecord = false;

    /**
     * 初始化参数
     * @param showLevel {@linkplain TZLogLevel 日志显示最低等级}  默认0时不显示
     */
    public static void init(int showLevel){
        showLv = showLevel;
    }

    public static void i(String tag, String msg){
        if(showLv >= TZLogLevel.INFO){
            Log.i(tag, msg);
        }
    }
    public static void w(String tag, String msg){
        if(showLv >= TZLogLevel.WARN){
            Log.w(tag, msg);
        }
    }
    public static void e(String tag, String msg){
        if(showLv >= TZLogLevel.ERROR){
            Log.e(tag, msg);
        }
    }
}
