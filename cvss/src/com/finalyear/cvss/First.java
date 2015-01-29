package com.finalyear.cvss;

import com.finalyear.cvss.*;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

public class First extends Activity {
	Button signup;
	Button signin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		 signup = (Button)findViewById(R.id.signup);
		 signin = (Button)findViewById(R.id.signin);
     signup.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			Intent goToNextActivity = new Intent(getApplicationContext(), Signup.class);
    		startActivity(goToNextActivity);
		}
			// TODO Auto-generated method stub
			
		});
     signin.setOnClickListener(new View.OnClickListener() {
 		
 		@Override
 		public void onClick(View view) {
 			Intent goToNextActivity = new Intent(getApplicationContext(), Login.class);
    		startActivity(goToNextActivity);
 		}
 			// TODO Auto-generated method stub
 			
 		});
}
}