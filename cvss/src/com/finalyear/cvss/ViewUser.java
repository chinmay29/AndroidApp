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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class ViewUser extends Activity {
	String url;
	Button view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);
		view = (Button) findViewById(R.id.viewuser);
        
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		 view.setOnClickListener(new View.OnClickListener() {
	           public void onClick(View view) {
	        	   String command="view";
	        	   TextView t = (TextView) findViewById(R.id.users); 
	       		
	       		
	        	   url="http://mobilelock.freeiz.com/test/loginuser.php?command="+command;
	        	   HttpClient httpclient = new DefaultHttpClient();
	          		HttpGet httpget = new HttpGet(url);
	          		try {
	          		    HttpResponse response = httpclient.execute(httpget);
	          		    if(response != null) {
	          		        String line = "";
	          		        InputStream inputstream = response.getEntity().getContent();
	          		        
	          		        line = convertStreamToString(inputstream);
	          		      t.setText(line);
	          		      //if(line.equals)
	          		       
	          		    } else {
	          		        Toast.makeText(ViewUser.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
	          		    }
	          		} catch (ClientProtocolException e) {
	          		    Toast.makeText(ViewUser.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
	          		} catch (IOException e) {
	          		    Toast.makeText(ViewUser.this, "Caught IOException", Toast.LENGTH_SHORT).show();
	          		} catch (Exception e) {
	          		    Toast.makeText(ViewUser.this, "Caught Exception", Toast.LENGTH_SHORT).show();
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
	          	            
	          	        }
	          	    } catch (Exception e) {
	          	        Toast.makeText(ViewUser.this, "Stream Exception", Toast.LENGTH_SHORT).show();
	          	    }
	          	    return total.toString();
	          	}
	          	
	           }

    