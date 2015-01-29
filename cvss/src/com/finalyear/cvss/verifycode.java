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
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class verifycode extends Activity {
	String url;
	Button done;
	
	EditText code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verify);
		  final String uname = getIntent().getStringExtra("username");
    		 
		
		code = (EditText) findViewById(R.id.code);
        done = (Button) findViewById(R.id.submit2);
        done.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
        	   String verifycode = code.getText().toString();
                         
               String command="code";
               url="http://mobilelock.freeiz.com/test/change.php?&code="+verifycode+"&command="+command+"&uname="+uname;
               HttpClient httpclient = new DefaultHttpClient();
       		HttpGet httpget = new HttpGet(url);
       		try {
       		    HttpResponse response = httpclient.execute(httpget);
       		    if(response != null) {
       		        String line = "";
       		        InputStream inputstream = response.getEntity().getContent();
       		        
       		        line = convertStreamToString(inputstream, uname);
       		        
       		        //if(line.equals)
       		        Toast.makeText(verifycode.this, line, Toast.LENGTH_SHORT).show();
       		    } else {
       		        Toast.makeText(verifycode.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
       		    }
       		} catch (ClientProtocolException e) {
       		    Toast.makeText(verifycode.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
       		} catch (IOException e) {
       		    Toast.makeText(verifycode.this, "Caught IOException", Toast.LENGTH_SHORT).show();
       		} catch (Exception e) {
       		    Toast.makeText(verifycode.this, "Caught Exception", Toast.LENGTH_SHORT).show();
       		}
                   }
               });
                
       	}
       	
       	private String convertStreamToString(InputStream is, String u) {
       	    String line = "";
       	    StringBuilder total = new StringBuilder();
       	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
       	 try {
 	        line=rd.readLine();
 	        boolean  b = line.startsWith("correct");
 	        if(b==true) {
 	            total.append(line);
 	            Intent goToNextActivity = new Intent(getApplicationContext(), forgetpwd.class);
 	           goToNextActivity.putExtra("username", u);
 	            startActivity(goToNextActivity);
 	            
 	    	}
 	        else
 	        {
 	        	total.append(line);
 	            Intent goToNextActivity = new Intent(getApplicationContext(), Login.class);
 	           goToNextActivity.putExtra("username", u);
 	            startActivity(goToNextActivity);
 	            
 	        }
 	    } catch (Exception e) {
 	        Toast.makeText(verifycode.this, "Stream Exception", Toast.LENGTH_SHORT).show();
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

