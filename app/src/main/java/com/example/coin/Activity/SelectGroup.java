package com.example.coin.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coin.Adapter.GroupAdapter;
import com.example.coin.Bean.Group_Entity;
import com.example.coin.Database.AppDB;
import com.example.coin.R;

import java.util.List;

public class SelectGroup extends AppCompatActivity {

    private List<Group_Entity> list;
    private ListView lv_gr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group);
        setActionBarString("Choose Group");
        initGroupList();
        setControl();
        setEvent();
    }

    private void setEvent() {
        lv_gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                int avt = list.get(position).getHinh();
                String name = list.get(position).getTen();
                int id_gr = list.get(position).getId();
                intent.putExtra("GR_IMG", avt);
                intent.putExtra("GR_NAME",name);
                intent.putExtra("GR_ID", id_gr);
                setResult(2, intent);
                finish();
            }
        });
    }

    private void setControl() {
        lv_gr = findViewById(R.id.lv_group_add);
        lv_gr.setAdapter(new GroupAdapter(SelectGroup.this,list));
    }

    private void initGroupList(){ Intent intent = getIntent();
       String type =  intent.getStringExtra("TYPE_GR");
       AppDB db = new AppDB(getApplicationContext());
       list = db.getAllGroup(type);
    }
    private void setActionBarString(String text){
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.choose_icon_actionbar);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFFFF"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setElevation(0);
        TextView tv = findViewById(R.id.tv_title);
        tv.setText(text);
    }
}