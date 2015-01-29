package com.finalyear.cvss;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Settings extends Activity {
	Button lock;
	Button addlock;
	Button add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		lock = (Button)findViewById(R.id.lock);
		 addlock = (Button)findViewById(R.id.addlock);
		 add = (Button)findViewById(R.id.add);
		 String uname = getIntent().getStringExtra("username");
			
		 lock.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			Intent goToNextActivity = new Intent(getApplicationContext(), Lock1.class);
    		startActivity(goToNextActivity);
		}
			// TODO Auto-generated method stub
			
		});
     addlock.setOnClickListener(new View.OnClickListener() {
 		
 		@Override
 		public void onClick(View view) {
 			Intent goToNextActivity = new Intent(getApplicationContext(), Add.class);
 			
 			startActivity(goToNextActivity);
 		}
 			// TODO Auto-generated method stub
 			
 		});
     add.setOnClickListener(new View.OnClickListener() {
 		
 		@Override
 		public void onClick(View view) {
 			Intent goToNextActivity = new Intent(getApplicationContext(), AddLock.class);
 			
 			startActivity(goToNextActivity);
 		}
 			// TODO Auto-generated method stub
 			
 		});
     
}}
