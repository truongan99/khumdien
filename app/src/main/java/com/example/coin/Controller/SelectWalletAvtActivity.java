package com.example.coin.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.coin.Controller.GridWalletAvtAdapter;
import com.example.coin.R;

import java.util.ArrayList;
import java.util.List;

public class SelectWalletAvtActivity extends AppCompatActivity {
    private GridView grv_avt;
    private List<Integer> WalletAvtList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_wallet_avt);
        initWalletAvt();
        setControl();
        setEvent();
    }

    private void setControl(){
        grv_avt= findViewById(R.id.grid_view_avt_wallet);
        GridWalletAvtAdapter adapter = new GridWalletAvtAdapter(this, WalletAvtList);
        grv_avt.setAdapter(adapter);
    }
    private void setEvent() {
        grv_avt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int avt = WalletAvtList.get(position);
                Intent intent=getIntent();
                intent.putExtra("avt_wallet", avt);
                setResult(1, intent);
                finish();
            }
        });
    }
    public void initWalletAvt(){
        WalletAvtList = new ArrayList<>();
        int i=1;
        //this.getResources().getResourceEntryName(R.drawable.england) lay ten anh
        //String mDrawableName = "myimg";
        //int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName()); set anh theo String
        while (i<23){
            String mDrawableName = "wallet_icon_"+i;
            int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
            WalletAvtList.add(resID);
            i++;
        }
    }
}