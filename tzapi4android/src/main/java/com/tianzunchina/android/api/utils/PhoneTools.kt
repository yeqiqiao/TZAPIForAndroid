package com.tianzunchina.android.api.utils

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.telephony.TelephonyManager
import com.tianzunchina.android.api.control.TZApplication

import java.io.File

/**
 * 手机信息工具类
 * CraetTime 2016-3-4
 * @author SunLiang
 */
object  PhoneTools {
	var tm: TelephonyManager
	var pm: PackageManager
	val app = TZApplication.instance

    init {
        tm = app!!.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        pm = app.packageManager
    }

    val versionCode: Int
        get() {
            var packInfo: PackageInfo?
            var version = 0
            try {
                packInfo = pm.getPackageInfo(app!!.packageName, 0)
                version = packInfo!!.versionCode
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return version
        }

    val versionName: String
        get() {
            var packInfo: PackageInfo?
            var versionName = ""
            try {
                packInfo = pm.getPackageInfo(app!!.packageName, 0)
                versionName = packInfo!!.versionName
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return versionName
        }

    /**
	 * 唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID.
	 * @return 获得设备ID
	 */
    val deviceId: String
        get() = tm.deviceId

    /**
	 * 手机号码
	 * @return 获取本机号码
	 */
    val line1Number: String
        get() = tm.line1Number

    val simCountryIso: String
        get() = tm.simCountryIso

    fun showSettingDate(context: Context) {
        context.startActivity(Intent(Settings.ACTION_DATE_SETTINGS))
    }

	fun getApkFileIntent(param: String): Intent {
		val intent = Intent()
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		intent.action = android.content.Intent.ACTION_VIEW
		val uri = Uri.fromFile(File(param))
		intent.setDataAndType(uri, "application/vnd.android.package-archive")
		return intent
	}

	fun getImageFileIntent(file: File): Intent {
		val uri = Uri.fromFile(file)
		return getImageFileIntent(uri)
	}

	fun getImageFileIntent(uri: Uri): Intent {
		val intent = Intent("android.intent.action.VIEW")
		intent.addCategory("android.intent.category.DEFAULT")
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		intent.setDataAndType(uri, "image/*")
		return intent
	}
}
