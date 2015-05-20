package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by mtime on 15/5/7.
 */
public class GsonRequest<T> extends Request<T> {

    private final Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;


    /**
     * 这个构�?方法的使用？
     * @param method
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     */
    public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);

        mGson = new Gson();
        mClass = clazz;
        mListener = listener;
    }

    public GsonRequest(String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener){

        this(Method.GET, url, clazz, listener, errorListener);
    }

    /**
     * 将最终的数据进行回调
     * @param response The parsed response returned by
     */
    @Override
    protected void deliverResponse(T response) {

        mListener.onResponse(response);

    }

    /**
     * 先是将服务器响应的数据解析出来，然后通过调用Gson的fromJson方法将数据组装成对象
     * @param response Response from the network
     * @return
     */

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try{
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString, mClass), HttpHeaderParser.parseCacheHeaders(response));
        }catch(UnsupportedEncodingException e){
            return Response.error(new ParseError(e));
        }
    }
}
