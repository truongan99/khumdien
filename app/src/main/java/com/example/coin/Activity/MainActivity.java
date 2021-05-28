package com.example.coin.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.coin.Bean.Group_Detail_Entity;
import com.example.coin.Bean.Group_Entity;
import com.example.coin.Bean.Transaction_Entity;
import com.example.coin.Bean.WalletDetail_Entity;
import com.example.coin.Database.AppDB;
import com.example.coin.Fragment.Account;
import com.example.coin.Fragment.Home;
import com.example.coin.Fragment.Plan;
import com.example.coin.R;
import com.example.coin.Fragment.Report;
import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private final int ID_HOME = 1;
    private final int ID_REPORT = 2;
    private final int ID_PLAN = 3;
    private final int ID_ACCOUNT = 4;
    private final int ID_INSERT_PLAN = 5;
    MeowBottomNavigation bottomNavigation;
    Integer message = -1;
    public static WalletDetail_Entity wallet;
    private ImageView img_wallet;
    private TextView name_wallet;
    private TabLayout tabLayout;
    private TextView tv_money;
    public static List<WalletDetail_Entity> allWalletDe = new ArrayList<>();
    public static List<Group_Entity> allGroup = new ArrayList<>();
    public static List<Group_Detail_Entity> allGroupDe = new ArrayList<>();
    public static List<Transaction_Entity> allTran = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMessage();
        initWalletDetail(Login.acc_login.getID());
        initBottomNavigation();
        changeStatusBarColor();
    }

    private void getMessage(){
        Intent intent = getIntent();
        message = intent.getIntExtra("message", -1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_fragment, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_nav_book_transaction));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_REPORT, R.drawable.ic_nav_chart));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_PLAN, R.drawable.ic_nav_plan));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_nav_user));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = new Home();
                replace(fragment);
                setActionBar(R.layout.home_action_bar);
                switch (item.getId()) {
                    case ID_HOME:
                        Log.d("interrrrr", "susss");
                        fragment = new Home();
                        setActionBar(R.layout.home_action_bar);
                        setItemHomeActionbar();
                        break;
                    case ID_REPORT:
                        fragment = new Report();
                        setActionBar(R.layout.home_action_bar);
                        setItemHomeActionbar();
                        break;
                    case ID_PLAN:
                        if (message != -1) {
                            fragment = new Plan().newInstance("go", "budget");

                        } else {
                            fragment = new Plan();

                        }
                        break;
                    case ID_ACCOUNT:
                        fragment = new Account();
                        setActionBar(R.layout.home_action_bar);
                        setItemHomeActionbar();
                        break;
                    default:
                        fragment = new Home();
                }
                replace(fragment);
            }
        });
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case 1:
                        replace(new Home());
                        break;
                    case 2:
                        replace(new Report());
                        break;
                    case 3:
                        replace(new Plan());
                        break;
                    case 4:
                        replace(new Account());
                        break;
                }
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {
                    case ID_HOME:
                        fragment = new Home();
                        break;
                    case ID_REPORT:
                        fragment = new Report();
                        break;
                    case ID_PLAN:
                        fragment = new Plan();
                        break;
                    case ID_ACCOUNT:
                        fragment = new Account();
                        break;
                    default:
                        fragment = new Home();
                }
            }
        });
        if (message != -1) {
            bottomNavigation.show(ID_PLAN, true);
        } else {
            bottomNavigation.show(ID_HOME, true);
        }
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }

    public void setActionBar(int v) {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(v);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFFFF"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setElevation(0);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
    }
    private void initWalletDetail(int id_user){
        AppDB db = new AppDB(getApplicationContext());
        wallet = db.Select_WalletDetailLast(Login.acc_login.getID());
        allWalletDe = db.SelecAllWalletDetail(wallet.getId_vi());
        allGroup = db.getAllGroupNoType();
        allGroupDe = db.getAllGroupDe();
        allTran = db.selectAllTran();
    }
    private void setItemHomeActionbar() {
        img_wallet = findViewById(R.id.iv_wallet_img_home_fragment);
        img_wallet.setImageResource(Login.acc_login.getHinhanh_vi());
        name_wallet = findViewById(R.id.tv_namewallet_home_fragment);
        name_wallet.setText(Login.acc_login.getTenvi());
        tv_money = findViewById(R.id.tv_currency_home_fragment);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        tv_money.setText(formatter.format(wallet.getSotien())+" "+Login.acc_login.getDon_vi_tien());
    }
}