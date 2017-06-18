package com.example.lazar.domaci;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.example.lazar.domaci.Init.Tool;

/**
 * Created by Lazar on 5/7/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 1500; // splash ce biti vidljiv minimum SPLASH_TIME_OUT milisekundi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Tool.InitBase();


        new InitTask().execute();
    }

    private class InitTask extends AsyncTask<Void, Void, Void> {
        private long startTime;

        @Override
        protected void onPreExecute() {
            startTime = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            continueLogin();
            startMainActivity();
            return null;
        }

        private void continueLogin() {
            // sacekaj tako da splash bude vidljiv minimum SPLASH_TIME_OUT milisekundi
            long timeLeft = SPLASH_TIME_OUT - (System.currentTimeMillis() - startTime);
            if (timeLeft < 0) timeLeft = 0;
            SystemClock.sleep(timeLeft);
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        finish(); // da nebi mogao da ode back na splash
    }
}
