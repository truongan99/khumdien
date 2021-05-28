package com.example.coin.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coin.Bean.Group_Detail_Entity;
import com.example.coin.Database.AppDB;
import com.example.coin.R;

import java.util.List;

public class AddGroup extends AppCompatActivity {
    private ImageView iv_avt_gr;
    private RadioGroup rdg_type;
    private RadioButton rd_thu;
    private RadioButton rd_chi;
    private EditText select_gr,name_gr;
    private Button btn_add;
    public static final int REQUEST_AVT=110;
    public static final int REQUEST_GROUP=111;
    int avt_gr=-1;
    int id_gr=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        setActionBarString("ADD GROUP");
        setcontrol();
        setEvent();
    }

    private void setcontrol() {
        iv_avt_gr = findViewById(R.id.iv_avt_group);
        rdg_type = findViewById(R.id.rdg_type);
        select_gr = findViewById(R.id.edt_select_gr_add_tran);
        name_gr = findViewById(R.id.edt_name_group);
        rd_thu = findViewById(R.id.rd_thu);
        rd_chi = findViewById(R.id.rd_chi);
        rd_chi.setChecked(true);
        btn_add = findViewById(R.id.btn_add_group);
    }
    private void setEvent(){
        iv_avt_gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddGroup.this, SelectWalletAvtActivity.class);
                //gọi startActivityForResult
                startActivityForResult(intent, REQUEST_AVT);
            }
        });
        select_gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdg_type.getCheckedRadioButtonId() == R.id.rd_chi){
                    Intent intent=new Intent(getApplicationContext(), SelectGroup.class);
                    intent.putExtra("TYPE_GR","0");
                    startActivityForResult(intent, REQUEST_GROUP);
                }
                if(rdg_type.getCheckedRadioButtonId() == R.id.rd_thu){
                    Intent intent=new Intent(getApplicationContext(), SelectGroup.class);
                    intent.putExtra("TYPE_GR","1");
                    startActivityForResult(intent, REQUEST_GROUP);
                }
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_gr.getText()!=null&&avt_gr!=-1&&id_gr!=-1){
                    Group_Detail_Entity detail = new Group_Detail_Entity();
                    detail.setTen(name_gr.getText().toString());
                    detail.setHinhanh(avt_gr);
                    detail.setId_loai(id_gr);
                    AppDB db = new AppDB(getApplicationContext());
                    db.InsertGroup_Detail(detail);
                    MainActivity.allGroupDe.add(detail);
                    db.close();
                    Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddGroup.this,AddTransaction.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_AVT && data != null) {
            avt_gr = data.getIntExtra("avt_wallet", 0);
            iv_avt_gr.setImageResource(avt_gr);
        }
        if (requestCode == REQUEST_GROUP && data != null) {
            select_gr.setText(data.getStringExtra("GR_NAME"));
            id_gr = data.getIntExtra("GR_ID",-1);
        }
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