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

public class PreparationsAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> preparations;

    public PreparationsAdapter(Context context, List<String> preparations) {
        this.context = context;
        this.preparations = preparations;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.preparations_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myHolder = ((MyViewHolder) holder);
        myHolder.name.setText(preparations.get(position));
    }

    @Override
    public int getItemCount() {
        return preparations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.preparation_text);
        }
    }
}
