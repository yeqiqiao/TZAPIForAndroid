package com.tianzunchina.android.api.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * 时间格式转换器
 * CraetTime 2016-3-7
 * @author SunLiang
 */
object TimeConverter {
    val SDF = SimpleDateFormat(
            "yyyy-MM-dd HH:mm", Locale.CHINA)
    val SDF_TIME = SimpleDateFormat(
            "HH:mm", Locale.CHINA)
    val SDF_WEEK_TIME = SimpleDateFormat(
            "aa HH:mm", Locale.CHINA)
    val SDF_DATE = SimpleDateFormat(
            "yyyy-MM-dd", Locale.CHINA)
    val SDF_DATE_NO_YEAR = SimpleDateFormat(
            "MM-dd HH:mm", Locale.CHINA)
    val SDF_DATE_NO_YEAR_NO_TIME = SimpleDateFormat(
            "MM-dd", Locale.CHINA)
    val SDF_FILE = SimpleDateFormat(
            "yyyyMMdd_HHmmss", Locale.CHINA)

	/**
	* 输出日期文字
	 * @param date
	 */
    fun date2Str(date: Date): String {
        return SDF.format(date)
    }

	/**
	 * 输出日期文字
	 * @param time 时间戳
	 */
    fun date2Str(time: Long): String {
        return SDF.format(Date(time))
    }

	/**
	 * 自定义输出格式
	 * @param date
	 * @param format 输出格式
	 */
    fun date2Str(date: Date, format: String): String {
        val dateFormat = SimpleDateFormat(format, Locale.CHINA)
        return dateFormat.format(date)
    }

	/**
	* 根据文本转换日期
	 * @param dateStr 格式：yyyy-MM-dd HH:mm
	 */
    fun str2Date(dateStr: String): Date {
        try {
            return SDF.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return Date()
    }

	/**
	* 根据自定义文本转换日期
	 * @param dateStr
	 *
	 */
    fun str2Date(dateStr: String, format: String): Date {
        val dateFormat = SimpleDateFormat(format, Locale.CHINA)
        try {
            return dateFormat.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return Date()
    }

	/**
	* 判断2个日期是否是同一周
	 */
    fun isSameWeek(cal1: Calendar, cal2: Calendar): Boolean {
        val subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)
        // subYear==0,说明是同一年
        if (subYear == 0) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true
        } else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true
        } else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true
        }
        return false
    }

	/**
	 * 判断2个日期是否是同一周
	 */
    fun isSameWeek(date1: Date, date2: Date): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = date1
        cal2.time = date2
        return isSameWeek(cal1, cal2)
    }


	/**
	 * 获得cal的星期
	 * @param cal
	 */
    fun getWeek(cal: Calendar): String {
        var week = "周"
        when (cal.get(Calendar.DAY_OF_WEEK)) {
            1 -> week += "日"
            2 -> week += "一"
            3 -> week += "二"
            4 -> week += "三"
            5 -> week += "四"
            6 -> week += "五"
            7 -> week += "六"
        }
        return week
    }

    fun isSameDate(date1: Date, date2: Date): Boolean {
        val d1 = SDF_DATE.format(date1)
        val d2 = SDF_DATE.format(date2)
        return d1 == d2
    }

    /**
     * 是否2个日期是否同一年
     */
    fun isSameYear(cal1: Calendar, cal2: Calendar): Boolean {
        val d1 = cal1.get(Calendar.YEAR) - 1900
        val d2 = cal2.get(Calendar.YEAR) - 1900
        return d1 == d2
    }

    /**
     * 是否2个日期是否同一年
     */
    fun isSameYear(date1: Date, date2: Date): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = date1
        cal2.time = date2
        return isSameYear(cal1, cal2)
    }
}