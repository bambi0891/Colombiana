package com.colombiana.ingsoftware;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends Activity {

    private static final long DEMORA= 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Se quita ActionBar para Splash Screen

        TimerTask task= new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };

        Timer timer=new Timer();
        timer.schedule(task,DEMORA);


    }


}
