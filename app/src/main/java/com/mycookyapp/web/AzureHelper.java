package com.mycookyapp.web;

import com.mycookyapp.data.Recipe;

import java.util.List;

public class AzureHelper {

    private AzureListener listener;

    public AzureHelper(final AzureListener listener){

        this.listener = listener;

        GetRecipesTask task = new GetRecipesTask(new GetRecipesTask.RecipesListener() {
            @Override
            public void onReady(List<Recipe> recipes) {
                listener.onRecipesReady(recipes);
            }
        });
        task.execute();
    }

    public void getRecipeDetails(String id){
        GetRecipeDetailsTask task = new GetRecipeDetailsTask(id, new GetRecipeDetailsTask.RecipeDetailsListener() {
            @Override
            public void onReady(Recipe recipe) {
                listener.onRecipeDetailsReady(recipe);
            }
        });
        task.execute();
    }

   public interface AzureListener{
       public void onRecipesReady(List<Recipe> recipes);
       public void onRecipeDetailsReady(Recipe recipe);

   }
}
