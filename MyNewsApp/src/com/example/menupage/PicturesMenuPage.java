package com.example.menupage;

import com.cskaoyan.bean.PhotoData;
import com.cskaoyan.bean.PhotoData.PhotoDetail.Newsdata;
import com.example.global.GlobalConstanc;
import com.example.mynewsapp.R;
import com.exmaple.utils.MyBitmapUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PicturesMenuPage extends BaesMenuPage {

	private ListView lvListView;
	private GridView gView;
	private PhotoData photoData;

	public PicturesMenuPage(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		
		
		// TODO Auto-generated method stub
        /*TextView tv = new TextView(mActivity);
		
		tv.setText("组图menu页面");
		tv.setTextSize(30);
		return tv;*/
		
		
		View view = View.inflate(mActivity, R.layout.picture_layout, null);
		
		
		lvListView = (ListView) view.findViewById(R.id.lv_picture);
		gView = (GridView) view.findViewById(R.id.gv_pricture);
		
	
		return view;
	}

	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		
		GetDataFromServer();
		
		
	}
	
	private void GetDataFromServer() {
		// TODO Auto-generated method stub
		
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, GlobalConstanc.Server_URL+"/photos/photos_1.json", new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				String json =responseInfo.result;
				
				parsedata(json);
			}

			

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
			
				error.printStackTrace();
				Toast.makeText(mActivity, "获取组图失败", 0).show();
			}
		});
		
	}
	
	
	private void parsedata(String json) {
		// TODO Auto-generated method stub
		
		Gson gson = new Gson();
		photoData = gson.fromJson(json, PhotoData.class);
		MyAdpater myAdpater = new MyAdpater();
		lvListView.setAdapter(myAdpater);
		gView.setAdapter(myAdpater);
	}

	class MyAdpater extends BaseAdapter{

		
		//BitmapUtils bitmaputils;
		
		  MyBitmapUtils Mybitmaputils;
		
		public MyAdpater() {
			super();
			// TODO Auto-generated constructor stub
			Mybitmaputils = new MyBitmapUtils();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return photoData.data.news.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			 View view = View.inflate(mActivity, R.layout.list_photo_item, null);

		     ImageView  iv_phtot_pic=(ImageView) 	view.findViewById(R.id.iv_phtot_pic);
			 TextView tv_photo_title = (TextView) view.findViewById(R.id.tv_photo_title);
		     
			 Newsdata newsdata = photoData.data.news.get(position);			 
			 Mybitmaputils.display(iv_phtot_pic, newsdata.listimage);
			 
			 tv_photo_title.setText(newsdata.title);
 			 
			return view;
		}
		
		
		
		
	}
	
	boolean change =false;
	
	public void changeview(){
		
		if (!change) {//切换到gridview
			
			lvListView.setVisibility(View.GONE);
			gView.setVisibility(View.VISIBLE);
			change=true;
		}
		else {//且回到listview
			gView.setVisibility(View.GONE);
			lvListView.setVisibility(View.VISIBLE);
			change=false;
		}
		
		
		
	}
}
