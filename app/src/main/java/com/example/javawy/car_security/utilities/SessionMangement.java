package com.example.javawy.car_security.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.javawy.car_security.Login;

/**
 * Created by human on 6/24/17.
 */

public class SessionMangement {
    private final SharedPreferences mPref;
    private final SharedPreferences.Editor mEditor;
    Context context;
    public SessionMangement(Context context){
        this.context=context;
         mPref = context.getSharedPreferences("status", Context.MODE_PRIVATE);
        mEditor= mPref.edit();


    }
    //create login session with login activity
    public void create_session(boolean IS_LOGIN){
        mEditor.putBoolean("login",IS_LOGIN);
        mEditor.commit();
    }

    public boolean checkLogin(){

        return mPref.getBoolean("login",false);
    }
    public void logout(){
        mEditor.clear();
        mEditor.commit();
        Intent intent = new Intent(context, Login.class);
        //Closing all the Activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //add new task
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        //redirect
        context.startActivity(intent);
    }
}
