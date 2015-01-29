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
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class RemoveUser extends Activity {
	String url;
	Button remove;
	EditText lock;
	EditText uname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.removeuser);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		lock = (EditText) findViewById(R.id.lock);
        uname = (EditText) findViewById(R.id.uname);
        remove = (Button) findViewById(R.id.remove);
        
        remove.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
        	   String lock1 = lock.getText().toString();
               String uname1 = uname.getText().toString();
                 
               String command="remove";
               url="http://mobilelock.freeiz.com/test/retrieve.php?uname="+uname1+"&lock="+lock1+"&command="+command;
               HttpClient httpclient = new DefaultHttpClient();
       		HttpGet httpget = new HttpGet(url);
       		try {
       		    HttpResponse response = httpclient.execute(httpget);
       		    if(response != null) {
       		        String line = "";
       		        InputStream inputstream = response.getEntity().getContent();
       		        
       		        line = convertStreamToString(inputstream);
       		        
       		        //if(line.equals)
       		        Toast.makeText(RemoveUser.this, line, Toast.LENGTH_SHORT).show();
       		    } else {
       		        Toast.makeText(RemoveUser.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
       		    }
       		} catch (ClientProtocolException e) {
       		    Toast.makeText(RemoveUser.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
       		} catch (IOException e) {
       		    Toast.makeText(RemoveUser.this, "Caught IOException", Toast.LENGTH_SHORT).show();
       		} catch (Exception e) {
       		    Toast.makeText(RemoveUser.this, "Caught Exception", Toast.LENGTH_SHORT).show();
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
       	            Intent goToNextActivity = new Intent(getApplicationContext(), user1.class);
       	    		startActivity(goToNextActivity);
       	        }
       	    } catch (Exception e) {
       	        Toast.makeText(RemoveUser.this, "Stream Exception", Toast.LENGTH_SHORT).show();
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
