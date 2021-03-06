package com.example.pages;

import com.example.mynewsapp.MainActivity;
import com.example.mynewsapp.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.R.raw;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasePage {

	
	public Activity mActivity;
	public View pageRootView;
	public TextView tv_basepage_titile;
	public  ImageButton bt_basepage_leftbt;
	public FrameLayout fl_basepager_content;
	public ImageButton bt_basepage_rigthbt;
	
	public BasePage(Activity activity){
		
		mActivity=activity;
		initView();
	}


	public void initView() {
		// TODO Auto-generated method stub
		pageRootView=	View.inflate(mActivity, R.layout.basepage_layout, null);
	
		tv_basepage_titile = (TextView) pageRootView.findViewById(R.id.tv_basepage_titile);
	
	    bt_basepage_leftbt = (ImageButton) pageRootView.findViewById(R.id.bt_basepage_leftbt);
	    
	    fl_basepager_content = (FrameLayout) pageRootView.findViewById(R.id.fl_basepager_content);
	    
	    bt_basepage_rigthbt = (ImageButton) pageRootView.findViewById(R.id.bt_basepage_rigthbt);
	    
	    
	    bt_basepage_leftbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity mainActivity =(MainActivity) mActivity;
				mainActivity.toggle();
			}
		});
	}
	
	
	public void initData(){
		
		
	}
	
	public void setSlidingMenuEnalbe(boolean enable){
		SlidingFragmentActivity mainActivity=  (SlidingFragmentActivity) mActivity;
		SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
		//����õ�slidingmenu
		if (!enable) {

			slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_NONE);
		}else {
			 
			slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
		}
		
	}
	
}
