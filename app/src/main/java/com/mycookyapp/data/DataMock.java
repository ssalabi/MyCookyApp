package com.mycookyapp.data;

import java.util.ArrayList;
import java.util.List;

public class DataMock {

    private ArrayList<Recipe> recipes;

    public DataMock() {
        recipes = new ArrayList();
        recipes.add(new Recipe("name0", "https://cdn.pixabay.com/photo/2017/09/01/00/16/png-2702697_960_720.png", "id0", "", "- 2 glasses of rice - 1 spoon of oil"));
        recipes.add(new Recipe("name1", "https://cdn.pixabay.com/photo/2017/09/01/00/16/png-2702697_960_720.png", "id1", "", "- 2 glasses of rice - 1 spoon of oil"));
        recipes.add(new Recipe("name2", "https://cdn.pixabay.com/photo/2017/09/01/00/16/png-2702697_960_720.png", "id2", "", "- 2 glasses of rice - 1 spoon of oil"));
    }

    public UserData getUserData(String id){
        return new UserData("aaa@gmail", "user0", "female", "0000$");
    }

    public List<Recipe> getRecipes(){
        return recipes;
    }

    public List<String> getIngredients(String id) {
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
    }

    public String getNameRecipe(String id) {
        return findWithId(id).getName();
    }
}
