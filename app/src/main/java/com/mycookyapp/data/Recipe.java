package com.mycookyapp.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe {
    private String name;
    private String image;
    private String id;
    private String preperation;
    private String ingredians;

    public Recipe(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public Recipe(String name, String image, String id, String preperation, String ingredians) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.preperation = preperation;
        this.ingredians = ingredians;
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

    public String getPreperation() {
        return preperation;
    }

    public void setPreperation(String preperation) {
        this.preperation = preperation;
    }

    public String getIngredians() {
        return ingredians;
    }

    public void setIngredians(String ingredians) {
        this.ingredians = ingredians;
    }

    public List<String> getIngrediansList(){
        return strToList(ingredians);
    }

    public List<String> getPreparationList(){
        return strToList(preperation);
    }

    private List<String> strToList(String str) {
        return new ArrayList<String>(Arrays.asList(str.split("-")));
    }
}
