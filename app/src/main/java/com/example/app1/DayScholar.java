package com.example.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DayScholar extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText dName, dRoll, dSection, dPhone, dReason;
    Button submitBtn;
    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_scholar);

        dName = findViewById(R.id.textInputEditText1);
        dRoll = findViewById(R.id.textInputEditText2);
        dSection = findViewById(R.id.textInputEditText3);
        dPhone = findViewById(R.id.textInputEditText4);
        dReason = findViewById(R.id.textInputEditText5);
        submitBtn = findViewById(R.id.button);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        submitBtn.setOnClickListener(view -> {

            String name = dName.getText().toString();
            String roll = dRoll.getText().toString();
            String section = dSection.getText().toString();
            String phone = dPhone.getText().toString();
            String reason = dReason.getText().toString();

            if (TextUtils.isEmpty(name)) {
                dName.setError("Please enter a name");
                return;
            }
            if (TextUtils.isEmpty(roll)) {
                dRoll.setError("Please enter your roll number");
                return;
            }
            if (TextUtils.isEmpty(section)) {
                dSection.setError("Please enter your section");
                return;
            }
            if (TextUtils.isEmpty(phone)) {
                dPhone.setError("Please enter a phone number");
                return;
            }
            if (phone.length() < 6) {
                dPhone.setError("Password must be greater than 6 characters");
            }
            //next line gets the user id (UID)
            userID = fAuth.getCurrentUser().getUid();
            DocumentReference documentReference = fstore.collection("Form").document(userID);
            Map<String, Object> user = new HashMap<>();
            user.put("name", name);
            user.put("Rollno", roll);
            user.put("Section", section);
            user.put("PhNo", phone);
            user.put("Reason", reason);
            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                    Toast.makeText(DayScholar.this, "Successfully created.", Toast.LENGTH_SHORT).show();
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