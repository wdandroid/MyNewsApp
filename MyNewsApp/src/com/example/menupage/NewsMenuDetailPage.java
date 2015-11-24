package com.example.menupage;

import java.util.ArrayList;

import javax.crypto.Mac;

import cn.sharesdk.facebook.e;
import cn.sharesdk.sina.weibo.h;
import cn.sharesdk.wechat.utils.m;

import com.cskaoyan.bean.CategoryJson.MenuData.ChildrenData;
import com.cskaoyan.bean.MenuDetialNews;
import com.cskaoyan.bean.MenuDetialNews.MenuDetailData.NewsData;
import com.example.global.GlobalConstanc;
import com.example.mynewsapp.R;
import com.example.mynewsapp.ShowNewsActivity;
import com.example.view.RefreshListView;
import com.example.view.RefreshListView.RefreshingListener;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.SyncStateContract.Constants;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NewsMenuDetailPage extends BaesMenuPage {

	
	ChildrenData detaildata;
	
	MenuDetialNews  menudetaildata;
	
 	private ViewPager vp_menudetailpage_topnews;
	private RefreshListView lv_menudetailpage_news;

	private TextView tv_newsmenudetail_toptitle;

	private CirclePageIndicator indiactor;

	private View headerview;
	
	private String moreUrl;
	
	private ArrayList<NewsData> listdata;

	private MyListViewAdatper myListViewAdatper;
	
	public NewsMenuDetailPage(Activity mActivity,ChildrenData child) {
		super(mActivity);
		// TODO Auto-generated constructor stub
		
		detaildata=child;
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		
		//tv = new TextView(mActivity);
		View view = View.inflate(mActivity, R.layout.news_menu_detailpage, null);
		lv_menudetailpage_news = (RefreshListView) view.findViewById(R.id.lv_menudetailpage_news);

		
		headerview = View .inflate(mActivity, R.layout.list_item_header, null);
		vp_menudetailpage_topnews = (ViewPager) headerview.findViewById(R.id.vp_menudetailpage_topnews);
		tv_newsmenudetail_toptitle = (TextView) headerview.findViewById(R.id.tv_newsmenudetail_toptitle);
		indiactor = (CirclePageIndicator) headerview.findViewById(R.id.indicator_topnews_ind);
		
		
		//处理listview的数据填充
		lv_menudetailpage_news.addHeaderView(headerview);
		
		lv_menudetailpage_news.setRefreshingListener(new RefreshingListener() {			
			@Override
			public void refresh() {
				// TODO Auto-generated method stub
				getDataFromServer() ;
			}

			@Override
			public void loadmore() {
				// TODO Auto-generated method stub
				
				if (moreUrl!=null) { //此时才需去load
					getMoreDataFromServer();

				}else {
					Toast.makeText(mActivity, "没有更多新闻了，休息下吧", 0).show();
					lv_menudetailpage_news.onLoadmoreComplete();
				}
				
			}
			
			
		}); 
		
		
		lv_menudetailpage_news.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//记录一下当前点击的新闻id
				NewsData newsData = listdata.get(position-2);//包含了两个header

				SharedPreferences sp = mActivity.getSharedPreferences("config", mActivity.MODE_PRIVATE);
				
				String histroy = sp.getString("history", "");
				
				
				if (!histroy.contains(newsData.id+"")) {
					String newhistory =histroy+newsData.id+",";
					Editor edit = sp.edit();
					edit.putString("history", newhistory);
					edit.commit();
				}
				
				
				
				//调到新的activity展示
 				
			   System.out
					.println("NewsMenuDetailPage.initView() onItemClick()  position"+position);
				
				Intent intent = new Intent(mActivity, ShowNewsActivity.class);
				
				intent.putExtra("url", newsData.url);
				mActivity.startActivity(intent);
			}
		});
		
 		return view;
	}

	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
