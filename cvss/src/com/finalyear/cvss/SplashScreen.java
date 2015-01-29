package com.finalyear.cvss;

import android.annotation.TargetApi;
import android.app.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class SplashScreen extends Activity {
	/**
     * The thread to process splash screen events
     */
    private Thread mSplashThread;    

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Splash screen view
        setContentView(R.layout.splash);
        
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                /* start the activity */
                startActivity(new Intent("com.finalyear.cvss.First"));
            }
        }, 5000);

    }
        
    /**
     * Processes splash screen touch events
     */
    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
    }    
} 

