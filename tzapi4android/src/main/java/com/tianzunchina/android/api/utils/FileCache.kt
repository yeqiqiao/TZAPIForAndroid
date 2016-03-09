package com.tianzunchina.android.api.utils


import android.app.Application
import android.os.Environment

import com.tianzunchina.android.api.control.TZApplication

import java.io.File
import java.net.MalformedURLException
import java.net.URL
import java.util.Date

/**
 * 文件缓存操作类
 * 需要设置 [com.tianzunchina.android.api.control.TZApplication]
 * CraetTime 2016-3-4
 * @author SunLiang
 */
object FileCache{
    val app = TZApplication.instance!!
    /**
     * 获得一个隐藏的缓存目录
     * @return 缓存目录
     */
    val cacheDir: File
        get() {
            val dir = File(Environment.getExternalStorageDirectory(),
                    "." + app.packageName)
            if (!dir.exists()) {
                dir.mkdir()
            }
            return dir
        }

    /**
     * 判断该url文件是否已被缓存
     * @param url
     * *
     * @return 是否已缓存
     */
    fun haveCache(url: String): Boolean {
        val file = getSaveFile(url)
        if (file != null && file.exists()) {
            return true
        }
        return false
    }

    /**
     * 获得该url的缓存路径
     * @param url
     * *
     * @return 缓存路径
     */
    fun getSavePath(url: String): String {
        val filename = url.hashCode().toString()
        var type = ""
        try {
            val url1 = URL(url)
            val name = url1.file
            type = "." + name.split(".".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return File(cacheDir, filename + type).absolutePath
    }

    /**
     * 获得该url的缓存文件
     * @param url
     * *
     * @return 缓存文件
     */
    fun getSaveFile(url: String): File? {
        val f = File(getSavePath(url))
        return f
    }

    /**
     * 获取缓存图片
     * @return
     */
    val tempJPG: File
        get() {
            val file = File(cacheDir, "tmp.jpg")
            return file
        }

    /**
     * 获取该url文件的文件名
     * @param url
     * *
     * @return 文件名
     */
    fun url2fileName(url: URL): String {
        var fileName = url.file
        fileName = url2fileName(fileName)
        return fileName
    }

    /**
     * 截取url路径中的文件名
     * @param url
     * *
     * @return
     */
    fun url2fileName(url: String): String {
        var fileName = url
        if (fileName.contains("=")) {
            fileName = fileName.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        } else if (fileName.contains("/")) {
            val strs = fileName.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            fileName = strs[strs.size - 1]
        }
        fileName = fileName.replace("/", "_")
        return fileName
    }

}
