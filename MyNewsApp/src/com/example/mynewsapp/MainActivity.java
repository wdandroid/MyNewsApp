package com.example.mynewsapp;

import com.example.fragment.ContentFragment;
import com.example.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {

	private SlidingMenu slidingMenu;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
 		setBehindContentView(R.layout.left_menu);
		
		slidingMenu = getSlidingMenu();
		
		//
		
	    int width = getWindowManager().getDefaultDisplay().getWidth();

		slidingMenu.setBehindOffset(width*2/3);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		
		
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		transaction.replace(R.id.fl_leftmenu_menu, new LeftMenuFragment(),"leftmenu_fragemtn");
		transaction.replace(R.id.fl_main_content, new ContentFragment(),"content_fragemtn");
		
		transaction.commit();
	}
	
	
	public LeftMenuFragment getLeftMenuFragment(){
		
		FragmentManager fragmentManager = getFragmentManager();
		LeftMenuFragment findFragmentByTag = (LeftMenuFragment) fragmentManager.findFragmentByTag("leftmenu_fragemtn");

		return findFragmentByTag;
	}
	
	
    public ContentFragment getContentFragment(){
		
		FragmentManager fragmentManager = getFragmentManager();
		ContentFragment findFragmentByTag = (ContentFragment) fragmentManager.findFragmentByTag("content_fragemtn");

		return findFragmentByTag;
	}
	
	
 
    public void toggleSlidingMenu(){
		slidingMenu.toggle();
    }
    
}
