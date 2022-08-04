package com.example.scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Scanner;

public class ScanningActivity extends AppCompatActivity implements View.OnClickListener  {
    ImageButton scanButton, signoutButton, AboutUsButton, AbtPinkTaxButton;
    ImageButton btnInsertData;
    FirebaseAuth mAuth;
    DatabaseReference DBR;
    FirebaseDatabase DB;
    String Productname;
    String idnumber;
    TextView productname;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    /*---------------------Hooks------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        scanButton = findViewById(R.id.scanButton);

        btnInsertData = findViewById(R.id.rectangle_5);
        mAuth = FirebaseAuth.getInstance();
        DB = FirebaseDatabase.getInstance("https://pinkbird-a0d69-default-rtdb.europe-west1.firebasedatabase.app");
        DBR = DB.getReference("products");
        scanButton.setOnClickListener(this);


        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




       btnInsertData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(ScanningActivity.this,InsertingData.class));
           }
       });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void GoToAboutUS(MenuItem item) {
        Intent intent = new Intent(ScanningActivity.this,AboutUsActivity.class);

        startActivity(intent);

    }
    public void gotoaboutpinktax(MenuItem item){
        Intent intent = new Intent(ScanningActivity.this, AbtPinkTaxActivity.class);

        startActivity(intent);

    }
    public void signoutFunction(MenuItem item){
        mAuth.signOut();
        Intent intent = new Intent(ScanningActivity.this,MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);


    }

    private void scancode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScanningActivity.this, CaptureAct.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents()!=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage((result.getContents()));
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scancode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      idnumber = result.getContents();
                      Intent intent =new Intent(ScanningActivity.this,ProductContentActivity.class);
                      intent.putExtra("idnumber",idnumber);
                      startActivity(intent);
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
            else{
                Toast.makeText(this,"No Results", Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
//    @Override
//    public


//    public void onClick(MenuItem item) {
//
//        if (item == scanButton) {
//            scancode();
//        } else if (item == signoutButton) {
//            signoutFunction(item);
//        } else if (item == AboutUsButton) {
//            GoToAboutUS(item);
//        } else if (item == AbtPinkTaxButton) {
//            gotoaboutpinktax(item);
//        }
//
//
//    }


    @Override
    public void onClick(View view) {
        if (view == scanButton){
            scancode();
        }
    }
}