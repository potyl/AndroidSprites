package com.example.androidtest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class MyDrawable extends Drawable {

	private final Bitmap bitmap;
	private final Rect srcR;
	private final Rect destR;

	private final Paint paint = new Paint();

	public MyDrawable(Bitmap bitmap, int x, int y, int w, int h) {
		if (bitmap == null) {
			throw new IllegalArgumentException("Bitmap can't be null");
		}
		this.bitmap = bitmap;
		this.srcR  = new Rect(x, y, x + w, h + y);
		this.destR = new Rect(0, 0, w, h);
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		canvas.drawBitmap(this.bitmap, this.srcR, this.destR, this.paint);
		canvas.restore();
	}

	@Override
	public void setAlpha(int alpha) {
		int oldAlpha  = this.paint.getAlpha();
		if (oldAlpha == alpha) return;
		this.paint.setAlpha(alpha);
		invalidateSelf();
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		this.paint.setColorFilter(cf);
		invalidateSelf();
	}

	@Override
	public int getOpacity() {
		return this.getOpacity();
	}
}
