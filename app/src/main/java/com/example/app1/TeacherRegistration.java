package com.example.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TeacherRegistration extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mfullname,mRoll,mEmail,mpassword;
    TextView mtextView2,mtextView,mtextView3,mphone;
    Button registerBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    String userID;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mfullname     = findViewById(R.id.textInputEditText);
        mRoll         = findViewById(R.id.textInputEditText4);
        mEmail        = findViewById(R.id.textInputEditText6);
        mpassword     = findViewById(R.id.textInputEditText5);
        mtextView2    = findViewById(R.id.textView2);
        mtextView3    = findViewById(R.id.textView3);
        mtextView     = findViewById(R.id.textView);
        registerBtn   = findViewById(R.id.registerBtn);
        mphone        = findViewById(R.id.textInputEditText7);

        fAuth       =FirebaseAuth.getInstance();
        fstore      =FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        registerBtn.setOnClickListener(view -> {

            String fullname = mfullname.getText().toString();
            String password = mpassword.getText().toString().trim();
            String emailid  = mEmail.getText().toString().trim();
            String rollno   = mRoll.getText().toString();
            String phone    = mphone.getText().toString();

            if(TextUtils.isEmpty(fullname)){
                mfullname.setError("Please enter a name");
                return;
            }
            if(TextUtils.isEmpty(password)){
                mfullname.setError("Please a password");
                return;
            }
            if(TextUtils.isEmpty(rollno)){
                mfullname.setError("Please enter your Employee Code");
                return;
            }
            if(TextUtils.isEmpty(emailid)){
                mfullname.setError("Please enter an designation");
                return;
            }
            if(TextUtils.isEmpty(phone)){
                mfullname.setError("Please enter a phone number");
                return;
            }
            if(password.length() < 6){
                mpassword.setError("Password must be greater than 6 characters");
            }
            progressBar.setVisibility(View.VISIBLE);
            //Register the user in firebase

            fAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        // send verification link

                        FirebaseUser fuser = fAuth.getCurrentUser();
                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(TeacherRegistration.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                            }
                        });

                        Toast.makeText(TeacherRegistration.this, "User Created.", Toast.LENGTH_SHORT).show();
                        userID = fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fstore.collection("TeacherUsers").document(userID);
                        Map<String,Object> user = new HashMap<>();
                        user.put("fullName",fullname);
                        user.put("Designation",emailid);
                        user.put("phone",phone);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.toString());
                            }
                        });
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));

                    }else {
                        Toast.makeText(TeacherRegistration.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

        });
        mtextView3.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),TeacherLogin.class));

        });



    }
}