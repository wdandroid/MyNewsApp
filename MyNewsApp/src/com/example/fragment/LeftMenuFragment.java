package com.example.fragment;

import com.cskaoyan.bean.CategoryJson;
import com.example.mynewsapp.R;

import android.R.menu;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftMenuFragment extends Fragment {

	
	private CategoryJson categoryJson;
	
	private ListView lv_leftfragment_menu;
	private String[] menus;

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
			return view;
		}
		
		
		
	}
	
	public void SetCategoryData(CategoryJson categoryJson){
		
		this.categoryJson =categoryJson;
		
		System.out.println("LeftMenuFragment.SetCategoryData()"+categoryJson);
	
		lv_leftfragment_menu.setAdapter(new MyListViewAdapter());

	
	}

   
}
