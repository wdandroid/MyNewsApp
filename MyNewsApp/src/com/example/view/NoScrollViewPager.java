package com.example.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoScrollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return  false;  // super.onTouchEvent(ev); // 为什么返回false，不返回true
	}

}
