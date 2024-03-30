package com.example.trashgg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Players> list;

    public MyAdapter(Context context, ArrayList<Players> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Players players = list.get(position);
        holder.nikName.setText(players.getName());
        holder.score.setText(String.valueOf(players.getRecord()));
        holder.placement.setText(String.valueOf(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nikName, score, placement;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nikName= itemView.findViewById(R.id.tvNikName);
            score= itemView.findViewById(R.id.tvScore);
            placement = itemView.findViewById(R.id.tvPlacement);

        }
    }
}
