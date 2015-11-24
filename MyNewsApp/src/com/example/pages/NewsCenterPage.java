package com.example.pages;

import java.util.ArrayList;
import java.util.List;

import com.cskaoyan.bean.CategoryJson;
import com.example.fragment.LeftMenuFragment;
import com.example.menupage.BaesMenuPage;
import com.example.menupage.InteractMenuPage;
import com.example.menupage.NewMenuPage;
import com.example.menupage.PicturesMenuPage;
import com.example.menupage.TopicMenuPage;
import com.example.mynewsapp.MainActivity;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class NewsCenterPage extends BasePage {

	private CategoryJson categoryJson;

	public List<BaesMenuPage> menupagelist;
	public NewsCenterPage(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initData() {
		
		tv_basepage_titile.setText("新闻中心");
	    setSlidingMenuEnalbe(true);
		
	/*	TextView tv_basepager_content=new TextView(mActivity);
		tv_basepager_content.setText("新闻内容");
		
		fl_basepager_content.addView(tv_basepager_content);*/

	    System.out.println("NewsCenterPage.initData()");
	    
	    // 
	    //httpurlconnection
	    //httpclient
	    //asynchttpmaster
	    //xutils
	    
	    
	    //从服务器下载json数据
	    HttpUtils httpUtils =new HttpUtils();
	    
	    //10.0.2.2 这个ip表示在模拟器上访问本机电脑的通用IP
	    httpUtils.send(HttpMethod.GET, "http://10.0.2.2:8080/zhbj/categories.json", new MyRequestCallback());
	    
	    System.out.println("NewsCenterPage.initData() over");
	    
	    bt_basepage_rigthbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PicturesMenuPage picturesMenuPage = (PicturesMenuPage) menupagelist.get(2);
				
				picturesMenuPage.changeview();
			}
		});

 	}
	
	
	
	class MyRequestCallback  extends RequestCallBack<String>
	{


		@Override
		public void onSuccess(ResponseInfo<String> responseInfo) {
			// TODO Auto-generated method stub
			
		      String JsonString =	responseInfo.result;
			  System.out.println("NewsCenterPage.MyRequestCallback.onSuccess()"+JsonString);
 		
		      //解析数据
			  // fillbean
			  //Gson google+json 
			  
			  //Gson 解析数据
			  Gson gson = new Gson();
			  
			  categoryJson = gson.fromJson(JsonString, CategoryJson.class);
			  
			  System.out.println("NewsCenterPage.MyRequestCallback.onSuccess()"+categoryJson.toString());
			  
			  
			  MainActivity mainUI=  (MainActivity) mActivity;
			  LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
			  leftMenuFragment.SetCategoryData(categoryJson);
			  
			  //往我们的pagelist里塞数据备用
			  
			  menupagelist = new ArrayList<BaesMenuPage>();
			  
			  menupagelist.add(new NewMenuPage(mActivity,categoryJson.data.get(0).children));
			  menupagelist.add(new TopicMenuPage(mActivity));
			  menupagelist.add(new PicturesMenuPage(mActivity));
			  menupagelist.add(new InteractMenuPage(mActivity));

			  //让这个新闻也默认显示leftmenu的第一个menupage
			  setCurrentMenuPage(0);
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			// TODO Auto-generated method stub
			
			Toast.makeText(mActivity, "下载失败", 1).show();
			error.printStackTrace();
 		}
		
	}
	
	
	public void setCurrentMenuPage(int position){
		
		tv_basepage_titile.setText(categoryJson.data.get(position).getTitle());
		
		//点击侧边栏的时候，初始化一下该menupage页面
		menupagelist.get(position).initData();
		
		fl_basepager_content.removeAllViews();
		fl_basepager_content.addView(menupagelist.get(position).mMenuRootView);
		
		
	}
	
	
}
