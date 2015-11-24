package com.cskaoyan.bean;

import java.util.List;

public class PhotoData {

	
	public int retcode;
	
	public PhotoDetail data;
	public class PhotoDetail{
		
		public List<Newsdata>  news;
		public class Newsdata{
			public String title;
			public String listimage;
			public String smallimage;

		}
		
		public String title;
	}
}
