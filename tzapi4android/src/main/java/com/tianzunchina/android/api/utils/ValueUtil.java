package com.tianzunchina.android.api.utils;

import android.app.Application;


/**
 * 数值转换工具类
 * @author 孙亮
 * CraetTime 2014-10-17
 */
public class ValueUtil {
	private Application app;
	/**
	 * 
	 * @param app 本Application对象
	 */
	public ValueUtil(Application app){
		this.app = app;
	}
	
	/**
	 * 将dp单位的数据转换为px
	 * @param dpValue dp单位的数值
	 * @return 转换后的数值单位为px
	 */
	public int dip2px(float dpValue) {
		final float scale = app.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	/**
	 * 将px单位的数据转换为dp
	 * @param pxValue px单位的数值
	 * @return 转换后的数值单位为dp
	 */
	public int px2dip(float pxValue) {
		final float scale = app.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
