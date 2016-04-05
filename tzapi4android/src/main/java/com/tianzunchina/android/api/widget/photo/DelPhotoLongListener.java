package com.tianzunchina.android.api.widget.photo;

import android.view.View;
import android.view.View.OnLongClickListener;

public class DelPhotoLongListener implements OnLongClickListener {
	private PhotoBox photoBox;

	public DelPhotoLongListener(PhotoBox pbox){
		this.photoBox = pbox;
	}
	
	@Override
	public boolean onLongClick(View v) {
 		photoBox.readyDelete();
		return true;
	}
}
