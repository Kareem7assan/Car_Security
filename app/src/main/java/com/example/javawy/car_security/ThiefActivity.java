package com.example.javawy.car_security;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThiefActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thief);
        //get the image from server
        getImage();

    }

    private void getImage() {

    }

    public void onBack(View view) {
        //back to main activity
    }

    public void onDownload(View view) {
        //download the image thief
    }
}
