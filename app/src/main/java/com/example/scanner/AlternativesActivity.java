package com.example.scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AlternativesActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    ListView productListview;
    ArrayList<Product> ProductList;
    ArrayAdapter<Product> arrayAdapter;
    DatabaseReference DBR,DBRALTS;
    FirebaseDatabase DB;
    String Productname,Productprice, category, getProductname, getCategory;
    Long Productcode;
    String idnumber;
    ImageView rImage;
    TextView productname,productcode,productprice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternatives);
        DB = FirebaseDatabase.getInstance("https://pinkbird-a0d69-default-rtdb.europe-west1.firebasedatabase.app");
        DBR = DB.getReference("products");
        DBRALTS = DB.getReference("alternatives");
        productprice = this.findViewById(R.id.productprice);
        productcode= this.findViewById(R.id.productcode);
        productname = this.findViewById(R.id.productname);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        category = (String) b.get("category");


        DBRALTS.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        {
                            for (DataSnapshot data : snapshot.getChildren()) {

                                Product product = new Product ();

                                if(product.getCategory().equals(category)){
                                    {
                                        productname.setText(product.getProductname());
                                        productcode.setText(product.getBarcode().toString());
                                        productprice.setText((product.getPinktax()));

                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




    }
}