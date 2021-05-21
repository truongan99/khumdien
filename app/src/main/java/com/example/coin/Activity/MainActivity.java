package com.example.coin.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.coin.Fragment.Account;
import com.example.coin.Fragment.Home;
import com.example.coin.Fragment.Plan;
import com.example.coin.R;
import com.example.coin.Fragment.Report;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private final  int ID_HOME = 1;
    private final  int ID_REPORT = 2;
    private final  int ID_PLAN = 3;
    private final  int ID_ACCOUNT = 4;
    private TabLayout tabLayout;
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomNavigation();
        changeStatusBarColor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_fragment, menu);
        return super.onCreateOptionsMenu(menu);
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
                setActionBar(R.layout.home_action_bar);
                switch (item.getId()){
                    case ID_HOME:
                        fragment = new Home();
                        setActionBar(R.layout.home_action_bar);
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
        bottomNavigation.show(ID_HOME,false);
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
    private void setActionBar(int v){
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
}