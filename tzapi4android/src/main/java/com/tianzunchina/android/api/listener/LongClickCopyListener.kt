package com.tianzunchina.android.api.listener

import android.content.ClipData
import android.content.Context
import android.os.Build
import android.text.ClipboardManager
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.TextView
import android.widget.Toast


/**
 * 长按复制文本框内容
 * CraetTime 2014-10-11
 * @author SunLiang
 *
 */

/**
 * 构造方法
 * @param tv 需要监听的文本框
 * @param context 文本框所在上下文
 * @param info 复制成功后的提示
 */
class LongClickCopyListener(private val tv: TextView, private val context: Context, private val info: String) : OnLongClickListener {

    /**
     * 重写长按事件
     * 将tv中的内容复制到剪贴板
     * @param v
     * @return
     */
    override fun onLongClick(v: View): Boolean {
        /*
		 * API11之前 用android.text.ClipboardManager
		 * API11之后用android.content.ClipboardManager
		 * 所以此处会标记过时
		 */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            val cmb = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            cmb.primaryClip = ClipData.newPlainText(null, tv.text.toString())
        } else {
            val cmb = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cmb.text = tv.text.toString()
        }
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
        return false
    }

}
