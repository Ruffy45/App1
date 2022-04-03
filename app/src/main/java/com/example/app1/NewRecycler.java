package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NewRecycler extends AppCompatActivity {
    RecyclerView recview;
    ArrayList<ProductsModel>datalist;
    FirebaseFirestore db;
    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recycler);

        recview = (RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist = new ArrayList<>();
        adapter = new myadapter(datalist);
        recview.setAdapter(adapter);

        //Firestore thingy
        db = FirebaseFirestore.getInstance();
        db.collection("Form").orderBy("name").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                     List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                     for(DocumentSnapshot d:list ){
                         ProductsModel obj = d.toObject(ProductsModel.class);
                         datalist.add(obj);

                     }
                     adapter.notifyDataSetChanged();
                    }
                });

    }
}