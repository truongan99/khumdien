package com.example.coin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coin.Bean.Currency_Class;
import com.example.coin.R;

import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<Currency_Class> {

    public CurrencyAdapter(@NonNull Context context, List<Currency_Class> currencyList) {
        super(context,0, currencyList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initDropdownView(position, convertView, parent);
    }

    private View initDropdownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_curency_item, parent, false
            );
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.img_currency_avt);
        TextView textViewName = convertView.findViewById(R.id.tv_currency_name);
        TextView textViewSymbol = convertView.findViewById(R.id.tv_currency_symbol);
        Currency_Class currentItem = getItem(position);
        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.getImg());
            textViewName.setText(currentItem.getName());
            textViewSymbol.setText(currentItem.getSymbol());
        }
        return convertView;
    }
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_item_selected, parent, false
            );
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.img_flag_sec);
        TextView textViewName = convertView.findViewById(R.id.tv_currency_name_sec);
        Currency_Class currentItem = getItem(position);
        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.getImg());
            textViewName.setText(currentItem.getName());
        }
        return convertView;
    }
}
