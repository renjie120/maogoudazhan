package com.maogoudazhan.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * 签到界面.采用tab的布局方式.
 * 
 */
public class MyTableActivity extends TabActivity {

	private static final String Tab1 = "Tab1";
	private static final String Tab2 = "Tab2";
	private static final String Tab3 = "Tab3";
	private static final String Tab4 = "Tab4";
	private static final String Tab5 = "Tab5";
	private float screenHeight = 0;
	private float screenWidth = 0;
	public static float barH = 0.1f;

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
		setContentView(R.layout.main);
		float[] screen2 = getScreen2();
		screenHeight = screen2[1];
		screenWidth = screen2[0];

		final TabHost tabHost = this.getTabHost();
		final TabWidget tabWidget = tabHost.getTabWidget();

		// 设置第一个tab页的对应的intent布局
		TabHost.TabSpec tabSpec = tabHost.newTabSpec(Tab1);
		tabSpec.setIndicator(composeLayout("推荐", R.drawable.tab_1_on));
		tabSpec.setContent(new Intent(MyTableActivity.this,
				NewHomeActivity.class)
				.putExtra("url", LoadActivity.TUIJIAN_URL));
		tabHost.addTab(tabSpec);

		// 设置第二个tab页的对应的intent布局
		TabHost.TabSpec tabSpec2 = tabHost.newTabSpec(Tab2);
		tabSpec2.setIndicator(composeLayout("资料", R.drawable.tab_2_off));
		tabSpec2.setContent(new Intent(MyTableActivity.this,
				NewHomeActivity.class).putExtra("url", LoadActivity.ZILIAO_URL));
		tabHost.addTab(tabSpec2);

		// 设置第三个tab页的对应的intent布局
		TabHost.TabSpec tabSpec3 = tabHost.newTabSpec(Tab3);
		tabSpec3.setIndicator(composeLayout("攻略", R.drawable.tab_3_off));
		tabSpec3.setContent(new Intent(MyTableActivity.this,
				NewHomeActivity.class)
				.putExtra("url", LoadActivity.GONGLUE_URL));
		tabHost.addTab(tabSpec3);

		// 设置第三个tab页的对应的intent布局
		TabHost.TabSpec tabSpec4 = tabHost.newTabSpec(Tab3);
		tabSpec4.setIndicator(composeLayout("配置", R.drawable.tab_4_off));
		tabSpec4.setContent(new Intent(MyTableActivity.this,
				NewHomeActivity.class).putExtra("url", LoadActivity.CONFIG_URL));
		tabHost.addTab(tabSpec4);

		// 设置第三个tab页的对应的intent布局
		TabHost.TabSpec tabSpec5 = tabHost.newTabSpec(Tab3);
		tabSpec5.setIndicator(composeLayout("更多", R.drawable.tab_5_off));
		tabSpec5.setContent(new Intent(MyTableActivity.this,
				NewHomeActivity.class)
				.putExtra("url", LoadActivity.GENGDUO_URL));
		tabHost.addTab(tabSpec5);

		// 这是对Tab标签本身的设置
		int height = (int) (screenHeight * barH);
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			// 设置高度、宽度，不过宽度由于设置为fill_parent，在此对它没效果
			tabWidget.getChildAt(i).getLayoutParams().height = height;
			View v = tabWidget.getChildAt(i);
			if (tabHost.getCurrentTab() == i) {
				v.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.dot9_tab_on));
			} else {
				v.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.dot9_tab_off));
			}
		}

		// 设置Tab变换时的监听事件
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				for (int i = 0; i < tabWidget.getChildCount(); i++) {
					View v = tabWidget.getChildAt(i);
					LinearLayout layout = (LinearLayout) v;
					TextView iv = (TextView) layout.getChildAt(0);
					if (tabHost.getCurrentTab() == i) {
						if (i == 0)
							setTextImg(iv, R.drawable.tab_1_on);
						else if (i == 1)
							setTextImg(iv, R.drawable.tab_2_on);
						else if (i == 2)
							setTextImg(iv, R.drawable.tab_3_on);
						else if (i == 3)
							setTextImg(iv, R.drawable.tab_4_on);
						else if (i == 4)
							setTextImg(iv, R.drawable.tab_5_on);
					} else {
						if (i == 0)
							setTextImg(iv, R.drawable.tab_1_off);
						else if (i == 1)
							setTextImg(iv, R.drawable.tab_2_off);
						else if (i == 2)
							setTextImg(iv, R.drawable.tab_3_off);
						else if (i == 3)
							setTextImg(iv, R.drawable.tab_4_off);
						else if (i == 4)
							setTextImg(iv, R.drawable.tab_5_off);
					}
				}
			}
		});

	}

	private void setTextImg(TextView tv, int i) {
		Drawable img_off;
		Resources res = getResources();
		img_off = res.getDrawable(i);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		img_off.setBounds(0, 0, (int)(img_off.getMinimumWidth()*0.8),
				(int)(img_off.getMinimumHeight()*0.8));
		tv.setCompoundDrawables(null, img_off, null, null);
	}

	/**
	 * 动态生成下面的tab底部图标的布局.
	 * 
	 * @param s
	 * @param i
	 * @return
	 */
	public View composeLayout(String s, int i) {
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		TextView tv = new TextView(this);
		tv.setGravity(Gravity.CENTER);
		tv.setSingleLine(true);
		tv.setText(s);
		tv.setTextSize(12);
		tv.setTextColor(Color.WHITE);
		setTextImg(tv, i);
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, 0, 1);
		lp2.setMargins(0, 5, 0, 5);
		tv.setLayoutParams(lp2);
		layout.addView(tv);
		return layout;
	}
}