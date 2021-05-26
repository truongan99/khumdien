package com.example.coin.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.coin.Fragment.AddBudgetPlan;
import com.example.coin.R;

public class AddBudget extends AppCompatActivity {

    Integer message = -1;
    Integer ID;
    String money;
    String note;
    String dateStart;
    String dateEnd;
    Integer id_gr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);
        setActionBar(R.layout.add_budget_actionbar);
        getMessage();

        if (message != -1) {
            Fragment nextFrag = new AddBudgetPlan().dataTransfer(ID, money, "", note, dateStart, dateEnd, 4, id_gr, -1);
            replace(nextFrag);
        } else {
            Fragment nextFrag = new AddBudgetPlan();
            replace(nextFrag);
        }
    }

    private void getMessage() {
        Intent intent = getIntent();
        message = intent.getIntExtra("message", -1);
        ID = intent.getIntExtra("ID", -1);
        money = intent.getStringExtra("money");
        note = intent.getStringExtra("note");
        dateStart = intent.getStringExtra("dateStart");
        dateEnd = intent.getStringExtra("dateEnd");
        id_gr = intent.getIntExtra("id_gr", -1);
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.add_budget_frame, fragment);
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
}