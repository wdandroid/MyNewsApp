package com.example.pages;

import android.app.Activity;
import android.view.View;

public class SettingPage extends BasePage {

	public SettingPage(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initData() {
		
		tv_basepage_titile.setText("设置");
	    bt_basepage_leftbt.setVisibility(View.INVISIBLE);	

	    System.out.println("SettingPage.initData()");
	    setSlidingMenuEnalbe(false);
 	}
}
