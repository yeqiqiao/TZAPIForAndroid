package com.tianzunchina.android.api.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.Drawable

/**
 * 圆角图
 * 用法: iv.setImageDrawable(new RoundImageDrawable(bitmap));
 * CraetTime 2016-3-7
 * @author SunLiang
 */
class RoundImageDrawable(private val mBitmap: Bitmap) : Drawable() {
    private val mPaint: Paint

    private var rectF: RectF? = null

    init {
        val bitmapShader = BitmapShader(mBitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP)
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.shader = bitmapShader
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        rectF = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(rectF, 30f, 30f, mPaint)
    }

    override fun getIntrinsicWidth(): Int {
        return mBitmap.width
    }

    override fun getIntrinsicHeight(): Int {
        return mBitmap.height
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
