package com.example.scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrivingDataActivity extends AppCompatActivity {
    ListView productsListview;
    List<Products> productsList;

    DatabaseReference ProductsDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retriving_data);

        productsListview = findViewById(R.id.productsListView);
        productsList = new ArrayList<>();

        ProductsDbRef = FirebaseDatabase.getInstance().getReference("Products");

        ProductsDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productsList.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()){
                    Products products = studentDatasnap.getValue(Products.class);
                    productsList.add(products);
                }

                ListAdapter adapter = new ListAdapter(RetrivingDataActivity.this,productsList);
                productsListview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        productsListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Products products = productsList.get(position);
                showUpdateDialog(products.getId(), products.getProdname());

                return false;
            }
        });
    }

    private void showUpdateDialog(final String id, String name){

        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog, null);

        mDialog.setView(mDialogView);

        final EditText etUpdateProdName = mDialogView.findViewById(R.id.etUpdateProdName);
        final EditText etUpdateBarcode = mDialogView.findViewById(R.id.etUpdateBarcode);
        final Spinner mSpinner = mDialogView.findViewById(R.id.updateSpinner);
        Button btnUpdate = mDialogView.findViewById(R.id.btnUpdate);
        Button btnDelete = mDialogView.findViewById(R.id.btnDelete);

        mDialog.setTitle("Updating " + etUpdateProdName +" record");

        final AlertDialog alertDialog = mDialog.create();
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newProdName = etUpdateProdName.getText().toString();
                String newBarcode = etUpdateBarcode.getText().toString();
                String newCategory = mSpinner.getSelectedItem().toString();

                updateData(id,newProdName,newBarcode,newCategory);

                Toast.makeText(RetrivingDataActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }

        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(id);

                alertDialog.dismiss();
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void deleteRecord(String id){
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("Products").child(id);

        Task<Void> mTask = DbRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showToast("Deleted");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("Error deleting product");
            }
        });
    }

    private void updateData(String id, String ProdName, String Barcode, String category){

        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("Products").child(id);
        Products products = new Products(id, ProdName, Barcode, category);
        DbRef.setValue(products);
    }

}