package com.example.menupage;

import com.cskaoyan.bean.CategoryJson.MenuData.ChildrenData;
import com.cskaoyan.bean.MenuDetialNews;
import com.cskaoyan.bean.MenuDetialNews.MenuDetailData.NewsData;
import com.example.global.GlobalConstanc;
import com.example.mynewsapp.R;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.SyncStateContract.Constants;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
	private ListView lv_menudetailpage_news;

	private TextView tv_newsmenudetail_toptitle;

	private CirclePageIndicator indiactor;

	private View headerview;
	
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
		
		
		headerview = View .inflate(mActivity, R.layout.list_item_header, null);
		
		vp_menudetailpage_topnews = (ViewPager) headerview.findViewById(R.id.vp_menudetailpage_topnews);
		tv_newsmenudetail_toptitle = (TextView) headerview.findViewById(R.id.tv_newsmenudetail_toptitle);
		indiactor = (CirclePageIndicator) headerview.findViewById(R.id.indicator_topnews_ind);
		
		
		lv_menudetailpage_news = (ListView) view.findViewById(R.id.lv_menudetailpage_news);
		
		
 		return view;
	}

	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
/*		tv.setText(detaildata.title);
		tv.setTextColor(Color.RED);
		tv.setTextSize(30);
		tv.setGravity(Gravity.CENTER);*/
		
		HttpUtils httpUtils =new HttpUtils() ;
		httpUtils.send(HttpMethod.GET, GlobalConstanc.Server_URL+detaildata.url, new MyRequestCallback());
		
		
		
		super.initData();
	}
	
	class MyRequestCallback extends RequestCallBack<String>{

		@Override
		public void onSuccess(ResponseInfo<String> responseInfo) {
			// TODO Auto-generated method stub
			
			String resultjson= responseInfo.result;
			
			parseData(resultjson);
			
		}

	

		@Override
		public void onFailure(HttpException error, String msg) {
			// TODO Auto-generated method stub
			
			error.printStackTrace();
			Toast.makeText(mActivity, msg, 1).show();
		}
		
		
		
	}
	
	
	private void parseData(String resultjson) {
		// TODO Auto-generated method stub
		
		Gson gson =new Gson();
		
		menudetaildata = gson.fromJson(resultjson, MenuDetialNews.class);
		
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
		
		
		//处理listview的数据填充
		lv_menudetailpage_news.addHeaderView(headerview);
		lv_menudetailpage_news.setAdapter(new MyListViewAdatper());
		
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
			return menudetaildata.data.news.size();
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
			
			NewsData newsData = menudetaildata.data.news.get(position);
			
			holder. tv_newsitme_title.setText(newsData.title);
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
