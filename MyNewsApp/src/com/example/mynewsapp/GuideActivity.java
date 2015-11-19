package com.example.mynewsapp;

import java.util.ArrayList;

 













import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class GuideActivity extends Activity {
	
	private ArrayList<ImageView> ivlist;
	private LinearLayout ll_guide_greypoints;
	private View redpoint;
	private Button bt_guide_enter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		   //使用代码去掉标题栏
		   requestWindowFeature(Window.FEATURE_NO_TITLE);
		   setContentView(R.layout.guide_layout);
		   
		
		  
		   ViewPager vp_main =(ViewPager) findViewById(R.id.vp_main);
		   ll_guide_greypoints = (LinearLayout) findViewById(R.id.ll_guide_greypoints);
		   redpoint = findViewById(R.id.redpoint);
		   bt_guide_enter = (Button) findViewById(R.id.bt_guide_enter);
		   init_view();
		   
		   
		   vp_main.setAdapter(new Myadpter());
		   vp_main.setOnPageChangeListener(new MyOnPageChageLinstener());

	}


	private void init_view() {
		   ivlist = new ArrayList<ImageView>();
		   
		   int[] drawablelist =new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
		   for(int i=0;i<3;i++){
			   
			   ImageView iv = new ImageView(getApplicationContext());
			   iv.setBackgroundResource(drawablelist[i]);
			   
			   ivlist.add(iv);
		   }
		   
		   //初始化三个灰色的点		  
		   for(int i=0;i<3;i++){

			   View greypoint =new View(getApplicationContext());
			   greypoint.setBackgroundResource(R.drawable.greypoint);
			   
			   LayoutParams layoutParams = new LayoutParams(10, 10);//10个像素
			   
			    if (i>0) {
				  layoutParams.leftMargin=10;
			    }
			   
			   greypoint.setLayoutParams(layoutParams);
			   
			 /*
			  * //onmeasure  onlayout  ondraw 此处layoutParams获取不到
			  *   LayoutParams layoutParams = greypoint.getLayoutParams();
		   
			   layoutParams.height=10;
			   layoutParams.width =10;*/
			   
			   
			   
			   ll_guide_greypoints.addView(greypoint);
		   
		   }
		   
	}
	
	
	class Myadpter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		//此处传的object 即是我们instantiateItem返回的
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			//arg0   ： imageview  
			//arg1 : imageview
			return arg0==arg1;
		}
		
		
		//往viewpage里添加view的api
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			
			container.addView(ivlist.get(position));			
			return ivlist.get(position);//imageview
		}
		
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((ImageView)object);;
			//super.destroyItem(container, position, object);
		}
		
		
	}

	
	class MyOnPageChageLinstener implements OnPageChangeListener{

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			
			/*System.out
					.println("position:"+position+
							 " positionOffset:"+positionOffset+":" 
							+" positionOffsetPixels:"+positionOffsetPixels);*/
			// TODO Auto-generated method stub
			
			 int leftmargin = (int) (position*20 +20*positionOffset);
			
			 android.widget.FrameLayout.LayoutParams  layoutParams = (android.widget.FrameLayout.LayoutParams) redpoint.getLayoutParams();			
			 layoutParams.leftMargin=leftmargin;
			 redpoint.setLayoutParams(layoutParams);
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			
			if (position==2) {
				bt_guide_enter.setVisibility(View.VISIBLE);
			}else {
				bt_guide_enter.setVisibility(View.INVISIBLE);

			}
			
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public void enterhome(View v){
		
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
		finish();
		
		SharedPreferences sp=getSharedPreferences("config", MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putBoolean("is_show_guide", true);
		edit.commit();
	}
}
