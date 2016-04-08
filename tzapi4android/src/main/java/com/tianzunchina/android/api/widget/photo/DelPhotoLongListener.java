package com.tianzunchina.android.api.widget.photo;

import android.view.View;
import android.view.View.OnLongClickListener;

public class DelPhotoLongListener implements OnLongClickListener {
	private TZPhotoBox photoBox;

	public DelPhotoLongListener(TZPhotoBox pbox){
		this.photoBox = pbox;
	}
	
	@Override
	public boolean onLongClick(View v) {
 		photoBox.readyDelete();
		return true;
	}
}
