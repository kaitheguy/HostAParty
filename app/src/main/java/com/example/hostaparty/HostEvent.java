package com.example.hostaparty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Build;

public class HostEvent extends Activity {
	
	String serverIP = "73.48.172.36";
	String selectedName, selectedPass, selectedType;
	Button button;
	EditText eventName, eventPass;
	String[] eventTypes = {"Birthday", "Wedding"};
	TextView output;
	String name, type, pass;
	 Spinner spinner;
	 String message;

	 //Intent i;
	 
	 public static final String TAG = HostEvent.class.getSimpleName();
	 JSONObject json;
	 
	 private ProgressDialog pDialog;
	    JSONParser jsonParser = new JSONParser();
	 
	     String url_create_product = "http://" + serverIP + "/hostaparty/create_event.php";
	    //private static String url_create_product = "http://76.224.229.178/app_login.php";
	    //private static String url_create_product = "http://192.186.227.71/app_login.php";
	 
	    // JSON Node names
	    private static final String TAG_SUCCESS = "success";
	    
	    
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_host_event);

		eventName = (EditText)findViewById(R.id.eventNameEt);
		
		
		 spinner = (Spinner)findViewById(R.id.spinner1);
	    ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
	        android.R.layout.simple_spinner_dropdown_item, eventTypes);
	    spinner.setAdapter(spinnerArrayAdapter);
	    
	    eventPass = (EditText)findViewById(R.id.eventPassEt);
		
	    output = (TextView)findViewById(R.id.outputTv);
	    button = (Button) findViewById(R.id.createEventBtn);
		

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedName = eventName.getText().toString();
				selectedPass = eventPass.getText().toString();
				selectedType = spinner.getSelectedItem().toString();
				button.setText(selectedName + " " + selectedType  +" " + selectedPass);
				
				new CreateNewEvent().execute();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.host_event, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	public void changeAct(){
		
		 Intent intent = new Intent(this, AddQuiz.class);
		 startActivity(intent);


	}; */
	
	/**
	 * Here we call asynch task to communicate with server
	 */
	
	 class CreateNewEvent extends AsyncTask<String, String, String> {
		 
	        /**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(HostEvent.this);
	            pDialog.setMessage("Creating Event..");
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
	        	            params.add(new BasicNameValuePair("name", selectedName));
	        	            params.add(new BasicNameValuePair("pass", selectedPass));
	        	            params.add(new BasicNameValuePair("type", selectedType));

	        	            Log.d(TAG, "grabbed values from text views, about to send to server, " + selectedName + " " + selectedPass + " " + selectedType);
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
	        	output.setText(message);
	            pDialog.dismiss();
	            
	            //new Intent here for next activity
	            

	            Log.d(TAG, "onPost");
	            
	            
	            
	            //startActivity(i);
	            
	            try{
	            	Log.d(TAG, "try");
	 	           // Bundle b = new Bundle();
	 				//b.putString("user_id", user_id);
	 				//i.putExtras(b);
	            	Intent i = new Intent(HostEvent.this, AddQuiz.class);
	 				 startActivity(i);
	 				//changeAct();
	 	            //finish();
	     		} catch (Exception e ){
	     	          //  Bundle b = new Bundle();
	     				//b.putString("user_id", user_id);
	     				//i.putExtras(b);
	     				 //startActivity(i);
	     	            //finish();	
	    			}finally{
	    				
	    	            Log.d(TAG, "finally");
	    			}
	            
	            /*
	            try{
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
