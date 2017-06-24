package com.example.javawy.car_security;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.multidex.MultiDexApplication;

import com.example.javawy.car_security.utilities.SessionMangement;

import static android.support.multidex.MultiDex.install;


public class Splash extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SessionMangement session = new SessionMangement(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session.checkLogin()==false) {
                    Log.e("splash",session.checkLogin()+"");
                    startActivity(new Intent(Splash.this, Login.class));
                    finish();
                }
                else {
                    Log.e("splash",session.checkLogin()+"");
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                }
            }
        },3000);

    }


}
