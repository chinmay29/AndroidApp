
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
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TimeConstraint extends Activity {

	Button set;
	EditText fromdate;
    EditText fromtime;
    EditText todate;
    EditText totime;
    EditText uname;
    String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello);
		 fromdate = (EditText) findViewById(R.id.fromdate);
	        fromtime = (EditText) findViewById(R.id.fromtime);
	         todate = (EditText) findViewById(R.id.todate);
	       totime = (EditText) findViewById(R.id.totime);
	      uname= (EditText) findViewById(R.id.uname);
	      set = (Button) findViewById(R.id.set);
	        set.setOnClickListener(new View.OnClickListener() {
	        	 
	            public void onClick(View view) {
	                String fdate = fromdate.getText().toString();
	                String ftime = fromtime.getText().toString();
	                String tdate = todate.getText().toString();
	                String ttime = totime.getText().toString();
	                String user = uname.getText().toString();
	                
	                url="http://mobilelock.freeiz.com/test/time.php?fdate="+fdate+"&ftime="+ftime+"&tdate="+tdate+"&ttime="+ttime+"&uname="+user;
	                HttpClient httpclient = new DefaultHttpClient();
	        		HttpGet httpget = new HttpGet(url);
	        		try {
	        		    HttpResponse response = httpclient.execute(httpget);
	        		    
	        		    if(response != null) {
	        		        String line = "";
	        		        InputStream inputstream = response.getEntity().getContent();
	        		        line = convertStreamToString(inputstream);
	        		        
	        		        //if(line.equals)
	        		        Toast.makeText(TimeConstraint.this, line, Toast.LENGTH_SHORT).show();
	        		    } else {
	        		        Toast.makeText(TimeConstraint.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
	        		    }
	        		} catch (ClientProtocolException e) {
	        		    Toast.makeText(TimeConstraint.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
	        		} catch (IOException e) {
	        		    Toast.makeText(TimeConstraint.this, "Caught IOException", Toast.LENGTH_SHORT).show();
	        		} catch (Exception e) {
	        		    Toast.makeText(TimeConstraint.this, "Caught Exception", Toast.LENGTH_SHORT).show();
	        		}
	                    }   });
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
	        Toast.makeText(TimeConstraint.this, "Stream Exception", Toast.LENGTH_SHORT).show();
	    }
	    return total.toString();
	}    
	
	            }
