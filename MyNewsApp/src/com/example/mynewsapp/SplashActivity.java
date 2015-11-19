package com.example.mynewsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	
	private LinearLayout ll_splash_bg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splash);
		
		
		ll_splash_bg = (LinearLayout) findViewById(R.id.ll_splash_bg);
	
		showAnimation();
	
	}

	private void showAnimation() {
		// TODO Auto-generated method stub
		
		AnimationSet as = new AnimationSet(false);
		
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		
		rotateAnimation.setDuration(3000);
		
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(3000);
		
		AlphaAnimation alphaAnimation =new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(3000);

		
		as.addAnimation(rotateAnimation);
        as.addAnimation(scaleAnimation);
        as.addAnimation(alphaAnimation);

		ll_splash_bg.setAnimation(as);
		as.start();
		
	}
	
	

}
