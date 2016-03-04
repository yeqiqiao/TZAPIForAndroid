package com.tianzunchina.android.api.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.File;

public class PhoneTools {
	private static TelephonyManager tm;
	private static PackageManager pm;
	private static PhoneTools pt;

	public static PhoneTools getInstence(Application app){
		if(pt == null){
			pt = new PhoneTools(app);
		}
		return pt;
	}
	private PhoneTools(Application app) {
		tm = (TelephonyManager) app.getSystemService(Context.TELEPHONY_SERVICE);
		pm = app.getPackageManager();
	}

	public int getVersionCode() {
		PackageInfo packInfo = null;
		int version = 0;
		try {
			packInfo = pm.getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
			version = packInfo.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return version;
	}
	
	public String getVersionName() {
		PackageInfo packInfo = null;
		String versionName = "";
		try {
			packInfo = pm.getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
			versionName = packInfo.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/*
	 * 唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID.
	 * 
	 * @return 获得设备ID
	 */
	public String getDeviceId() {
		return tm.getDeviceId();
	}

	/*
	 * 手机号码
	 * 
	 * @return 获取本机号码
	 */
	public String getLine1Number() {
		return tm.getLine1Number();
	}

	public String getSimCountryIso() {
		return tm.getSimCountryIso();
	}

	public void showSettingDate(Context context) {
		context.startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
	}

	public static Intent getApkFileIntent(String param) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		return intent;
	}

	public static Intent getImageFileIntent(File file) {
		Uri uri = Uri.fromFile(file);
		return getImageFileIntent(uri);
	}
	
	public static Intent getImageFileIntent(Uri uri) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(uri, "image/*");
		return intent;
	}
}
