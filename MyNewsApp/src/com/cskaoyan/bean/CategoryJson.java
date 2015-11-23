package com.cskaoyan.bean;

import java.util.ArrayList;

public class CategoryJson {

	//如果json数据里有某一个字段，但是对应的类里没有这个字段，则解析的时候忽略该字段
	//如果我们写的类里有接受的字段，但是对应的json里没有该数据，则该字段默认赋值为null;
	public ArrayList<MenuData> data;
	int retcode ;
	
	public class MenuData{
		
		String id ;
		String title;
		int type;
		
		String url;
		String url1;

		public ArrayList<ChildrenData> children;
		
		public class ChildrenData{
			
			public  String id;
			public  String title;
			public  int type;
			public  String url;
			
		}

		
		
		public String getTitle() {
			return title;
		}



		public void setTitle(String title) {
			this.title = title;
		}



		@Override
		public String toString() {
			return "MenuData [id=" + id + ", title=" + title + ", type=" + type
					+ ", url=" + url + ", url1=" + url1 + ", children="
					+ children + "]";
		}
		
		
	}

	@Override
	public String toString() {
		return "CategoryJson [data=" + data + ", retcode=" + retcode + "]";
	}

	 
	
	
}
