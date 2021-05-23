package com.example.coin.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coin.Adapter.CurrencyAdapter;
import com.example.coin.Bean.Account_Entity;
import com.example.coin.Bean.Currency_Class;
import com.example.coin.Database.AppDB;
import com.example.coin.R;

import java.util.ArrayList;
import java.util.List;

public class First_add_wallet extends AppCompatActivity {
    private Spinner sp_currency;
    private EditText edt_wallet_name;
    private Button btn_change_avt,btn_continue;
    private List<Currency_Class> currencyList;
    private CurrencyAdapter mAdapter;
    private ImageView img_wallet;
    public static final int REQUEST_AVT=112;
    private String wallet_currency;
    private static int avt_wallet = R.drawable.icon_109;
    private String EMAIL_USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_add_wallet);
        this.getSupportActionBar().hide();
        Intent intent = getIntent();
        EMAIL_USER = intent.getStringExtra("USER_LOGIN");
        initCurrencyData();
        setControl();
        setEvent();

    }

    private void setEvent() {
        sp_currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wallet_currency = currencyList.get(position).getSymbol();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                wallet_currency = currencyList.get(0).getSymbol();
            }
        });
        btn_change_avt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(First_add_wallet.this, SelectWalletAvtActivity.class);
                //gọi startActivityForResult
                startActivityForResult(intent, REQUEST_AVT);
            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wallet_name = edt_wallet_name.getText().toString();
                String email_user = Login.user_email;
                Account_Entity wallet = new Account_Entity(email_user,wallet_name,wallet_currency,avt_wallet);
                Login.acc_login.setTenvi(wallet_name);
                Login.acc_login.setDon_vi_tien(wallet_currency);
                Login.acc_login.setHinhanh_vi(avt_wallet);
                try {
                    AppDB db = new AppDB(getApplicationContext());
                    db.UpdateAccount(wallet);
                    Toast.makeText(getApplicationContext(),"Create Wallet Done !",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(First_add_wallet.this, MainActivity.class);
                    //gọi startActivityForResult
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Wallet Name Already !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_AVT && data != null) {
            avt_wallet = data.getIntExtra("avt_wallet", 0);
            img_wallet.setImageResource(avt_wallet);
        }
    }

    private void setControl() {
        sp_currency = findViewById(R.id.sp_currency);
        edt_wallet_name = findViewById(R.id.edt_wallet_name_first);
        btn_change_avt = findViewById(R.id.btn_change_avatar_wallet);
        btn_continue = findViewById(R.id.btn_continue_first);
        img_wallet = findViewById(R.id.img_wallet_first_add);
        mAdapter = new CurrencyAdapter(this, currencyList);
        sp_currency.setAdapter(mAdapter);

    }

    public void initCurrencyData(){
        currencyList = new ArrayList<>();
        Currency_Class vietnam = new Currency_Class(getResources().getString(R.string.vietnam_dong),R.drawable.vietnam,getResources().getString(R.string.vnd));
        Currency_Class china = new Currency_Class(getResources().getString(R.string.china),R.drawable.china,getResources().getString(R.string.china_cur));
        Currency_Class japan = new Currency_Class(getResources().getString(R.string.japan),R.drawable.japan,getResources().getString(R.string.japan_cur));
        Currency_Class euro = new Currency_Class(getResources().getString(R.string.eu),R.drawable.eu,getResources().getString(R.string.euro));
        Currency_Class usa = new Currency_Class(getResources().getString(R.string.united_states_dollar),R.drawable.usa,getResources().getString(R.string.us_doller));
        Currency_Class england = new Currency_Class(getResources().getString(R.string.England_pound),R.drawable.england,getResources().getString(R.string.pound));
        Currency_Class korea = new Currency_Class(getResources().getString(R.string.korea),R.drawable.korea,getResources().getString(R.string.korea_cur));
        currencyList.add(vietnam);
        currencyList.add(china);
        currencyList.add(japan);
        currencyList.add(euro);
        currencyList.add(usa);
        currencyList.add(england);
        currencyList.add(korea);
    }

}