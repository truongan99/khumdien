package com.example.coin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.coin.Bean.Group_Entity;
import com.example.coin.R;

import java.util.List;

public class GroupAdapter extends BaseAdapter {

    private Activity activity;
    private List<Group_Entity> list ;

    public GroupAdapter(Activity activity, List<Group_Entity> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =  activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.group_item,null);
        ImageView img_gr = convertView.findViewById(R.id.iv_avt_gr_item);
        img_gr.setImageResource(list.get(position).getHinh());
        TextView name_gr = convertView.findViewById(R.id.tv_group_name_item);
        name_gr.setText(list.get(position).getTen());
        return convertView;
    }
}
