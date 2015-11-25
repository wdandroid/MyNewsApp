package com.exmaple.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileLock;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class NetworkCacheUtils {

	
	private LocalCacheUtils localCacheUtils;
	
	public NetworkCacheUtils(LocalCacheUtils localCacheUtils) {
		// TODO Auto-generated constructor stub
		
		this.localCacheUtils=localCacheUtils;
	}

	public void display(ImageView iv_phtot_pic, String listimage) {
		// TODO Auto-generated method stub
		
		
		MyAsyncTask task = new MyAsyncTask();
		task.execute(iv_phtot_pic,listimage);
		
		
	}
	
	class MyAsyncTask extends AsyncTask<Object, Float, Bitmap>{

		
		ImageView iv;
		String url;
		
		//最先执行 在主线程执行
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		//第二个执行 doInBackground() 在子线程中执行，

		//去下载
		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			iv =(ImageView) params[0];
			url = (String) params[1];	
			
			iv.setTag(url);
			
		    Bitmap bitmap =	  downloadImage(url);
		   
			return bitmap;
		}

		

		//当doInBackground 执行完毕之后开始执行，在主线程执行
        //注意，该resutl即是 doInBackground的返回值
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub	
			
			String tag = (String) iv.getTag();
			if (tag!=null&&tag.equals(url)) {
				iv.setImageBitmap(result);
				System.out
						.println("NetworkCacheUtils.MyAsyncTask.onPostExecute() 从网络获取缓存");
				localCacheUtils.SaveBitmapToLocal(result, url);
				
			}
 		}
		

		/*//publishProgress
		@Override
		protected void onProgressUpdate(Float... values) {
			// TODO Auto-generated method stub
			//可以实现更新进度的一个功能
			super.onProgressUpdate(values);
		}*/
		 

		private Bitmap downloadImage(String url2) {
			// TODO Auto-generated method stub
			Bitmap bitmap =null;
			URL downloadurl;
			HttpURLConnection conn =null;
			try {
				downloadurl = new URL(url2);
			    conn = (HttpURLConnection) downloadurl.openConnection();

				conn.setConnectTimeout(2000);
				conn.setReadTimeout(2000);
				conn.setRequestMethod("GET");
				conn.connect();
				
				if (conn.getResponseCode()==200) {					
					InputStream inputStream = conn.getInputStream();
					//直接将该流decode成为bitmap对象，可能会崩溃					
				    bitmap = BitmapFactory.decodeStream(inputStream);				    
				    inputStream.close();					
				}
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
				if (conn!=null) {
					conn.disconnect();
				}
				
			}
			
			
			
			
			return bitmap;
		}
		
         
		
		
	}

}
