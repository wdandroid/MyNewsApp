package com.example.pages;

import com.example.mynewsapp.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.R.raw;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasePage {

	
	public Activity mActivity;
	public View pageRootView;
	public TextView tv_basepage_titile;
	public  ImageButton bt_basepage_leftbt;
	
	public BasePage(Activity activity){
		
		mActivity=activity;
		initView();
	}


	public void initView() {
		// TODO Auto-generated method stub
		pageRootView=	View.inflate(mActivity, R.layout.basepage_layout, null);
	
		tv_basepage_titile = (TextView) pageRootView.findViewById(R.id.tv_basepage_titile);
	
	    bt_basepage_leftbt = (ImageButton) pageRootView.findViewById(R.id.bt_basepage_leftbt);
	}
	
	
	public void initData(){
		
		
	}
	
	public void setSlidingMenuEnalbe(boolean enable){
		SlidingFragmentActivity mainActivity=  (SlidingFragmentActivity) mActivity;
		SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
		//ÈçºÎÄÃµ½slidingmenu
		if (!enable) {

			slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_NONE);
		}else {
			 
			slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
		}
		
	}
	
}
