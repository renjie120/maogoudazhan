package com.maogoudazhan.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioGroup;

import com.maogoudazhan.fragment.WebviewFragment;
import com.maogoudazhan.fragment.WebviewFragment.OnWebViewListener;

/**
 * 采用FragmentActivity+RadioGroup进行布局的tab布局.
 * 使用一个fragment进行页面的全部的加载，而不是5个独立的webviewFragment.
 * 
 * @author 130126
 * 
 */
public class MainActivity2 extends FragmentActivity implements
		OnWebViewListener {
	/**
	 * Called when the activity is first created.
	 */
	private RadioGroup rgs;

	public String hello = "hello ";
	MyFragmentTabAdapter tabAdapter;
	private String[] urls = { HomePageActivity.TUIJIAN_URL,
			HomePageActivity.ZILIAO_URL, HomePageActivity.GONGLUE_URL,
			HomePageActivity.CONFIG_URL, HomePageActivity.GENGDUO_URL };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		// 五个tab对应的各个片段..
		rgs = (RadioGroup) findViewById(R.id.tabs_rg);
		// 初始化页面的时候，加载web片段布局的片段.
		if (findViewById(R.id.tab_content) != null) {
			if (savedInstanceState != null) {
				return;
			}
			Bundle args = new Bundle();
			args.putString(WebviewFragment.URL, HomePageActivity.TUIJIAN_URL);
			WebviewFragment webFragment = new WebviewFragment();
			webFragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.tab_content, webFragment).commit();
		}
		
		tabAdapter = new MyFragmentTabAdapter(this, R.id.tab_content, rgs);
		
		tabAdapter
				.setOnRgsExtraCheckedChangedListener(new MyFragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
					@Override
					public void OnRgsExtraCheckedChanged(RadioGroup radioGroup,
							int checkedId, int index) {
						Bundle args = new Bundle();
						args.putString(WebviewFragment.URL, urls[index]); 
						// web页面的碎片
						WebviewFragment newFragment = new WebviewFragment();
						FragmentTransaction transaction = getSupportFragmentManager()
								.beginTransaction();
						// 动画
						transaction.setCustomAnimations(R.anim.slide_left_in,
								R.anim.slide_left_out);
						// 设置参数
						newFragment.setArguments(args);
						transaction.replace(R.id.tab_content, newFragment);
						transaction.addToBackStack(null);
						transaction.commit();
					}
				});

	}
}
