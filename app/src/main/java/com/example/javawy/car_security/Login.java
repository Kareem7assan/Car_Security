package com.example.javawy.car_security;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javawy.car_security.utilities.SessionMangement;
import com.example.javawy.car_security.utilities.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    private CheckBox mCheckBox;
    private boolean ISLOGIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       //
        setupWidgets();
        ISLOGIN=false;
        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("login");
        mProgress.setMessage("waiting for authentication ...");
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ISLOGIN=mCheckBox.isChecked();
            }
        });



    }

    private void setupWidgets() {
       mEmail = (EditText) findViewById(R.id.editText21);
        mPassword = (EditText) findViewById(R.id.editText22);
       mCheckBox= (CheckBox)findViewById(R.id.checkBox);

    }

    public void onForget(View view) {
    }

    public void onRegist(View view) {
        startActivity(new Intent(this,SignUp.class));
    }

    public void onLogin(View view) {
        //dialog start
        mProgress.show();
        // check
        final Validation val = new Validation();
        if (val.isEmpty(mEmail.getText().toString() ) || val.isEmpty(mPassword.getText().toString()) ){
            mProgress.hide();
            Toast.makeText(this, "fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {

            mAuth.signInWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        mProgress.dismiss();
                        //check stay login by shared pref
                        Log.e("login ",mCheckBox.isChecked()+"");
                        new SessionMangement(Login.this).create_session(ISLOGIN);
                        startActivity(new Intent(Login.this,MainActivity.class));
                        finish();
                    }
                    else {
                        mProgress.hide();
                        val.dialog("Error",task.getException().getMessage(),Login.this);
                    }
                }
            });
        }

    }
}
