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
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Door extends Activity {
	String url;
	Button open;
	Button close;
	public TextView timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.door);
		
		open = (Button) findViewById(R.id.unlock);
		close = (Button) findViewById(R.id.lock);
		final String lock = getIntent().getStringExtra("lock");
		final String uname = getIntent().getStringExtra("username");
		 timer=(TextView) findViewById(R.id.timer);

	        MyCount counter = new MyCount(30000,1000);
	        counter.start();
		open.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
        	  String special;
               String command="open";
               if(lock.length()==2)
    		   {
    	        	 special="multi";
    		   }
    		   else
    		   {
    	                 special="";
    		   }
               
               url="http://mobilelock.freeiz.com/test/loginuser.php?command="+command+"&special="+special+"&lock="+lock+"&uname="+uname;
               HttpClient httpclient = new DefaultHttpClient();
       		HttpGet httpget = new HttpGet(url);
       		try {
       		    HttpResponse response = httpclient.execute(httpget);
       		    if(response != null) {
       		        String line = "";
       		        InputStream inputstream = response.getEntity().getContent();
       		        
       		        line = convertStreamToString(inputstream);
       		        
       		        //if(line.equals)
       		        Toast.makeText(Door.this, line, Toast.LENGTH_SHORT).show();
       		    } else {
       		        Toast.makeText(Door.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
       		    }
       		} catch (ClientProtocolException e) {
       		    Toast.makeText(Door.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
       		} catch (IOException e) {
       		    Toast.makeText(Door.this, "Caught IOException", Toast.LENGTH_SHORT).show();
       		} catch (Exception e) {
       		    Toast.makeText(Door.this, "Caught Exception", Toast.LENGTH_SHORT).show();
       		}
                   }
               });
		close.setOnClickListener(new View.OnClickListener() {
	           public void onClick(View view) {
	        	  String special="multi";
	               String command="close";
	               url="http://mobilelock.freeiz.com/test/loginuser.php?command="+command+"&special="+special+"&lock="+lock;
	               HttpClient httpclient = new DefaultHttpClient();
	       		HttpGet httpget = new HttpGet(url);
	       		try {
	       		    HttpResponse response = httpclient.execute(httpget);
	       		    if(response != null) {
	       		        String line = "";
	       		        InputStream inputstream = response.getEntity().getContent();
	       		        
	       		        line = convertStreamToString(inputstream);
	       		        
	       		        //if(line.equals)
	       		        Toast.makeText(Door.this, line, Toast.LENGTH_SHORT).show();
	       		    } else {
	       		        Toast.makeText(Door.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
	       		    }
	       		} catch (ClientProtocolException e) {
	       		    Toast.makeText(Door.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
	       		} catch (IOException e) {
	       		    Toast.makeText(Door.this, "Caught IOException", Toast.LENGTH_SHORT).show();
	       		} catch (Exception e) {
	       		    Toast.makeText(Door.this, "Caught Exception", Toast.LENGTH_SHORT).show();
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
    	    }

       	    catch (Exception e) {
       	        Toast.makeText(Door.this, "Stream Exception", Toast.LENGTH_SHORT).show();
       	    }
       	    return total.toString();
       	}
       	public class MyCount extends CountDownTimer{
            public MyCount(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);
                }

            
            @Override
            public void onFinish() {
                // TODO Auto-generated method stub

            	 Intent goToNextActivity = new Intent(getApplicationContext(), Login.class);
  	          		           
  	             
  	           startActivity(goToNextActivity);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            	timer.setText(Long.toString(millisUntilFinished/1000));

            }
        }
       	@Override
       	public boolean onCreateOptionsMenu(Menu menu) {
       		// Inflate the menu; this adds items to the action bar if it is present.
       		getMenuInflater().inflate(R.menu.login, menu);
       		return true;
       	}

           }
