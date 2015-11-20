package com.example.pages;

import android.app.Activity;

public class GovAffairsPage extends BasePage {

	public GovAffairsPage(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void initData() {
		
		tv_basepage_titile.setText("政务");
		System.out.println("GovAffairsPage.initData()");
	    setSlidingMenuEnalbe(true);

 	}

}
