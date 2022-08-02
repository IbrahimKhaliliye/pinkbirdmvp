package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AbtPinkTaxActivity extends AppCompatActivity {
    ImageButton backbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abt_pink_tax);
        backbutton = findViewById(R.id.backbutton);
    }

    private void GoBack(){
        Intent intent = new Intent(AbtPinkTaxActivity.this, ScanningActivity.class);
    }

    public void onClick(View view) {

        if (view == backbutton) {
            GoBack();
        }
}}

