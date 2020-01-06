package com.mycookyapp.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe {
    private String name;
    private String image;
    private String id;
    private String preparation_process;
    private String ingredients;

    public Recipe(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public Recipe(String name, String image, String id, String preparation_process, String ingredients) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.preparation_process = preparation_process;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreparation_process() {
        return preparation_process;
    }

    public void setPreparation_process(String preparation_process) {
        this.preparation_process = preparation_process;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngrediansList(){
        return strToList(ingredients);
    }

    public List<String> getPreparationList(){
        return strToList(preparation_process);
    }

    private List<String> strToList(String str) {
        if(str == null){
            return null;
        }
        return new ArrayList<String>(Arrays.asList(str.split("-")));
    }
}
