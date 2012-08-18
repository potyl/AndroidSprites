package com.example.androidtest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private RefrestTask refreshTask = null;
	private Handler handler = new Handler();



	static class RefrestTask implements Runnable {
		private static final long REFRESH_INTERVAL = 1000/30; // 30 frames per second

		private final Handler handler;
		private final ImageView img;
		private final BitmapDrawable [] bitmapDrawables;

		int idx = 0;
		int speed = 10; // speed in pixels
		boolean goRight = true; // Direction

		
		static RefrestTask start(Handler handler, ImageView img, BitmapDrawable [] bitmapDrawables) {
			RefrestTask task = new RefrestTask(handler, img, bitmapDrawables);
			handler.postDelayed(task, 0); 
			return task;
		}
		
		public RefrestTask(Handler handler, ImageView img, BitmapDrawable [] bitmapDrawables) {
			this.img = img;
			this.handler = handler;
			this.bitmapDrawables = bitmapDrawables;
		}
		
		public void run() {
			BitmapDrawable d = bitmapDrawables[idx];
			img.setImageDrawable(d);
			
			if (goRight) {
				idx += speed;
				if (idx >= bitmapDrawables.length) {
					idx = bitmapDrawables.length - 1;
					goRight = false;
				}
			}
			else {
				idx -= speed;
				if (idx < 0) {
					idx = 0;
					goRight = true;
				}
			}

			// Schedule the next refresh
			handler.postDelayed(this, REFRESH_INTERVAL);
		}
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView img = (ImageView) findViewById(R.id.imageView1);

        // Create the bitmaps
		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.budapest);
		Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

		int w = 100;//img.getWidth();
		int h = 100;//img.getHeight();

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		
		printf("bitmap: %s x %s", width, height);
		printf("img: %s x %s", w, h);

		long memA = getMemoryUsed();
		final BitmapDrawable [] bitmapDrawables = new BitmapDrawable [width - w];
		printf("Creating: %s bitmaps", bitmapDrawables.length);
		int x = 0;
		int y = 0;
		for (int i = 0; i < bitmapDrawables.length; ++i) {
			if (x + w > width) {
				// A new row
				x = 0;
				++y;
			}
			Bitmap bClipped = Bitmap.createBitmap(bitmap, x, y, w, h);
			BitmapDrawable bDrawable = new BitmapDrawable(res, bClipped);
			bitmapDrawables[i] = bDrawable;
			++x;
		}
		long memB = getMemoryUsed();
		printf("Memory increase: %s", memB - memA);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (refreshTask != null) handler.removeCallbacks(refreshTask);
				refreshTask = RefrestTask.start(handler, img, bitmapDrawables);
			}
		});
    }

    static void printf(String format, Object...args) {
    	String message = String.format(format, args);
    	Log.d("test", message);
    }

    long getMemoryUsed() {
    	ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    	MemoryInfo mi = new MemoryInfo();
    	activityManager.getMemoryInfo(mi);
    	return mi.availMem;
    }
}


