package com.exmaple.utils;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MemoryCacheUtils {
	
	
	//Reference<T>
	
	
	
/*	
 *  默认强引用                                                                      强引用： 用户没有置为null，GC不能释放
 *  java.lang.ref.SoftReference,  软引用： 即是用户没有主动释放，GC也可以回收，不是每次GC的时候都回收，memory实在不够的时候回收。
	java.lang.ref.WeakReference,  弱引用： 即是用户没有主动释放，GC也可以回收，在每次GC的时候，都会释放
    //java.lang.ref.PhantomReference. "
	*/
	
	
	//API9 android 2.3之后，android 虚拟机做了一些改动，会更加主动的去回收 软引用 或者若引用，则会导致我们的这个内存缓存机制 趋于无效。
	//android提供了一种LruCache 来解决
	// LruCache = Least recently used 最近最少使用 cache= 将你最近最少使用的 memory 回收掉
	
	
	
	
	
	//HashMap<String,  Bitmap> memorycache =new HashMap<String, Bitmap>(); //默认的强引用
	
	//HashMap<String,  SoftReference<Bitmap>> memorycache =new HashMap<String,  SoftReference<Bitmap>>(); //java可以，android不行

	
	LruCache<String, Bitmap> memorycache;
	
	
	
	
	
	public MemoryCacheUtils() {
		super();
		// TODO Auto-generated constructor stub
		
	    long maxsie=	Runtime.getRuntime().maxMemory()  ;//我们heap的最大内存size
		
		memorycache = new LruCache<String, Bitmap>((int) (maxsie/8)){
			
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return  value.getByteCount(); //super.sizeOf(key, value);
			}
			
		};
	}


	public Bitmap getBitmapFromMemory(String url ){
		
		/*SoftReference<Bitmap> softReference = memorycache.get(url);
		
		if (softReference!=null) {
			return softReference.get();		
		}*/
		return memorycache.get(url);
		
	}
	
	
	public void saveBitmapToMemory(Bitmap bitmap ,String url){
		
		/*SoftReference<Bitmap> reference = new SoftReference<Bitmap>(bitmap);
		memorycache.put(url, reference);*/
		
		memorycache.put(url, bitmap);
		
	}

}
