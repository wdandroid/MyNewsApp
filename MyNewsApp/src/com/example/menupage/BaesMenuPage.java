package com.example.menupage;

import android.app.Activity;
import android.view.View;

public abstract class BaesMenuPage {
	
	public Activity mActivity;
	
	public View mMenuRootView;
	
	public BaesMenuPage(Activity mActivity){
		
		this.mActivity = mActivity;
		
		mMenuRootView =initView();
	}
	
	
	public abstract  View initView();
	
	public void initData(){
	}

}
