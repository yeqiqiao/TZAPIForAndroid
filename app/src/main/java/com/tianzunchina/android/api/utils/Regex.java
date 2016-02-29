package com.tianzunchina.android.api.utils;


/**
 * 
 * @author 孙亮
 * CraetTime 2014-10-11
 * 枚举正则表达式
 *
 */
public enum Regex {
	NO_HAN(1, "^[\u4E00-\u9FFF]+$"),//汉字
	NO_HAN_SYMBOL(2, "^[/w]+&"); //限数字字母下划线
	
	private int regexID;
	private String regexStr;
	private static final String HAN = "\u4E00-\u9FFF"; 
	
	/**
	 * 
	 * @param regexID 编号
	 * @param regexStr 正则表达式
	 */
	Regex(int regexID, String regexStr){
		this.regexID = regexID;
		this.regexStr = regexStr;
	}
}
