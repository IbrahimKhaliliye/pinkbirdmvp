package com.example.scanner;

import androidx.annotation.NonNull;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefernceProducts;
    private List<Product> products = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<Product> products, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mRefernceProducts = mDatabase.getReference("products");

    }
    public void readProducts(final DataStatus dataStatus){
        mRefernceProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Product product = keyNode.getValue(Product.class);
                    products.add(product);
                }
                dataStatus.DataIsLoaded(products,keys);
            }
            //method to return products

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
