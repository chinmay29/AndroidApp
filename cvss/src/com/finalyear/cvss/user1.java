package com.finalyear.cvss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

public class user1 extends Activity {
	Button view;
	Button add;
	Button remove;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user1);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		  view = (Button) findViewById(R.id.view);
		  add = (Button)findViewById(R.id.approve);
	      remove = (Button) findViewById(R.id.remove);  
     view.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			Intent goToNextActivity = new Intent(getApplicationContext(), ViewUser.class);
    		startActivity(goToNextActivity);
		}
			// TODO Auto-generated method stub
			
		});
     add.setOnClickListener(new View.OnClickListener() {
  		
  		@Override
  		public void onClick(View view) {
  			Intent goToNextActivity = new Intent(getApplicationContext(), ApproveUser.class);
     		startActivity(goToNextActivity);
  		}
  			// TODO Auto-generated method stub
  			
  		});
    remove.setOnClickListener(new View.OnClickListener() {
 		
 		@Override
 		public void onClick(View view) {
 			Intent goToNextActivity = new Intent(getApplicationContext(), RemoveUser.class);
    		startActivity(goToNextActivity);
 		}
 			// TODO Auto-generated method stub
 			
 		});
}
	}
