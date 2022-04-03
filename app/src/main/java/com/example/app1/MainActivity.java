package com.example.app1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private TextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button teacher =findViewById(R.id.warden);
        Button Day =findViewById(R.id.DayScholar);
        Button Host =findViewById(R.id.Teacher);
        Button Ward =findViewById(R.id.Hostler);
        Button Guard =findViewById(R.id.Admin);
        Button Admin =findViewById(R.id.Guard);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.Lemail);


        teacher.setOnClickListener(view -> {
            Intent intent = new Intent(this, TeacherRegistration.class);
                    startActivity(intent);
        });

        Day.setOnClickListener(view -> {
            Intent intent = new Intent(this, Registration.class);
            startActivity(intent);

        });

        Host.setOnClickListener(view -> {
            Intent intent = new Intent(this, Registration.class);
            startActivity(intent);

        });

        Ward.setOnClickListener(view -> {
            Intent intent = new Intent(this, Registration.class);
            startActivity(intent);

        });

        Guard.setOnClickListener(view -> {
            Intent intent = new Intent(this, GuardLogin.class);
            startActivity(intent);

        });

        Admin.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewRecycler.class);
            startActivity(intent);

        });


    }

    public void logout(View view) {
        mFirebaseAuth.signOut();
        Toast.makeText(MainActivity.this, "Successfully Logged Out!",Toast.LENGTH_SHORT).show();
    }

}
