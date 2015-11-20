package com.example.pages;

import android.app.Activity;

public class SmartServicePage extends BasePage {

	public SmartServicePage(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initData() {
		
		tv_basepage_titile.setText("智能服务");
		System.out.println("SmartServicePage.initData()");
	    setSlidingMenuEnalbe(true);

 	}
}
