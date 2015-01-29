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
import android.widget.TextView;
import android.widget.Toast;

public class UserAcc extends Activity {

	Button setting;
    Button logout;
	Button open;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.useracc);
		setting = (Button)findViewById(R.id.setting);
		logout = (Button)findViewById(R.id.logout);
		open = (Button)findViewById(R.id.open1);
		
		final String uname;
			uname = getIntent().getStringExtra("username");
		TextView t = (TextView) findViewById(R.id.hello); 
		t.setText("Hello "+uname);
		setting.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			Intent goToNextActivity = new Intent(getApplicationContext(), Details.class);
			goToNextActivity.putExtra("username", uname);
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
		logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				String uname,url;
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
	    		        
	    		        line = convertStreamToString(inputstream, uname);
	    		        
	    		        //if(line.equals)
	    		        Toast.makeText(UserAcc.this, line, Toast.LENGTH_SHORT).show();
	    		    } else {
	    		        Toast.makeText(UserAcc.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
	    		    }
	    		} catch (ClientProtocolException e) {
	    		    Toast.makeText(UserAcc.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
	    		} catch (IOException e) {
	    		    Toast.makeText(UserAcc.this, "Caught IOException", Toast.LENGTH_SHORT).show();
	    		} catch (Exception e) {
	    		    Toast.makeText(UserAcc.this, "Caught Exception", Toast.LENGTH_SHORT).show();
	    		}
	                }
	            });
	             
	    	}
	    	
	    	private String convertStreamToString(InputStream is, String u) {
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
	    	        Toast.makeText(UserAcc.this, "Stream Exception", Toast.LENGTH_SHORT).show();
	    	    }
	    	    return total.toString();
	    	}

}