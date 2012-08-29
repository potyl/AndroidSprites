package sk.xeito.android.sprites;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareDialog();
    }

    static void printf(String format, Object...args) {
    	String message = String.format(format, args);
    	Log.d("test", message);
    }

    private void prepareDialog() {
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				createDialog();
			}
        });
        
        Button toastButton = (Button) findViewById(R.id.toast_button);
        toastButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "Toasty !!!", Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    static class MyDialog extends Dialog {

		public MyDialog(Context context, int theme) {
			super(context, theme);
		}
		
		@Override
		public boolean dispatchTouchEvent(MotionEvent ev) {
			Activity activity = getOwnerActivity();
			boolean consumed = super.dispatchTouchEvent(ev);
			printf("consumed? %s", consumed);
			if (consumed) return true;
			return activity.dispatchTouchEvent(ev);
		}

    }
    
    private Dialog dialog;
    private void createDialog() {
    	
    	if (dialog != null) dialog.dismiss();
        dialog = new MyDialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setOwnerActivity(this);
        Window window = dialog.getWindow();
        int flags = LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCH_MODAL;
        window.setFlags(flags, flags);

        // The main widget
        Button view = new Button(this);
        view.setText("Overlay");
        view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "*** IT WORKS ***", Toast.LENGTH_SHORT).show();
			}
		});

        // Position the main widget with a frame layout
        FrameLayout layout = new FrameLayout(this);
        layout.setBackgroundColor(Color.argb(25, 0, 0, 250));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
        		FrameLayout.LayoutParams.WRAP_CONTENT,
        		FrameLayout.LayoutParams.WRAP_CONTENT,
        		Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
        );
        view.setLayoutParams(params);
        
        layout.addView(view);
        dialog.setContentView(layout);
        dialog.show();
    }
}
