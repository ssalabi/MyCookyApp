package com.mycookyapp.web;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycookyapp.data.Recipe;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetRecipeDetailsTask extends AsyncTask<String, String, String> {

    private  RecipeDetailsListener listener;
    private String recipeID;
    private String message = "";

    GetRecipeDetailsTask(String recipeID, RecipeDetailsListener listener){
        this.recipeID = recipeID;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
//        https://cookyfunction.azurewebsites.net/api/getRecipeDetails?code=DilUEz7oGeateJQ4UaPGLa9a1wiyEMNC/omexUWzzjcaKAv3REc3wA==
//&id=2

        String urlStr = "https://cookyfunction.azurewebsites.net/api/getRecipeDetails?code=DilUEz7oGeateJQ4UaPGLa9a1wiyEMNC/omexUWzzjcaKAv3REc3wA==&id=" + recipeID;

        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                //System.out.print(current);
                message = message + current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }



        return message;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Type listType = new TypeToken<ArrayList<Recipe>>(){}.getType();
        List<Recipe> recipes = new Gson().fromJson(s, listType);
        listener.onReady(recipes.get(0));
    }

    public interface RecipeDetailsListener{
         void onReady (Recipe recipe);
    }
}
