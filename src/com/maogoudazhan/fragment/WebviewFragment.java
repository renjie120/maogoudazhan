package com.maogoudazhan.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.maogoudazhan.activity.R;

/**
 * webView碎片显示网页.
 * 
 * @author 130126
 * 
 */
public class WebviewFragment extends BaseFragment {
	public static final String URL = "url";
	private WebView mWebView;
	private Handler mHandler = new Handler();
	private String url, title;
	private OnWebViewListener listener;

	public interface OnWebViewListener {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (OnWebViewListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnWebViewListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.webviewdemo, container, false);
	}

	@SuppressLint("JavascriptInterface")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
		url = getArguments().getString(WebviewFragment.URL);
		WebSettings webSettings = mWebView.getSettings();
		System.out.println("url==========" + url);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setUserAgentString("APP-Android");
		mWebView.addJavascriptInterface(new Object() {
			public void share(final String i) {
				mHandler.post(new Runnable() {
					public void run() {
						openWeixin(i);
					}
				});
			}
		}, "weixin");
		//支持html5的存储。。。
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
		String appCachePath = getApplicationContext().getCacheDir()
				.getAbsolutePath();
		mWebView.getSettings().setAppCachePath(appCachePath);
		mWebView.getSettings().setAllowFileAccess(true);
		mWebView.getSettings().setAppCacheEnabled(true);

		mWebView.setWebViewClient(new WebViewClient());
		if (url == null || "null".equals(url))
			mWebView.loadUrl("http://www.baidu.com");
		else {
			// url = "file:///android_asset/ichart.html";
			mWebView.loadUrl(url);
		}
	}

	/**
	 * 打开新浪
	 */
	private void openSina() {
		Intent intent = new Intent();
		ComponentName cmp = new ComponentName("com.sina.weibo",
				"com.sina.weibo.EditActivity");
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setComponent(cmp);

		startActivityForResult(intent, 0);
	}

	/**
	 * 打开微信
	 */
	private void openWeixin(String s) {
		Toast.makeText(getActivity(), "you clicked button!" + s, // 进行短暂的提示！
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		ComponentName cmp = new ComponentName("com.tencent.mm",
				"com.tencent.mm.ui.LauncherUI");
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setComponent(cmp);

		startActivityForResult(intent, 0);
	}
}