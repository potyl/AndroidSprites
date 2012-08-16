package com.example.androidtest;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView) findViewById(R.id.imageView1);

        Drawable drawable = getResources().getDrawable(R.drawable.test);
        ClipDrawable clip = new ClipDrawable(drawable, Gravity.TOP, ClipDrawable.HORIZONTAL);
        Rect bounds = new Rect(0, 0, 200, 200);
        clip.setBounds(bounds);
        clip.setLevel(10000);// max is 10000
        img.setImageDrawable(drawable);
//        img.setImageDrawable(clip);
        
//        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.main);
//        MySurafaceView surfaveView = new MySurafaceView(this);
//        printf("MySurafaceView: %s", surfaveView.getClass().getCanonicalName());
//        surfaveView.setDrawable(drawable);
//        surfaveView.setVisibility(View.VISIBLE);
//        viewGroup.addView(surfaveView, 10, 10);

//        MySurafaceView surfaceView2 = (MySurafaceView) findViewById(R.id.surface);
//        surfaceView2.setDrawable(drawable);
    }

    static void printf(String format, Object...args) {
    	String message = String.format(format, args);
    	Log.d("test", message);
    }

    
    static class MyClipDrawable extends Drawable {
    	final Drawable mDrawable;
    	Rect bounds = new Rect(0, 0, 100, 100);

    	MyClipDrawable(Drawable drawable) {
    		this.mDrawable = drawable;
    	}
    	
		@Override
		public void draw(Canvas canvas) {
			printf("*** drawin");
			canvas.save();
//			canvas.clipRect(0, 0, 100, 100);
			this.mDrawable.draw(canvas);
			canvas.restore();
		}

		@Override
		public int getOpacity() {
			return this.mDrawable.getOpacity();
		}

		@Override
		public void setAlpha(int alpha) {
			this.mDrawable.setAlpha(alpha);
		}

		@Override
		public void setColorFilter(ColorFilter cf) {
			this.mDrawable.setColorFilter(cf);
		}
    }
}
