package com.maogoudazhan.activity;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Choreographer.FrameCallback;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

import com.maogoudazhan.tool.Constant;

/**
 * 首页.
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
	private String token, loginUser;
	private float screenHeight = 0;
	private float screenWidth = 0;
	public static float barH = 0.05f;
	/**
	 * 推荐的url
	 */
	public static String TUIJIAN_URL = Constant.HOST + "/index.aspx?gameid=2";
	/**
	 * 资料的url
	 */
	public static String ZILIAO_URL = Constant.HOST + "/list.aspx?gameid=2";
	/**
	 * 攻略的url
	 */
	public static String GONGLUE_URL = Constant.HOST + "/guide.aspx?gameid=2";

	/**
	 * 更多的url
	 */
	public static String GENGDUO_URL = Constant.HOST + "/more.aspx?gameid=2";
	/**
	 * 配置的url
	 */
	public static String CONFIG_URL = Constant.HOST + "/setting.aspx?gameid=2";

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

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.old_main);
		Intent intent = getIntent();
		token = intent.getStringExtra("token");
		loginUser = intent.getStringExtra("loginUser");

		float[] screen2 = getScreen2();
		screenHeight = screen2[1];
		screenWidth = screen2[0];

		final TabHost tabHost = this.getTabHost();
		final TabWidget tabWidget = tabHost.getTabWidget();

		// 设置第一个tab页的对应的intent布局
		TabHost.TabSpec tabSpec = tabHost.newTabSpec(Tab1);
		tabSpec.setIndicator(composeLayout(R.drawable.nav_tuijian_act,
				screenWidth, screenHeight));
		tabSpec.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class).putExtra("url", TUIJIAN_URL));
		tabHost.addTab(tabSpec);

		// 设置第二个tab页的对应的intent布局
		TabHost.TabSpec tabSpec2 = tabHost.newTabSpec(Tab2);
		tabSpec2.setIndicator(composeLayout(R.drawable.nav_ziliao_off,
				screenWidth, screenHeight));
		tabSpec2.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class).putExtra("url", ZILIAO_URL));
		tabHost.addTab(tabSpec2);

		// 设置第三个tab页的对应的intent布局
		TabHost.TabSpec tabSpec3 = tabHost.newTabSpec(Tab3);
		tabSpec3.setIndicator(composeLayout(R.drawable.nav_gonglue_off,
				screenWidth, screenHeight));
		tabSpec3.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class).putExtra("url", GONGLUE_URL));
		tabHost.addTab(tabSpec3);

		// 设置第四个tab页的对应的intent布局
		TabHost.TabSpec tabSpec4 = tabHost.newTabSpec(Tab4);
		tabSpec4.setIndicator(composeLayout(R.drawable.nav_settings_off,
				screenWidth, screenHeight));
		tabSpec4.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class).putExtra("url", CONFIG_URL));
		tabHost.addTab(tabSpec4);

		TabHost.TabSpec tabSpec5 = tabHost.newTabSpec(Tab5);
		tabSpec5.setIndicator(composeLayout(R.drawable.nav_more_off,
				screenWidth, screenHeight));
		tabSpec5.setContent(new Intent(HomePageActivity.this,
				NewHomeActivity.class).putExtra("url", GENGDUO_URL));
		tabHost.addTab(tabSpec5);

		// 这是对Tab标签本身的设置
		int height = (int) (screenHeight * barH);
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			// 设置高度、宽度，不过宽度由于设置为fill_parent，在此对它没效果
			tabWidget.getChildAt(i).getLayoutParams().height = height;
			View v = tabWidget.getChildAt(i);
			v.setBackgroundColor(getResources().getColor(
					R.color.homepage_bottom));
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
									R.drawable.nav_tuijian_act));
						else if (i == 1)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.nav_ziliao_act));
						else if (i == 2)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.nav_gonglue_act));
						else if (i == 3)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.nav_settings_act));
						else if (i == 4)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.nav_more_act));
					} else {
						if (i == 0)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.nav_tuijian_off));
						else if (i == 1)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.nav_ziliao_off));
						else if (i == 2)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.nav_gonglue_off));
						else if (i == 3)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.nav_settings_off));
						else if (i == 4)
							iv.setImageDrawable(getResources().getDrawable(
									R.drawable.nav_more_off));
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
	public View composeLayout(int i, float screenWidth, float screenHeight) {
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		ImageView iv = new ImageView(this);
		iv.setImageResource(i);
		iv.setBackgroundColor(getResources().getColor(R.color.blue));
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		lp.setMargins(0, 0, 0, 0);
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
				(int) (screenWidth / 5.0),
				LinearLayout.LayoutParams.FILL_PARENT);
		iv.setLayoutParams(lp);
		layout.setLayoutParams(lp2);
		layout.addView(iv);
		return layout;
	}
}
