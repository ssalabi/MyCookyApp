package com.mycookyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycookyapp.data.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.recepies_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String name = recipes.get(position).getName();
        String id = recipes.get(position).getId();
        ((MyViewHolder)holder).name.setText(name);
        ((MyViewHolder)holder).id.setText(id);
        ImageView image = ((MyViewHolder)holder).image;

      /*  Picasso.with(context).load("http://cdn.journaldev.com/wp-content/uploads/2016/11/android-image-picker-project-structure.png").into();
        Picasso.get().load()
                .into(image);*/
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        public TextView name;
        public TextView id;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipe_name);
            id = itemView.findViewById(R.id.recipe_id);
            image = itemView.findViewById(R.id.recipe_image);

        }
    }
}
