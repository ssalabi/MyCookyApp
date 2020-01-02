package com.mycookyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycookyapp.R;
import com.mycookyapp.data.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter {

    private RecipeClickListener listener;
    private Context context;
    private List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> recipes, Context context, RecipeClickListener listener) {
        this.recipes = recipes;
        this.context = context;
        this.listener = listener;
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
        Recipe recipe = recipes.get(position);
        String name = recipe.getName();
        final String id = recipe.getId();

        MyViewHolder myHolder = ((MyViewHolder) holder);
        myHolder.name.setText(name);
        myHolder.id.setText(id);
        ImageView image = myHolder.image;

        Picasso.get().load(recipes.get(position).getUrl())
                .into(image);

        myHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private View layout;
        private ImageView image;
        public TextView name;
        public TextView id;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipe_name);
            id = itemView.findViewById(R.id.recipe_id);
            image = itemView.findViewById(R.id.recipe_image);
            layout = itemView.findViewById(R.id.recipe_layout);

        }
    }

    public interface RecipeClickListener{
        public void onClick(String id);
    }
}
