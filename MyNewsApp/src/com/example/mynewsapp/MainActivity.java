package com.example.mynewsapp;

import com.example.fragment.ContentFragment;
import com.example.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setBehindContentView(R.layout.left_menu);
		
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setBehindOffset(200);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		transaction.replace(R.id.fl_leftmenu_menu, new LeftMenuFragment());
		transaction.replace(R.id.fl_main_content, new ContentFragment());
		
		transaction.commit();
	}
 
}
