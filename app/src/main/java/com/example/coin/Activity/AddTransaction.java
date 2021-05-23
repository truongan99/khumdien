package com.example.coin.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.coin.R;

public class AddTransaction extends AppCompatActivity {
    private TextView tv_currency;
    private EditText edt_currency,edt_select_grd,edt_note,edt_date;
    private RadioGroup rdg_type;
    private RadioButton rd_thu,rd_chi;
    private ImageView img_grd;
    public static final int REQUEST_GROUP_DE=116;
    int avt_grd=-1;
    int id_grd=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        setActionBar(R.layout.add_tran_actionbar);
        setcontrol();
        setEvent();
    }

    private void setcontrol() {
        tv_currency = findViewById(R.id.tv_currency_add_tran);
        edt_currency = findViewById(R.id.edt_money_add_tran);
        rdg_type = findViewById(R.id.rdg_type_add_tran);
        rd_thu = findViewById(R.id.rd_thu_add_tran);
        rd_chi=findViewById(R.id.rd_chi_add_tran);
        rd_chi.setChecked(true);
        img_grd = findViewById(R.id.iv_img_grd);
        edt_select_grd = findViewById(R.id.edt_select_gr_add_tran);
        edt_note = findViewById(R.id.edt_note_add_tran);
        edt_date = findViewById(R.id.edt_day_add_tran);
    }
    private void setEvent(){
        edt_select_grd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdg_type.getCheckedRadioButtonId() == R.id.rd_chi_add_tran){
                    Intent intent=new Intent(getApplicationContext(), SelectGroupDetail.class);
                    intent.putExtra("TYPE_GRD","0");
                    startActivityForResult(intent, REQUEST_GROUP_DE);
                }
                if(rdg_type.getCheckedRadioButtonId() == R.id.rd_thu_add_tran){
                    Intent intent=new Intent(getApplicationContext(), SelectGroupDetail.class);
                    intent.putExtra("TYPE_GRD","1");
                    startActivityForResult(intent, REQUEST_GROUP_DE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GROUP_DE && data != null) {
            edt_select_grd.setText(data.getStringExtra("GRD_NAME"));
            id_grd = data.getIntExtra("GRD_ID",-1);
            avt_grd = data.getIntExtra("GRD_IMG",-1);
            img_grd.setImageResource(avt_grd);
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