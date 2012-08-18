package com.example.androidtest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	BitmapDrawable [] bitmapDrawables;
	
	int idx = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);

        final ImageView img = (ImageView) findViewById(R.id.imageView1);

		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.budapest);
		Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

		int w = 100;//img.getWidth();
		int h = 100;//img.getHeight();

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		
		printf("bitmap: %s x %s", width, height);
		printf("img: %s x %s", w, h);

		printf("1) %s", getMemoryUsed());
		bitmapDrawables = new BitmapDrawable [ 500 ];
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
		printf("2) %s", getMemoryUsed());

        if (button != null) button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (idx++ == bitmapDrawables.length) idx = 0;
				BitmapDrawable d = bitmapDrawables[idx];
				printf("Using bitmapt: %s; bouns: %s", idx, d.getBounds());
				img.setImageDrawable(d);
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


