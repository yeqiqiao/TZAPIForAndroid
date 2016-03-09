package com.tianzunchina.android.api.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * 圆形图片辅助类
 * Created by sl on 2015/10/26.
 */
public class CircleTransform implements Transformation {
   /* @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap;
    }*/

    //重载函数
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();
        int x = 0, y = 0;
        if (width != height) {
            if (width < height) {
                y = (height - width) / 2;
            } else {
                x = (width - height) / 2;
            }
            width = height = Math.min(width, height);
            Bitmap tempSource = Bitmap.createBitmap(source, x, y, width, height);
            source.recycle();
            source = tempSource;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        Canvas canvas = new Canvas(output);
        final RectF bounds = new RectF(0, 0, width, height);
        canvas.drawOval(bounds, paint);
        source.recycle();
        return output;
    }

    @Override
    public String key() {
        return "circle";
    }
}
