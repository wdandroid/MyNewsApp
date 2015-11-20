package com.example.pages;

import com.cskaoyan.bean.CategoryJson;
import com.example.fragment.LeftMenuFragment;
import com.example.mynewsapp.MainActivity;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.widget.Toast;

public class NewsCenterPage extends BasePage {

	public NewsCenterPage(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void initData() {
		
		tv_basepage_titile.setText("新闻中心");
	    setSlidingMenuEnalbe(true);

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
			  
			  CategoryJson categoryJson = gson.fromJson(JsonString, CategoryJson.class);
			  
			  System.out.println("NewsCenterPage.MyRequestCallback.onSuccess()"+categoryJson.toString());
			  
			  
			  MainActivity mainUI=  (MainActivity) mActivity;
			  LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
			  leftMenuFragment.SetCategoryData(categoryJson);
			  
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			// TODO Auto-generated method stub
			
			Toast.makeText(mActivity, "下载失败", 1).show();
			error.printStackTrace();
 		}
		
	}
}
