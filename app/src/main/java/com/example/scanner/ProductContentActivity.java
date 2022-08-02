package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class ProductContentActivity extends AppCompatActivity {
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

    ImageButton alternative, backbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_content);

        DB = FirebaseDatabase.getInstance("https://pinkbird-a0d69-default-rtdb.europe-west1.firebasedatabase.app");
        DBR = DB.getReference("products");
        DBRALTS = DB.getReference("alternatives");
        productprice = this.findViewById(R.id.productprice);
        productcode= this.findViewById(R.id.productcode);
        productname = this.findViewById(R.id.productname);
        alternative = this.findViewById(R.id.alternative);
        backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(this::onClick);


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
                        Productprice = value.get("pinktax");
                        category = value.get("category ");
                        productprice.setText((Productprice.toString()));
                        new DownloadImageFromInternet((ImageView) findViewById(R.id.rImage)).
                                execute(value.get("image"));
                    } else {
                    }
                }
            });
        }else {
            productname.setText("no value found");
        }
        rImage = findViewById(R.id.rImage);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference getImage = databaseReference.child("image");
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(rImage);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProductContentActivity.this, "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });

        alternative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(ProductContentActivity.this, AlternativesActivity.class);
                inten.putExtra("category", category);
                startActivity(inten);
            }
        });

    }
    public class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView=imageView;
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }


    public void onClick(View view) {

        if (view == backbutton) {
            Intent intent = new Intent(this, ScanningActivity.class);
            startActivity(intent);
        }
    }
}

