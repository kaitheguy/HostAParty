package com.example.hostaparty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.util.Log;
 

public class JSONParser {
	public static final String TAG = JSONParser.class.getSimpleName();
	 static InputStream is = null;
	    static JSONObject jObj = null;
	    static String json = "";
	    // constructor
	    public JSONParser() {
	 
	    }
	 
	    // function get json from url
	    // by making HTTP POST or GET mehtod
	    public JSONObject makeHttpRequest(String url, String method,
	            List<NameValuePair> params) {
	 
	        // Making HTTP request
	        try {
	        	Log.d(TAG, "tryingthis");
	            // check for request method
	            if(method == "POST"){
	                // request method is POST
	                // defaultHttpClient
	                DefaultHttpClient httpClient = new DefaultHttpClient();
	                HttpPost httpPost = new HttpPost(url);
	                httpPost.setEntity(new UrlEncodedFormEntity(params));
	                //httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
	                HttpResponse httpResponse = httpClient.execute(httpPost);
	                HttpEntity httpEntity = httpResponse.getEntity();
	                is = httpEntity.getContent();
	                Log.d(TAG, "tryingthis3");
	            }else if(method == "GET"){
	                // request method is GET
	                DefaultHttpClient httpClient = new DefaultHttpClient();
	                String paramString = URLEncodedUtils.format(params, "utf-8");
	                url += "?" + paramString;
	                HttpGet httpGet = new HttpGet(url);
	                Log.d(TAG, "tryingthis4");
	                HttpResponse httpResponse = httpClient.execute(httpGet);
	                HttpEntity httpEntity = httpResponse.getEntity();
	                is = httpEntity.getContent();
	            }          
	 
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 
	        try {
	        	
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    is, "utf-8"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            is.close();
	            Log.d(TAG, "turning3");
	            json = sb.toString();
	            Log.d(TAG, "turning 4");
	        } catch (Exception e) {
	            Log.e("Buffer Error", "Error converting result " + e.toString());
	        }
	 
	        // try parse the string to a JSON object
	        try {
	            Log.d(TAG, "turning " + json);
	            jObj = new JSONObject(json);
	            Log.d(TAG, "turning win");
	        } catch (JSONException e) {
	        	Log.d(TAG, "turning5");
	            Log.e("JSON Parser", "Error parsing data " + e.toString());
	        }
	 
	        // return JSON String
	        Log.d(TAG, "turning6");
	        return jObj;
	 
	    }

}
