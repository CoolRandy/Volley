package com.android.volley.utils;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
/**
 * LruCache���㷨ԭ���ǰ����ʹ�õĶ�����ǿ���ô洢�� LinkedHashMap �У����Ұ��������ʹ�õĶ����ڻ���ֵ�ﵽԤ�趨ֵ֮ǰ���ڴ����Ƴ�
 * �����û������� (SoftReference or WeakReference)�����Ѳ��Ƽ�ʹ��
 * ���ڻ���Ĵ�С����Ҫ���������ڴ�ʹ�����������ָ��������ռ�̫С��ʹ��ͼƬ��Ƶ�����ͷź����¼��أ�̫�����п������OOM
 * @author a
 *
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache{

	public LruBitmapCache(int maxSize) {
		super(maxSize);
	}
	 public static int getDefaultLruCacheSize() {
		 	// ��ȡ�������ڴ�����ֵ��ʹ���ڴ泬�����ֵ������OutOfMemory�쳣��  
		    // LruCacheͨ�����캯�����뻺��ֵ����KBΪ��λ��  
	        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	        // ʹ���������ڴ�ֵ��1/8��Ϊ����Ĵ�С��  
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
