package com.example.hostaparty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.hostaparty.HostEvent.CreateNewEvent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class JoinEvent extends Activity {

	String serverIP = "73.48.172.36";
	TextView outputTv;
	EditText nameEt, eventNameEt, passwordEt;
	Button signInBt;
	
	String name, eventName, password;
	
	
	String TAG = HostEvent.class.getSimpleName();
	 JSONObject json;
	 
	 ProgressDialog pDialog;
	    JSONParser jsonParser = new JSONParser();
	 
	    String url_create_product = "http://" + serverIP + "/hostaparty/join_event.php";
	    
	    // JSON Node names
	   String TAG_SUCCESS = "success";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_event);
		
		outputTv = (TextView)findViewById(R.id.headingTv);
		
		nameEt = (EditText)findViewById(R.id.jEgNameEt);
		eventNameEt = (EditText)findViewById(R.id.jEventNameEt);
		passwordEt = (EditText)findViewById(R.id.jEventPasswordEt);
		
		signInBt = (Button)findViewById(R.id.signInBtn);
		
		signInBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				eventName = eventNameEt.getText().toString();
				password = passwordEt.getText().toString();
				name = nameEt.getText().toString();
				
				signInBt.setText("your name is " + name);
				
				new JoinNewEvent().execute();
			}
			
		});
	}
	
	
	
	 class JoinNewEvent extends AsyncTask<String, String, String> {
		 
	        /**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(JoinEvent.this);
	            pDialog.setMessage("Joining Event..");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	            Log.d(TAG, "login pre execute");
	            

				
				
	        }
	 
	        /**
	         * Creating product
	         * */
	        protected String doInBackground(String... args) {
	        	
	        	
	        	         
	        	            // Building Parameters
	        	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	        	            params.add(new BasicNameValuePair("name", name));
	        	            params.add(new BasicNameValuePair("pass", password));
	        	            params.add(new BasicNameValuePair("event_name", eventName));

	        	            Log.d(TAG, "grabbed values from text views, about to send to server, " + name + " " + password + " " + eventName);
	        	            // getting JSON Object
	        	            // Note that create product url accepts POST method

	        	            json = jsonParser.makeHttpRequest(url_create_product, "POST", params);
	 
	        	            Log.d(TAG, "sent values to server, ");
	        	            try{     
	        	            	Log.d(TAG, "testing");
	        	            	Log.d(TAG, "testing2");
	        	               //int success = json.getInt("success");
	        	            Log.d(TAG, "testing3");
	        	            
	        					/*if(json.getInt("success") == 1) {
	        						 Log.d(TAG, "successful"+ json.getString("message") );
	        		      			message = json.getString("message");
	        		      			return json.getString("message");
	        		                    // closing this screen
	        						//return json.getString("message");
	        					}
	        					else{
	        						 Log.d(TAG, "testing4");
	        						 message = json.getString("message");*/
	        						 return json.getString("message");
	        					//}
	        	        	}catch(JSONException e){
	        	        		
	        	        		 Log.d(TAG, "Exception  getting results from server");
	        	        		e.printStackTrace();

	        	        	}
	        	            Log.d(TAG, "wheres the error at");
							return null;	        }
	 
	        /**
	         * After completing background task Dismiss the progress dialog
	         * **/
	        protected void onPostExecute(String message) {
	            // dismiss the dialog once done
	        	outputTv.setText(message);
	            pDialog.dismiss();
	            
	            Intent i = new Intent(JoinEvent.this, ChooseGame.class);
	            startActivity(i);
	            
	            Log.d(TAG, "onPost");
	         /*   try{
	            	Log.d(TAG, "try");
	 	            Bundle b = new Bundle();
	 				b.putString("user_id", user_id);
	 				i.putExtras(b);
	 				 startActivity(i);
	 	            finish();
	     		} catch (Exception e ){
	     	            Bundle b = new Bundle();
	     				b.putString("user_id", user_id);
	     				i.putExtras(b);
	     				 startActivity(i);
	     	            finish();	
	    			}finally{
	    				
	    	            Log.d(TAG, "finally");
	    			}
	        */
	            }
	 
	    }
	
}
