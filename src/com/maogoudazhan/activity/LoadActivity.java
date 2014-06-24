package com.maogoudazhan.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class LoadActivity extends Activity {

	// time for picture display
	private static final int LOAD_DISPLAY_TIME = 1500;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFormat(PixelFormat.RGBA_8888);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		setContentView(R.layout.load);

		new Handler().postDelayed(new Runnable() {
			public void run() {
				// 经过指定时间之后自动跳转到后面的第一个首页面.
				Intent mainIntent = new Intent(LoadActivity.this,
						MainActivity.class);
				LoadActivity.this.startActivity(mainIntent);
				LoadActivity.this.finish();
			}
		}, LOAD_DISPLAY_TIME);
	}
}