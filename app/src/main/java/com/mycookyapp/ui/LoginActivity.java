package com.mycookyapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mycookyapp.R;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    private View login;
    private ImageView testImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });

         testImage = findViewById(R.id.test_image);
        View btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load("https://cdn.pixabay.com/photo/2017/09/01/00/16/png-2702697_960_720.png")
                        .into(testImage);


            }
        });
    }
}
