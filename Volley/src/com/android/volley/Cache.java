/*
 * Copyright (C) 2011 The Android Open Source Project
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

package com.android.volley;

import java.util.Collections;
import java.util.Map;

/**
 * 缓存请求结果，Volley 默认使用的是基于 sdcard 的DiskBasedCache�? * NetworkDispatcher得到请求结果后判断是否需要存储在 Cache，CacheDispatcher会从 Cache 中取缓存结果
 */
/**
 * (1). 主要方法�?
     public Entry get(String key); 通过 key 获取请求的缓存实�?     public void put(String key, Entry entry); 存入�?��请求的缓存实�?     public void remove(String key); 移除指定的缓存实�?     public void clear(); 清空缓存
     (2). 代表缓存实体的内部类 Entry

     成员变量和方�?     byte[] data 请求返回的数据（Body 实体�?     String etag Http 响应首部中用于缓存新鲜度验证�?ETag
     long serverDate Http 响应首部中的响应产生时间
     long ttl 缓存的过期时�?     long softTtl 缓存的新鲜时�?     Map<String, String> responseHeaders 响应�?Headers
     boolean isExpired() 判断缓存是否过期，过期缓存不能继续使�?     boolean refreshNeeded() 判断缓存是否新鲜，不新鲜的缓存需要发到服务端做新鲜度的检�? */
/**
 * An interface for a cache keyed by a String with a byte array as data.
 */
public interface Cache {
    /**
     * Retrieves an entry from the cache.
     * @param key Cache key
     * @return An {@link com.android.volley.Cache.Entry} or null in the event of a cache miss
     */
    public Entry get(String key);

    /**
     * Adds or replaces an entry to the cache.
     * @param key Cache key
     * @param entry Data to store and metadata for cache coherency, TTL, etc.
     */
    public void put(String key, Entry entry);

    /**
     * Performs any potentially long-running actions needed to initialize the cache;
     * will be called from a worker thread.
     */
    public void initialize();

    /**
     * Invalidates an entry in the cache.
     * @param key Cache key
     * @param fullExpire True to fully expire the entry, false to soft expire
     */
    public void invalidate(String key, boolean fullExpire);

    /**
     * Removes an entry from the cache.
     * @param key Cache key
     */
    public void remove(String key);

    /**
     * Empties the cache.
     */
    public void clear();

    /**
     * Data and metadata for an entry returned by the cache.
     */
    public static class Entry {
        /** The data returned from cache. */
        public byte[] data;

        /** ETag for cache coherency. */
        public String etag;

        /** Date of this response as reported by the server. */
        public long serverDate;

        /** The last modified date for the requested object. */
        public long lastModified;

        /** TTL for this record. */
        public long ttl;

        /** Soft TTL for this record. */
        public long softTtl;

        /** Immutable response headers as received from server; must be non-null. */
        public Map<String, String> responseHeaders = Collections.emptyMap();

        /** True if the entry is expired. */
        public boolean isExpired() {
            return this.ttl < System.currentTimeMillis();
        }

        /** True if a refresh is needed from the original data source. */
        public boolean refreshNeeded() {
            return this.softTtl < System.currentTimeMillis();
        }
    }

}
