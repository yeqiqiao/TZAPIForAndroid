package com.tianzunchina.android.api.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff.Mode
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.media.ExifInterface
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.URL

object  PhotoTools {
    private val converter: UnitConverter

    init {
        converter = UnitConverter
    }

    fun circleBitmap(bitmap: Bitmap, ratio: Int): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width,
                bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawRoundRect(rectF, (bitmap.width / ratio).toFloat(),
                (bitmap.height / ratio).toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }

    /*
	 * 压缩图片文件大小
	 */
    @JvmOverloads fun convertToBitmap(path: String, w: Int = 80, h: Int = 80, isDip: Boolean = true): Bitmap? {
        var w = w
        var h = h
        if (isDip) {
            w = converter.dip2px(w.toFloat())
            h = converter.dip2px(h.toFloat())
        }
        if (!File(path).exists()) {
            return null
        }
        val opts = BitmapFactory.Options()
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888
        // 返回为空
        BitmapFactory.decodeFile(path, opts)
        val width = opts.outWidth
        val height = opts.outHeight
        var scaleWidth = 0f
        var scaleHeight = 0f
        if (width > w || height > h) {
            scaleWidth = width.toFloat() / w
            scaleHeight = height.toFloat() / h
        }
        opts.inJustDecodeBounds = false
        val scale = Math.max(scaleWidth, scaleHeight)
        opts.inSampleSize = scale.toInt()
        val weak = WeakReference(
                BitmapFactory.decodeFile(path, opts))
        if (weak.get() == null) {
            return null
        }
        return Bitmap.createScaledBitmap(weak.get(), w, h, true)
    }

    fun saveBitmap(bitmap: Bitmap, file: File): File {
        if (file.exists()) {
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            bitmap.compress(CompressFormat.JPEG, 80, out)
            out.flush()
            out.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file
    }

    /**
	 * 保存图片到缓存
	 */
    fun saveBitmap(bitmap: Bitmap): File {
        val file = FileCache.cacheDir
        saveBitmap(bitmap, file)
        return file
    }

    fun saveBitmap(bitmap: Bitmap, url: URL): File {
        val file = File(FileCache.cacheDir, FileCache.url2fileName(url))
        saveBitmap(bitmap, file)
        return file
    }

    fun saveBitmap(path: String): File {
        return saveBitmap(getBitmapFromPath(path))
    }

    fun saveBitmap(path: String, angle: Int): File {
        val bitmap = rotateBitmap(getBitmapFromPath(path), angle)
        return saveBitmap(bitmap)
    }

    /*
	 * 按比例缩放尺寸
	 */
    @JvmOverloads fun zoomBitmap(source: Bitmap, width: Int = 800, height: Int = 600): Bitmap {
        var width = width
        var height = height
        val oldW = source.width
        val oldH = source.height
        if (oldW < oldH) {
            val tmp = height
            height = width
            width = tmp
        }
        val w = Math.round(oldW.toFloat() / width)
        val h = Math.round(oldH.toFloat() / height)
        var newW = 0
        var newH = 0
        if (w <= 1 && h <= 1) {
            return source
        }
        val i = if (w > h) w else h
        newW = oldW / i
        newH = oldH / i
        val imgThumb = ThumbnailUtils.extractThumbnail(source, newW, newH)
        return imgThumb
    }

    /*
	 * 缩放或切割图片尺寸
	 * 
	 * @param source: 源图片 width: 目标宽度 height: 目标高度 isDip: width、heigth的单位. true
	 * 为dp，false为px isRoate: 是否旋转, true旋转90°,false不旋转 isCut: 是否进行切割, true
	 * 对source按照width*heigth的尺寸切割， false 按照比例缩放直到宽度和高度都不超过width和heigth
	 */
    @JvmOverloads fun createThumbnail(source: Bitmap?, width: Int = 80, height: Int = 80,
                                      isDip: Boolean = true, isRotate: Boolean = false, isCut: Boolean = true): Bitmap? {
        var width = width
        var height = height
        if (source == null) {
            return null
        }
        if (isDip) {
            width = converter.dip2px(width.toFloat())
            height = converter.dip2px(height.toFloat())
        }
        val oldW = source.width
        val oldH = source.height
        var imgThumb: Bitmap? = null
        if (isCut) {
            imgThumb = ThumbnailUtils.extractThumbnail(source, width, height)
        } else {
            imgThumb = zoomBitmap(source, width, height)
        }
        if (isRotate && oldW < oldH) {
            imgThumb = rotateBitmap(imgThumb, 90)
        }
        return imgThumb
    }

    /**
     * 获取照片旋转角度
     */
    fun getRotate(path: String): Int {
        var exif: ExifInterface? = null
        try {
            exif = ExifInterface(path)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val rot = exif!!.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL)
        return rot
    }

    /*
	 * 旋转图片
	 */
    fun rotateBitmap(bitmap: Bitmap?, angle: Int): Bitmap {
        var bitmap = bitmap
        val width = bitmap!!.width
        val height = bitmap.height
        var matrix: Matrix? = Matrix()
        matrix!!.preRotate(angle.toFloat())
        var mRotateBitmap: Bitmap? = null
        try {
            mRotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                    matrix, true)
        } catch (e: OutOfMemoryError) {
            System.gc()
            System.runFinalization()
            mRotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                    matrix, true)
        }
        return mRotateBitmap!!
    }

    /*
	 * 获取指定uri中的图片
	 */
    fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(
                    context.contentResolver, uri)
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    fun getBitmapFromPath(filePath: String): Bitmap {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565// 表示16位位图
        // 565代表对应三原色占的位数
        options.inInputShareable = true
        options.inPurgeable = true
        val bitmap = BitmapFactory.decodeFile(filePath, options)
        return bitmap
    }

    fun getExifOrientation(filepath: String): Int {
        var degree = 0
        var exif: ExifInterface? = null
        try {
            exif = ExifInterface(filepath)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        if (exif != null) {
            val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, -1)
            if (orientation != -1) {
                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                    ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                    ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
                }
            }
        }
        return degree
    }
}
