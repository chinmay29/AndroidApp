package com.finalyear.cvss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.TargetApi;
import android.app.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin extends Activity {
	Button details;
	Button settings;
	Button logout;
	Button open;
	
	String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin);
		details = (Button)findViewById(R.id.details);
		open = (Button)findViewById(R.id.open);
		final String uname;
		uname = getIntent().getStringExtra("username");
		 //Toast.makeText(Admin.this,uname, Toast.LENGTH_LONG).show();
   	  
		settings = (Button)findViewById(R.id.settings);
     logout= (Button)findViewById(R.id.logout);
		 details.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			Intent goToNextActivity = new Intent(getApplicationContext(), Details.class);
    		startActivity(goToNextActivity);
		}
			// TODO Auto-generated method stub
			
		});
		 open.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					Intent goToNextActivity = new Intent(getApplicationContext(), Open.class);
					goToNextActivity.putExtra("username", uname);
		    		
					startActivity(goToNextActivity);
				}
					// TODO Auto-generated method stub
					
				});
		    
		 settings.setOnClickListener(new View.OnClickListener() {
 		
 		@Override
 		public void onClick(View view) {
 			Intent goToNextActivity = new Intent(getApplicationContext(), Settings.class);
    		startActivity(goToNextActivity);
 		}
 			// TODO Auto-generated method stub
 			
 		});
     logout.setOnClickListener(new View.OnClickListener() {
  		
  		@Override
  		public void onClick(View view) {
  			String uname;
  			uname = getIntent().getStringExtra("username");
  			String command="logout";
            url="http://mobilelock.freeiz.com/test/loginuser.php?&command="+command+"&uname="+uname;
            HttpClient httpclient = new DefaultHttpClient();
    		HttpGet httpget = new HttpGet(url);
    		try {
    		    HttpResponse response = httpclient.execute(httpget);
    		    if(response != null) {
    		        String line = "";
    		        InputStream inputstream = response.getEntity().getContent();
    		        
    		        line = convertStreamToString(inputstream);
    		        
    		        //if(line.equals)
    		        Toast.makeText(Admin.this, line, Toast.LENGTH_SHORT).show();
    		    } else {
    		        Toast.makeText(Admin.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
    		    }
    		} catch (ClientProtocolException e) {
    		    Toast.makeText(Admin.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
    		} catch (IOException e) {
    		    Toast.makeText(Admin.this, "Caught IOException", Toast.LENGTH_SHORT).show();
    		} catch (Exception e) {
    		    Toast.makeText(Admin.this, "Caught Exception", Toast.LENGTH_SHORT).show();
    		}
                }
            });
             
    	}
    	
    	private String convertStreamToString(InputStream is) {
    	    String line = "";
    	    StringBuilder total = new StringBuilder();
    	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    	    try {
    	        while ((line = rd.readLine()) != null) {
    	            total.append(line);
    	            Intent goToNextActivity = new Intent(getApplicationContext(), First.class);
    	    		startActivity(goToNextActivity);
    	        }
    	    } catch (Exception e) {
    	        Toast.makeText(Admin.this, "Stream Exception", Toast.LENGTH_SHORT).show();
    	    }
    	    return total.toString();
    	}
    	
		}
	
