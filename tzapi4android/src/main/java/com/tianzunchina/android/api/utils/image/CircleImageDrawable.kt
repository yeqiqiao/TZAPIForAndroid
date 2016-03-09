package com.tianzunchina.android.api.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Shader
import android.graphics.drawable.Drawable

/**
 * 圆形图
 * 用法: iv.setImageDrawable(new CircleImageDrawable(bitmap));
 * CraetTime 2016-3-7
 * @author SunLiang
 */
class CircleImageDrawable(private val mBitmap: Bitmap) : Drawable() {
    private val mPaint: Paint
    private val mWidth: Int

    init {
        val bitmapShader = BitmapShader(mBitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP)
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.shader = bitmapShader
        mWidth = Math.min(mBitmap.width, mBitmap.height)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle((mWidth / 2).toFloat(), (mWidth / 2).toFloat(), (mWidth / 2).toFloat(), mPaint)
    }

    override fun getIntrinsicWidth(): Int {
        return mWidth
    }

    override fun getIntrinsicHeight(): Int {
        return mWidth
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        mPaint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}
