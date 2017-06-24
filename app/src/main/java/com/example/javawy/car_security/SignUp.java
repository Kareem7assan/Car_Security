package com.example.javawy.car_security;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javawy.car_security.utilities.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private EditText mPhone;
    private EditText mCode;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private FirebaseDatabase mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setup containers and widgets
        setUpWidegets();
        //progress dialog
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Registering");
        mProgressDialog.setMessage("please wait ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mAuth=FirebaseAuth.getInstance();
        mDataBase=FirebaseDatabase.getInstance();


    }

    private void setUpWidegets() {
        mEmail = (EditText) findViewById(R.id.editText);
        mPassword = (EditText) findViewById(R.id.password);
        mPhone = (EditText) findViewById(R.id.phone);
        mCode = (EditText) findViewById(R.id.code);

    }

    public void onSignUp(View view) {


        //validate fields
        final Validation val = new Validation();
        if (val.isEmpty(mEmail.getText().toString())||val.isEmpty(mPassword.getText().toString())||val.isEmpty(mCode.getText().toString())||val.isEmpty(mPhone.getText().toString())){

            Toast.makeText(this, "please fill all fields", Toast.LENGTH_SHORT).show();

        }

        else {
                mProgressDialog.show();
                mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString()).
                        addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //progress dialog hide
                        mProgressDialog.hide();

                        //dialog alert
                        val.dialog("Error",e.getMessage(),SignUp.this);

                    }
                })
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    //progress dialog exit
                                    mProgressDialog.dismiss();
                                    //location
                                    DatabaseReference reference = mDataBase.getReference();
                                    //root
                                    reference.child("users");
                                    //set other child id
                                    DatabaseReference child = reference.child(mAuth.getCurrentUser().getUid());
                                    HashMap<String, String> data = new HashMap<>();
                                    data.put("email",mEmail.getText().toString());
                                    data.put("password",mPassword.getText().toString());
                                    data.put("code",mCode.getText().toString());
                                    data.put("phone",mPhone.getText().toString());
                                    //set values (branches)
                                    child.setValue(data);

                                    Intent intent = new Intent(SignUp.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(SignUp.this, "task.getException().getMessage()", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

        }


    }
}
