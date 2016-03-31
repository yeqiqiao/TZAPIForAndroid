package com.tianzunchina.android.api.base;

import android.support.multidex.MultiDexApplication;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;
import com.tianzunchina.android.api.network.okhttp.TZOkHttpStack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CraetTime 2016-3-14
 *
 * @author SunLiang
 */
public class TZApplication extends MultiDexApplication {
    private static TZApplication instence;
    private RequestQueue mRequestQueue;
    private ExecutorService executorService;
    private int poolCount = 3;

    public static TZApplication getInstance() {
        return instence;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instence = this;
        executorService = Executors.newFixedThreadPool(poolCount);
    }

    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }

    /**
     * 设置线程池大小
     * 在onCreate前执行
     * @param count
     */
    protected void setPoolCount(int count){
        poolCount = count;
    }

    /**
     * 获取全局Volley队列实例
     *
     * @return
     */
    public RequestQueue getVolleyRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this, new TZOkHttpStack(new OkHttpClient()));
        }
        return mRequestQueue;
    }

    /**
     * 队列中添加请求
     * @param request
     */
    public static void addRequest(Request<?> request) {
        getInstance().getVolleyRequestQueue().add(request);
    }

    /**
     * 在请求中加入tag标识 并加入队列
     *
     * @param request
     * @param tag
     */
    public static void addRequest(Request<?> request, String tag) {
        request.setTag(tag);
        addRequest(request);
    }

    /**
     * 取消该tag标识对应的请求
     * @param tag 请求标识
     */
    public static void cancelAllRequests(String tag) {
        if (getInstance().getVolleyRequestQueue() != null) {
            getInstance().getVolleyRequestQueue().cancelAll(tag);
        }
    }
}
