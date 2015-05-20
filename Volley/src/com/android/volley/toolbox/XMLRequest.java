package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mtime on 15/5/4.
 */
public class XMLRequest extends Request<XmlPullParser>{

    private final Listener<XmlPullParser> mListener;

    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link com.android.volley.Request.Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public XMLRequest(int method, String url, Listener<XmlPullParser> listener,
                         ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    /**
     * Creates a new GET request.
     *
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public XMLRequest(String url, Listener<XmlPullParser> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }



    @Override
    protected void deliverResponse(XmlPullParser response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {

        try {

            String xmlString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlString));
            return Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(response));

        }catch(UnsupportedEncodingException e){
            return Response.error(new ParseError(e));
        }catch (XmlPullParserException e){

            return Response.error(new ParseError(e));
        }
    }

    /**
     * 关于 User Agent
     通过代码我们发现如果是使�?AndroidHttpClient，Volley 还会将请求头中的 User-Agent 字段设置�?App �?${packageName}/${versionCode}，如果异常则使用 "volley/0"，不过这个获�?User-Agent 的操作应该放�?if else 内部更合适�?而对�?HttpURLConnection 却没有任何操作，为什么呢�?     如果�?Fiddler �?Charles 对数据抓包我们会发现，我们会发现 HttpURLConnection 默认是有 User-Agent 的，类似�?
     Dalvik/1.6.0 (Linux; U; Android 4.1.1; Google Nexus 4 - 4.1.1 - API 16 - 768x1280_1 Build/JRO03S)
     经常�?WebView 的同学会也许会发现似曾相识，是的，WebView 默认�?User-Agent 也是这个。实际在请求发出之前，会�?�� User-Agent 是否为空，如果不为空，则加上系统默认 User-Agent。在 Android 2.1 之后，我们可以�?�?
     String userAgent = System.getProperty("http.agent");
     得到系统默认�?User-Agent，Volley 如果希望自定�?User-Agent，可在自定义 Request 中重�?getHeaders() 函数
     此处自定义UA
     * @return
     * @throws AuthFailureError
     */

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        // self-defined user agent
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "android-open-project-analysis/1.0");
        return headerMap;
    }
}
