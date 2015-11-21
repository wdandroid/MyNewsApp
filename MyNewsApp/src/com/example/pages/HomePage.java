package com.example.pages;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

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
	    

		
		TextView tv_basepager_content=new TextView(mActivity);
		tv_basepager_content.setText("首页内容");
		tv_basepager_content.setTextColor(Color.RED);
		tv_basepager_content.setTextSize(20);
		LayoutParams layoutParams =   new LayoutParams(-1, -1);//tv_basepager_content.getLayoutParams();
 
		tv_basepager_content.setLayoutParams(layoutParams);
		tv_basepager_content.setGravity(Gravity.CENTER);
		fl_basepager_content.addView(tv_basepager_content);
		
	    setSlidingMenuEnalbe(false);
	    
 	}
	

}
