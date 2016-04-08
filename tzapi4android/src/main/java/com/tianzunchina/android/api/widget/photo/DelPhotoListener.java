package com.tianzunchina.android.api.widget.photo;

import android.view.View;
import android.view.View.OnClickListener;

public class DelPhotoListener implements OnClickListener {
	private TZPhotoBox photoBox;
	private ImpDelCallBack callBack;
	private int index;

	public DelPhotoListener(TZPhotoBox pbox){
		this.photoBox = pbox;
	}

	public DelPhotoListener(TZPhotoBox pbox, int index, ImpDelCallBack back){
		this.photoBox = pbox;
		callBack = back;
		this.index = index;
	}

	@Override
	public void onClick(View v) {
		if(callBack != null){
			callBack.beforeForDel(index);
		}
		photoBox.deletePhoto();
		if(callBack != null){
			callBack.afterForDel(index);
		}
	}

	public interface ImpDelCallBack{
		void beforeForDel(int i);
		void afterForDel(int i);
	}
}
