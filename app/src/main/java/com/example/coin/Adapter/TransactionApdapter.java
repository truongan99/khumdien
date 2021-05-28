package com.example.coin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coin.Activity.MainActivity;
import com.example.coin.Bean.Group_Detail_Entity;
import com.example.coin.Bean.Group_Entity;
import com.example.coin.Bean.Item_gr;
import com.example.coin.Bean.Transaction_Entity;
import com.example.coin.Fragment.Transaction;
import com.example.coin.R;

import java.text.DecimalFormat;
import java.util.List;

public class TransactionApdapter extends BaseAdapter {
    private Activity activity;
    private List<Transaction_Entity> listItem;

    public TransactionApdapter(Activity activity, List<Transaction_Entity> listItem) {
        this.activity = activity;
        this.listItem = listItem;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =  activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.transaction_item, null);
        ImageView img_gr = convertView.findViewById(R.id.iv_img_tran_show);
        TextView sotien_tv = convertView.findViewById(R.id.tv_money_tran_show);
        TextView note_tv = convertView.findViewById(R.id.tv_note_tran_show);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        int hinh = 0;
        String ten = null;
        for(Group_Detail_Entity item : MainActivity.allGroupDe){
            if(listItem.get(position).getId_cr_loai()==item.getId())
            {
                hinh = item.getHinhanh();
                ten = item.getTen();
                for(Group_Entity gr : MainActivity.allGroup){
                    if(item.getId_loai()==gr.getId()&&gr.getLoai().equalsIgnoreCase("1")){
                        sotien_tv.setTextColor(Color.parseColor("#2cb74b"));
                    }
                }
            }

        }
        img_gr.setImageResource(hinh);
        TextView name_gr = convertView.findViewById(R.id.tv_name_tran_show);
        name_gr.setText(ten);
        sotien_tv.setText(formatter.format(listItem.get(position).getSotien()));
        TextView ngay_tv = convertView.findViewById(R.id.tv_date_tran_show);
        ngay_tv.setText(listItem.get(position).getNgaygiaodich());
        note_tv.setText(listItem.get(position).getMota());
        return convertView;
    }
}
