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
        backbutton.setOnClickListener(this::onClick);

    }


    public void onClick(View view) {

        if (view == backbutton) {
            Intent intent = new Intent(this, ScanningActivity.class);
            startActivity(intent);
}}}
