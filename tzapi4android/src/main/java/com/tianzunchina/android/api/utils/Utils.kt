package com.tianzunchina.android.api.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.graphics.Bitmap
import android.media.ExifInterface
import android.widget.EditText

import java.io.ByteArrayOutputStream
import java.io.IOException

/*
 * 工具类：Date转换, 图片缩放切割旋转,转换等
 */
object  Utils {
	val SCROLL_STATE_LEFT = 1
	val SCROLL_STATE_DEFULT = 2
	val SCROLL_STATE_RIGHT = 3

	private val REG_EMAIL = "w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*"
	private val SETTING_DATETIME = "DateTimeSettings"
	private val SETTING_GPS = "SecuritySettings"
    init {
        System.gc()
        System.runFinalization()
    }


    fun bitmap2Bytes(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }

    fun enable(str: String?, reg: String): Boolean {
        if (str == null) {
            return false
        }
        return str.matches(reg.toRegex())
    }

    fun isEmail(str: String): Boolean {
        return enable(str, REG_EMAIL)
    }


    fun openDatetimeSetting(activity: Activity) {
        openSetting(activity, SETTING_DATETIME)
    }

    fun openGPSSetting(activity: Activity) {
        openSetting(activity, SETTING_GPS)
    }

	/**
	* 打开设置界面
	 */
    fun openSetting(activity: Activity, setting: String) {
        val intent = Intent("/")
        try {
            val cm = ComponentName("com.android.settings",
                    "com.android.settings." + setting)
            intent.component = cm
            intent.action = "android.intent.action.VIEW"
            activity.startActivityForResult(intent, 0)
        } catch (e: Exception) {
        }

    }
}