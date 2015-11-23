package com.example.menupage;

import java.util.ArrayList;

import com.cskaoyan.bean.CategoryJson.MenuData.ChildrenData;
import com.example.mynewsapp.R;
import com.viewpagerindicator.TabPageIndicator;

import android.R.color;
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewMenuPage extends BaesMenuPage {

	private ViewPager vp_newsmenupage_content;

	public ArrayList<ChildrenData> children;

	private TabPageIndicator indicator_newsmenupage_tab;
	
	public NewMenuPage(Activity mActivity,ArrayList<ChildrenData> children) {
		super(mActivity);
		this.children=children;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
       /* TextView tv = new TextView(mActivity);
		
		tv.setText("新闻menu页面");
		tv.setTextSize(30);
		return tv;*/
		View view= View.inflate(mActivity, R.layout.news_menu_page, null);
		
		vp_newsmenupage_content = (ViewPager) view.findViewById(R.id.vp_newsmenupage_content);		
		
		indicator_newsmenupage_tab = (TabPageIndicator) view.findViewById(R.id.indicator_newsmenupage_tab);
		
		return view;
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		//初始化vp
		
		vp_newsmenupage_content.setAdapter(new MyPageAdapter());
		//此处需要在vp设置好之后，才能舍设置indicator
		
		
		indicator_newsmenupage_tab.setViewPager(vp_newsmenupage_content);
		
		
 	}
	
	class MyPageAdapter extends PagerAdapter{
		
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			
			return children.get(position).title;//super.getPageTitle(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return children.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view==object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			
			TextView tv = new TextView(mActivity);
			
			tv.setText(children.get(position).title);
			tv.setTextColor(Color.RED);
			tv.setTextSize(30);
			container.addView(tv);
			
			//将上面的tv 换成一个由 布局来填充的一个view
			
			return tv;//super.instantiateItem(container, position);
		}
		
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			
			container.removeView((View)object);
			
			//super.destroyItem(container, position, object);
		}
		
		
		
	}
	
	

}
