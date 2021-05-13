package com.example.coin.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.coin.Account;
import com.example.coin.Home;
import com.example.coin.Plan;
import com.example.coin.R;
import com.example.coin.Report;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class MainActivity extends AppCompatActivity {
    private final  int ID_HOME = 1;
    private final  int ID_REPORT = 2;
    private final  int ID_PLAN = 3;
    private final  int ID_ACCOUNT = 4;
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME,R.drawable.ic_nav_book_transaction));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_REPORT,R.drawable.ic_nav_chart));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_PLAN,R.drawable.ic_nav_plan));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT,R.drawable.ic_nav_user));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = new Home();
                replace(fragment);
                switch (item.getId()){
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
                replace(fragment);
            }

        });
        bottomNavigation.show(ID_HOME,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
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
                switch (item.getId()){
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
    }
    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main,fragment);
        transaction.commit();
    }
}