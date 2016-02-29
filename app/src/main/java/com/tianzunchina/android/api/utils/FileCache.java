package com.tianzunchina.android.api.utils;


import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.net.URL;
import java.util.Date;

public class FileCache {

	private static int conut = 0;
	private Application app;
	private static FileCache cache;

	private FileCache(Application app){
		this.app = app;
	}
	
	public static FileCache getInstence(Application app){
		if(cache == null){
			cache = new FileCache(app);
		}
		return cache;
	}
	
	public String getSavePath(String url) {
		String filename = String.valueOf(url.hashCode());
		return getThumbDir() + filename;
	}
	
	public File getThumbFile() {
		File file = new File(getThumbDir(), "Thumb_"
				+ TimeConverter.SDF_FILE.format(new Date()) + "_" + conut + ".jpg");
		conut++;
		return file;
	}

	public File getThumbDir() {
		File dir = new File(Environment.getExternalStorageDirectory(),
				".".concat(app.getPackageName()));
		if (!dir.exists()) {
			dir.mkdir();
		}
		return dir;
	}

	public File getTempJPG() {
		File path = new File(Environment.getExternalStorageDirectory(),
				app.getPackageName());
		if (!path.exists()) {
			path.mkdir();
		}
		File file = new File(path, "tmp.jpg");
		return file;
	}
	
	public String url2fileName(URL url) {
		String fileName = url.getFile();
		fileName = url2fileName(fileName);
		return fileName;
	}

	public String url2fileName(String url) {
		String fileName = url;
		if(fileName.contains("=")){
			fileName = fileName.split("=")[1];
		}
		fileName = fileName.replace("/", "_");
		return fileName;
	}
	
	public File getFile(String url) {
		File f = new File(getSavePath(url));
		return f;
	}
}
