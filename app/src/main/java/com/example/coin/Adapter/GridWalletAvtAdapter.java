package com.example.coin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.coin.R;

import java.util.List;

public class GridWalletAvtAdapter extends BaseAdapter {
    private List<Integer> mavtList;
    private LayoutInflater mLayoutInflater;
    public GridWalletAvtAdapter(Context context, List<Integer> avtList) {
        mLayoutInflater = LayoutInflater.from(context);
        mavtList = avtList;
    }
    @Override
    public int getCount() {
        // quy định số lượng hiển thị
        return mavtList == null ? 0 : mavtList.size();
    }

    @Override
    public Object getItem(int position) {
        return mavtList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mavtList.indexOf(mavtList.get(position));
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewImageCustom(position,convertView, parent);
    }
    private View getViewImageCustom(int position, View convertView, ViewGroup parent) {
        ImageView avt;
        if (convertView == null) {
            convertView =
                    mLayoutInflater.inflate(R.layout.wallet_avt_item, parent, false);
            avt = (ImageView) convertView.findViewById(R.id.image_item);
            convertView.setTag(avt);

        } else {
            avt = (ImageView) convertView.getTag();
        }
        avt.setImageResource(mavtList.get(position));
        return convertView;
    }
}
