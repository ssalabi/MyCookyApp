package com.mycookyapp.data;


import java.util.ArrayList;
import java.util.List;

public class DAO {

    public DAO() {
    }

    public UserData getUserData(String id){
        return new UserData("aaa@gmail", "user0", "female", "0000$");
    }

    public List<Recipe> getRecipes(){
        List recipes = new ArrayList();
        recipes.add(new Recipe("name0", "url0", "id0"));
        recipes.add(new Recipe("name1", "url1", "id1"));
        recipes.add(new Recipe("name2", "url2", "id2"));

        return recipes;
    }
}
