package com.example.androidtest;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);

        final ImageView img = (ImageView) findViewById(R.id.imageView1);

        if (button != null) button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				printf("Using a bitmap");
				Resources res = getResources();
				Drawable drawable = res.getDrawable(R.drawable.budapest);
				Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
				Bitmap clippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, 100, 100);
				BitmapDrawable bitmapDrawable = new BitmapDrawable(res, clippedBitmap);
				img.setImageDrawable(bitmapDrawable);
			}
		});


        if (button == null) button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Drawable drawable = getResources().getDrawable(R.drawable.budapest);
				printf("draw: %s x %s", drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
				printf("img: %s x %s", img.getWidth(), img.getHeight());


				ClipDrawable clip = new ClipDrawable(drawable, Gravity.TOP, ClipDrawable.HORIZONTAL);
				Rect bounds = new Rect(0, 0, img.getWidth(), img.getHeight());
				clip.setBounds(bounds);
				clip.setLevel(10000);// max is 10000
//				img.setVisibility(View.VISIBLE);
				img.setImageDrawable(clip);

				img.refreshDrawableState();
				img.invalidate();
			}
		});

        if (button == null) button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Drawable drawable = getResources().getDrawable(R.drawable.budapest);

				ViewGroup viewGroup = (ViewGroup) findViewById(R.id.main);
		        MySurafaceView surfaceView = new MySurafaceView(MainActivity.this);
		        surfaceView.setWillNotDraw(false);
		        printf("MySurafaceView: %s", surfaceView.getClass().getCanonicalName());
		        surfaceView.setDrawable(drawable);
		        surfaceView.setVisibility(View.VISIBLE);
		        viewGroup.addView(surfaceView, 10, 10);
			}
		});

//        MySurafaceView surfaceView = (MySurafaceView) findViewById(R.id.surface);
//		Drawable drawable = getResources().getDrawable(R.drawable.budapest);
//        surfaceView.setWillNotDraw(false);
//        surfaceView.setDrawable(drawable);
////        surfaceView.refreshDrawableState();
////        surfaceView.invalidateDrawable(drawable);
////        surfaceView.invalidate();
//
//        if (button != null) button.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				Drawable drawable = getResources().getDrawable(R.drawable.budapest);
//
//				printf("Use existin surface");
//		        MySurafaceView surfaceView = (MySurafaceView) findViewById(R.id.surface);
//		        surfaceView.setDrawable(drawable);
//			}
//		});

    }

    static void printf(String format, Object...args) {
    	String message = String.format(format, args);
    	Log.d("test", message);
    }
}
