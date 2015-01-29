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
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class AddLock extends Activity {
String url;
Button set;
EditText user1;
EditText user2;
EditText lock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addlock);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		user1 = (EditText) findViewById(R.id.user1);
        user2 = (EditText) findViewById(R.id.user2);
        lock = (EditText) findViewById(R.id.lock);
        
        set = (Button) findViewById(R.id.set);
        
         set.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
        String u1 = user1.getText().toString();
        String u2 = user2.getText().toString();
        String lock1 = lock.getText().toString();
        url="http://mobilelock.freeiz.com/test/multi.php?user1="+u1+"&user2="+u2+"&lock="+lock1;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		try {
		    HttpResponse response = httpclient.execute(httpget);
		    if(response != null) {
		        String line = "";
		        InputStream inputstream = response.getEntity().getContent();
		        
		        line = convertStreamToString(inputstream);
		        
		        //if(line.equals)
		        Toast.makeText(AddLock.this, line, Toast.LENGTH_SHORT).show();
		    } else {
		        Toast.makeText(AddLock.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
		    }
		} catch (ClientProtocolException e) {
		    Toast.makeText(AddLock.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
		    Toast.makeText(AddLock.this, "Caught IOException", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
		    Toast.makeText(AddLock.this, "Caught Exception", Toast.LENGTH_SHORT).show();
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
	            Intent goToNextActivity = new Intent(getApplicationContext(), Write.class);
	    		startActivity(goToNextActivity);
	        }
	    } catch (Exception e) {
	        Toast.makeText(AddLock.this, "Stream Exception", Toast.LENGTH_SHORT).show();
	    }
	    return total.toString();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
