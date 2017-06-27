package com.example.javawy.car_security.myServices;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.javawy.car_security.utilities.SessionMangement;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by human on 6/26/17.
 */

public class Registeration_Service extends IntentService {
    private String PROJECT_ID="343420179777";
    private String scope=FirebaseMessaging.INSTANCE_ID_SCOPE;
    private String LINK="http://javawy.pe.hu/arduino/upload_token.php";
    private SessionMangement shared;

    public Registeration_Service() {
        super("Registeration_Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        TokenListner tokenListner = new TokenListner();
        FirebaseInstanceId id = FirebaseInstanceId.getInstance();
        shared = new SessionMangement(this);
        try {
            //get token from FCM in the back ground
            String token = id.getToken(PROJECT_ID, scope);
            //check if the token already exist id DB
            if(shared.isExist()==false){
                //send token if not exist
                sendToken(token);

            }

            //sendToken(token,context);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendToken(final String token) {

        StringRequest request = new StringRequest(Request.Method.POST, LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //check
                if (response.equalsIgnoreCase("1")){
                    //put boolean true
                    shared.tokenUpload(true);
                    Log.d("token","uploading success");
                }
             //   shared.tokenUpload(true);
                else {

                    //put boolean false
                    shared.tokenUpload(false);
                    Log.d("token","false");

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //put boolean false
                shared.tokenUpload(false);
                Log.e("error",error.getMessage());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("mob_token",token);
                return map;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
