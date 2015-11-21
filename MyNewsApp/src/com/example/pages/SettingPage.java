package com.example.pages;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class SettingPage extends BasePage {

	public SettingPage(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initData() {
		
		tv_basepage_titile.setText("设置");
	    bt_basepage_leftbt.setVisibility(View.INVISIBLE);	
		
	    
	    TextView tv_basepager_content=new TextView(mActivity);
	    tv_basepager_content.setText("设置内容");
		
		fl_basepager_content.addView(tv_basepager_content);

	    System.out.println("SettingPage.initData()");
	    setSlidingMenuEnalbe(false);
 	}
}
