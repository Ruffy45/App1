package com.example.app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class recycler extends AppCompatActivity {
    public static final String TAG = "TAG";
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    Button acceptBtn;
    FirebaseAuth fAuth;
    String userID;
    FirebaseFirestore fstore;



    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        mFirestoreList =findViewById(R.id.firestore_list);
        firebaseFirestore = FirebaseFirestore.getInstance();
        acceptBtn =findViewById(R.id.button4);
        fAuth       =FirebaseAuth.getInstance();
        fstore      =FirebaseFirestore.getInstance();






        //Query
        Query query = firebaseFirestore.collection("Form");
        //RecyclerOptions
        FirestoreRecyclerOptions<ProductsModel> options =new FirestoreRecyclerOptions.Builder<ProductsModel>()
                .setQuery(query, ProductsModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ProductsModel, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent , false );
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull ProductsModel model) {
                holder.list_PhNo.setText(model.getName());
                holder.list_Section.setText(model.getSection());
                holder.list_Reason.setText(model.getReason());
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder {

        private TextView list_PhNo;
        private TextView list_Section;
        private TextView list_Reason;


        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            list_PhNo = itemView.findViewById(R.id.List_PhNo);
            list_Section = itemView.findViewById((R.id.List_Section));
            list_Reason = itemView.findViewById(R.id.List_Reason);

            //OnClickListener for accept button

            itemView.findViewById(R.id.button4).setOnClickListener(view -> {
                DocumentReference rollref,nameref,sectionref,UID;
                nameref    = fstore.collection("Form").document("name");
                sectionref = fstore.collection("Form").document("Section");
                rollref    = fstore.collection("Form").document("Rollno");

                FirebaseUser fuser = fAuth.getCurrentUser();
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("AcceptedUsers").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("Rollno",rollref);
                user.put("Section",sectionref);
                user.put("Name",nameref);
                documentReference.set(user).addOnSuccessListener(aVoid -> {
                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                });

            });
            //onClick end

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

}