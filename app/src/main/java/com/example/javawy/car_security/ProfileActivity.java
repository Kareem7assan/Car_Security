package com.example.javawy.car_security;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private CollapsingToolbarLayout mCollapse;
    private FloatingActionButton mFab;
    private boolean mChecked;
    private EditText mName;
    private EditText mPass;
    private EditText mEmail;
    private EditText mPhone;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.proftoolbar);
         mFab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mCollapse = (CollapsingToolbarLayout) findViewById(R.id.collapse);
         mChecked=false;

        AppBarLayout barLayout = (AppBarLayout) findViewById(R.id.MyAppbar);
        setupViews();
        barLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset==1){
                    //collapse
                 //   mCollapse.setEnabled(false);
                    mCollapse.setExpandedTitleGravity(View.TEXT_ALIGNMENT_CENTER);
                    mCollapse.setExpandedTitleTextAppearance(R.style.collapseAdapter);

                }
                else {
                    //expanded
                    mCollapse.setEnabled(true);

                    mCollapse.setExpandedTitleGravity(View.TEXT_ALIGNMENT_CENTER);
                    mCollapse.setExpandedTitleTextAppearance(R.style.collapseAdapter);


                }
            }
        });
        if (mChecked==false){
            mName.setEnabled(mChecked);
            mPass.setEnabled(mChecked);
            mPhone.setEnabled(mChecked);
            mEmail.setEnabled(mChecked);
            mBtn.setEnabled(mChecked);


        }

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChecked=true;
                mName.setEnabled(mChecked);
                mPass.setEnabled(mChecked);
                mPhone.setEnabled(mChecked);
                mEmail.setEnabled(mChecked);
                mBtn.setEnabled(mChecked);

            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "update successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setupViews() {
         mName = (EditText) findViewById(R.id.profName);
        mPass = (EditText) findViewById(R.id.profPass);
        mEmail = (EditText) findViewById(R.id.profMail);
        mPhone = (EditText) findViewById(R.id.profPhone);
        mBtn=(Button)findViewById(R.id.button4);

    }
}
