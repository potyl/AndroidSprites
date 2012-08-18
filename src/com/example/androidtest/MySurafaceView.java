package com.example.androidtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

public class MySurafaceView extends SurfaceView {
	Bitmap mBitmap;
	  
	public MySurafaceView(Context context) {
		super(context);
	}
	
	public MySurafaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MySurafaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	void setBitmap(Bitmap bitmap) {
		this.mBitmap = bitmap;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();

		int w = getWidth();
		int h = getHeight();

		Paint paint;

		// Draw the image
		paint = new Paint();
		paint.setAntiAlias(false);
		paint.setFilterBitmap(false);
		Rect srcR = new Rect(mX, mY, w + mX, h + mY);
		Rect destR = new Rect(0, 0, w, h);
		canvas.drawBitmap(mBitmap, srcR, destR, paint);

		// Draw text
		paint = new Paint(); 
		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		canvas.drawText("Emo", 20, 60, paint);

		canvas.restore();
	}

    static void printf(String format, Object...args) {
    	String message = String.format(format, args);
    	Log.d("test", message);
    }

    
    int mX = 0;
    int mY = 0;
	public void setXY(int x, int y) {
		// TODO Auto-generated method stub
		mX = x;
		mY = y;
		this.invalidate();
		
	}
}
