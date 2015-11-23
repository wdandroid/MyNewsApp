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
	
	
	//第一层的viewpager默认不要去拦截任何的touchevent
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return   false;//super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return  false;  // super.onTouchEvent(ev); // Ϊʲô����false��������true
	}

}
