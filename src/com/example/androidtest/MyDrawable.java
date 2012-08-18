package com.example.androidtest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class MyDrawable extends Drawable {

	final Bitmap mBitmap;
	final Rect srcR;
	final Rect destR;

	int alpha;
	ColorFilter cf;
	int opacity;

	public MyDrawable(Bitmap bitmap, int x, int y, int w, int h) {
		this.mBitmap = bitmap;
		this.srcR  = new Rect(x, y, x + w, h + y);
		this.destR = new Rect(0, 0, w, h);

		this.alpha = 0;
		this.cf = null;
		this.opacity = 0;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		canvas.drawBitmap(mBitmap, srcR, destR, null);
		canvas.restore();
	}

	@Override
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		this.cf = cf;
	}

	@Override
	public int getOpacity() {
		return this.getOpacity();
	}
}
