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

/*Volley�ǽ�AsyncHttpClient��Universal-Image-Loader���ŵ㼯����һ���һ�����*/
public class MainActivity extends Activity {

    @SuppressWarnings("unused")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //����ͼƬ
       /* Button mButton = (Button)findViewById(R.id.btn);
        final ImageView imageView = (ImageView) findViewById(R.id.image);

        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);
        //�˴�ImageLoader�ĵڶ�������ImageCache()Ϊһ���յ�ʵ����ȫû��
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
         * 1. ����һ��RequestQueue����

         2. ����һ��StringRequest����

         3. ��StringRequest������ӵ�RequestQueue���档
         Get
         */
/*        //��ʱ�����ǵ��urlʱ��Ҫ�ύ���������Ϊ��������Ҫ��д getParams()����,�÷�����Ҫ��key-value����ʽ���ز����б�
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
         * ��ȡjson����
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
        
        //����AppController
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
         * Universal-Image-Loader�߱��ǳ�ǿ��ļ�������ͼƬ�Ĺ��ܣ�
         * ��ʹ��Volley������Ҳ����ʵ�ֻ������Ƶ�Ч����������������Ҳ����ѷɫ��Universal-Image-Loader
         */

        /**
         * ��������ͼƬ
         * ImageRequest���÷�
         * 1. ����һ��RequestQueue����

           2. ����һ��Request����

           3. ��Request������ӵ�RequestQueue���档
           �������ͣ�1.url  2.ͼƬ����ɹ��Ļص����������ǰѷ��ص�Bitmap�������õ�ImageView��
                    3.4.�������ĸ������ֱ�����ָ������ͼƬ���Ŀ�Ⱥ͸߶ȣ�
                        ���ָ��������ͼƬ�Ŀ�Ȼ�߶ȴ�����������ֵ������ͼƬ����ѹ����ָ����0�Ļ��ͱ�ʾ����ͼƬ�ж�󣬶��������ѹ����
                    5.����ָ��ͼƬ����ɫ���ԣ�Bitmap.Config�µļ�������������������ʹ�ã�
                        ����ARGB_8888����չʾ��õ���ɫ���ԣ�ÿ��ͼƬ����ռ��4���ֽڵĴ�С����RGB_565���ʾÿ��ͼƬ����ռ��2���ֽڴ�С
                    6.������������ͼƬ����ʧ�ܵĻص����������ǵ�����ʧ��ʱ��ImageView����ʾһ��Ĭ��ͼƬ
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
         *  ImageLoader���÷�
         *  ImageLoader����Ҫ��ImageRequest���Ӹ�Ч����Ϊ���������԰����Ƕ�ͼƬ���л��棬�����Թ��˵��ظ������ӣ������ظ���������
         *  1. ����һ��RequestQueue����

         2. ����һ��ImageLoader����

         3. ��ȡһ��ImageListener����

         4. ����ImageLoader��get()�������������ϵ�ͼƬ��
         */


        /**
         * Volley�����ּ�������ͼƬ�ķ�ʽNetworkImageView
         *  NetworkImageView��һ���Զ�����ƣ����Ǽ̳���ImageView�ģ��߱�ImageView�ؼ������й��ܣ�������ԭ���Ļ���֮�ϼ����˼�������ͼƬ�Ĺ���
         *  1. ����һ��RequestQueue����

            2. ����һ��ImageLoader����

            3. �ڲ����ļ������һ��NetworkImageView�ؼ���
            4. �ڴ����л�ȡ�ÿؼ���ʵ����

            5. ����Ҫ���ص�ͼƬ��ַ��
         */

        /**
         * NetworkImageView������ȫû���ṩ��������Ⱥ͸߶ȵķ���,����ζ�ͼƬ����ѹ�����أ�
         * NetworkImageView������Ҫ�ṩ�κ���������ߵķ���Ҳ�ܹ��Լ��ص�ͼƬ����ѹ������������NetworkImageView��һ���ؼ����ڼ���ͼƬ��ʱ�������Զ���ȡ����Ŀ�ߣ�
         * Ȼ��Ա�����ͼƬ�Ŀ�ȣ��پ����Ƿ���Ҫ��ͼƬ����ѹ����Ҳ����˵��ѹ�����������ڲ���ȫ�Զ����ģ�������Ҫ���ǹ���
         * ��Ҳ���ǲ��������ÿ��Ϊ200dp��ԭ���ڲ������������õĴ�С���Զ�����ѹ��
         * �������ѹ��������Խ��������Ϊwrap_content����
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
         * �Զ���XmlRequest
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
         * ʹ��GsonRequest
         * ͨ��Weather��������
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
