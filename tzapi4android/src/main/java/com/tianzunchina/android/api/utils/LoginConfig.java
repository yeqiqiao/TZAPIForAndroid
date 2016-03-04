package com.tianzunchina.android.api.utils;

import com.tianzunchina.android.api.login.SignInUser;

public class LoginConfig extends Config {
	
	private static final String USER_NAME = "account";
	private static final String USER_PASSWORD = "password";
	private static final String IS_SAVE_PASSWORD = "isKeep";
	
	private static LoginConfig instence;
	
	
	private LoginConfig(){  }
	
	public static LoginConfig getInstence(){
		if(instence == null){
			instence = new LoginConfig();
		}
		return instence;
	}
	/**
	 * 保存登录状态
	 * @param user 包含userName及userPassword的对象
	 * @return 没有返回值
	 */
	public void saveUser(SignInUser user){
//		保存用户名及密码信息 并标记是否记住密码及自动登录
		saveInfo(USER_NAME, user.getAccount());
		if(user.isKeep()){
			saveInfo(USER_PASSWORD, user.getPassword());
		}
		saveInfo(IS_SAVE_PASSWORD, user.isKeep());
	}
	
	/*
	 * 判断是否保存登录状态
	 * 将之前在本地登录的信息进行返回
	 * @return 返回本地保存的User对象
	 */
	public SignInUser loadUser(){
		SignInUser user = null;
		String userName = loadString(USER_NAME);
		String password = loadString(USER_PASSWORD);
		boolean isKeep = loadBoolean(IS_SAVE_PASSWORD);
		if(userName != null){
			user = new SignInUser(userName, password, isKeep);
		}
		return user;
	}
	
	public void removePWD(){
		removeInfo(USER_PASSWORD);
		removeInfo(IS_SAVE_PASSWORD);
	}

	public static String getUserName() {
		return USER_NAME;
	}

	public static String getUserPassword() {
		return USER_PASSWORD;
	}
}