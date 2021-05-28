package com.example.coin.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coin.Adapter.EntityItem;
import com.example.coin.Adapter.GroupAdapter;
import com.example.coin.Adapter.ItemAdapter;
import com.example.coin.Adapter.SectionItem;
import com.example.coin.Bean.Group_Detail_Entity;
import com.example.coin.Bean.Group_Entity;
import com.example.coin.Bean.Item_gr;
import com.example.coin.Database.AppDB;
import com.example.coin.R;

import java.util.ArrayList;
import java.util.List;

public class SelectGroupDetail extends AppCompatActivity {
    private ListView lv_grd;
    private ImageView iv_add;
    private List<Item_gr> list =  new ArrayList<Item_gr>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group_detail);
        setActionBar(R.layout.select_group_detail_actionbar);
        setControl();
        setEvent();
    }
    private void setEvent() {
        lv_grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                int avt = list.get(position).getHinh();
                String name = list.get(position).getName();
                int id_gr = list.get(position).getID();
                Toast.makeText(getApplicationContext(), "Ma so : "+list.get(position).getID(), Toast.LENGTH_SHORT).show();
                intent.putExtra("GRD_IMG", avt);
                intent.putExtra("GRD_NAME",name);
                intent.putExtra("GRD_ID", id_gr);
                setResult(2, intent);
                finish();
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),AddGroup.class);
                startActivity(intent);
            }
        });
    }
    private void setControl() {
        initGroupList();
        lv_grd =(ListView) findViewById(R.id.lv_group_de);
        ItemAdapter adapter = new ItemAdapter(SelectGroupDetail.this, list);
        lv_grd.setAdapter(adapter);
        iv_add = (ImageView) findViewById(R.id.iv_add_grd);

    }
    public void initGroupList(){
        Intent intent = getIntent();
        String type =  intent.getStringExtra("TYPE_GRD");
        List<Group_Entity> listGr = new ArrayList<>();
        for(Group_Entity item : MainActivity.allGroup){
            if(item.getLoai().equalsIgnoreCase(type)){
                listGr.add(item);
            }
        }
        for (int i=0;i<listGr.size();i++){
            list.add(new SectionItem(listGr.get(i).getTen(),listGr.get(i).getId(),listGr.get(i).getHinh()));
            for(int j = 0;j<MainActivity.allGroupDe.size();j++){
                if(listGr.get(i).getId() == MainActivity.allGroupDe.get(j).getId_loai()){
                    list.add(new EntityItem(MainActivity.allGroupDe.get(j).getTen(),MainActivity.allGroupDe.get(j).getId(),MainActivity.allGroupDe.get(j).getHinhanh()));
                }
            }
        }
    }
    private void setActionBar(int v){
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(v);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFFFFFFF"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setElevation(0);
    }
}