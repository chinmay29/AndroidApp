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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Forget extends Activity {
	Button submit;
	EditText inputans;
    EditText inputuname;
    String url;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget);
		 inputuname = (EditText) findViewById(R.id.username);
	        inputans = (EditText) findViewById(R.id.ans);
	         submit = (Button) findViewById(R.id.submit);
		       
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
	        
	        submit.setOnClickListener(new View.OnClickListener() {
	        	 
	            public void onClick(View view) {
	            	String uname = inputuname.getText().toString();
	                String ans = inputans.getText().toString();
	                String command="forget";
	            	  
	                url="http://mobilelock.freeiz.com/test/forget.php?uname="+uname+"&ans="+ans+"&command="+command;
	                HttpClient httpclient = new DefaultHttpClient();
	        		HttpGet httpget = new HttpGet(url);
	        		try {
	        		    HttpResponse response = httpclient.execute(httpget);
	        		    
	        		    if(response != null) {
	        		        String line = "";
	        		        InputStream inputstream = response.getEntity().getContent();
	        		        line = convertStreamToString(inputstream, uname);
	        		        //if(line.equals)
	        		        Toast.makeText(Forget.this, line, Toast.LENGTH_SHORT).show();
	        		    } else {
	        		        Toast.makeText(Forget.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
	        		    }
	        		} catch (ClientProtocolException e) {
	        		    Toast.makeText(Forget.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
	        		} catch (IOException e) {
	        		    Toast.makeText(Forget.this, "Caught IOException", Toast.LENGTH_SHORT).show();
	        		} catch (Exception e) {
	        		    Toast.makeText(Forget.this, "Caught Exception", Toast.LENGTH_SHORT).show();
	        		}
	                    }   });
}
    private String convertStreamToString(InputStream is, String u) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    try {
	        line=rd.readLine();
	        boolean  b = line.startsWith("verification");
	       
	    	if(b==true) {
	            total.append(line);
	            Intent goToNextActivity = new Intent(getApplicationContext(), verifycode.class);
	           goToNextActivity.putExtra("username", u);
	            startActivity(goToNextActivity);
	            
	    	}
	    	else
	    	{
	    		total.append(line);
	            
	            
	    	}
	    	    } catch (Exception e) {
	        Toast.makeText(Forget.this, "Stream Exception", Toast.LENGTH_SHORT).show();
	    }
	    return total.toString();
	}    }
	