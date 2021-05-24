package com.example.coin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.coin.Database.AppDB;
import com.example.coin.R;
import com.example.coin.Bean.Account_Entity;

public class Login extends AppCompatActivity {

    Button login_btn;
    EditText username_edt,password_edt;
    TextView username_view,password_view,title_view;
    ColorStateList dfcoler;
    public static Account_Entity acc_login = new Account_Entity();
    public static String user_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        setControl();
        setEvent();
    }

    private void setEvent() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username_edt.getText().toString().equals("")) username_view.setTextColor(Color.RED);
                if(password_edt.getText().toString().equals("")) password_view.setTextColor(Color.RED);
                if(!username_edt.getText().toString().equals("")&&!password_edt.getText().toString().equals("")){
                    try {
                        String username = username_edt.getText().toString();
                        String password = password_edt.getText().toString();
                        AppDB userdb = new AppDB(Login.this);
                        acc_login = userdb.select_User(username,password);

                        if(acc_login==null) {
                            title_view.setText("username or password is incorrect");
                            title_view.setTextSize(20);
                        }
                        else if (acc_login.getTenvi()==null){
                            user_email = acc_login.getEmail();
                            Intent intent = new Intent(Login.this, First_add_wallet.class);
                            startActivity(intent);
                            setResult(Activity.RESULT_OK,intent);
                            intent.putExtra("USER_LOGIN",acc_login.getEmail());
                            finish();
                        }
                        else if(acc_login.getTenvi()!=null&&acc_login.getDon_vi_tien()!=null&&acc_login.getHinhanh_vi()!=null) {
                            user_email = acc_login.getEmail();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void setControl() {
        login_btn = findViewById(R.id.login_l_btn);
        username_edt = findViewById(R.id.email_l_edt);
        password_edt = findViewById(R.id.passwd_l_edt);
        username_view = findViewById(R.id.email_l_view);
        password_view = findViewById(R.id.passwd_l_view);
        title_view = findViewById(R.id.welcome_l_view);
        dfcoler = username_view.getTextColors();
    }
}