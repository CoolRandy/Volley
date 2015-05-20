package com.android.volley.volleyTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.ResponseConnControl;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.*;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.randy.volley.R;

/*Volley是将AsyncHttpClient和Universal-Image-Loader的优点集成于一身的一个框架*/
public class MainActivity extends Activity {

    @SuppressWarnings("unused")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载图片
       /* Button mButton = (Button)findViewById(R.id.btn);
        final ImageView imageView = (ImageView) findViewById(R.id.image);

        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);
        //此处ImageLoader的第二个参数ImageCache()为一个空的实现完全没有
        *//*final ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });*//*

        final ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());

        final ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.drawable.ic_launcher, R.drawable.dy);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ImageLoader.ImageContainer container = imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener, 200, 200);
//        container.getBitmap();

                imageView.setImageBitmap(container.getBitmap());
            }
        });*/


        /**
         * 1. 创建一个RequestQueue对象。

         2. 创建一个StringRequest对象。

         3. 将StringRequest对象添加到RequestQueue里面。
         Get
         */
/*        //有时当我们点击url时需要提交请求参数，为此我们需要重写 getParams()方法,该方法需要以key-value的形式返回参数列表
        String url = "http://api.androidhive.info/volley/person_object.json";
        String tag_json_obj = "json_obj_req";
//        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                           Log.d("TAG", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                }){
        	@Override
        	protected Map<String, String> getParams()
        			throws AuthFailureError {
        		Map<String, String> params = new HashMap<String, String>();
        		params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("password", "password123");
        		return params;
        	}
        	
        	*//**
             * Passing some request headers
             * *//*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("apiKey", "xxxxxxxxxxxxxxx");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);*/
//        mQueue.add(stringRequest);

        /**
         * 读取json数据
         */
    /*    RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.weather.com.cn/data/sk/101010100.html", null,
        		new Response.Listener<JSONObject>() {
        			@Override
        			public void onResponse(JSONObject response) {
        				Log.d("TAG", response.toString());
        			}
        		}, new Response.ErrorListener() {
        			@Override
        			public void onErrorResponse(VolleyError error) {
        				Log.e("TAG", error.getMessage(), error);
        			}
        		});
        
       mQueue.add(jsonObjectRequest);*/
        
        //采用AppController
       /* String tag_json_obj = "json_obj_req";
        String url = "http://api.androidhive.info/volley/person_object.json";
        
        final TextView textView = (TextView)findViewById(R.id.text);
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.weather.com.cn/data/sk/101010100.html", null,
        		new Response.Listener<JSONObject>() {
        			@Override
        			public void onResponse(JSONObject response) {
        				Log.d("TAG", response.toString());
        				textView.setText(response.toString());
        				pDialog.hide();
        			}
        		}, new Response.ErrorListener() {
        			@Override
        			public void onErrorResponse(VolleyError error) {
        				Log.e("TAG", error.getMessage(), error);
        				pDialog.hide();
        			}
        		});
    
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);*/
        
        
        /**
         * Universal-Image-Loader具备非常强大的加载网络图片的功能，
         * 而使用Volley，我们也可以实现基本类似的效果，并且在性能上也豪不逊色于Universal-Image-Loader
         */

        /**
         * 加载网络图片
         * ImageRequest的用法
         * 1. 创建一个RequestQueue对象。

           2. 创建一个Request对象。

           3. 将Request对象添加到RequestQueue里面。
           参数解释：1.url  2.图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中
                    3.4.第三第四个参数分别用于指定允许图片最大的宽度和高度，
                        如果指定的网络图片的宽度或高度大于这里的最大值，则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。
                    5.用于指定图片的颜色属性，Bitmap.Config下的几个常量都可以在这里使用，
                        其中ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小
                    6.第六个参数是图片请求失败的回调，这里我们当请求失败时在ImageView中显示一张默认图片
         */

   /*     RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

        ImageRequest imageRequest = new ImageRequest(
                "http://developer.android.com/images/home/aw_dac.png",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(R.drawable.abc_menu_hardkey_panel_mtrl_mult);
            }
        });
        mQueue.add(imageRequest);*/

        /**
         *  ImageLoader的用法
         *  ImageLoader明显要比ImageRequest更加高效，因为它不仅可以帮我们对图片进行缓存，还可以过滤掉重复的链接，避免重复发送请求
         *  1. 创建一个RequestQueue对象。

         2. 创建一个ImageLoader对象。

         3. 获取一个ImageListener对象。

         4. 调用ImageLoader的get()方法加载网络上的图片。
         */


        /**
         * Volley第三种加载网络图片的方式NetworkImageView
         *  NetworkImageView是一个自定义控制，它是继承自ImageView的，具备ImageView控件的所有功能，并且在原生的基础之上加入了加载网络图片的功能
         *  1. 创建一个RequestQueue对象。

            2. 创建一个ImageLoader对象。

            3. 在布局文件中添加一个NetworkImageView控件。
            4. 在代码中获取该控件的实例。

            5. 设置要加载的图片地址。
         */

        /**
         * NetworkImageView中则完全没有提供设置最大宽度和高度的方法,则如何对图片进行压缩的呢？
         * NetworkImageView并不需要提供任何设置最大宽高的方法也能够对加载的图片进行压缩。这是由于NetworkImageView是一个控件，在加载图片的时候它会自动获取自身的宽高，
         * 然后对比网络图片的宽度，再决定是否需要对图片进行压缩。也就是说，压缩过程是在内部完全自动化的，并不需要我们关心
         * 这也就是布局中设置宽高为200dp的原因，内部会根据这个设置的大小来自动进行压缩
         * 如果不想压缩，则可以将宽高设置为wrap_content即可
         */
/*
        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });

        NetworkImageView networkImageView = (NetworkImageView) findViewById(R.id.network_imageview);
        networkImageView.setDefaultImageResId(R.drawable.ic_launcher);
        networkImageView.setErrorImageResId(R.drawable.dy);
        networkImageView.setImageUrl("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
                imageLoader);*/

        /**
         * 自定义XmlRequest
         */
/*
        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

        XMLRequest xmlRequest = new XMLRequest("",
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser response) {

                        try {
                            int eventType = response.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        String nodeName = response.getName();
                                        if ("city".equals(nodeName)) {
                                            String pName = response.getAttributeValue(0);
                                            Log.d("TAG", "pName is " + pName);
                                        }
                                        break;
                                }
                                eventType = response.next();
                            }
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });

        mQueue.add(xmlRequest);*/


        /**
         * 使用GsonRequest
         * 通过Weather类来测试
         */

       /* RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);
        GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(
                "http://www.weather.com.cn/data/sk/101010100.html", Weather.class,
                new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {
                        WeatherInfo weatherInfo = weather.getWeatherinfo();
                        System.out.println("--->" + weatherInfo);
                        Log.d("TAG", "city is " + weatherInfo.getCity());
                        Log.d("TAG", "temp is " + weatherInfo.getTemp());
                        Log.d("TAG", "time is " + weatherInfo.getTime());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        mQueue.add(gsonRequest);
*/
        /**
         *
         */

    }
}
