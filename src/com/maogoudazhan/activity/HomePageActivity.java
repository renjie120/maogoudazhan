package com.maogoudazhan.activity;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 使用传统的activity进行tab的布局，而不是碎片!
 * 
 * @author 130126
 * 
 */
public class HomePageActivity extends TabActivity {
	private static final String Tab1 = "Tab1";
	private static final String Tab2 = "Tab2";
	private static final String Tab3 = "Tab3";
	private static final String Tab4 = "Tab4";
	private static final String Tab5 = "Tab5"; 
	private float screenHeight = 0;
	private float screenWidth = 0;
	public static float barH = 0.05f;

	/**
	 * 得到屏幕的高宽.
	 * 
	 * @return
	 */
	public float[] getScreen2() {
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		return new float[] { dm.widthPixels, dm.heightPixels };
	}

	long mExitTime;

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();

			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.old_main); 

		float[] screen2 = getScreen2();
		screenHeight = screen2[1];
		screenWidth = screen2[0];

		final TabHost tabHost = this.getTabHost();
		final TabWidget tabWidget = tabHost.getTabWidget();

		// 设置第一个tab页的对应的intent布局
		TabHost.TabSpec tabSpec = tabHost.newTabSpec(Tab1);
		tabSpec.setIndicator(composeLayout(R.drawable.tab_1_on, screenWidth,
				screenHeight, "推荐"));
		tabSpec.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class)
				.putExtra("url", LoadActivity.TUIJIAN_URL));
		tabHost.addTab(tabSpec);

		// 设置第二个tab页的对应的intent布局
		TabHost.TabSpec tabSpec2 = tabHost.newTabSpec(Tab2);
		tabSpec2.setIndicator(composeLayout(R.drawable.tab_2_off, screenWidth,
				screenHeight, "资料"));
		tabSpec2.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class).putExtra("url", LoadActivity.ZILIAO_URL));
		tabHost.addTab(tabSpec2);

		// 设置第三个tab页的对应的intent布局
		TabHost.TabSpec tabSpec3 = tabHost.newTabSpec(Tab3);
		tabSpec3.setIndicator(composeLayout(R.drawable.tab_3_off, screenWidth,
				screenHeight, "攻略"));
		tabSpec3.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class)
				.putExtra("url", LoadActivity.GONGLUE_URL));
		tabHost.addTab(tabSpec3);

		// 设置第四个tab页的对应的intent布局
		TabHost.TabSpec tabSpec4 = tabHost.newTabSpec(Tab4);
		tabSpec4.setIndicator(composeLayout(R.drawable.tab_4_off, screenWidth,
				screenHeight, "配置"));
		tabSpec4.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class).putExtra("url", LoadActivity.CONFIG_URL));
		tabHost.addTab(tabSpec4);

		TabHost.TabSpec tabSpec5 = tabHost.newTabSpec(Tab5);
		tabSpec5.setIndicator(composeLayout(R.drawable.tab_5_off, screenWidth,
				screenHeight, "更多"));
		tabSpec5.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class)
				.putExtra("url", LoadActivity.GENGDUO_URL));
		tabHost.addTab(tabSpec5);

		// 这是对Tab标签本身的设置
		int height = (int) (screenHeight * barH);
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			// 设置高度、宽度，不过宽度由于设置为fill_parent，在此对它没效果
			tabWidget.getChildAt(i).getLayoutParams().height = height;
			View v = tabWidget.getChildAt(i);
			if (i != 0)
				v.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.dot9_tab_off));
			else
				v.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.dot9_tab_on));
			// v.setBackgroundColor(getResources().getColor(
			// R.color.homepage_bottom));
		}

		// 设置Tab变换时的监听事件
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				for (int i = 0; i < tabWidget.getChildCount(); i++) {
					View v = tabWidget.getChildAt(i);
					LinearLayout layout = (LinearLayout) v;
					ImageView iv = (ImageView) layout.getChildAt(0);
					if (tabHost.getCurrentTab() == i) {
						if (i == 0)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_1_on));
						else if (i == 1)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_2_on));
						else if (i == 2)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_3_on));
						else if (i == 3)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_4_on));
						else if (i == 4)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_5_on));
					} else {
						if (i == 0)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_1_off));
						else if (i == 1)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_2_off));
						else if (i == 2)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_3_off));
						else if (i == 3)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_4_off));
						else if (i == 4)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.tab_5_off));
					}
				}
			}
		});

	}

	/**
	 * 动态生成下面的tab底部图标的布局.
	 * 
	 * @param s
	 * @param i
	 * @return
	 */
	@SuppressLint("NewApi")
	public View composeLayout(int i, float screenWidth, float screenHeight,
			String text) {
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL); 
		ImageView iv = new ImageView(this);
		iv.setImageResource(i);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				(int) (screenHeight * 0.05));
		// lp.setMargins(0, AdjustScreenUtil.adjustTabitem((int) screenWidth),
		// 0,
		// 0);
		iv.setLayoutParams(lp);
		layout.addView(iv);

		TextView tv = new TextView(this);
		tv.setGravity(Gravity.CENTER);
		tv.setSingleLine(true);
		tv.setText(text);
		tv.setTextSize(12);
		tv.setTextColor(Color.WHITE);
//		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
//				LinearLayout.LayoutParams.FILL_PARENT, 0, 1);
		// lp2.setMargins(0, AdjustScreenUtil.adjustTabitem((int) screenWidth),
		// 0,
		// 0);
//		tv.setLayoutParams(lp2);
		layout.addView(tv);
		return layout;

		// LinearLayout layout = new LinearLayout(this);
		// layout.setOrientation(LinearLayout.VERTICAL);
		// LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
		// (int) (screenWidth / 5.0),
		// LinearLayout.LayoutParams.FILL_PARENT);
		// layout.setLayoutParams(lp2);
		// TextView tv = new TextView(this);
		// tv.setGravity(Gravity.CENTER);
		// tv.setSingleLine(true);
		// tv.setText(text);
		// tv.setTextSize(12);
		// tv.setTextColor(Color.BLACK);
		// /// 这一步必须要做,否则不会显示.
		// Drawable drawable = getResources().getDrawable(i);
		// drawable.setBounds(0, 0, drawable.getMinimumWidth(),
		// drawable.getMinimumHeight());
		// tv.setCompoundDrawables(null,drawable,null,null);
		// tv.setPadding(1, 1, 1, 1);
		// layout.addView(tv);
		// return layout;
	}

}
