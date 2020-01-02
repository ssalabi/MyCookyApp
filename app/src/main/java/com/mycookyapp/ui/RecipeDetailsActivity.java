package com.mycookyapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mycookyapp.Names;
import com.mycookyapp.R;
import com.mycookyapp.adapters.IngridiensAdapter;
import com.mycookyapp.data.DAO;

public class RecipeDetailsActivity extends AppCompatActivity {

    private RecyclerView ingredients;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        String id = getIntent().getStringExtra(Names.RECIPE_ID);

        ingredients = findViewById(R.id.details_ingredients);

        dao = new DAO();

        IngridiensAdapter ingredientsAdapter = new IngridiensAdapter(dao.getIngredients(id), getApplicationContext());
        ingredients.setAdapter(ingredientsAdapter);
        ingredients.setLayoutManager(new LinearLayoutManager(this));
    }
}
