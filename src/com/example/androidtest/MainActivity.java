package com.example.androidtest;

import android.app.Activity;
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
				int w = img.getWidth();
				int h = img.getHeight();
				printf("img: %s x %s", w, h);
				Bitmap clippedBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h);
				BitmapDrawable bitmapDrawable = new BitmapDrawable(res, clippedBitmap);
				img.setImageDrawable(bitmapDrawable);
			}
		});
    }

    static void printf(String format, Object...args) {
    	String message = String.format(format, args);
    	Log.d("test", message);
    }
}
