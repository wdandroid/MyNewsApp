package com.example.pages;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.view.View;

public class HomePage extends BasePage {

	public HomePage(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	
	 

 
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
	}

	
	@Override
	public void initData() {
		
		tv_basepage_titile.setText("首页");
	    bt_basepage_leftbt.setVisibility(View.INVISIBLE);	
	    System.out.println("HomePage.initData()");
	    //����viewpager�Ļ����¼�
	    setSlidingMenuEnalbe(false);
	    
 	}
	

}
