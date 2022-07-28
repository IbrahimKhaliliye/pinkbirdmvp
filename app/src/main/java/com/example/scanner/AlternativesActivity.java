package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
    String Productname,Productprice, category;
    Long Productcode;
    String idnumber;
    ImageView rImage;
    TextView productname,productcode,productprice;
    ImageButton backbutton;
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
        backbutton = this.findViewById(R.id.backbutton);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        category = (String) b.get("category");


        DBRALTS
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Product product = snapshot.getValue(Product.class);
                            if(product.getCategory().equals(category)){
                                {
                                        productname.setText(product.getProductname());
                                        productcode.setText(product.getBarcode().toString());
                                        productprice.setText((product.getPinktax()));

                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlternativesActivity.this, ScanningActivity.class);
                startActivity(intent);
            }
        });


    }
}