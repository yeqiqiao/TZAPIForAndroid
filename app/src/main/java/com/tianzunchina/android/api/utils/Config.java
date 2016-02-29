package com.tianzunchina.android.api.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/*
 * 配置信息管理的父类
 * 所有用到的配置信息管理都继承此类 以便于对信息进行保存及读取
 * @author SunLiang 
 * @version 0.0.1 
 */
public class Config {
	private static SharedPreferences share;
	private static Editor editor;
	private static final String PREFERENCE_NAME = "saveInfo";
	/*
	 * @return 获取一个唯一的私有的SharedPreferences
	 */
	private SharedPreferences getSP(){
		if(share == null){
			share = MyApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
		}
		return share;
	}
	
	/*
	 * 提供配置信息修改的编辑器
	 * @return 获取一个唯一的私有的Editor
	 */
	private Editor getED(){
		if(editor == null){
			if(share == null){
				getSP();
			}
			editor = share.edit();
			editor.commit();
		}
		return editor;
	}
	
	/*
	 * 提交对编辑器内容的修改
	 * 在对属性editor进行修改后请调用此方法进行保存
	 * @return 无返回值
	 */
	private void edCommit(){
		editor.commit();
	}
	
	/*
	 * 读取配置中key对应的String内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回null
	 */
	protected String loadString(String key){
		getSP();
		return share.getString(key, null);
	}
	
	/*
	 * 读取配置中key对应的String内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
	protected String loadString(String key, String defVal){
		getSP();
		return share.getString(key, defVal);
	}
	
	/*
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回false
	 */
	protected boolean loadBoolean(String key){
		getSP();
		return share.getBoolean(key, false);
	}
	
	/*
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
	protected boolean loadBoolean(String key, Boolean defVal){
		getSP();
		return share.getBoolean(key, defVal);
	}
	
	/*
	 * 读取配置中key对应的int内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回0
	 */
	protected int loadInt(String key){
		getSP();
		return share.getInt(key, 0);
	}
	
	/*
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
	protected int loadInt(String key, int defVal){
		getSP();
		return share.getInt(key, defVal);
	}
	
	/*
	 * 读取配置中key对应的long内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回0
	 */
	protected long loadLong(String key){
		getSP();
		return share.getInt(key, 0);
	}
	
	/*
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
	protected long loadLong(String key, long defVal){
		getSP();
		return share.getLong(key, defVal);
	}
	
	/*
	 * 读取配置中key对应的long内容
	 * @param key 所需内容对应的键值
	 * @return 返回key对应的内容 找不到对应内容时返回0
	 */
	protected Float loadFloat(String key){
		getSP();
		return share.getFloat(key, 0);
	}
	
	/*
	 * 读取配置中key对应的boolean内容
	 * @param key 所需内容对应的键值
	 * @param defVal 在找不到key对应内容时返回defVal
	 * @return 返回key对应的内容 找不到对应内容时返回defVal
	 */
	protected Float loadFloat(String key, Float defVal){
		getSP();
		return share.getFloat(key, defVal);
	}
	
	/*
	 * 将key及对应的String类型的val保存至配置中
	 * @param key 保存的键值
	 * @param val key对应的String类型的值
	 * @return 无返回值
	 */
	protected void saveInfo(String key, String val){
		getED();
		editor.putString(key, val);
		edCommit();
	}
	
	/*
	 * 将key及对应的String类型的val保存至配置中 并提交修改
	 * @param key 保存的键值
	 * @param val key对应的boolean类型的值
	 * @return 无返回值
	 */
	protected void saveInfo(String key, boolean val){
		getED();
		editor.putBoolean(key, val);
		edCommit();
	}
	
	/*
	 * 将key及对应的String类型的val保存至配置中 并提交修改
	 * @param key 保存的键值
	 * @param val key对应的int类型的值
	 * @return 无返回值
	 */
	protected void saveInfo(String key, int val){
		getED();
		editor.putInt(key, val);
		edCommit();
	}
	
	/*
	 * 将key及对应的String类型的val保存至配置中 并提交修改
	 * @param key 保存的键值
	 * @param val key对应的long类型的值
	 * @return 无返回值
	 */
	protected void saveInfo(String key, long val){
		getED();
		editor.putLong(key, val);
		edCommit();
	}
	
	/*
	 * 将key及对应的String类型的val保存至配置中 并提交修改
	 * @param key 保存的键值
	 * @param val key对应的Float类型的值
	 * @return 无返回值
	 */
	protected void saveInfo(String key, Float val){
		getED();
		editor.putFloat(key, val);
		edCommit();
	}
	
	public String getSPCon(){
		getSP();
		return share.toString();
	}
	
	protected void removeInfo(String key){
		getED();
		editor.remove(key);
		edCommit();
	}
}