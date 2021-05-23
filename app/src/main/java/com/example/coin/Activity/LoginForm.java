package com.example.coin.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.coin.R;

public class LoginForm extends AppCompatActivity {
    private Button btn_login,btn_signup;
    private static final int REQUEST_CODE_SIGN= 113;
    private static final int REQUEST_CODE_LOGIN= 114;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_login_form);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForm.this, Login.class);
                startActivityForResult(intent, REQUEST_CODE_LOGIN);

            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForm.this, Signup.class);
                startActivityForResult(intent, REQUEST_CODE_SIGN);
            }
        });
    }

    private void setControl() {
        btn_login = findViewById(R.id.login_f_btn);
        btn_signup = findViewById(R.id.signup_f_btn);
    }
}