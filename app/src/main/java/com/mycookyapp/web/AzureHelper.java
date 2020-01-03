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

   public interface AzureListener{
       public void onRecipesReady(List<Recipe> recipes);
   }
}
