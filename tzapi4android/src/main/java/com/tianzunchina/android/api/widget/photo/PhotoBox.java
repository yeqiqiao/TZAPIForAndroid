package com.tianzunchina.android.api.widget.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tianzunchina.android.api.R;
import com.tianzunchina.android.api.utils.PhotoTools;

import java.io.File;

/**
 * 拍照盒子控件
 * 用于帮助用户通过拍照/相册等方式获取照片，并展示
 * CraetTime 2016-3-14
 * @author SunLiang
 */
public class PhotoBox {
	private int index;
	private int mode = MODE_ADD;
	private Context context;
	private ImageView ivThumb;
	private ImageView ivDel;
	File fileImage;
	String url;
	private PhotoTools pt = PhotoTools.getInstence();

	public PhotoBox(Context context, PhotoBoxView piv, int index) {
		this.context = context;
		this.ivThumb = piv.ivThumb;
		this.ivDel = piv.ivDel;
		this.index = index;
		init();
	}
	
	public PhotoBox(Context context, ImageView thumbIv, ImageView delIv, int index) {
		this.context = context;
		this.ivThumb = thumbIv;
		this.ivDel = delIv;
		this.index = index;
		init();
	}

	public PhotoBox(Context context, ImageView thumbIv, ImageView delIv, int index, int mode) {
		this.context = context;
		this.ivThumb = thumbIv;
		this.ivDel = delIv;
		this.index = index;
		this.mode = mode;
		init();
	}

	public PhotoBox(Context context, ImageView thumbIv, ImageView delIv, int i, DelPhotoListener.ImpDelCallBack back) {
		this.ivThumb = thumbIv;
		this.ivDel = delIv;
		this.context = context;
		this.index = i;
		ivThumb.setOnLongClickListener(new DelPhotoLongListener(this));
		ivDel.setOnClickListener(new DelPhotoListener(this, i, back));
	}

	private void init(){
		ivThumb.setOnClickListener(new CameraListener(context, index, this));
		ivThumb.setOnLongClickListener(new DelPhotoLongListener(this));
		ivDel.setOnClickListener(new DelPhotoListener(this));
	}

	/**
	 * 无效状态
	 * 不能点击 不显示图片
	 */
	public void invalid(){
		setMode(MODE_NULL);
		ivThumb.setImageBitmap(null);
		ivDel.setVisibility(View.INVISIBLE);
		ivThumb.setEnabled(false);
	}

	/**
	 * 允许操作状态
	 * 允许点击，显示“+”图片
	 */
	public void allow(){
		setMode(MODE_ADD);
		fileImage = null;
		ivDel.setVisibility(View.INVISIBLE);
		ivThumb.setEnabled(true);
		ivThumb.setImageResource(R.mipmap.ico_add_photo);
	}

	/**
	 * 只读模式
	 * 只能单击浏览图片、不能删除或拍照
	 */
	public void onlyRead(){
		setMode(MODE_ONLY_READ);
		ivDel.setVisibility(View.INVISIBLE);
		if(url == null) {
			invalid();
		}
		ivThumb.setOnLongClickListener(null);
	}


	/**
	 * 待删除状态
	 * 显示删除图标，保留缩略图
	 */
	public void readyDelete(){
		if(mode == MODE_BROWSE){
			ivDel.setVisibility(View.VISIBLE);
		}
	}

	public void cancelDelete(){
		ivDel.setVisibility(View.INVISIBLE);
	}

	/**
	 * 删除照片
	 * 清空缩略图及本地缓存图片
	 */
	public void deletePhoto(){
		allow();
	}

	public void addPhoto(File file) {
		if(file == null){
			return;
		}
		fileImage =  file;
		ivThumb.setEnabled(true);
		Picasso.with(context).load(file).placeholder(R.mipmap.pic_loading).error(R.mipmap.pic_loading).config(Bitmap.Config.ALPHA_8).resize(200, 200).centerCrop().into(ivThumb, picassoCallback);
		setMode(MODE_BROWSE);
	}

	public void addPhoto(String url) {
		if(url == null){
			return;
		}
		this.url =  url;
		ivThumb.setEnabled(true);
		Picasso.with(context).load(url).placeholder(R.mipmap.pic_loading).error(R.mipmap.pic_loading).config(Bitmap.Config.ALPHA_8).resize(200, 200).centerCrop().into(ivThumb, picassoCallback);
		setMode(MODE_BROWSE);
	}

	public void setMode(int mode){
		this.mode = mode;
	}

	public boolean isBrowse() {
		return mode == MODE_BROWSE || mode == MODE_ONLY_READ;
	}

	public File getFileImage() {
		return fileImage;
	}

	public static final int MODE_NULL = 0, MODE_ONLY_READ = 1, MODE_ADD = 2, MODE_BROWSE = 3;

	@Override
	 public String toString() {
		return "PhotoBox [activity=" + context + ", ivThumb=" + ivThumb
				+ ", fileImage=" + fileImage + ", ivDel=" + ivDel + ", pt="
				+ pt +  "]";
	}

	Callback picassoCallback =  new Callback() {
		@Override
		public void onSuccess() {}

		@Override
		public void onError() {
			switch (mode){
				case MODE_BROWSE:
					allow();
					break;
				case MODE_ONLY_READ:
					invalid();
					break;
			}
		}
	};
}
