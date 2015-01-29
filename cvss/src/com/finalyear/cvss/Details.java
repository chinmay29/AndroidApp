package com.finalyear.cvss;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

public class Details extends Activity {
	Button cemail;
	Button cpwd;
	Button cmob;
	Button cdevice;
	Button vemail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		cemail = (Button)findViewById(R.id.email);
		 cpwd = (Button)findViewById(R.id.pwd);
		 cmob = (Button)findViewById(R.id.mob);
		 cdevice = (Button)findViewById(R.id.device);
		 vemail = (Button)findViewById(R.id.vemail);
			
		 final String uname = getIntent().getStringExtra("username");
			
		 cemail.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			Intent goToNextActivity = new Intent(getApplicationContext(), CEmail.class);
    		goToNextActivity.putExtra("username", uname);
			startActivity(goToNextActivity);
		}
			// TODO Auto-generated method stub
			
		});
		 vemail.setOnClickListener(new View.OnClickListener() {
				
				
			 @Override
				public void onClick(View view) {
					Intent goToNextActivity = new Intent(getApplicationContext(), Validate.class);
		    		goToNextActivity.putExtra("username", uname);
					startActivity(goToNextActivity);
				}
					// TODO Auto-generated method stub
					
				});
     cpwd.setOnClickListener(new View.OnClickListener() {
 		
 		@Override
 		public void onClick(View view) {
 			Intent goToNextActivity = new Intent(getApplicationContext(), CPwd.class);
 			goToNextActivity.putExtra("username", uname);
			
 			startActivity(goToNextActivity);
 		}
 			// TODO Auto-generated method stub
 			
 		});
     cmob.setOnClickListener(new View.OnClickListener() {
  		
  		@Override
  		public void onClick(View view) {
  			Intent goToNextActivity = new Intent(getApplicationContext(), CMob.class);
  			goToNextActivity.putExtra("username", uname);
			
  			startActivity(goToNextActivity);
  		}
  			// TODO Auto-generated method stub
  			
  		});
     cdevice.setOnClickListener(new View.OnClickListener() {
  		
  		@Override
  		public void onClick(View view) {
  			Intent goToNextActivity = new Intent(getApplicationContext(), CDevice.class);
  			goToNextActivity.putExtra("username", uname);
			
  			startActivity(goToNextActivity);
  		}
  			// TODO Auto-generated method stub
  			
  		});
}}
