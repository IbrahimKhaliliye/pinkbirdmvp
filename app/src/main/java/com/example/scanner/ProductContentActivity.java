package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductContentActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    ListView productListview;
    ArrayList<Product> ProductList;
    ArrayAdapter<Product> arrayAdapter;
    DatabaseReference DBR;
    FirebaseDatabase DB;
    String Productname;
    Long Productcode,Productprice;
    String idnumber;
    TextView productname,productcode,productprice;
    ImageButton backbutton, alternative;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_content);

        DB = FirebaseDatabase.getInstance("https://pinkbird-a0d69-default-rtdb.europe-west1.firebasedatabase.app");
        DBR = DB.getReference("products");
        productprice = this.findViewById(R.id.productprice);
        productcode= this.findViewById(R.id.productcode);
        productname = this.findViewById(R.id.productname);
        backbutton = this.findViewById(R.id.backbutton);
        alternative = this.findViewById(R.id.alternative);

        backbutton.setOnClickListener(this);
        alternative.setOnClickListener(this);

        if (this.getIntent().getStringExtra("idnumber") != null) {
            DBR.child(this.getIntent().getStringExtra("idnumber")).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    HashMap<String, String> value = (HashMap<String, String>) task.getResult().getValue();
                    HashMap<String, Long> value2 = (HashMap<String, Long>) task.getResult().getValue();

                    if (value != null ){
                        Productname = value.get("productname");
                        productname.setText(Productname);
                        Productcode = value2.get("barcode");
                        productcode.setText(Productcode.toString());
                        Productprice = value2.get("pinktax");
                        productprice.setText((Productprice.toString()));
                    } else {
                    }
                }
            });
        }else {
            productname.setText("no value found");
        }


    }

    public void onClick(View view) {

        if (view == backbutton) {
            Intent intent = new Intent(this, ScanningActivity.class);
            startActivity(intent);
        } else if (view == alternative){
            Intent intent1 = new Intent(this,MainActivity.class);
            startActivity(intent1);
        }

    }
}