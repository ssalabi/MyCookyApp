package com.mycookyapp;

import com.mycookyapp.data.Recipe;
import com.mycookyapp.data.UserData;

import java.util.List;

public class AzureHelper {

    public AzureHelper(){
        GetRecipesTask task = new GetRecipesTask();
        task.doInBackground();
    }

   /* public UserData getUserData(String id){
        return new UserData("aaa@gmail", "user0", "female", "0000$");
    }*/

    public List<Recipe> getRecipes(){



        return null;
    }

   /* public List<String> getIngredients(String id) {
        Recipe recipe = findWithId(id);
        return recipe.getIngrediansList();
    }

    private Recipe findWithId(String id){
        for (Recipe recipe: recipes) {
            if(recipe.getId().equals(id)){
                return recipe;
            }
        }
        return null;
    }

    public String getImageUrl(String id) {
        return findWithId(id).getUrl();
    }*/
}
