package com.example.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_reject);

        acceptBtn = findViewById(R.id.button);
        rejectBtn = findViewById(R.id.button6);
       DocumentReference  nameref    = fstore.collection("Form").document("name");
        DocumentReference sectionref = fstore.collection("Form").document("Section");
       DocumentReference  rollref    = fstore.collection("Form").document("Rollno");


        acceptBtn.setOnClickListener(view -> {


            String s1,s2,s3;
            s1 = "Bullfrog";
            s2 = "Section-A";
            s3 = "Don't remember";

            userID = fAuth.getCurrentUser().getUid();
            DocumentReference documentReference = fstore.collection("StudentUsers").document(userID);
            Map<String,Object> user = new HashMap<>();
            user.put("Name",s1);
            user.put("Section",s2);
            user.put("RollNo",s3);
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


        });
    }
}