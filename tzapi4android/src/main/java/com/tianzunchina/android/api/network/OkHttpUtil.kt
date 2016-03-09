package com.tianzunchina.android.api.network

import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response

import org.apache.http.client.utils.URLEncodedUtils
import org.apache.http.message.BasicNameValuePair

import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by admin on 2016/3/7 0007.
 */
object OkHttpUtil {
    private val mOkHttpClient = OkHttpClient()

    init {
        mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS)
    }

    /**
     * 不会开启异步线程。
     * @param request
     * @return
     * @throws IOException
     */

    @Throws(IOException::class)
    fun execute(request: Request): Response {
        return mOkHttpClient.newCall(request).execute()

    }

    /**
     * 开启异步线程访问网络
     * @param request
     * @param responseCallback
     */

    fun enqueue(request: Request, responseCallback: Callback) {
        mOkHttpClient.newCall(request).enqueue(responseCallback)

    }

    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     * @param request
     */

    fun enqueue(request: Request) {
        mOkHttpClient.newCall(request).enqueue(object : Callback {
            @Throws(IOException::class)
            override fun onResponse(arg0: Response) {
            }

            override fun onFailure(arg0: Request, arg1: IOException) {
            }
        })

    }

    @Throws(IOException::class)
    fun getStringFromServer(url: String): String {
        val request = Request.Builder().url(url).build()
        val response = execute(request)
        if (response.isSuccessful) {
            val responseUrl = response.body().string()
            return responseUrl
        } else {
            throw IOException("Unexpected code " + response)
        }
    }

    private val CHARSET_NAME = "UTF-8"

    /**
     * 这里使用了HttpClinet的API。只是为了方便
     * @param params
     * @return
     */

    fun formatParams(params: List<BasicNameValuePair>): String {
        return URLEncodedUtils.format(params, CHARSET_NAME)
    }

    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     * @param url
     * @param params
     * @return
     */

    fun attachHttpGetParams(url: String, params: List<BasicNameValuePair>): String {
        return url + "?" + formatParams(params)
    }

    /**
     * 为HttpGet 的 url 方便的添加1个name value 参数。
     * @param url
     * @param name
     * @param value
     * @return
     */

    fun attachHttpGetParam(url: String, name: String, value: String): String {
        return "$url?$name=$value"
    }
}
