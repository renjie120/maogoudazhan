package com.maogoudazhan.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.maogoudazhan.tool.Constant;

public class LoadActivity extends Activity {

	// time for picture display
	private static final int LOAD_DISPLAY_TIME = 1500;
	/**
	 * 推荐的url
	 */
	public static String TUIJIAN_URL = null;
	/**
	 * 资料的url
	 */
	public static String ZILIAO_URL = null;
	/**
	 * 攻略的url
	 */
	public static String GONGLUE_URL = null;

	/**
	 * 更多的url
	 */
	public static String GENGDUO_URL = null;
	/**
	 * 配置的url
	 */
	public static String CONFIG_URL = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFormat(PixelFormat.RGBA_8888);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		setContentView(R.layout.load);

		HttpUtils http = new HttpUtils();
		RequestParams p = new RequestParams();
		http.send(HttpRequest.HttpMethod.GET, Constant.HOST
				+ "/ajax/api.ashx?action=getmenu&gameid=2", p,
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
					}

					public void onLoading(long total, long current,
							boolean isUploading) {
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						JSONObject obj = JSON.parseObject(responseInfo.result);
						TUIJIAN_URL = obj.getString("index_url");
						ZILIAO_URL = obj.getString("info_url");
						GONGLUE_URL = obj.getString("guide_url");
						GENGDUO_URL = obj.getString("setting_url");
						CONFIG_URL = obj.getString("more_url");
						new Handler().postDelayed(new Runnable() {
							public void run() {
								// 经过指定时间之后自动跳转到后面的第一个首页面.
								Intent mainIntent = new Intent(
										LoadActivity.this,
										MyTableActivity.class);
								LoadActivity.this.startActivity(mainIntent);
								LoadActivity.this.finish();
							}
						}, LOAD_DISPLAY_TIME);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});

	}
}