package com.exmaple.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class MyBitmapUtils {

	
	NetworkCacheUtils networkCacheUtils;
	LocalCacheUtils localCacheUtils;
	MemoryCacheUtils memoryCacheUtils;
	
	
	
	public MyBitmapUtils() {
		super();
		// TODO Auto-generated constructor stub
		memoryCacheUtils= new MemoryCacheUtils();
		localCacheUtils =new LocalCacheUtils();
		networkCacheUtils = new NetworkCacheUtils(localCacheUtils);
	}





	//从给定的url里去获取一张图片
	public void display(ImageView iv_phtot_pic, String listimage) {
		// TODO Auto-generated method stub
		 Bitmap bitmap =null;
		//先看内存缓存有没有，如果有就从内存那
		
		bitmap=memoryCacheUtils.getBitmapFromMemory(listimage);
		
		if (bitmap!=null) {
			 iv_phtot_pic.setImageBitmap(bitmap);
			 System.out.println("MyBitmapUtils.display() 从内存缓存中获取数据");
 			 return;
		}
		
		
		
		//从本地缓存里拿数据，如果有就从本地拿
		 bitmap = localCacheUtils.getBitmap(listimage);
		 if (bitmap!=null) {
			 iv_phtot_pic.setImageBitmap(bitmap);
			 System.out.println("MyBitmapUtils.display() 从本地缓存中获取数据");
			 memoryCacheUtils.saveBitmapToMemory(bitmap, listimage);
			 return;
		 }
	
		//从服务器上去下载
		
		networkCacheUtils.display(iv_phtot_pic,  listimage);
		
	}
	
	
	

}
