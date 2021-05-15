package com.example.coin.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coin.Database.AppDB;
import com.example.coin.R;
import com.example.coin.Bean.User_Entity;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    EditText edtEmail,edtPassword,edtConfPassword;
    Button btnSignUp,btnLogin;
    TextView tvEmail,tvPassword,tvCfpassword;
    ColorStateList dfTextColer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtEmail.getText().toString().equals("")) {
                    tvEmail.setTextColor(Color.RED);
                }
                if(!edtEmail.getText().toString().equals("")) {
                    tvEmail.setTextColor(dfTextColer);
                }
                if(edtPassword.getText().toString().equals("")) {
                    tvPassword.setTextColor(Color.RED);
                }
                if(!edtPassword.getText().toString().equals("")) {
                    tvPassword.setTextColor(dfTextColer);
                }
                if(edtConfPassword.getText().toString().equals("")) {
                    tvCfpassword.setTextColor(Color.RED);
                }
                if(!edtConfPassword.getText().toString().equals("")) {
                    tvCfpassword.setTextColor(dfTextColer);
                }

                if(!edtEmail.getText().toString().equals("")&&!checkEMail(edtEmail.getText().toString())){
                    tvEmail.setText(R.string.email_err_fm);
                    tvEmail.setTextColor(Color.RED);
                }
                if(checkEMail(edtEmail.getText().toString())){
                    tvEmail.setText(R.string.email);
                    tvEmail.setTextColor(dfTextColer);
                }
                if(!edtPassword.getText().toString().equals("")&&!checkPassword(edtPassword.getText().toString())){
                    tvPassword.setText(R.string.password_err_fm);
                    tvPassword.setTextColor(Color.RED);
                }
                if(checkPassword(edtPassword.getText().toString())){
                    tvPassword.setText(R.string.password);
                    tvPassword.setTextColor(dfTextColer);
                }
                if(!edtPassword.getText().toString().equals(edtConfPassword.getText().toString())){
                    tvCfpassword.setText(R.string.password_cf_err);
                    tvCfpassword.setTextColor(Color.RED);
                }
                if(checkEMail(edtEmail.getText().toString())&&checkPassword(edtPassword.getText().toString())&&edtPassword.getText().toString().equals(edtConfPassword.getText().toString())) {
                    try {
                        AppDB userdb = new AppDB(Signup.this);
                        User_Entity user = new User_Entity(edtEmail.getText().toString(),edtPassword.getText().toString());
                        userdb.InsertUser(user);
                        Intent intent = new Intent(Signup.this, Login.class);
                        startActivity(intent);
                        finish();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Tài khoản đã tồn tại", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private void setControl() {
        edtEmail=findViewById(R.id.email_s_edt);
        edtPassword=findViewById(R.id.passwd_s_edt);
        edtConfPassword=findViewById(R.id.passwd_cf_edt);
        btnSignUp=findViewById(R.id.signup_s_btn);
        btnLogin=findViewById(R.id.login_s_btn);
        tvEmail=findViewById(R.id.email_s_view);
        tvPassword=findViewById(R.id.passwd_s_view);
        tvCfpassword=findViewById(R.id.passwd_cf_view);
        dfTextColer = tvPassword.getTextColors();
    }
    private static boolean checkEMail(String email){
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
       Pattern pattern = Pattern.compile(EMAIL_PATTERN);
       return pattern.matcher(email).matches();
    }
    private static boolean checkPassword(String password){
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";//a-zA-Z0-9 6-20 ki tu
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }
}