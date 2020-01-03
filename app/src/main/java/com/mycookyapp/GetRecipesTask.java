package com.mycookyapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetRecipesTask extends AsyncTask<Void, Void, Void> {

    private String message;

    @Override
    protected Void doInBackground(Void... voids) {

        //try {

        try {
            URL url = new URL("https://cookyfunction.azurewebsites.net/api/getNameRecipees?code=cZxgtFzWcj0JZ5ZkY4TCznXEqEnS7Q2x0OM2wfF9319s6Pdp3wGK2w==");
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }





            //urlConnection.setRequestMethod("GET"); //i added that
//            InputStream in = urlConnection.getInputStream();
//
//            InputStreamReader isw = new InputStreamReader(in);
//
//            int data = isw.read();
//
//            while (data != -1) {
//                char current = (char) data;
//                data = isw.read();
//                message = message + current;
//
//            }
//
//            urlConnection.disconnect();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }





        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


    }
}