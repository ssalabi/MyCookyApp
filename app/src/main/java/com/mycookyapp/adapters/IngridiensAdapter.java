package com.mycookyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycookyapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngridiensAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> ingridients;

    public IngridiensAdapter(List<String> ingridients, Context context) {
        this.ingridients = ingridients;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.ingridient_row, parent, false);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myHolder = ((MyViewHolder) holder);
        myHolder.name.setText(ingridients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingridients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingridient_text);
        }
    }
}
