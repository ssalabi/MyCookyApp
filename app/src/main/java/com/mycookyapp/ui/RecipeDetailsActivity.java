package com.mycookyapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycookyapp.Names;
import com.mycookyapp.R;
import com.mycookyapp.adapters.IngridiensAdapter;
import com.mycookyapp.data.DAO;
import com.squareup.picasso.Picasso;

public class RecipeDetailsActivity extends AppCompatActivity {

    private RecyclerView ingredients;
    private DAO dao;
    private ImageView image;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        String id = getIntent().getStringExtra(Names.RECIPE_ID);

        ingredients = findViewById(R.id.details_ingredients);
        image = findViewById(R.id.details_image);
        name = findViewById(R.id.details_name);

        dao = DAO.getInstance();

        IngridiensAdapter ingredientsAdapter = new IngridiensAdapter(dao.getIngredients(id), getApplicationContext());
        ingredients.setAdapter(ingredientsAdapter);
        ingredients.setLayoutManager(new LinearLayoutManager(this));
        name.setText(dao.getNameRecipe(id));//added that
        Picasso.get().load(dao.getImageUrl(id))
                .into(image);
    }
}
