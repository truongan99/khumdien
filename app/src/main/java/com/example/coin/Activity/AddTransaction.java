package com.example.coin.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coin.Bean.Transaction_Entity;
import com.example.coin.Bean.WalletDetail_Entity;
import com.example.coin.Database.AppDB;
import com.example.coin.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTransaction extends AppCompatActivity {
    private TextView tv_currency;
    private EditText edt_currency,edt_select_grd,edt_note,edt_date;
    private RadioGroup rdg_type;
    private RadioButton rd_thu,rd_chi;
    private ImageView img_grd;
    private Button btn_add;
    private int ID_CT_VI ;
    final Calendar myCalendar = Calendar.getInstance();
    public static final int REQUEST_GROUP_DE=116;
    int avt_grd=-1;
    int id_grd=-1;
    String myFormat = "dd/MM/yyyy";
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
        btn_add = findViewById(R.id.btn_add_tran);
        ID_CT_VI = MainActivity.wallet.getId();
        edt_currency = findViewById(R.id.edt_money_add_tran);
    }
    private void setEvent(){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTransaction.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
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
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_currency.getText()!=null&&edt_date.getText()!=null&&edt_select_grd.getText()!=null&&edt_note.getText()!=null){

                    int sotien = Integer.parseInt(edt_currency.getText().toString());
                    String ngaygiaodich = edt_date.getText().toString();
                    String note = edt_note.getText().toString();
                    int id_ct_vi = ID_CT_VI;
                    Log.d("data",String.valueOf(ID_CT_VI));
                    AppDB db = new AppDB(getApplicationContext());
                    if(rdg_type.getCheckedRadioButtonId() == R.id.rd_chi_add_tran && sotien > MainActivity.wallet.getSotien())
                        Toast.makeText(getApplicationContext(),"Not enough money",Toast.LENGTH_LONG).show();
                    if(rdg_type.getCheckedRadioButtonId() == R.id.rd_chi_add_tran && sotien<= MainActivity.wallet.getSotien()){
                        Transaction_Entity tran = new Transaction_Entity(sotien,ngaygiaodich,note,id_grd,id_ct_vi);
                        db.InsertChiTieu(tran);
                        MainActivity.allTran.add(tran);
                        WalletDetail_Entity well = new WalletDetail_Entity(MainActivity.wallet.getSotien() - sotien,ngaygiaodich,MainActivity.wallet.getId_vi());
                        db.InsertCT_VI(well);
                        MainActivity.allWalletDe.add(well);
                        Toast.makeText(getApplicationContext(),"Thanh cong ",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddTransaction.this,MainActivity.class);
                        startActivity(intent);
                    }
                    if(rdg_type.getCheckedRadioButtonId() == R.id.rd_thu_add_tran){
                        Transaction_Entity tran = new Transaction_Entity(sotien,ngaygiaodich,note,id_grd,id_ct_vi);
                        db.InsertChiTieu(tran);
                        WalletDetail_Entity well = new WalletDetail_Entity(MainActivity.wallet.getSotien() + sotien,ngaygiaodich,MainActivity.wallet.getId_vi());
                        db.InsertCT_VI(well);
                        Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddTransaction.this,MainActivity.class);
                        startActivity(intent);
                    }

                    finish();
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
    private void updateLabel() {
         //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edt_date.setText(sdf.format(myCalendar.getTime()));
    }
}