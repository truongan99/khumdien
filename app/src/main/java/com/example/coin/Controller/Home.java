package com.example.coin.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coin.Database.AppDB;
import com.example.coin.R;

public class Home extends AppCompatActivity {
    Button btntest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btntest = findViewById(R.id.btntest);
        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginForm.editor.clear();
                LoginForm.editor.commit();
                Intent intent = new Intent(Home.this, LoginForm.class);
                startActivity(intent);
                finish();
            }
        });
    }
}