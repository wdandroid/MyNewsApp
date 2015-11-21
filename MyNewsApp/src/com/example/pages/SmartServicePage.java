package com.example.pages;

import android.app.Activity;
import android.widget.TextView;

public class SmartServicePage extends BasePage {

	public SmartServicePage(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initData() {
		
		tv_basepage_titile.setText("智能服务");
		System.out.println("SmartServicePage.initData()");

		    TextView tv_basepager_content=new TextView(mActivity);
			tv_basepager_content.setText("服务内容");
			
			fl_basepager_content.addView(tv_basepager_content);
	    setSlidingMenuEnalbe(true);

 	}
}
