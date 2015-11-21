package com.example.menupage;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class PicturesMenuPage extends BaesMenuPage {

	public PicturesMenuPage(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
        TextView tv = new TextView(mActivity);
		
		tv.setText("组图menu页面");
		tv.setTextSize(30);
		return tv;
	}

}
