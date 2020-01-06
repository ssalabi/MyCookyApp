package com.mycookyapp.login;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mycookyapp.R;
import com.mycookyapp.screens.MainActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.json.JSONException;
import org.json.JSONObject;

//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2);
        new NukeSSLCerts().nuke();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        //if user presses on login
        //calling the method login
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    userLogin();
                } catch (UnrecoverableKeyException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //if user presses on not registered
        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                finish();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

//    @org.jetbrains.annotations.NotNull
//    public  static String Getdata (String uri ){
//
//        BufferedReader reader = null;
//
//        try {
//
//            URL url = new URL(uri);
//            HttpURLConnection con = null;
//
//            URL testUrlHttps = new URL(uri);
//            if (testUrlHttps.getProtocol().toLowerCase().equals("https"))
//            {
//                trustAllHosts();
//                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
//                https.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//                con = https;
//            } else
//            {
//                con = (HttpURLConnection) url.openConnection();
//            }
//
//
//            con.setReadTimeout(15000);
//            con.setConnectTimeout(15000);
//            StringBuilder sb = new StringBuilder();
//            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//
//            return sb.toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return "";
//                }
//            }
//        }
//    }

    private void userLogin() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, CertificateException, IOException {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }


        {
            try {

                AsyncTask<Void,Void,Void> a = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try
                        {
                            URL url = new URL("https://cookyfunction.azurewebsites.net/api/HttpTrigger1");
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("POST");
                            con.setRequestProperty("User-Agent", "Mozilla/5.0"); ///// need that?

                            con.setDoOutput(true);


                            DataOutputStream out = new DataOutputStream(con.getOutputStream());
                            //String resultString = result.toString();
                            String resultString = "{ username: '" + username + "', password: '"+ password + "' }";
                            //out.writeBytes(resultString);
                            JSONObject obj1 = new JSONObject(resultString);
                            out.writeBytes(obj1.toString());

                            out.flush();
                            out.close();
                            int responseCode = con.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                                BufferedReader in = new BufferedReader(new InputStreamReader(
                                        con.getInputStream()));
                                String inputLine;
                                StringBuffer response = new StringBuffer();

                                while ((inputLine = in.readLine()) != null) {
                                    response.append(inputLine);
                                }
                                in.close();

                                // print result
                                System.out.println(response.toString());
                                try {
                                    //converting response to json object
                                    JSONObject obj = new JSONObject(response.toString());

                                    //creating a new user object
                                    User user = new User(
                                            obj.getInt("id"),
                                            obj.getString("username"),
                                            obj.getString("email"),
                                            obj.getString("gender")
                                    );

                                    //storing the user in shared preferences
                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                    //starting the profile activity
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        catch (Exception e)
                        {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                a.execute();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
}


