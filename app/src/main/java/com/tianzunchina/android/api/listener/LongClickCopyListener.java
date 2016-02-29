package com.tianzunchina.android.api.listener;

import android.content.Context;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author 孙亮
 * CraetTime 2014-10-11
 * 长按复制文本框内容
 */
public class LongClickCopyListener implements OnLongClickListener{
	private TextView tv;
	private Context context;
	private String info;
	
	/**
	 * 构造方法
	 * @param tv 需要监听的文本框
	 * @param context 文本框所在上下文
	 * @param info 复制成功后的提示
	 */
	public LongClickCopyListener(TextView tv, Context context, String info){
		this.tv = tv;
		this.context = context;
		this.info = info;
	}
	
	/**
	 * 重写长按事件
	 * 将tv中的内容复制到剪贴板
	 */
	@Override
	public boolean onLongClick(View v) {
		/*
		 * API11之前 用android.text.ClipboardManager
		 * API11之后用android.content.ClipboardManager
		 * 所以此处会标记过时
		 */
		ClipboardManager cmb = (ClipboardManager) context.getSystemService("clipboard");
		cmb.setText(tv.getText().toString());
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
		return false;
	}

}
