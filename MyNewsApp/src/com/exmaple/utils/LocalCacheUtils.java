package com.exmaple.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

public class LocalCacheUtils {

	
	public static String sdpath =Environment.getExternalStorageDirectory().getAbsolutePath();

	public Bitmap getBitmap(String listimage) {
		// TODO Auto-generated method stub
		
		String filename = Md5Digest.getDigest(listimage);
		String cachepath = sdpath+"/mynewsapp/cache";
		File file =new File(cachepath, filename);
		
		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
		
		return bitmap;
		
		
	}
	
	
	public void SaveBitmapToLocal(Bitmap bitmap ,String url){
		//存的时候，应该是将url和保存的文件关联起来
		
		
		String filename = Md5Digest.getDigest(url);
		String cachepath = sdpath+"/mynewsapp/cache";
		
		
		File file =new File(cachepath, filename);
		
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		
		
		try {
			bitmap.compress(CompressFormat.JPEG, 100, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
