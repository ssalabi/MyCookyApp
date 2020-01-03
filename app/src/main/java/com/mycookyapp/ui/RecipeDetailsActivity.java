package com.mycookyapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycookyapp.Names;
import com.mycookyapp.R;
import com.mycookyapp.adapters.IngridiensAdapter;
import com.mycookyapp.data.DAO;
import com.mycookyapp.data.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

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

        dao = DAO.getInstance(new DAOListener());

        List<String> ingredians = dao.getIngredients(id, new DAOListener());

        if(ingredians != null){
            loadRecyclerIngridiens(ingredians);
        }

        name.setText(dao.getNameRecipe(id));//added that
        Picasso.get().load(dao.getImageUrl(id))
                .into(image);
    }

    private void loadRecyclerIngridiens(List<String> ingredians) {
        IngridiensAdapter ingredientsAdapter = new IngridiensAdapter(ingredians, getApplicationContext());
        ingredients.setAdapter(ingredientsAdapter);
        ingredients.setLayoutManager(new LinearLayoutManager(this));

    }

    class DAOListener implements DAO.DAOListener{

        @Override
        public void onRecipesReady(List<Recipe> recipes) {

        }

        @Override
        public void onRecipeDetailsReady(Recipe recipe) {
            loadRecyclerIngridiens(recipe.getIngrediansList());
        }
    }
}
