package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.mynewsapp.R;
import com.example.pages.BasePage;
import com.example.pages.GovAffairsPage;
import com.example.pages.HomePage;
import com.example.pages.NewsCenterPage;
import com.example.pages.SettingPage;
import com.example.pages.SmartServicePage;
import com.example.view.NoScrollViewPager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class ContentFragment extends Fragment {

	
	private NoScrollViewPager vp_content_fragment;

	List<BasePage> pageList;

	private RadioGroup rg_contentfragment_tab;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
	
	   /* TextView tvTextView=new TextView(getActivity());
	    tvTextView.setText("����ҳ");
	    return tvTextView;*/
		
		
		View view =inflater.inflate(R.layout.content_fragment, null);
		vp_content_fragment = (NoScrollViewPager) view.findViewById(R.id.vp_content_fragment);

		rg_contentfragment_tab = (RadioGroup) view.findViewById(R.id.rg_contentfragment_tab);
		
		return view;
	}
	
	// oncreate oncreateview onattach onstart onactivitystart   onresume onpause onstop ondetach  ondestory 

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//��ʼ�������
		pageList=new ArrayList<BasePage>();
		
		/*for(int i=0;i<5;i++)
		{
			
			pageList.add(new BasePage(getActivity()));
		}*/
		pageList.add(new HomePage(getActivity()));
		pageList.add(new NewsCenterPage(getActivity()));
		pageList.add(new SmartServicePage(getActivity()));
		pageList.add(new GovAffairsPage(getActivity()));
		pageList.add(new SettingPage(getActivity()));
		vp_content_fragment.setAdapter(new MyPageAdapter());
		
		//��radiogroup���õ���¼�
		
		rg_contentfragment_tab.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
				switch (checkedId) {
				case R.id.rb_contentfragment_index:
					vp_content_fragment.setCurrentItem(0,false);
					pageList.get(0).initData(); 
					break;
				case R.id.rb_contentfragment_news:
					vp_content_fragment.setCurrentItem(1,false);
					pageList.get(1).initData(); 

					break;
				case R.id.rb_contentfragment_service:
					vp_content_fragment.setCurrentItem(2,false);
					pageList.get(2).initData(); 

					break;
				case R.id.rb_contentfragment_gov:
					vp_content_fragment.setCurrentItem(3,false);
					pageList.get(3).initData(); 

					break;
				case R.id.rb_contentfragment_setting:
					vp_content_fragment.setCurrentItem(4,false);
					pageList.get(4).initData(); 

					break;
					

				default:
					break;
				}
				
			}
		});
		
		
		rg_contentfragment_tab.check(R.id.rb_contentfragment_index);
		
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
			//pageList.get(position).initData(); //������������ã���Ϊ����viewpager ����ǰһ��pageȥ����
			
			
			return pageList.get(position).pageRootView;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			
			container.removeView((View)object);
			//super.destroyItem(container, position, object);
		}
	}
	
	public NewsCenterPage getNewsCenterPage(){
		
		 return  (NewsCenterPage) pageList.get(1);
		
	}
	
}
