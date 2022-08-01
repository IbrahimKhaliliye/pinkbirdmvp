package com.example.scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternatives);

        DB = FirebaseDatabase.getInstance("https://pinkbird-a0d69-default-rtdb.europe-west1.firebasedatabase.app");
        DBR = DB.getReference("products");
        DBRALTS = DB.getReference("alternatives");
        productprice = this.findViewById(R.id.productprice);
        productcode = this.findViewById(R.id.productcode);
        productname = this.findViewById(R.id.productname);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        category = (String) b.get("category");

        DBRALTS.orderByChild("category").equalTo(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()){
                    Product product = data.getValue(Product.class);
                    new DownloadImageFromInternet((ImageView) findViewById(R.id.rImage)).
                            execute(product.getImage());


                }
            }

            public void onDataChange1 (@NonNull DataSnapshot dataSnapshot){
                String Productname = dataSnapshot.child("productname").getValue(String.class);
                String Productprice = dataSnapshot.child("productprice").getValue(String.class);
                Long productcode = dataSnapshot.child("productcode").getValue(Long.class);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
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

}
