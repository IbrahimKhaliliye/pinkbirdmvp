package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class AboutUsActivity extends AppCompatActivity {
    ImageButton backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(this::onClick);

    }



    public void onClick(View view) {

        if (view == backbutton) {
            Intent intent = new Intent(this, ScanningActivity.class);
            startActivity(intent);
        }
    }
}