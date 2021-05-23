package com.example.coin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coin.Bean.Item_gr;
import com.example.coin.R;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private Activity activity;
    private List<Item_gr> listItem;

    public ItemAdapter(Activity activity, List<Item_gr> listItem) {
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
        if (listItem.get(position).isSection()) {
            // if section header
            convertView = inflater.inflate(R.layout.group_item, null);
            TextView tvSectionTitle = (TextView) convertView.findViewById(R.id.tv_group_name_item);
            ImageView ivSectionImg = convertView.findViewById(R.id.iv_avt_gr_item);
            tvSectionTitle.setText(( (SectionItem) listItem.get(position)).getName());
            ivSectionImg.setImageResource(( (SectionItem) listItem.get(position)).getHinh());
        }
        else
        {
            // if item
            convertView = inflater.inflate(R.layout.group_detail_item, parent, false);
            TextView tvSectionTitle = (TextView) convertView.findViewById(R.id.tv_groupde_name_item);
            ImageView ivSectionImg = convertView.findViewById(R.id.iv_avt_grde_item);
            tvSectionTitle.setText(( (EntityItem) listItem.get(position)).getName());
            ivSectionImg.setImageResource(( (EntityItem) listItem.get(position)).getHinh());
        }
        return convertView;
    }
}
