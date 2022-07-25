package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductContentActivity extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_content);

        DB = FirebaseDatabase.getInstance("https://pinkbird-a0d69-default-rtdb.europe-west1.firebasedatabase.app");
        DBR = DB.getReference("products");
        productprice = this.findViewById(R.id.productprice);
        productcode= this.findViewById(R.id.productcode);
        productname = this.findViewById(R.id.productname);

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
}