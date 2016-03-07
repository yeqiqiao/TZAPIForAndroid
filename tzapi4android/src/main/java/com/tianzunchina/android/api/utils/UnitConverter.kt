package com.tianzunchina.android.api.utils

import android.util.Base64
import com.tianzunchina.android.api.control.TZApplication

import com.tianzunchina.android.api.utils.model.LatLon
import com.tianzunchina.android.api.utils.trans.Trans

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

/**
 * 常用数据类型转换器
 * CraetTime 2016-3-7
 * @author SunLiang
 */
object  UnitConverter{

	/**
	* 把bytes转换为base64
	 */
    fun byte2base64(bytes: ByteArray?): String? {
        if (bytes == null || bytes.size == 0) {
            return null
        }
        val str = Base64.encodeToString(bytes, Base64.DEFAULT)
        return str
    }

	/**
	 * 把base64转换为bytes
	 */
    fun base642byte(str: String?): ByteArray? {
        if (str == null || str === "") {
            return null
        }
        return Base64.decode(str, Base64.DEFAULT)
    }

	/**
	*
	 */
    fun file2byte(file: File?): ByteArray {
        var bytes: ByteArray? = null
        try {
            if (file != null && file.exists()) {
                val `is` = FileInputStream(file)
                val length = file.length().toInt()
                if (length > Integer.MAX_VALUE) {
                    System.err.println("this file is max ")
                }
                bytes = ByteArray(length)
                var offset = 0
                var numRead = 0
                while (offset < bytes.size) {
					numRead = `is`.read(bytes, offset, bytes.size - offset)
					if(numRead >= 0){
						offset += numRead
					} else {
						break
					}
                }
                if (offset < bytes.size) {
                }
                `is`.close()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bytes!!
    }

    /**
     * 由dp至px进行转换
     * 例如 dip2px(1) 将得到1dp对应的px值
     * @param dpValue
     * *
     * @return
     */
    fun dip2px(dpValue: Float): Int {
        val scale = TZApplication.instance!!.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 由dp至px进行转换
     * 例如 px2dip(1) 将得到1px对应的dp值
     * @param pxValue
     * *
     * @return
     */
    fun px2dip(pxValue: Float): Int {
        val scale = TZApplication.instance!!.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 84坐标系转大地2000
     * @param lon 经度
     * *
     * @param lat 纬度
     * *
     * @return
     */
    fun WGS84ToCGCS2000(lon: Double, lat: Double): LatLon {
        if (lon == 0.0) {
            return LatLon(0.0, 0.0)
        }
        val trans = Trans(1)
        var x = lon
        var y = lat
        val coords = trans.doTrans(x, y)
        x = coords[0]
        y = coords[1] - 50
        return LatLon(y, x)
    }
}
