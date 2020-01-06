package com.mycookyapp.web;

import android.os.AsyncTask;

import com.mycookyapp.data.Recipe;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StartTask extends AsyncTask<String, String, String> {
    private String message;
    private String recipeID;
    private StartListener listener;

    public StartTask(String recipeID, StartListener listener) {
        this.recipeID = recipeID;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("https://cookyfunction.azurewebsites.net/api/StartRecipe?code=Kbs1pcy5vff7cWX80L8XGvRcligOdRd7RFpBibOOSiQeu8KjGgYCJg==&id=" + recipeID);

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

        listener.onReady();
    }

    public interface StartListener{
        void onReady ();
    }
}
