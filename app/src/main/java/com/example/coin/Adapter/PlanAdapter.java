package com.example.coin.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coin.Bean.Plan_Entity;
import com.example.coin.Database.AppDB;
import com.example.coin.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PlanAdapter extends ArrayAdapter<Plan_Entity> {

    Context context;
    int resource;
    ArrayList<Plan_Entity> data = null;
    Integer type; // 0: expense | 1:revenue
    Integer id_account;
    Calendar calendar = Calendar.getInstance();
    Date today = calendar.getTime();
    SimpleDateFormat checkformat = new SimpleDateFormat("dd/MM/yyyy");

    public PlanAdapter(Context context, int resource, ArrayList data, Integer type, Integer id_account) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.type = type;
        this.id_account = id_account;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(resource, null);

            holder.group_item_list = (TextView) convertView.findViewById(R.id.group_item_list);
            holder.money_plan_item_list = (TextView) convertView.findViewById(R.id.money_plan_item_list);
            holder.money_left_item_list = (TextView) convertView.findViewById(R.id.money_left_item_list);
            holder.status_item_list = (TextView) convertView.findViewById(R.id.status_item_list);
            holder.img_item_list = (ImageView) convertView.findViewById(R.id.img_item_list);
            holder.progress_bar_item_list = (ProgressBar) convertView.findViewById(R.id.progress_bar_item_list);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.group_item_list.setText(new AppDB(getContext()).selectName(data.get(position).getId_gr()));

        holder.money_plan_item_list.setText(data.get(position).getMoney());

        if (type == 1) {
            holder.money_left_item_list.setText("Get " + new AppDB(getContext()).sumMoney(data.get(position).getId_gr(), id_account, data.get(position).getDateStart(), data.get(position).getDateEnd()));

        } else {
            holder.money_left_item_list.setText("Used " + new AppDB(getContext()).sumMoney(data.get(position).getId_gr(), id_account, data.get(position).getDateStart(), data.get(position).getDateEnd()));
        }

        holder.img_item_list.setImageResource(new AppDB(getContext()).selectImg(data.get(position).getId_gr()));
        if (new AppDB(getContext()).sumMoney(data.get(position).getId_gr(), id_account, data.get(position).getDateStart(), data.get(position).getDateEnd()) != 0) {
            float money = Integer.parseInt(data.get(position).getMoney());
            float left = new AppDB(getContext()).sumMoney(data.get(position).getId_gr(), id_account, data.get(position).getDateStart(), data.get(position).getDateEnd());
            float percent = (left / money) * 100;
            if ((int) percent <= 100) {
                holder.progress_bar_item_list.setProgress((int) percent);
            } else {
                holder.progress_bar_item_list.setProgress(100);
            }

        } else {
            holder.progress_bar_item_list.setProgress(0);
        }

        try {
            Date afDate = checkformat.parse(data.get(position).getDateEnd());


            if (today.compareTo(afDate) < 0) {
                holder.status_item_list.setText("Ongoing");
                holder.status_item_list.setTextColor(Color.GREEN);
                holder.progress_bar_item_list.getProgressDrawable().setColorFilter(
                        Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            }
            if (holder.progress_bar_item_list.getProgress() == 100) {
                if (type == 1) {
                    holder.status_item_list.setText("Overtarget");
                    holder.progress_bar_item_list.getProgressDrawable().setColorFilter(
                            Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                    holder.status_item_list.setTextColor(Color.YELLOW);
                } else {
                    holder.status_item_list.setText("Overspending");
                    holder.progress_bar_item_list.getProgressDrawable().setColorFilter(
                            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                    holder.status_item_list.setTextColor(Color.RED);
                }
            }
            if (today.compareTo(afDate) > 0) {
                holder.status_item_list.setText("Expired");
                holder.status_item_list.setTextColor(Color.BLACK);
                holder.progress_bar_item_list.getProgressDrawable().setColorFilter(
                        Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    public class ViewHolder {
        ImageView img_item_list;
        TextView group_item_list, money_plan_item_list, money_left_item_list, status_item_list;
        ProgressBar progress_bar_item_list;
    }

}
