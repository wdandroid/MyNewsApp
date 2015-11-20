package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.mynewsapp.R;
import com.example.pages.BasePage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {

	
	private ViewPager vp_content_fragment;

	List<BasePage> pageList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
	
	   /* TextView tvTextView=new TextView(getActivity());
	    tvTextView.setText("ฤฺศÝาณ");
	    return tvTextView;*/
		
		
		View view =inflater.inflate(R.layout.content_fragment, null);
		vp_content_fragment = (ViewPager) view.findViewById(R.id.vp_content_fragment);

		return view;
	}
	
	// oncreate oncreateview onattach onstart onactivitystart   onresume onpause onstop ondetach  ondestory 

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		pageList=new ArrayList<BasePage>();
		
		for(int i=0;i<5;i++)
		{
			
			pageList.add(new BasePage(getActivity()));
		}
		
		vp_content_fragment.setAdapter(new MyPageAdapter());
		super.onActivityCreated(savedInstanceState);
	}
	
	
	class MyPageAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pageList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view==object;
		}
		
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			
			container.addView(pageList.get(position).pageRootView);
			return pageList.get(position).pageRootView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			
			container.removeView((View)object);
			//super.destroyItem(container, position, object);
		}
	}
	
}
