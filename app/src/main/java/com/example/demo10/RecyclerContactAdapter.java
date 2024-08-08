package com.example.demo10;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter <RecyclerContactAdapter.ViewHolder>{
    private  ArrayList<ContactModel> modelArrayList;
    private Context context;

    public RecyclerContactAdapter(ArrayList<ContactModel> arrContacts, Context context) {
        this.modelArrayList = arrContacts;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerContactAdapter.ViewHolder holder, int position) {
        ContactModel model = modelArrayList.get(position);
        holder.name.setText(model.getName());
        holder.number.setText("+91 " + model.getNumber());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
        }
    }
}
