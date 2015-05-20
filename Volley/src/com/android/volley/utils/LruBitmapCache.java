package com.android.volley.utils;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
/**
 * LruCache的算法原理是把最近使用的对象用强引用存储在 LinkedHashMap 中，并且把最近最少使用的对象在缓存值达到预设定值之前从内存中移除
 * 软引用或弱引用 (SoftReference or WeakReference)现在已不推荐使用
 * 对于缓存的大小，需要分析程序内存使用情况来合理指定，缓存空间太小会使得图片被频繁的释放和重新加载，太大则有可能造成OOM
 * @author a
 *
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache{

	public LruBitmapCache(int maxSize) {
		super(maxSize);
	}
	 public static int getDefaultLruCacheSize() {
		 	// 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。  
		    // LruCache通过构造函数传入缓存值，以KB为单位。  
	        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	        // 使用最大可用内存值的1/8作为缓存的大小。  
	        final int cacheSize = maxMemory / 8;
	 
	        return cacheSize;
	    }
	public LruBitmapCache() {
		this(getDefaultLruCacheSize());
	}

	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
		return get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
		put(url, bitmap);
	}
	
	@Override
	protected int sizeOf(String key, Bitmap value) {
		
		return value.getRowBytes() * value.getHeight();
	}

}
