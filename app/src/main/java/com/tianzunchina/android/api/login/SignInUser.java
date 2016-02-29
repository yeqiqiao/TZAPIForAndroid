package com.tianzunchina.android.api.login;

import com.tianzunchina.android.api.utils.LoginConfig;

import java.util.HashMap;
import java.util.Map;

public class SignInUser {
	
	private String account;
	private String password;
	private String phone;
	private int regionID;
	private boolean isKeep = false;
	private boolean isAuto = false;

	public SignInUser(String userName, String userPassword) {
		this.account = userName;
		this.password = userPassword;
	}

	public SignInUser(String account, String password, String phone, int regionID) {
		this.account = account;
		this.password = password;
		this.phone = phone;
		this.regionID = regionID;
	}
	
	public SignInUser(String userName, String userPassword, boolean isKeep) {
		this.account = userName;
		this.password = userPassword;
		this.isKeep = isKeep;
	}

	public SignInUser(String userName, String userPassword, boolean isKeep,
					  boolean isAuto) {
		this.account = userName;
		this.password = userPassword;
		this.isKeep = isKeep;
		this.isAuto = isAuto;
	}

	public boolean isKeep() {
		return isKeep;
	}
	public void setKeep(boolean isKeep) {
		this.isKeep = isKeep;
	}
	public boolean isAuto() {
		return isAuto;
	}
	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}
	public SignInUser(String userName){
		this.account = userName;
	}
	
	public String getUserName() {
		return account;
	}
	public void setUserName(String userName) {
		this.account = userName;
	}
	public String getUserPassword() {
		return password;
	}
	public void setUserPassword(String userPassword) {
		this.password = userPassword;
	}
	public String getPhone() {
		return phone;
	}
	public int getRegionID() {
		return regionID;
	}
	public Map<String, String> getData(){
		Map<String, String> map = new HashMap<String, String>();
		map.put(LoginConfig.getUserName(), account);
		map.put(LoginConfig.getUserPassword(), password);
		return map;
	}

	@Override
	public String toString() {
		return "SignInUser [account=" + account + ", password=" + password
				+ ", isAuto=" + isAuto + "]";
	}
}
