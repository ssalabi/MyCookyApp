package com.mycookyapp.data;

import java.util.List;

public class DataContainer {

    private List<Recipe> recipes;

    public DataContainer(){

    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
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
        return findWithId(id).getImage();
    }

    public String getNameRecipe(String id) {
        return findWithId(id).getName();
    }

    public void updateRecipe(Recipe recipe) {
        Recipe myRecipe = findWithId(recipe.getId());
        myRecipe.setIngredients(recipe.getIngredients());
        myRecipe.setPreperation(recipe.getPreperation());
    }
}
