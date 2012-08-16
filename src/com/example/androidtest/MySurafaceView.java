package com.example.androidtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

public class MySurafaceView extends SurfaceView {
	Drawable mDrawable;
	  
	public MySurafaceView(Context context) {
		super(context);
	}
	
	public MySurafaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MySurafaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	void setDrawable(Drawable drawable) {
		this.mDrawable = drawable;
		this.refreshDrawableState();
		this.invalidate();
	}
	
	@Override
	public void draw(Canvas canvas) {
		printf("***** Drawing my surface");
		canvas.save();
		//canvas.clipRect(0, 0, 100, 100);
//		this.mDrawable.setBounds(0, 0, 100, 100);
		Paint paint = new Paint(); 
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);
		canvas.drawPaint(paint);
		
		this.mDrawable.draw(canvas);
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		canvas.drawText("Emo", 10, 10, paint);
		canvas.drawRGB(255, 0, 0);
		canvas.restore();
	}

    static void printf(String format, Object...args) {
    	String message = String.format(format, args);
    	Log.d("test", message);
    }
}
