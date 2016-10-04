package com.msd.smartstreet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import admin.msd.com.smartstreetadmin.R;

public class ActivitySplashScreen extends AppCompatActivity {

    /**
     * Splash screen for 2 minutes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_splash_screen);

        Thread startTimer = new Thread() {
            public void run(){
                try {
                    sleep(2000);
                    Intent intent = new Intent(ActivitySplashScreen.this,AdminLogin.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        startTimer.start();
    }
}
