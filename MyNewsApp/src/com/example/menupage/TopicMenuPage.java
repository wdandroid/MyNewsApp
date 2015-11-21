package com.example.menupage;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class TopicMenuPage extends BaesMenuPage {

	public TopicMenuPage(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
        TextView tv = new TextView(mActivity);
		
		tv.setText("专题menu页面");
		tv.setTextSize(30);
		return tv;
	}

}
