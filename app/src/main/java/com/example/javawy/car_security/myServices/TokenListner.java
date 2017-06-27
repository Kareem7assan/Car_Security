package com.example.javawy.car_security.myServices;

import android.content.Intent;

import com.example.javawy.car_security.utilities.SessionMangement;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by human on 6/26/17.
 */

public class TokenListner extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, Registeration_Service.class);
        new SessionMangement(this).tokenUpload(false);
        startService(intent);
    }
}