/*		tv.setText(detaildata.title);
		tv.setTextColor(Color.RED);
		tv.setTextSize(30);
		tv.setGravity(Gravity.CENTER);*/		
		getDataFromServer();
		super.initData();
	}

	private void getDataFromServer() {
		HttpUtils httpUtils =new HttpUtils() ;
		httpUtils.send(HttpMethod.GET, GlobalConstanc.Server_URL+detaildata.url, new MyRequestCallback());
	}
	
	
	private void getMoreDataFromServer() {
		HttpUtils httpUtils =new HttpUtils() ;
		httpUtils.send(HttpMethod.GET, GlobalConstanc.Server_URL+moreUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				String resultjson= responseInfo.result;

				System.out.println("NewsMenuDetailPage.onSuccess()"+resultjson);

	            parseData(resultjson,true);	

				lv_menudetailpage_news.onLoadmoreComplete();

			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				lv_menudetailpage_news.onLoadmoreComplete();

			}
		});
	}
	
	
	class MyRequestCallback extends RequestCallBack<String>{

		@Override
		public void onSuccess(ResponseInfo<String> responseInfo) {
			// TODO Auto-generated method stub
			
			String resultjson= responseInfo.result;
			System.out.println("NewsMenuDetailPage.onSuccess()"+resultjson);

            parseData(resultjson,false);	
            
            lv_menudetailpage_news.onRefreshComplete();
		}
		
		@Override
		public void onFailure(HttpException error, String msg) {
			// TODO Auto-generated method stub
			
			error.printStackTrace();
			Toast.makeText(mActivity, msg, 1).show();
            lv_menudetailpage_news.onRefreshComplete();

		}
		
		
		
	}
	
	
	private void parseData(String resultjson,boolean ismore) {
		// TODO Auto-generated method stub
		
		Gson gson =new Gson();
		
		menudetaildata = gson.fromJson(resultjson, MenuDetialNews.class);
		
	     String more=	menudetaildata.data.more;
		if (TextUtils.isEmpty(more)) {
			moreUrl=null;
		}else {
			moreUrl=more;
		}
	     
	     if (!ismore) {
	    	 
	    	listdata = menudetaildata.data.news;
	    	vp_menudetailpage_topnews.setAdapter(new MyPagerAdapter());
	 		tv_newsmenudetail_toptitle.setText(menudetaildata.data.topnews.get(0).title);

	 		vp_menudetailpage_topnews.setOnPageChangeListener(new OnPageChangeListener() {
	 			
	 			@Override
	 			public void onPageSelected(int position) {
	 				// TODO Auto-generated method stub
	 				tv_newsmenudetail_toptitle.setText(menudetaildata.data.topnews.get(position).title);
	 			}
	 			
	 			@Override
	 			public void onPageScrolled(int position, float positionOffset,
	 					int positionOffsetPixels) {
	 				// TODO Auto-generated method stub
	 				
	 			}
	 			
	 			@Override
	 			public void onPageScrollStateChanged(int state) {
	 				// TODO Auto-generated method stub
	 				
	 			}
	 		});

	 		indiactor.setViewPager(vp_menudetailpage_topnews);
	 		indiactor.setSnap(true);
	 		myListViewAdatper = new MyListViewAdatper();
	 		lv_menudetailpage_news.setAdapter(myListViewAdatper);
		}else {
			
			ArrayList<NewsData>  moredata=	menudetaildata.data.news;
			
			listdata.addAll(moredata);
			myListViewAdatper.notifyDataSetChanged();
		}
		
		
	}
	
	
	class MyListViewAdatper extends BaseAdapter{

		BitmapUtils bitmaputils;
		
		
		
		
		public MyListViewAdatper() {
			super();
			// TODO Auto-generated constructor stub
			bitmaputils=new BitmapUtils(mActivity);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listdata.size();
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
			
			/*TextView tv = new TextView(mActivity);
			
			tv.setText(menudetaildata.data.news.get(position).title)*/;
			Hodler holder =null;
			View view =null;
			if (convertView!=null) {
				view=convertView;
				holder=(Hodler) view.getTag();
			}else {
			    view = View.inflate(mActivity, R.layout.list_news_item, null);
				
			    holder=new Hodler();		
				holder. tv_newsitme_title = (TextView) view.findViewById(R.id.tv_newsitme_title);
				holder.  tv_newsitme_time = (TextView) view.findViewById(R.id.tv_newsitme_time);
				holder.  iv = (ImageView) view.findViewById(R.id.iv_newsitem_newsimage);
				view.setTag(holder);
			
			}
			
			NewsData newsData = listdata.get(position);
			
			holder. tv_newsitme_title.setText(newsData.title);
			SharedPreferences sp =mActivity.getSharedPreferences("config", mActivity.MODE_PRIVATE);
			String history = sp.getString("history", "");
			 
			if (history.contains(newsData.id+"")) {
				holder. tv_newsitme_title.setTextColor(Color.GRAY);	
				System.out
						.println("NewsMenuDetailPage.MyListViewAdatper.setTextColor()"+history+":"+newsData.id);
			}else {
				holder. tv_newsitme_title.setTextColor(Color.BLACK);	 

			}
			
			holder.  tv_newsitme_time .setText(newsData.pubdate);
			bitmaputils.display(holder.iv, newsData.listimage);
			
			
			return view;//tv;
		}
		
		
		
		
		class Hodler{
			
			TextView tv_newsitme_title;
			TextView tv_newsitme_time;
			ImageView iv ;
		}
		
		
	}
	
	
	
	class MyPagerAdapter extends  PagerAdapter{

		
		BitmapUtils bitmapUtils;
		
		
		
		public MyPagerAdapter() {
			super();
			// TODO Auto-generated constructor stub
			bitmapUtils=new BitmapUtils(mActivity);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return menudetaildata.data.topnews.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view==object;
		}
		
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			
			ImageView iv = new ImageView(mActivity);
			//iv.setImageResource(R.drawable.news_pic_default);
			
			bitmapUtils.display(iv, menudetaildata.data.topnews.get(position).topimage);
			
			iv.setScaleType(ScaleType.FIT_XY);
			//menudetaildata.data.topnews.get(position).topimage;
			
			
			container.addView(iv);
			
			return iv;//super.instantiateItem(container, position);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			
			container.removeView((View)object);
			//super.destroyItem(container, position, object);
		}
		
		
		
		
	}
}
