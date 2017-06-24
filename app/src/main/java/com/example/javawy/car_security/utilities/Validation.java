package com.example.javawy.car_security.utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by human on 6/24/17.
 */

public class Validation {
    boolean stat=true;
    Pattern pattern;
    Matcher matcher;
    public boolean isEmpty(String res){

        if (!res.trim().isEmpty()){
            stat=false;
            return stat;
        }

        return stat;
    }

    public boolean isMail(String res){
        String EMAIL_Pattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern=Pattern.compile(EMAIL_Pattern);
        pattern.matcher(res);
        stat = matcher.matches();
        return stat;
    }
public void dialog(String title, String message, Context context){
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
        }
    });
    AlertDialog alertDialog = builder.create();
    alertDialog.show();
}
}
