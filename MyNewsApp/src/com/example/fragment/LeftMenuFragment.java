package com.example.fragment;

import org.apache.http.impl.client.TunnelRefusedException;

import com.cskaoyan.bean.CategoryJson;
import com.example.mynewsapp.MainActivity;
import com.example.mynewsapp.R;
import com.example.pages.NewsCenterPage;

import android.R.menu;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftMenuFragment extends Fragment {

	
	private CategoryJson categoryJson;
	
	private ListView lv_leftfragment_menu;
	private String[] menus;

	private MyListViewAdapter myListViewAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
	
	   /* TextView tvTextView=new TextView(getActivity());
	    tvTextView.setText("�����");
	    return tvTextView;*/
		
	     View  view =	inflater.inflate(R.layout.leftmenu_fragment, null);
		 lv_leftfragment_menu = (ListView) view.findViewById(R.id.lv_leftfragment_menu);

	     return view;
		 
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		//menus = new String[] {"ͷ��","����","���","�ƾ�"};
				
				
		super.onActivityCreated(savedInstanceState);
	}
	
	
	
	
	class MyListViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return categoryJson.data.size();
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
			
/*			TextView tv = new TextView(getActivity());
			tv.setText(categoryJson.data.get(position).getTitle());*/
			
			View view =View.inflate(getActivity(), R.layout.menu_list_item, null);
			TextView tv_menulistitem_category = (TextView) view.findViewById(R.id.tv_menulistitem_category);
			tv_menulistitem_category.setText(categoryJson.data.get(position).getTitle());
			
			//说明此事 应该是需要重新去获取当前我点击的这个itme
			if (mCurrentPostion==position) {
				tv_menulistitem_category.setEnabled(true);
			}
			return view;
		}
		
		
		
	}
	private int mCurrentPostion;

	public void SetCategoryData(CategoryJson categoryJson){
		
		this.categoryJson =categoryJson;
		
		System.out.println("LeftMenuFragment.SetCategoryData()"+categoryJson);
	
		myListViewAdapter = new MyListViewAdapter();
		lv_leftfragment_menu.setAdapter(myListViewAdapter);

		
		lv_leftfragment_menu.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				//去更新contentfragment里面的  newscenterpage这个子页面的 framelayou里面的东西
				
			   MainActivity mainActivity =	(MainActivity) getActivity();
			   ContentFragment contentFragment = mainActivity.getContentFragment();
			   NewsCenterPage newsCenterPage = contentFragment.getNewsCenterPage();
			   
			   newsCenterPage.setCurrentMenuPage(position);
			   
			  // view.setEnabled(true);
			   
			   mCurrentPostion = position;
			   
			   myListViewAdapter.notifyDataSetChanged();
			   
			   mainActivity.toggle();
			   
			   
			   
			}
			
			
		});
	
	}

   
}
