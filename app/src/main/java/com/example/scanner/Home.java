package com.example.scanner;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* The use of this class is to keep the user logged in after they leave the app */

public class Home extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser FireUser = mAuth.getCurrentUser();

        if(FireUser != null) {
            Intent intent = new Intent(Home.this, ScanningActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
