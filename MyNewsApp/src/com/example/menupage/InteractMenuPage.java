package com.example.menupage;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class InteractMenuPage extends BaesMenuPage {

	public InteractMenuPage(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		
		TextView tv = new TextView(mActivity);		
		tv.setText("互动menu页面");
		tv.setTextSize(30);
		return tv;
	}

}
