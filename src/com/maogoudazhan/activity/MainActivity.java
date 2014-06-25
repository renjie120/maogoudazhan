package com.maogoudazhan.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.maogoudazhan.fragment.WebviewFragment;
import com.maogoudazhan.fragment.WebviewFragment.OnWebViewListener;

/**
 * 采用FragmentActivity+RadioGroup进行布局的tab布局.
 * 
 * @author 130126
 * 
 */
public class MainActivity extends FragmentActivity implements OnWebViewListener {
	/**
	 * Called when the activity is first created.
	 */
	private RadioGroup rgs;
	public List<Fragment> fragments = new ArrayList<Fragment>();

	public String hello = "hello ";
	FragmentTabAdapter tabAdapter;

	public float[] getScreen2() {
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		return new float[] { dm.widthPixels, dm.heightPixels };
	}

	public void ck(View v) {
		if (v.getId() == R.id.tab_rb_a) {
			onShowUrl(HomePageActivity.TUIJIAN_URL);
		} else if (v.getId() == R.id.tab_rb_b) {
			onShowUrl(HomePageActivity.ZILIAO_URL);
		} else if (v.getId() == R.id.tab_rb_c) {
			onShowUrl(HomePageActivity.GONGLUE_URL);
		} else if (v.getId() == R.id.tab_rb_d) {
			onShowUrl(HomePageActivity.CONFIG_URL);
		} else if (v.getId() == R.id.tab_rb_e) {
			onShowUrl(HomePageActivity.GENGDUO_URL);
		}
	}

	public void onShowUrl(String url ) {
		Bundle args = new Bundle();
		args.putString(WebviewFragment.URL, url); 
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
		transaction.commit();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		// 五个tab对应的各个片段..
		fragments.add(new WebviewFragment(HomePageActivity.TUIJIAN_URL));
		fragments.add(new WebviewFragment(HomePageActivity.ZILIAO_URL));
		fragments.add(new WebviewFragment(HomePageActivity.GONGLUE_URL));
		fragments.add(new WebviewFragment(HomePageActivity.CONFIG_URL));
		fragments.add(new WebviewFragment(HomePageActivity.GENGDUO_URL));
		float[] size = getScreen2();
		System.out.println("宽度：" + size[0] + ",,--高度：" + size[1]);
		rgs = (RadioGroup) findViewById(R.id.tabs_rg);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, (int) (size[1] * 0.07));
		rgs.setLayoutParams(lp);

		tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content,
				rgs);
		tabAdapter
				.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
					@Override
					public void OnRgsExtraCheckedChanged(RadioGroup radioGroup,
							int checkedId, int index) {
					}
				});

	}

	long mExitTime = 0;

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
}
