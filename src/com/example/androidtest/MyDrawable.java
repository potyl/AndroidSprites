package com.example.androidtest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class MyDrawable extends Drawable {

	Bitmap mBitmap;
	int alpha;
	ColorFilter cf;
	int opacity;
	int x;
	int y;
	int w;
	int h;

	public MyDrawable(Bitmap bitmap, int x, int y, int w, int h) {
		this.mBitmap = bitmap;
		this.alpha = 0;
		this.cf = null;
		this.opacity = 0;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.save();

		Paint paint;

		// Draw the image
		paint = new Paint();
		paint.setAntiAlias(false);
		paint.setFilterBitmap(false);
		Rect srcR = new Rect(x, y, x + w, h + y);
		Rect destR = new Rect(0, 0, w, h);
		canvas.drawBitmap(mBitmap, srcR, destR, paint);

		// Draw text
		paint = new Paint(); 
		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		canvas.drawText("Emo", 20, 60, paint);

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
