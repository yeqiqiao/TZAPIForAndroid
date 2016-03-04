package com.tianzunchina.android.api.utils;

import android.app.Application;
import android.util.Base64;

import com.tianzunchina.android.api.utils.trans.Trans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class UnitConverter {
	private static UnitConverter converter;
	private Application app;
	
	public static UnitConverter getInstence(Application app){
		if(converter == null){
			converter = new UnitConverter(app);
		}
		return converter;
	}
	
	private UnitConverter(Application app){
		this.app = app;
	}
	
	public String byte2base64(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		String str = Base64.encodeToString(bytes, Base64.DEFAULT);
		return str;
	}

	public byte[] base642byte(String str) {
		if (str == null || str == "") {
			return null;
		}
		return Base64.decode(str, Base64.DEFAULT);
	}

	public byte[] file2byte(File file) {
		byte[] bytes = null;
		try {
			if (file != null && file.exists()) {
				InputStream is = new FileInputStream(file);
				int length = (int) file.length();
				if (length > Integer.MAX_VALUE) {
					System.err.println("this file is max ");
				}
				bytes = new byte[length];
				int offset = 0;
				int numRead = 0;
				while (offset < bytes.length
						&& (numRead = is.read(bytes, offset, bytes.length
								- offset)) >= 0) {
					offset += numRead;
				}
				if (offset < bytes.length) {
				}
				is.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public int dip2px(float dpValue) {
		final float scale = app.getResources()
				.getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public int px2dip(float pxValue) {
		final float scale = app.getResources()
				.getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	/*
	 * 84坐标系转大地2000
	 */
	public LatLon WGS84ToCGCS2000(double lon, double lat){
		if(lon == 0){
			return new LatLon(0, 0);
		}
		Trans trans = new Trans(1);
        double x = lon, y = lat;
        double[] coords = trans.doTrans(x, y);
        x = coords[0];
        y = coords[1] - 50;
		return new LatLon(y, x);
	}
}
