package com.maogoudazhan.activity;

import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;

/**
 * Created with IntelliJ IDEA. Author: wangjie email:tiantian.china.2@gmail.com
 * Date: 13-10-10 Time: 上午9:25
 */
public class MyFragmentTabAdapter implements RadioGroup.OnCheckedChangeListener {
	private RadioGroup rgs; // 用于切换tab
	private FragmentActivity fragmentActivity; // Fragment所属的Activity
	private int fragmentContentId; // Activity中所要被替换的区域的id
	private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener; // 用于让调用者在切换tab时候增加新的功能

	private int currentTab; // 当前Tab页面索引

	public MyFragmentTabAdapter(FragmentActivity fragmentActivity,
			int fragmentContentId, RadioGroup rgs) {
		this.rgs = rgs;
		this.fragmentActivity = fragmentActivity;
		this.fragmentContentId = fragmentContentId;

		rgs.setOnCheckedChangeListener(this);

	}

	private void showTab(int idx) {
		currentTab = idx; // 更新目标tab为当前tab
	}

	public int getCurrentTab() {
		return currentTab;
	}

	public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
		return onRgsExtraCheckedChangedListener;
	}

	public void setOnRgsExtraCheckedChangedListener(
			OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
		this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
	}

	/**
	 * 切换tab额外功能功能接口
	 */
	static class OnRgsExtraCheckedChangedListener {
		public void OnRgsExtraCheckedChanged(RadioGroup radioGroup,
				int checkedId, int index) {

		}
	}

	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
		for (int i = 0; i < rgs.getChildCount(); i++) {
			if (rgs.getChildAt(i).getId() == checkedId) {

				// 实际这里什么都没有做，交给了引用这里的adapter的外层接口具体实现.
				if (null != onRgsExtraCheckedChangedListener) {
					onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(
							radioGroup, checkedId, i);
				}
				showTab(i); // 显示目标tab
			}
		}

	}
}
