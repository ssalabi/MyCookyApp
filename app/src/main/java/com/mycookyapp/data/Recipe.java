package com.mycookyapp.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe {
    private String name;
    private String url;
    private String id;
    private String preperation;
    private String ingredians;

    public Recipe(String name, String url, String id) {
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public Recipe(String name, String url, String id, String preperation, String ingredians) {
        this.name = name;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
