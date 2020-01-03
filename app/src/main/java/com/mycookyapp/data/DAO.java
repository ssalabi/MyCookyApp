package com.mycookyapp.data;


import com.mycookyapp.AzureHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    private AzureHelper helper;
    private DataMock mock;
    private static DAO daoInstance = null;

    private DAO() {
        mock = new DataMock();
        helper = new AzureHelper();
    }

    public static DAO getInstance()
    {
        if (daoInstance == null)
            daoInstance = new DAO();

        return daoInstance;
    }

    public static void eraseData(){
        daoInstance = null;
    }

    public UserData getUserData(String id) {
        return mock.getUserData(id);
    }

    public List<Recipe> getRecipes() {
        return mock.getRecipes();
    }


    public List<String> getIngredients(String id) {
        return mock.getIngredients(id);
    }

    public String getImageUrl(String id) {
        return mock.getImageUrl(id);
    }

    public String getNameRecipe(String id) {
        return mock.getNameRecipe(id);
    }
}
