package com.maogoudazhan.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.LinearLayout;

import com.maogoudazhan.fragment.WebviewFragment;

/**
 * 首页.
 * 
 * @author 130126
 * 
 */
public class OldHomeActivity extends FragmentActivity implements
		WebviewFragment.OnWebViewListener {
	// 变量：登陆用户，token
	private String url;
	private static final int DIALOG_KEY = 0;
	/**
	 * fragment的主体布局.
	 */
	private LinearLayout all;
	/**
	 * 弹出框
	 */
	private ProgressDialog dialog;

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_KEY: {
			dialog = new ProgressDialog(this);
			dialog.setMessage("正在查询...");
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			return dialog;
		}
		}
		return null;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 加载布局
		setContentView(R.layout.new_home);
		all = (LinearLayout) findViewById(R.id.all);
		url = getIntent().getStringExtra("url");
		//设置第一个tab的片段.
		Bundle args = new Bundle();
		args.putString(WebviewFragment.URL, url);
		WebviewFragment newFragment =new WebviewFragment();//.getInstance();
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		// 设置参数
		newFragment.setArguments(args);
		transaction.replace(R.id.tab_content, newFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

}
