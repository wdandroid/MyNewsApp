package com.example.pages;

import com.example.mynewsapp.R;

import android.R.raw;
import android.app.Activity;
import android.view.View;

public class BasePage {

	
	public Activity mActivity;
	public View pageRootView;
	
	
	public BasePage(Activity activity){
		
		mActivity=activity;
		initView();
	}


	private void initView() {
		// TODO Auto-generated method stub
		pageRootView=	View.inflate(mActivity, R.layout.basepage_layout, null);
	}
	
	
}
