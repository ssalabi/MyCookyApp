package com.mycookyapp.data;


import com.mycookyapp.ui.RecipeDetailsActivity;
import com.mycookyapp.web.AzureHelper;

import java.util.List;

public class DAO {

    private DAOListener daoListener;
    private AzureHelper azureHelper;
    private DataMock mock;
    private static DAO daoInstance = null;
    private AzureListener azureListener;
    private DataContainer container;

    private DAO() {
        mock = new DataMock();
        azureListener = new AzureListener();
        azureHelper = new AzureHelper();
        container = new DataContainer();
    }

    public static DAO getInstance()
    {
        if (daoInstance == null){
            daoInstance = new DAO();
        }
        return daoInstance;
    }

    private void setDAOListener(DAOListener daoListener) {
        this.daoListener = daoListener;
    }

    public static void eraseData(){
        daoInstance = null;
    }

    public UserData getUserData(String id) {
        return mock.getUserData(id);
    }

    public void getRecipes(DAOListener listener) {
//        return mock.getRecipes();

        this.daoListener = listener;

        List<Recipe> recipes = container.getRecipes();
        if (recipes != null){
            listener.onRecipesReady(recipes);
        } else {
            azureHelper.getRecipes(azureListener);
        }

    }


    public void getIngredients(String id, DAOListener daoListener) {
//        return mock.getIngredients(id);
        this.daoListener = daoListener;
        List<String> ingredians = container.getIngredients(id);
        if(ingredians == null){
            azureHelper.getRecipeDetails(azureListener, id);
        } else {
            daoListener.onIngridiansReady(ingredians);
        }
    }

    public void getPraparation(String id, DAOListener daoListener) {
        this.daoListener = daoListener;
        List<String> praparations = container.getPreparations(id);
        if(praparations == null){
            azureHelper.getRecipeDetails(azureListener, id);
        } else {
            daoListener.onPreparationsReady(praparations);
        }
    }

    public String getImageUrl(String id) {
//        return mock.getImageUrl(id);
        return container.getImageUrl(id);

    }

    public String getNameRecipe(String id) {
    //    return mock.getNameRecipe(id);
        return container.getNameRecipe(id);
    }




    public class AzureListener implements AzureHelper.AzureListener {

        @Override
        public void onRecipesReady(List<Recipe> recipes) {
            container.setRecipes(recipes);
            daoListener.onRecipesReady(recipes);
        }

        @Override
        public void onRecipeDetailsReady(Recipe recipe) {
            container.updateRecipe(recipe);
            daoListener.onIngridiansReady(recipe.getIngrediansList());
            daoListener.onPreparationsReady(recipe.getPreparationList());

        }
    }

    public interface DAOListener{
        void onRecipesReady(List<Recipe> recipes);
        void onRecipeDetailsReady(Recipe recipe);
        void onIngridiansReady(List<String> ingridians);
        void onPreparationsReady(List<String> preparations);
    }
}
