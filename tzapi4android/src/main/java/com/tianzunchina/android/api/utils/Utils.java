package com.tianzunchina.android.api.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/*
 * 工具类：Date转换, 图片缩放切割旋转, dp px转换等
 */
public class Utils {
	@SuppressLint("SdCardPath")
	public static final int SCROLL_STATE_LEFT = 1;
	public static final int SCROLL_STATE_DEFULT = 2;
	public static final int SCROLL_STATE_RIGHT = 3;
	
	private static String REG_EMAIL = "w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*";
	private static final String SETTING_DATETIME = "DateTimeSettings";
	private static final String SETTING_GPS = "SecuritySettings";

	public Utils() {
		System.gc();
		System.runFinalization();
	}

	
	public int getExifOrientation(String filepath) {
		int degree = 0;
		ExifInterface exif = null;
		try {
			exif = new ExifInterface(filepath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (exif != null) {
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION, -1);
			if (orientation != -1) {
				switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
				}
			}
		}
		return degree;
	}

	public int getRotate(String path) {
		ExifInterface exif = null;
		try {
			exif = new ExifInterface(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int rot = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
				ExifInterface.ORIENTATION_NORMAL);
		return rot;
	}

	public byte[] bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		return baos.toByteArray();
	}

	public boolean enable(String str, String reg) {
		if (str == null) {
			return false;
		}
		return str.matches(reg);
	}

	public static boolean isNull(EditText et) {
		String text = et.getText().toString().trim();
		if (text.equals("")) {
			return true;
		}
		return false;
	}

	public boolean isEmail(String str) {
		return enable(str, REG_EMAIL);
	}

	

	public void openDatetimeSetting(Activity activity) {
		openSetting(activity, SETTING_DATETIME);
	}

	public void openGPSSetting(Activity activity) {
		openSetting(activity, SETTING_GPS);
	}

	public void openSetting(Activity activity, String setting) {
		Intent intent = new Intent("/");
		try {
			ComponentName cm = new ComponentName("com.android.settings",
					"com.android.settings." + setting);
			intent.setComponent(cm);
			intent.setAction("android.intent.action.VIEW");
			activity.startActivityForResult(intent, 0);
		} catch (Exception e) {
		}
	}
}