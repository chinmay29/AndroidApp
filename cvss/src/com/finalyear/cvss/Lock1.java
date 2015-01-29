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
public class Lock1 extends Activity {

	Button users;
	Button time;
	Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lock1);
		users = (Button)findViewById(R.id.users);
		 time = (Button)findViewById(R.id.time);
		 back = (Button)findViewById(R.id.back);
		    
		 users.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View view) {
			Intent goToNextActivity = new Intent(getApplicationContext(), user1.class);
    		startActivity(goToNextActivity);
		}
			// TODO Auto-generated method stub
			
		});
		 
     time.setOnClickListener(new View.OnClickListener() {
 		
 		@Override
 		public void onClick(View view) {
 			Intent goToNextActivity = new Intent(getApplicationContext(), TimeConstraint.class);
    		startActivity(goToNextActivity);
 		}
 			// TODO Auto-generated method stub
 			
 		});
     back.setOnClickListener(new View.OnClickListener() {
  		
  		@Override
  		public void onClick(View view) {
  			Intent goToNextActivity = new Intent(getApplicationContext(), Settings.class);
     		startActivity(goToNextActivity);
  		}
  			// TODO Auto-generated method stub
  			
  		});

}
}