package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class GuardLogin extends AppCompatActivity {
    EditText muser,mPassword;
    Button mLoginBtn;
    ProgressBar progressBar;
    String s1="security";
    String s2="123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_login);

        muser = findViewById(R.id.Lemail);
        mPassword = findViewById(R.id.Lpassword);
        progressBar = findViewById(R.id.progressBar);
        mLoginBtn=findViewById(R.id.loginBtn);

        mLoginBtn.setOnClickListener(view -> {

            String username = muser.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            if(TextUtils.isEmpty(username)){
                muser.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                mPassword.setError("Password is Required.");
                return;
            }

            if(password.length() < 6){
                mPassword.setError("Incorrect Password");
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            if(username.equals(s1) && password.equals(s2)){
                Toast.makeText(GuardLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), NewRecycler.class));
                return;
            }
            else {
                Toast.makeText(GuardLogin.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }


        });
    };}