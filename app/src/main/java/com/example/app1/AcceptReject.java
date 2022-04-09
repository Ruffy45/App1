package com.example.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AcceptReject extends AppCompatActivity {
    public static final String TAG = "TAG";
    TextView acceptBtn,rejectBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    DocumentReference rollref,nameref,sectionref;
    String userID;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_reject);
        userID = fAuth.getCurrentUser().getUid();

       // db = FirebaseFirestore.getInstance();
        acceptBtn = findViewById(R.id.button);
        rejectBtn = findViewById(R.id.button6);
       //DocumentReference  nameref    = fstore.collection("Form").document("name");
        //DocumentReference sectionref = fstore.collection("Form").document("Section");
       //DocumentReference  rollref    = fstore.collection("Form").document("Rollno");


        acceptBtn.setOnClickListener(view -> {

            Toast.makeText(AcceptReject.this, "Request Accepted", Toast.LENGTH_SHORT).show();

         //   String s1,s2,s3;
           // s1 = "Bullfrog";
            //s2 = "Section-A";
            //s3 = "Don't remember";


            //CollectionReference cities = db.collection("AcceptedUsers");
            //DocumentReference documentReference = fstore.collection("AcceptedUsers").document(userID);
            //Map<String,Object> user = new HashMap<>();
            //user.put("Name","Ruffy");
            //user.put("Section","A");
            //user.put("RollNo","550");
            //documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            //    @Override
             //   public void onSuccess(Void aVoid) {
              //      Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
              //  }
            //}).addOnFailureListener(new OnFailureListener() {
             //   @Override
              //  public void onFailure(@NonNull Exception e) {
              //      Log.d(TAG, "onFailure: " + e.toString());
              //  }
              //});


        });
    }
}