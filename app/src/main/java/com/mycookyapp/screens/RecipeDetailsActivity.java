package com.mycookyapp.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycookyapp.Names;
import com.mycookyapp.R;
import com.mycookyapp.adapters.IngridiensAdapter;
import com.mycookyapp.adapters.PreparationsAdapter;
import com.mycookyapp.data.DAO;
import com.mycookyapp.data.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {

    private RecyclerView ingredients;
    private DAO dao;
    private ImageView image;
    private TextView name;
    private String id;
    private RecyclerView preparation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        id = getIntent().getStringExtra(Names.RECIPE_ID);

        ingredients = findViewById(R.id.details_ingredients);
        preparation = findViewById(R.id.details_preparation);
        image = findViewById(R.id.details_image);
        name = findViewById(R.id.details_name);

        dao = DAO.getInstance();
//        dao.getIngredients()

        dao.getIngredients(id, new DAOListener());
        dao.getPraparation(id, new DAOListener());


        loadNameAndImage(id);
    }

    private void loadNameAndImage(String id) {
        name.setText(dao.getNameRecipe(id));//added that
        Picasso.get().load(dao.getImageUrl(id))
                .into(image);
    }

    private void loadRecyclerIngridiens(List<String> ingredians) {
        IngridiensAdapter ingredientsAdapter = new IngridiensAdapter(ingredians, getApplicationContext());
        ingredients.setAdapter(ingredientsAdapter);
        ingredients.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadRecyclerPraparation(List<String> preparations) {
        PreparationsAdapter preparationsAdapter = new PreparationsAdapter(getApplicationContext(), preparations);
        preparation.setAdapter(preparationsAdapter);
        preparation.setLayoutManager(new LinearLayoutManager(this));
    }

    class DAOListener implements DAO.DAOListener{

        @Override
        public void onRecipesReady(List<Recipe> recipes) {

        }

        @Override
        public void onRecipeDetailsReady(Recipe recipe) {
    //        loadRecyclerIngridiens(recipe.getIngrediansList());

        }

        @Override
        public void onIngridiansReady(List<String> ingridians) {
            loadRecyclerIngridiens(ingridians);
        }

        @Override
        public void onPreparationsReady(List<String> preparations) {
            loadRecyclerPraparation(preparations);
        }
    }


}
