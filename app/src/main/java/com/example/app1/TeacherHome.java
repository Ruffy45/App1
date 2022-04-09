package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class TeacherHome extends AppCompatActivity  {
    private Button login,register,status,logout;
    private FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        login     = findViewById(R.id.button3);
        register  = findViewById(R.id.button7);
        status    = findViewById(R.id.button8);
        fauth     = FirebaseAuth.getInstance();
        logout    = findViewById(R.id.button9);

        login.setOnClickListener(view -> {
            Intent intent = new Intent(TeacherHome.this, TeacherLogin.class);
            startActivity(intent);
        });

        register.setOnClickListener(view -> {
            Intent intent = new Intent(TeacherHome.this, TeacherRegistration.class);
            startActivity(intent);
        });
        status.setOnClickListener(view -> {
            Intent intent = new Intent(TeacherHome.this, MainActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fauth.signOut();
                Toast.makeText(TeacherHome.this, "Successfully Logged Out!",Toast.LENGTH_SHORT).show();
            }
        });
    }

}