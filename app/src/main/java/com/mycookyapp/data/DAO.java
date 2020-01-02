package com.mycookyapp.data;


import java.util.ArrayList;
import java.util.List;

public class DAO {

    private DataMock mock;

    public DAO() {
        mock = new DataMock();
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
}
