package com.example.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TopNewsViewPager extends ViewPager {

	private int startY;
	private int startx;



	public TopNewsViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TopNewsViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		//任何case下父控件都不要拦截，交给我
		
	switch (ev.getAction()) {
		
		case MotionEvent.ACTION_DOWN:
			startx = (int) ev.getRawX();
			startY = (int) ev.getRawY();
			getParent().requestDisallowInterceptTouchEvent(true);

			break;
		case MotionEvent.ACTION_MOVE:
			
			int endx = (int) ev.getRawX();
			int endy= (int) ev.getRawY();
			
			if (Math.abs(endx-startx)>Math.abs(endy-startY)) {//表面是水平滑动
				
				if (endx-startx>0&&getCurrentItem()==0) {//右滑，当滑动到第一个page的时候，交给父控件处理
					getParent().requestDisallowInterceptTouchEvent(false);
					
					
				}else if(startx-endx>0&&getCurrentItem()==getAdapter().getCount()-1)//左滑，当滑动到最后一个page的时候，应该让父控件处理
			    {
					getParent().requestDisallowInterceptTouchEvent(false);
			    }else {
				    getParent().requestDisallowInterceptTouchEvent(true);

				}
			

			}else {//竖直方向滑动
				
				//getParent().requestDisallowInterceptTouchEvent(true);

			}
			
			
			break;
			
		case MotionEvent.ACTION_UP:
		
			break;

		default:
			break;
		}
		
		
		
		
		//getParent().requestDisallowInterceptTouchEvent(true);
		return super.onTouchEvent(ev);
	}
	

}
