package com.example.androidtest;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView img = (ImageView) findViewById(R.id.imageView1);
//
//        Drawable drawable = getResources().getDrawable(R.drawable.test);
//        img.setImageDrawable(drawable);
//        ClipDrawable clip = new ClipDrawable(drawable, Gravity.TOP, ClipDrawable.HORIZONTAL);
//        Rect bounds = new Rect(0, 0, 200, 200);
//        clip.setBounds(bounds);
//        clip.setLevel(10000);// max is 10000
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
}
