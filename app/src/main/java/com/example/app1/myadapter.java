package com.example.app1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myveiwholder> {
    ArrayList<ProductsModel> datalist;

    public myadapter(ArrayList<ProductsModel> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myveiwholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myveiwholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myveiwholder holder, int position) {
        holder.T1.setText(datalist.get(position).getName());
        holder.T2.setText(datalist.get(position).getSection());
        holder.T3.setText(datalist.get(position).getReason());

        holder.T1.setOnClickListener(view -> {
            Intent intent = new Intent(holder.T1.getContext(), AcceptReject.class);
            intent.putExtra("uname",datalist.get(position).getName());
            intent.putExtra("ureason",datalist.get(position).getReason());
            intent.putExtra("usection",datalist.get(position).getSection());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.T1.getContext().startActivity(intent);


        });
        holder.T2.setOnClickListener(view -> {
            Intent intent = new Intent(holder.T2.getContext(), AcceptReject.class);
            intent.putExtra("uname",datalist.get(position).getName());
            intent.putExtra("ureason",datalist.get(position).getReason());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.T2.getContext().startActivity(intent);


        });
        holder.T3.setOnClickListener(view -> {
            Intent intent = new Intent(holder.T3.getContext(), AcceptReject.class);
            intent.putExtra("uname",datalist.get(position).getName());
            intent.putExtra("ureason",datalist.get(position).getReason());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.T3.getContext().startActivity(intent);


        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myveiwholder extends RecyclerView.ViewHolder {
        TextView T1,T2,T3;
        public myveiwholder(@NonNull View itemView) {
            super(itemView);
            T1 = itemView.findViewById(R.id.T1);
            T2 = itemView.findViewById(R.id.T2);
            T3 = itemView.findViewById(R.id.T3);

        }
    }
}
