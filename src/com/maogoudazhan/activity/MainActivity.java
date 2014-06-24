package com.maogoudazhan.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.RadioGroup;

import com.maogoudazhan.fragment.WebviewFragment;
import com.maogoudazhan.fragment.WebviewFragment.OnWebViewListener;

public class MainActivity extends FragmentActivity implements OnWebViewListener {
	/**
	 * Called when the activity is first created.
	 */
	private RadioGroup rgs;
	public List<Fragment> fragments = new ArrayList<Fragment>();

	public String hello = "hello ";
	FragmentTabAdapter tabAdapter;

//	private WebviewFragment getWf(String url) {
//		WebviewFragment wf1 = WebviewFragment.getInstance();
//		Bundle b = new Bundle();
//		b.putString(WebviewFragment.URL, url);
//		wf1.setArguments(b);
//		return wf1;
//	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main); 
		 
		fragments.add(new WebviewFragment(HomePageActivity.TUIJIAN_URL));
		fragments.add(new WebviewFragment(HomePageActivity.ZILIAO_URL));
		fragments.add(new WebviewFragment(HomePageActivity.GONGLUE_URL));
		fragments.add(new WebviewFragment(HomePageActivity.CONFIG_URL));
		fragments.add(new WebviewFragment(HomePageActivity.GENGDUO_URL));
		// fragments.add(new TabFFm());

		rgs = (RadioGroup) findViewById(R.id.tabs_rg);

		tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content,
				rgs);
		tabAdapter
				.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
					@Override
					public void OnRgsExtraCheckedChanged(RadioGroup radioGroup,
							int checkedId, int index) {
						System.out.println("Extra---- " + index
								+ " checked!!! ");
					}
				});

	}
}
