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

public class Validate extends Activity {
	String url;
	Button done;
	EditText user;
	
	EditText code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.validate);
			 
		
		code = (EditText) findViewById(R.id.code1);
		user = (EditText) findViewById(R.id.user);
        
		done = (Button) findViewById(R.id.sub);
        done.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
        	   String Validate = code.getText().toString();
               String uname= user.getText().toString();
               String command="ecode";
               url="http://mobilelock.freeiz.com/test/change.php?&code="+Validate+"&command="+command+"&uname="+uname;
               HttpClient httpclient = new DefaultHttpClient();
       		HttpGet httpget = new HttpGet(url);
       		try {
       		    HttpResponse response = httpclient.execute(httpget);
       		    if(response != null) {
       		        String line = "";
       		        InputStream inputstream = response.getEntity().getContent();
       		        
       		        line = convertStreamToString(inputstream, uname);
       		        
       		        //if(line.equals)
       		        Toast.makeText(Validate.this, line, Toast.LENGTH_SHORT).show();
       		    } 
       		    else {
       		        Toast.makeText(Validate.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
       		    }
       		} catch (ClientProtocolException e) {
       		    Toast.makeText(Validate.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
       		} catch (IOException e) {
       		    Toast.makeText(Validate.this, "Caught IOException", Toast.LENGTH_SHORT).show();
       		} catch (Exception e) {
       		    Toast.makeText(Validate.this, "Caught Exception", Toast.LENGTH_SHORT).show();
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
 	        boolean  b = line.startsWith("Email");
 	        if(b==true) {
 	            total.append(line);
 	            Intent goToNextActivity = new Intent(getApplicationContext(), Details.class);
 	           
 	            startActivity(goToNextActivity);
 	            
 	    	}
 	        else
 	        {
 	        	total.append(line);
 	            
 	        }
 	    } catch (Exception e) {
 	        Toast.makeText(Validate.this, "Stream Exception", Toast.LENGTH_SHORT).show();
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

