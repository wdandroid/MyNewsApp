package com.example.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CanScrollViewPager extends ViewPager {

	public CanScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CanScrollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	//设定该控件是否拦截touch事件的的方法
	//true 截断 
	//false 不截断 
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		//请求父控件不要去拦截我们的事件
		
		if (getCurrentItem()==0) {//第二层的viewpager 处于最左边的pager的时候，父控件处理
			getParent().requestDisallowInterceptTouchEvent(false);

		}else {//其他情况 都是第二层viewpager自己处理
			getParent().requestDisallowInterceptTouchEvent(true);
	 	}
		
		
		return super.onInterceptTouchEvent(ev);
	}
	
	
}
