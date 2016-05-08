package com.pei.weather.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class SuperScrollView extends ScrollView {

	private OnScrollListener mScrollListener;

	public void setOnScrollListener(OnScrollListener scrollListener) {
		this.mScrollListener = scrollListener;
	}

	public SuperScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SuperScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SuperScrollView(Context context) {
		super(context);
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (mScrollListener != null) {
			mScrollListener.onScroll(x, y, oldx, oldy);
			mScrollListener.onScrollY(getScrollY());
		}
	}

	public interface OnScrollListener {
		void onScroll(int x, int y, int oldx, int oldy);

		void onScrollY(int scrollY);
	}

}
