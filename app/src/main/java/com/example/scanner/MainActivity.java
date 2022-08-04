package com.example.scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email, password;
    private ImageButton sign_in, sign_up;
    private FirebaseAuth mAuth;
    private FirebaseUser FireUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sign_in = findViewById(R.id.sign_in);
        sign_up = findViewById(R.id.tttt);
        mAuth = FirebaseAuth.getInstance();
        FireUser = mAuth.getCurrentUser();
        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }


    public void signin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, ScanningActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if (sign_in == view) {
            signin(email.getText().toString(), password.getText().toString());


        }
        if (sign_up == view) {
            Intent intent1 = new Intent(this, SignupActivity.class);
            startActivity(intent1);
        }
    }

}
