package com.example.pages;

import android.app.Activity;
import android.widget.TextView;

public class GovAffairsPage extends BasePage {

	public GovAffairsPage(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void initData() {
		
		tv_basepage_titile.setText("政务");
		
		
		TextView tv_basepager_content=new TextView(mActivity);
		tv_basepager_content.setText("政务内容");
		
		fl_basepager_content.addView(tv_basepager_content);
		
		System.out.println("GovAffairsPage.initData()");
	    setSlidingMenuEnalbe(true);

 	}

}
