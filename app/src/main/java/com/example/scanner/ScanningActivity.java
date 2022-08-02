package com.example.scanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

public class ScanningActivity extends AppCompatActivity  implements View.OnClickListener{
    ImageButton scanButton, signoutButton, AboutUsButton, AbtPinkTaxButton;;
    FirebaseAuth mAuth;
    DatabaseReference DBR;
    FirebaseDatabase DB;
    String Productname;
    String idnumber;
    TextView productname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        scanButton = findViewById(R.id.scanButton);
        signoutButton = findViewById(R.id.signout);
        AboutUsButton = findViewById(R.id.AboutUsButton);
        AbtPinkTaxButton = findViewById(R.id.AbtPinkTaxButton);
        mAuth = FirebaseAuth.getInstance();
        DB = FirebaseDatabase.getInstance("https://pinkbird-a0d69-default-rtdb.europe-west1.firebasedatabase.app");
        DBR = DB.getReference("products");
        scanButton.setOnClickListener(this);
        signoutButton.setOnClickListener(this);
        AboutUsButton.setOnClickListener(this);
        AbtPinkTaxButton.setOnClickListener(this);

    }
    private void signoutFunction(){
        mAuth.signOut();
        Intent intent = new Intent(ScanningActivity.this,MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);


    }

    private void GoToAboutUS(){
        Intent intent = new Intent(ScanningActivity.this,AboutUsActivity.class);

        startActivity(intent);

    }
    private void GoToAbtPinkTax(){
        Intent intent = new Intent(ScanningActivity.this,AbtPinkTaxActivity.class);

        startActivity(intent);

    }


    private void scancode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
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


    public void onClick(View view) {

        if (view == scanButton) {
            scancode();
        } else if (view == signoutButton){
            signoutFunction();
        } else if (view == AboutUsButton){
            GoToAboutUS();
        } else if (view == AbtPinkTaxButton){
            GoToAbtPinkTax();

    }
}}