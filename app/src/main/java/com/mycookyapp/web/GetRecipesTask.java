package com.mycookyapp.web;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import com.mycookyapp.data.Recipe;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetRecipesTask extends AsyncTask<String, String, String> {

    private String message = "";
    private RecipesListener listener;

    public GetRecipesTask(RecipesListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("https://cookyfunction.azurewebsites.net/api/getNameRecipees?code=cZxgtFzWcj0JZ5ZkY4TCznXEqEnS7Q2x0OM2wfF9319s6Pdp3wGK2w==");

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

       /* Gson gson = new GsonBuilder().create();
        RecipeJsonCollection collection = gson.fromJson(s, RecipeJsonCollection.class);*/

      /*  Type listType = new TypeToken<ArrayList<RecipeJson>>(){}.getType();
        List<RecipeJson> recipes = new Gson().fromJson(s, listType);
        listener.onReady(recipes);*/

        Type listType = new TypeToken<ArrayList<Recipe>>(){}.getType();
        List<Recipe> recipes = new Gson().fromJson(s, listType);
        listener.onReady(recipes);

    }


    public interface RecipesListener{
        public void onReady(List<Recipe> recipes);
    }

}
