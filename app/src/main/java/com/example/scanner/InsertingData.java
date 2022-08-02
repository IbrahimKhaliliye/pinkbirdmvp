package com.example.scanner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.UUID;

public class InsertingData extends AppCompatActivity {

    EditText etProdName;
    EditText etBarcode;
    Spinner spinnerCategory;
    Button btnInsertData;
    DatabaseReference productDbRef;


        private Button btnSelect, btnUpload;
        private ImageView imageView;
        private Uri filePath;
        private Bundle savedInstanceState;
        private final int PICK_IMAGE_REQUEST = 22;

        FirebaseStorage storage;
        StorageReference storageReference;




        private void SelectImage()
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(
                    Intent.createChooser(
                            intent,
                            "Select Image from here..."),
                    PICK_IMAGE_REQUEST);
        }

        @Override
        protected void onActivityResult(int requestCode,
                                        int resultCode,
                                        Intent data)
        {

            super.onActivityResult(requestCode,
                    resultCode,
                    data);

            if (requestCode == PICK_IMAGE_REQUEST
                    && resultCode == RESULT_OK
                    && data != null
                    && data.getData() != null) {

                filePath = data.getData();
                try {

                    Bitmap bitmap = MediaStore
                            .Images
                            .Media
                            .getBitmap(
                                    getContentResolver(),
                                    filePath);
                    imageView.setImageBitmap(bitmap);
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void uploadImage()
        {
            if (filePath != null) {

                ProgressDialog progressDialog
                        = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference ref
                        = storageReference
                        .child(
                                "images/"
                                        + UUID.randomUUID().toString());

                ref.putFile(filePath)
                        .addOnSuccessListener(
                                new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                    @Override
                                    public void onSuccess(
                                            UploadTask.TaskSnapshot taskSnapshot)
                                    {

                                        progressDialog.dismiss();
                                        Toast
                                                .makeText(InsertingData.this,
                                                        "Image Uploaded!",
                                                        Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {

                                // Error, Image not uploaded
                                progressDialog.dismiss();
                                Toast
                                        .makeText(InsertingData.this,
                                                "Failed " + e.getMessage(),
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .addOnProgressListener(
                                new OnProgressListener<UploadTask.TaskSnapshot>() {

                                    @Override
                                    public void onProgress(
                                            UploadTask.TaskSnapshot taskSnapshot)
                                    {
                                        double progress
                                                = (100.0
                                                * taskSnapshot.getBytesTransferred()
                                                / taskSnapshot.getTotalByteCount());
                                        progressDialog.setMessage(
                                                "Uploaded "
                                                        + (int)progress + "%");
                                    }
                                });
            }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserting_data);

        etProdName = findViewById(R.id.etProdName);
        etBarcode = findViewById(R.id.etBarcode);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnInsertData = findViewById(R.id.btnInsertData);
        productDbRef = FirebaseDatabase.getInstance("https://pinkbird-a0d69-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Products");
        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertProductData();
            }
        });
    }

    private void insertProductData(){
        String ProdName = etProdName.getText().toString();
        String Barcode = etBarcode.getText().toString();
        String Category = spinnerCategory.getSelectedItem().toString();

        String id = productDbRef.push().getKey();

        Products products = new Products(id, ProdName, Barcode, Category);
        assert id != null;
        productDbRef.child(id).setValue(products);
        Toast.makeText(InsertingData.this,"Product added!", Toast.LENGTH_SHORT).show();
    }
}