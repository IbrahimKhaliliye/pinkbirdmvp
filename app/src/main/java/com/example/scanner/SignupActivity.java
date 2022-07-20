package com.example.scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name, email,password;
    Button signup;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(this);
    }

    public boolean Is_password_strong(String password){
        if(password.length()<6)
            return false;
        boolean lower,upper,digit,special;
        lower=upper=digit=special= false;
        for (int i=0; i<password.length(); i++){
            char c= password.charAt(i);
            if(c >= 'a' && c<= 'z')
                lower=true;
            if(c >= 'A' && c<= 'Z')
                upper = true;
            if(c >= '0' && c <= '9')
                digit= true;
            if(c>=33 && c<=47 || c>=58 && c<=64 || c>=91 && c<=96 || c>=123 && c<=126)
                special=true;

        }
        return lower && upper && digit && special;
    }


    public void createAccount(String email,String password){
        if(email != null && password != null) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {

                                Toast.makeText(SignupActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(View view) {

        if (view == signup){
            String user_password = password.getText().toString();
            if(Is_password_strong(user_password)) {
                createAccount(email.getText().toString(),password.getText().toString());
            }
            else {
                Toast.makeText(this, "weak password\n make sure you have\n 1 uppercase\n 1 lowercase\n 1 number\n special\n ", Toast.LENGTH_SHORT).show();
            }
        }

    }




}