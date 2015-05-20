/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build;

import com.android.volley.Network;
import com.android.volley.RequestQueue;

import java.io.File;

/**
 * 工具类， 用于构建�?��可用于添加网络请求的RequestQue对象
 */
public class Volley {

    /** Default on-disk cache directory. */
    private static final String DEFAULT_CACHE_DIR = "volley";

    /**
     * Creates a default instance of the worker pool and calls {@link com.android.volley.RequestQueue#start()} on it.
     * You may set a maximum size of the disk cache in bytes.
     *
     * @param context A {@link android.content.Context} to use for creating the cache dir.
     * @param stack An {@link HttpStack} to use for the network, or null for default.
     * @param maxDiskCacheBytes the maximum size of the disk cache, in bytes. Use -1 for default size.
     * @return A started {@link com.android.volley.RequestQueue} instance.
     */
    /**
     * 此处附加HttpURLConnection �?AndroidHttpClient(HttpClient 的封�?如何选择及原因：
     * �?Froyo(2.2) 之前，HttpURLConnection 有个重大 Bug，调�?close() 函数会影响连接池，导致连接复用失效，�?���?Froyo 之前使用 HttpURLConnection �?��关闭 keepAlive�?       另外�?Gingerbread(2.3) HttpURLConnection 默认�?���?gzip 压缩，提高了 HTTPS 的�?能，Ice Cream Sandwich(4.0) HttpURLConnection 支持了请求结果缓存�?
       再加�?HttpURLConnection 本身 API 相对�?��，所以对 Android 来说，在 2.3 之后建议使用 HttpURLConnection，之前建议使�?AndroidHttpClient�?     *
     */
    public static RequestQueue newRequestQueue(Context context, HttpStack stack, int maxDiskCacheBytes) {
        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);

        String userAgent = "volley/0";
        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (NameNotFoundException e) {
        }

        if (stack == null) {
            if (Build.VERSION.SDK_INT >= 9) {//如果系统�?Gingerbread 及之�?�?API Level >= 9)，采用基于HttpURLConnection的HurlStack
                stack = new HurlStack();
            } else {//如果小于 9，采用基�?HttpClient �?HttpClientStack
                // Prior to Gingerbread, HttpUrlConnection was unreliable.
                // See: http://android-developers.blogspot.com/2011/09/androids-http-clients.html
                stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }
        //得到HttpStack，然后�?过它构�?�?��代表网络Network的具体实现BasicNetwork
        Network network = new BasicNetwork(stack);
        //接着构�?�?��代表缓存（Cache）的基于 Disk 的具体实现DiskBasedCache
        //�?��将网络（Network）对象和缓存（Cache）对象传入构建一�?RequestQueue，启动这�?RequestQueue，并返回
        RequestQueue queue;
        if (maxDiskCacheBytes <= -1)
        {
        	// No maximum size specified
        	queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        }
        else
        {
        	// Disk cache size specified
        	queue = new RequestQueue(new DiskBasedCache(cacheDir, maxDiskCacheBytes), network);
        }

        queue.start();

        return queue;
    }
    
    /**
     * Creates a default instance of the worker pool and calls {@link com.android.volley.RequestQueue#start()} on it.
     * You may set a maximum size of the disk cache in bytes.
     *
     * @param context A {@link android.content.Context} to use for creating the cache dir.
     * @param maxDiskCacheBytes the maximum size of the disk cache, in bytes. Use -1 for default size.
     * @return A started {@link com.android.volley.RequestQueue} instance.
     */
    public static RequestQueue newRequestQueue(Context context, int maxDiskCacheBytes) {
        return newRequestQueue(context, null, maxDiskCacheBytes);
    }
    
    /**
     * Creates a default instance of the worker pool and calls {@link com.android.volley.RequestQueue#start()} on it.
     *
     * @param context A {@link android.content.Context} to use for creating the cache dir.
     * @param stack An {@link HttpStack} to use for the network, or null for default.
     * @return A started {@link com.android.volley.RequestQueue} instance.
     */
    public static RequestQueue newRequestQueue(Context context, HttpStack stack)
    {
    	return newRequestQueue(context, stack, -1);
    }
    
    /**
     * Creates a default instance of the worker pool and calls {@link com.android.volley.RequestQueue#start()} on it.
     *
     * @param context A {@link android.content.Context} to use for creating the cache dir.
     * @return A started {@link com.android.volley.RequestQueue} instance.
     */
    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }

}

