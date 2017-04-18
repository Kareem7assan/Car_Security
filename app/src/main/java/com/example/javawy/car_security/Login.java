package com.example.javawy.car_security;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    public void onForget(View view) {
    }

    public void onRegist(View view) {
        startActivity(new Intent(this,SignUp.class));
    }

    public void onLogin(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
