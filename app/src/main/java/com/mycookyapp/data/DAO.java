package com.mycookyapp.data;


import com.mycookyapp.web.AzureHelper;

import java.util.List;

public class DAO {

    private DAOListener daoListener;
    private AzureHelper helper;
    private DataMock mock;
    private static DAO daoInstance = null;
    private DAOAzureListener listener;
    private DataContainer container;
    private DAOListener daoDetailsListener;

    private DAO(DAOListener daoListener) {
        mock = new DataMock();
        listener = new DAOAzureListener();
        this.daoListener = daoListener;
        helper = new AzureHelper(listener);
        container = new DataContainer();
    }

    public static DAO getInstance(DAOListener daoListener)
    {
        if (daoInstance == null)
            daoInstance = new DAO(daoListener);

        return daoInstance;
    }

    public static void eraseData(){
        daoInstance = null;
    }

    public UserData getUserData(String id) {
        return mock.getUserData(id);
    }

    public List<Recipe> getRecipes() {
//        return mock.getRecipes();
        return container.getRecipes();
    }


    public List<String> getIngredients(String id, DAOListener daoDetailsListener) {
//        return mock.getIngredients(id);
        this.daoDetailsListener = daoDetailsListener;
        List<String> ingredians = container.getIngredients(id);
        if(ingredians == null){
            helper.getRecipeDetails(id);
        }

        return ingredians;
    }

    public String getImageUrl(String id) {
        return mock.getImageUrl(id);
    }

    public String getNameRecipe(String id) {
        return mock.getNameRecipe(id);
    }



    public class DAOAzureListener implements AzureHelper.AzureListener {

        @Override
        public void onRecipesReady(List<Recipe> recipes) {
            container.setRecipes(recipes);
            daoListener.onRecipesReady(recipes);
        }

        @Override
        public void onRecipeDetailsReady(Recipe recipe) {
            container.updateRecipe(recipe);
            daoDetailsListener.onRecipeDetailsReady(recipe);
        }
    }

    public interface DAOListener{
        void onRecipesReady(List<Recipe> recipes);
        void onRecipeDetailsReady(Recipe recipe);
    }
}
