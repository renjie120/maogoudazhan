package com.maogoudazhan.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

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
		System.out.println("宽度："+size[0]+",,--高度："+size[1]);
		rgs = (RadioGroup) findViewById(R.id.tabs_rg);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				(int)(size[1]*0.07));
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
}
