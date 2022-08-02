package com.example.scanner;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import com.squareup.picasso.Picasso;
import com.google.firebase.auth.FirebaseAuth;


public class Products {
    FirebaseAuth mAuth;
    ImageView rImage;
    String id;
    String prodname;
    String barcode;
    String category;

    public Products (String id, String prodname, String barcode, String category){
        this.id = id;
        this.prodname = prodname;
        this.barcode = barcode;
        this.category = category;
    }
    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public ImageView getrImage() {
        return rImage;
    }

    public void setrImage(ImageView rImage) {
        this.rImage = rImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}